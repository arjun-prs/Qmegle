package com.example.qmegle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class Third_Page extends AppCompatActivity implements View.OnClickListener {
    ImageButton Send, Attach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third__page);
        Send = findViewById(R.id.edButSend);
        Attach = findViewById(R.id.edButAttach);
        Send.setOnClickListener(this);
        Attach.setOnClickListener(this);
    }


    public void onClick(View view) {

        if (view.getId() == R.id.edButSend)
            Toast.makeText(this, "This feature is under development!!!", Toast.LENGTH_SHORT).show();
        if(view.getId()==R.id.edButAttach) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 3);
        }



       /* protected void onActivityResult(int requestCode, int resultCode, Intent intent3) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 3 && resultCode == RESULT_OK && data != null && data.getData() != null)
            {
                try {
                    Bitmap bitmap = MediaStore.
                            Images.Media.
                            getBitmap(getContentResolver(), data.getData());
                    //   Log.d(TAG, String.valueOf(bitmap));
                    ImageView attach =  findViewById(R.id.edButAttach);
                    attach.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
    public boolean onCreateOptionsMenu
            (Menu menu) {
        getMenuInflater().inflate
                (R.menu.m1, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected
            (MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.edMenu1:

                Toast.makeText
                        (this,"Archive Feature under development",Toast.LENGTH_SHORT).show();
                break;

            case R.id.edMenu2:

                Toast.makeText
                        (this,"Export Feature under Development",Toast.LENGTH_SHORT).show();
                break;

            case R.id.edMenu3:

                Toast.makeText
                        (this,"We are a bunch of Talented Programmers!",Toast.LENGTH_SHORT).show();

                Intent intent4 = new Intent(this, Fourth_Page.class);
                startActivity(intent4);
                break;
        }


        return true;
    }


}



