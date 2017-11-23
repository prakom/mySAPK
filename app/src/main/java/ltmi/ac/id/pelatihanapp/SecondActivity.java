package ltmi.ac.id.pelatihanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by root on 21/11/17.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        Intent intent = getIntent();
        String text1 = intent.getStringExtra("label");
        EditText text = (EditText)findViewById(R.id.editText);
        text.setText(text1, TextView.BufferType.NORMAL);
    }
}
