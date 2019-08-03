package com.example.qmegle;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(e1.getText().toString().equals(""))
        {
            Toast.makeText(this, "No entry without a nickname! :P", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i = new Intent(this,chat.class);
            i.putExtra("name",e1.getText().toString());
            startActivity(i);
        }
    }
}
