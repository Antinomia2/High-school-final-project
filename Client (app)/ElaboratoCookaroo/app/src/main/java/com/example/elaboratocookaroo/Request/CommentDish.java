package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.Valutation;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommentDish extends Thread {
    private String ip;
    private int port;
    private Valutation valutation;
    private float avg_valutazione;
    public CommentDish(String ip,int port,Valutation valutation){
        this.ip=ip;
        this.port=port;
        this.valutation=valutation;
    }

    public float getAvg_valutazione() {
        return avg_valutazione;
    }

    public void run() {
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            String json=new Gson().toJson(valutation);
            dos.writeUTF("CommentDish");
            dos.writeUTF(json);
            avg_valutazione=dis.readFloat();
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
