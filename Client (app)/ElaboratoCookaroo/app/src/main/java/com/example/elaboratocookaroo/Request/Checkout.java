package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.CartItem;
import com.example.elaboratocookaroo.Object.Order;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Checkout extends Thread {
    private String ip;
    private int port;
    private Order order;
    public Checkout(String ip,int port,Order order){
        this.ip=ip;
        this.port=port;
        this.order=order;
    }
    public void run() {
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Checkout");
            String json=new Gson().toJson(order);
            dos.writeUTF(json);
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
