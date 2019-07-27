package com.example.qmegle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Second_Page extends AppCompatActivity implements View.OnClickListener {
    private WebView myWebView;
    Button save;
    EditText NewUser, NewPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second__page);
        save=findViewById(R.id.edButSave);
        save.setOnClickListener(this);
        NewUser=findViewById(R.id.edETNewUser);
        NewPass=findViewById(R.id.edETNewPass);

    }

    @Override
    public void onClick(View v) {
        if(R.id.edButSave==v.getId())
        {
            Intent reverse = new Intent(this, MainActivity.class);
            reverse.putExtra("username", NewUser.getText().toString());
            reverse.putExtra("password", NewPass.getText().toString());
            setResult(RESULT_OK, reverse);
            finish();
        }
    }
}
