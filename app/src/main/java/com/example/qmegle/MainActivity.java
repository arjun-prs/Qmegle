package com.example.qmegle;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView t1,t2,t3;
    EditText e1;
    Button b1;
    DatabaseReference databaseChat;
    DatabaseReference chat;
    int userCount;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editText);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(this);
        t1 = findViewById(R.id.title);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(),"User Count = "+userCount,Toast.LENGTH_SHORT).show();
            }
        });
        /*t2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                chat.child("count").setValue(userCount+1);
                Toast.makeText(getApplicationContext(),"User Count = "+userCount,Toast.LENGTH_SHORT).show();
            }
        });
        t3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                chat.child("count").setValue(userCount-1);
                Toast.makeText(getApplicationContext(),"User Count = "+userCount,Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        databaseChat = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseChat.orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(!dataSnapshot.exists())
                {
                    key = databaseChat.push().getKey();
                    chat = databaseChat.child(key);
                    chat.child("count").setValue(0);
                }
                for(DataSnapshot chatSnapshot: dataSnapshot.getChildren())      //enhanced for loop that iterates through all children in data snapshot
                {
                    userCount = Integer.parseInt(chatSnapshot.child("count").getValue().toString());
                    Toast.makeText(getApplicationContext(),"User Count = "+userCount,Toast.LENGTH_SHORT).show();
                    if(userCount<2)
                    {
                        key = chatSnapshot.getKey().toString();
                        chat = databaseChat.child(key);
                    }
                    else
                    {
                        key = databaseChat.push().getKey();
                        chat = databaseChat.child(key);
                        userCount = 0;
                        chat.child("count").setValue(userCount);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });

    }

    @Override
    public void onClick(View view)
    {
        if(e1.getText().toString().equals(""))
        {
            Toast.makeText(this, "No entry without a nickname! \uD83D\uDE1B", Toast.LENGTH_SHORT).show();
        }
        else
        {
            chat.child("count").setValue(++userCount);
            Toast.makeText(getApplicationContext(),"User Count = "+userCount,Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,chat.class);
            i.putExtra("name",e1.getText().toString());
            i.putExtra("key",key);
            startActivity(i);
        }
    }
}
