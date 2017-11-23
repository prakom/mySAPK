package ltmi.ac.id.pelatihanapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by root on 22/11/17.
 */

public class Services extends AsyncTask<Object, Integer, String> {
    Context context;
    public Services (Context context) {
        this.context = context;
    }
    @Override
    protected String doInBackground(Object... params) {
        NetworkHelper nh = new NetworkHelper();
        return nh.getServiceSslList();
        //return nh.getServiceSsl("https://wstraining.bkn.go.id/bkn-resources-server/api/training/pns/list");
    }
    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        try {
            Log.d("reult",result);
            JSONTokener tokener = new JSONTokener(result);
            JSONObject jobj= (JSONObject)tokener.nextValue();
            JSONArray data=jobj.getJSONArray("data");
            JSONObject list1 = data.getJSONObject(0);
            String id = list1.getString("id");
            Toast.makeText(this.context, id, Toast.LENGTH_LONG).show();
            Log.d("jsom_key",jobj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
