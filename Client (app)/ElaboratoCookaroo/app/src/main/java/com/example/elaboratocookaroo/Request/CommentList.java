package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Object.Category;
import com.example.elaboratocookaroo.Object.Valutation;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommentList extends Thread {
    private String ip;
    private int port;
    private Valutation[] valutations;
    public CommentList(String ip,int port) {
        this.ip=ip;
        this.port=port;
    }
    public Valutation[] getValutations() {
        return valutations;
    }
    public void run(){
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("CommentList");
            dos.writeUTF(Variables.currentDish.getCodicep());
            valutations=new Gson().fromJson(dis.readUTF(),Valutation[].class);
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
