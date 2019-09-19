package com.example.qmegle;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class Message
{
    String id;
    String name;
    String message;

    public Message()
    {
    }

    public Message(String id, String name, String message)
    {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        return message;
    }


    public String encrypt() {
        int i;
        char[] temp=message.toCharArray();
        for(i=0;i<message.length();i++)
        {
            if(i%2==0) {
                temp[i]= (char) (temp[i]+1);
            }
            else
            {
                temp[i]= (char) (temp[i]-1);
            }
        }
        message=String.valueOf(temp);
        //Log.d(TAG, "encrypt: ");
        return message;
    }


    public void decrypt() {
        int i;
        char[] temp=message.toCharArray();
        for(i=0;i<message.length();i++)
        {
            if(i%2==0) {
                temp[i]= (char) (temp[i]-1);
            }
            else
            {
                temp[i]= (char) (temp[i]+1);
            }
        }
        message=String.valueOf(temp);
        //Log.d(TAG, "decrypt: ");
    }
}
