package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.SedeInfo;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PhoneList extends Thread {
    private String ip;
    private int port;
    private SedeInfo[] sedeInfos;
    public PhoneList(String ip,int port) {
        this.ip=ip;
        this.port=port;
    }
    public SedeInfo[] getSedeInfos() {
        return sedeInfos;
    }
    public void run(){
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("PhoneList");
            sedeInfos=new Gson().fromJson(dis.readUTF(),SedeInfo[].class);
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
