package ltmi.ac.id.pelatihanapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by root on 22/11/17.
 */

public class ServicesByNip extends AsyncTask<Object, Integer, String> {
    String editNip;
    Context context;
    public ServicesByNip(String editNip, Context context) {
        this.editNip = editNip;
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        NetworkHelper nh = new NetworkHelper();
        return nh.getServiceSslByNip(editNip);
        //return nh.getServiceSsl("https://wstraining.bkn.go.id/bkn-resources-server/api/training/pns/list");
    }
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        try {
            Log.d("reult",result);
            JSONTokener tokener = new JSONTokener(result);
            JSONObject jobj= (JSONObject)tokener.nextValue();
            JSONObject data=jobj.getJSONObject("data");
            JSONObject instansi=data.getJSONObject("instansi");
            String nama = instansi.getString("nama");
            Toast.makeText(this.context, nama, Toast.LENGTH_LONG).show();
            Log.d("jsom_key",jobj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
