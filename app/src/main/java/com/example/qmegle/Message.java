package com.example.qmegle;

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
}
