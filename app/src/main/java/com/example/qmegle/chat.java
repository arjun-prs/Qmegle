package com.example.qmegle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

public class chat extends AppCompatActivity implements View.OnClickListener {

    ImageButton Send;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        Send = findViewById(R.id.sendbutton);
        Send.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if (view.getId() == R.id.sendbutton)
            Toast.makeText(this, "This feature is under development!", Toast.LENGTH_SHORT).show();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.m1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.edMenu1:
                Toast.makeText(this,"This feature is under development!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edMenu2:
            {
                Intent i = new Intent(this, info.class);
                startActivity(i);
            }
            break;
        }
        return true;
    }
}



