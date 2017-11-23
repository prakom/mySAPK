package ltmi.ac.id.pelatihanapp;

import android.net.Uri;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by root on 22/11/17.
 */

public class NetworkHelper {
    private static final String URLHIT =  "http://192.168.43.172:8080/awws?action=check_event";
    private static final String LOG_TAG = NetworkHelper.class.getSimpleName();
    static String getService(String action){
        String stringJSON = "";
        HttpURLConnection urlConn = null;
        BufferedReader reader = null;
        try{
            Uri buildURI = Uri.parse(URLHIT).buildUpon()
                    .appendQueryParameter("action", action)
                    .build();
            URL requestUrl = new URL(buildURI.toString());
            urlConn = (HttpURLConnection) requestUrl.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.connect();
            InputStream is = urlConn.getInputStream();
            StringBuffer sb = new StringBuffer();
             reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                sb.append(line+"\n");
            }
            if(sb.length()==0){
                return null;
            }
            stringJSON = sb.toString();
            Log.d(LOG_TAG,stringJSON);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        finally {
            if(urlConn!=null){
                urlConn.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        return  stringJSON;
    }
    String getServiceSslList(){
        String urls = "https://wstraining.bkn.go.id/bkn-resources-server/api/training/pns/list";
        String ret="";
        HttpClient httpClient = getNewHttpClient();
        final String url = urls;
        String stringJSON;
        BufferedReader reader = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("origin","http://localhost:20000");

        try {

            HttpResponse response = httpClient.execute(httpGet);
            InputStream is = response.getEntity().getContent();
            StringBuffer sb = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                sb.append(line+"\n");
            }
            if(sb.length()==0){
                return null;
            }
            stringJSON = sb.toString();
            ret = stringJSON;
            Log.d("retJSON",stringJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    String getServiceSslByNip(String nip){
        String urls = "https://wstraining.bkn.go.id/bkn-resources-server/api/training/pns/nip/"+nip;
        Log.d("urlbynip",urls);
        String ret="";
        HttpClient httpClient = getNewHttpClient();
        final String url = urls;
        String stringJSON;
        BufferedReader reader = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("origin","http://localhost:20000");

        try {

            HttpResponse response = httpClient.execute(httpGet);
            InputStream is = response.getEntity().getContent();
            StringBuffer sb = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine())!=null){
                sb.append(line+"\n");
            }
            if(sb.length()==0){
                return null;
            }
            stringJSON = sb.toString();
            ret = stringJSON;
            Log.d("retJSON",stringJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
