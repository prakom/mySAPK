package ltmi.ac.id.pelatihanapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    MainActivity ini=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ini=this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button button = (Button)findViewById(R.id.tombol);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ini, SecondActivity.class);
                intent.putExtra("label","HELLO");*/
                /*Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://uai.ac.id"));
                ini.startActivity(intent);*/
                //AsyncTask service = new Services(getApplicationContext());

                //AsyncTask service = new ServicesByNip(editText.getText().toString());
                EditText txtUsername=(EditText) findViewById(R.id.inputNip);
                AsyncTask service = new ServicesByNip(txtUsername.getText().toString(), getApplicationContext(),ini);
                service.execute("action");
            }
        });
    }

    public void tampilData(String nip, String nama){
        EditText terimaNip= (EditText) findViewById(R.id.showNip);
        terimaNip.setText(nip,EditText.BufferType.NORMAL);

        EditText terimaNama=(EditText)findViewById(R.id.showNama);
        terimaNama.setText(nama,EditText.BufferType.NORMAL);
    }

    public void tampilDataObjectJSON(JSONObject data){
        try {
        String nip=data.getString("nipBaru");
        String nama=data.getString("nama");

        JSONObject instansi=data.getJSONObject("instansi");
        String instansiStr=instansi.getString("nama");
        String jabatan=instansi.getString("namaJabatan");
        String jk=data.getString("jenisKelamin");
        String jenisKelamin;
        if (jk.equalsIgnoreCase("M")){
            jenisKelamin="Laki-Laki";
        }else{
            jenisKelamin="Perempuan";
        }

        //tes
        EditText terimaNip= (EditText) findViewById(R.id.showNip);
        terimaNip.setText(nip,EditText.BufferType.NORMAL);

        EditText terimaNama=(EditText)findViewById(R.id.showNama);
        terimaNama.setText(nama,EditText.BufferType.NORMAL);

        EditText terimaInstansi= (EditText) findViewById(R.id.showInstansi);
        terimaInstansi.setText(instansiStr,EditText.BufferType.NORMAL);

<<<<<<< HEAD
        EditText terimaNamaJabatan= (EditText) findViewById(R.id.showNamaJabatan);
        terimaNamaJabatan.setText(jabatan,EditText.BufferType.NORMAL);
=======
            Log.d("jsom_key",data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void tampilGolonganObjectJSON(JSONObject gol){
        //tampil by golongan
        try {
            String nama=gol.getString("nama");
            String pangkat=gol.getString("namaPangkat");
            //String nipBaru=data.getString("nipBaru");
            //String tmtPns=data.getString("tmtPns");
            //String tmtGolongan=data.getString("tmtGolongan");
            //String jenisKelamin=data.getString("jenisKelamin");
            //String alamat=data.getString("alamat");


            EditText terimaGolongan= (EditText) findViewById(R.id.showGolongan);
            terimaGolongan.setText(nama,EditText.BufferType.NORMAL);
>>>>>>> origin/master

        EditText terimajenisKelamin= (EditText) findViewById(R.id.showjenisKelamin);
            terimajenisKelamin.setText(jenisKelamin,EditText.BufferType.NORMAL);

            Log.d("jsom_key",data.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
