package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.Cart;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AddToCart extends Thread {
    private String ip;
    private int port;
    private Cart cart;
    public AddToCart(String ip,int port,Cart cart){
        this.ip=ip;
        this.port=port;
        this.cart=cart;
    }
    public void run() {
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("AddToCart");
            String json=new Gson().toJson(cart);
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
