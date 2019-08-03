package com.example.qmegle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class chat extends AppCompatActivity implements View.OnClickListener {

    ImageButton send;
    EditText smsg;
    LinearLayout l1;
    String sender;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        send = findViewById(R.id.sendbutton);
        send.setOnClickListener(this);
        smsg = findViewById(R.id.editMessage);
        l1 = findViewById(R.id.linearLayout);
        Bundle b = getIntent().getExtras();
        sender = b.getString("name");
        scrollView = findViewById(R.id.scrollView);
    }

    public void onClick(View view)
    {
        if (view.getId() == R.id.sendbutton)
        {
            //Toast.makeText(this, "This feature is under development!", Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.RIGHT;
            layoutParams.setMargins(10, 10, 10, 10);
            TextView tv = new TextView(this);
            tv.setLayoutParams(layoutParams);
            tv.setTextSize(20);
            tv.setTextColor(getResources().getColor(R.color.colorPrimaryText));
            //tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));            //Color.parseColor("#FDFA72")
            tv.setBackground(getResources().getDrawable(R.drawable.chat_bubble));
            tv.setPadding(10,10,10,10);
            tv.setText(sender + ": " + smsg.getText().toString());
            this.l1.addView(tv);
            scrollView.post(new Runnable()
            {
                @Override
                public void run() {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });
            smsg.setText("");
            smsg.requestFocus();
        }
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



