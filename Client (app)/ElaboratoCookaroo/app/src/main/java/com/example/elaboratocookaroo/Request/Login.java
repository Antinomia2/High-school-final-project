package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.Credentials;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Login extends Thread {
    private String ip;
    private int port;
    private Credentials credentials;
    private boolean correct;
    public Login(String ip,int port,Credentials credentials) {
        this.ip=ip;
        this.port=port;
        this.credentials=credentials;
    }
    public boolean isCorrect() {
        return correct;
    }
    public void run(){
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Login");
            String json= new Gson().toJson(credentials);
            dos.writeUTF(json);
            correct=dis.readBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
