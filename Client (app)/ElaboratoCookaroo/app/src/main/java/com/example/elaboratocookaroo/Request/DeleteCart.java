package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Common.Variables;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DeleteCart extends Thread {
    private String ip;
    private int port;
    public DeleteCart(String ip,int port){
        this.ip=ip;
        this.port=port;
    }
    public void run() {
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("DeleteCart");
            dos.writeUTF(Variables.currentUser);
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
