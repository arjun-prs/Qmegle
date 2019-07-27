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
    Button login, signup;
    EditText userName, passWord;
    public String username="admin", password="default";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.edButLogin);
        signup=findViewById(R.id.edButSignUp);
        userName=findViewById(R.id.edETUsername);
        passWord=findViewById(R.id.edETPassword);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==RESULT_OK)
        {
            Bundle b = getIntent().getExtras();
            username = b.getString("username");
            password = b.getString("password");
            Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.edButSignUp)
        {

                Intent intent = new Intent(this, Second_Page.class);
                String url = "https://www.omegle.com/";
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                //startActivity(intent);
                username = userName.getText().toString();
                password = passWord.getText().toString();
                Toast.makeText(this,"Credentials Updated!", Toast.LENGTH_LONG).show();
                userName.setText("");
                passWord.setText("");

        }
        if(v.getId()==R.id.edButLogin)
        {
            if(username.equals(userName.getText().toString())&&password.equals(passWord.getText().toString())) {
                Intent intent3 = new Intent(this, Third_Page.class);

                Toast.makeText(this, "Qmegle under Construction!!! Please visit after some time!!!", Toast.LENGTH_LONG).show();
                startActivity(intent3);
            }
            else
            {
                //Toast.makeText(this, "Enter Correct Credentials!!!", Toast.LENGTH_LONG).show();
                AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
                al.setTitle("Alert");
                al.setMessage("Enter Correct Credentials!!!");
                al.setCancelable(false);
                al.setIcon(R.drawable.ic_launcher_background);
                al.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),
                                        "You clicked on YES :   ", Toast.LENGTH_SHORT).show();
                            }
                        });
                al.show();

            }

        }


    }

}
