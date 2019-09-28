package com.example.qmegle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chat extends AppCompatActivity implements View.OnClickListener
{
    ImageButton send;
    EditText smsg;
    //LinearLayout l1;
    String sender;
    //ScrollView scrollView;
    ListView lv;
    List<String> messageList;
    int userCount;
    DatabaseReference databaseChat;
    DatabaseReference databaseUserCount;
    ProgressDialog waitDialog;
    String key;

    @Override
    protected void onStart()
    {
        super.onStart();
        databaseChat.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                messageList.clear();
                for(DataSnapshot chatSnapshot: dataSnapshot.getChildren())      //enhanced for loop that iterates through all children in data snapshot
                {
                    Message m = chatSnapshot.getValue(Message.class);
                    m.decrypt();
                    messageList.add(m.getName() + ": " + m.getMessage());
                }
                ArrayAdapter ad = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,messageList);
                lv.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });

        databaseUserCount.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                userCount = Integer.parseInt(dataSnapshot.getValue().toString());
                if (userCount==1)
                {
                    //Toast.makeText(getApplicationContext(),"User Count: "+userCount+" is ODD",Toast.LENGTH_SHORT).show();
                    waitDialog.setMessage("Finding you a stranger.");
                    waitDialog.setIndeterminate(true);
                    waitDialog.setCancelable(false);
                    waitDialog.show();
                }
                else
                {
                    waitDialog.dismiss();
                    //Toast.makeText(getApplicationContext(),"User Count: "+userCount+" is EVEN",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        send = findViewById(R.id.sendbutton);
        send.setOnClickListener(this);
        smsg = findViewById(R.id.editMessage);
        //l1 = findViewById(R.id.linearLayout);             //When using text views inside scroll view for chat bubbles
        Bundle b = getIntent().getExtras();
        sender = b.getString("name");
        key = b.getString("key");
        //scrollView = findViewById(R.id.scrollView);       //When using text views inside scroll view for chat bubbles
        lv = findViewById(R.id.listView);
        messageList = new ArrayList<>();
        waitDialog = new ProgressDialog(chat.this);
        databaseChat = FirebaseDatabase.getInstance().getReference(key).child("chat");
        databaseUserCount = FirebaseDatabase.getInstance().getReference(key).child("count");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        databaseChat.removeValue();
        databaseUserCount.setValue(--userCount);
    }

    public void onClick(View view)
    {
        if (view.getId() == R.id.sendbutton)
        {
            /*//When using text views inside scroll view for chat bubbles
            //Toast.makeText(this, "This feature is under development!", Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.RIGHT;
            layoutParams.setMargins(10, 10, 10, 10);
            final TextView tv = new TextView(this);
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
                public void run()
                {
                  scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                  // scrollView.smoothScrollTo(0,0);
                }
            });
            smsg.setText("");
            smsg.requestFocus();*/
            String id = databaseChat.push().getKey();    //creates a unique string (key) inside the node
            Message m1 = new Message(id, sender, smsg.getText().toString());
            m1.encrypt();
            databaseChat.child(id).setValue(m1);
            smsg.setText("");
            /*lv.post(new Runnable() {
                @Override
                public void run() {
                    // Select the last row so it will scroll into view...
                    lv.setSelection(ad.getCount() - 1);
                }
            });*/
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