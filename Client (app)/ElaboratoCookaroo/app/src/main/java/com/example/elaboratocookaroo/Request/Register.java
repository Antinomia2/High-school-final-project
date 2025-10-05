package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.RegisterInfo;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Register extends Thread {
    private String ip;
    private int port;
    private RegisterInfo ri;
    private Boolean exists;
    public Register(String ip,int port,RegisterInfo ri){
        this.ip=ip;
        this.port=port;
        this.ri=ri;
    }
    public Boolean getExists() {
        return exists;
    }
    public void run() {
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Register");
            String json=new Gson().toJson(ri);
            dos.writeUTF(json);
            exists=dis.readBoolean();
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
