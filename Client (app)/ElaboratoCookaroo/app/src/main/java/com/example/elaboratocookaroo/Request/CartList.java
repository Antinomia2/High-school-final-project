package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Common.Variables;
import com.example.elaboratocookaroo.Object.CartItem;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CartList extends Thread {
    private String ip;
    private int port;
    private CartItem[] cartItems;
    public CartList(String ip,int port) {
        this.ip=ip;
        this.port=port;
    }
    public CartItem[] getCartItems() {
        return cartItems;
    }
    public void run(){
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("CartList");
            dos.writeUTF(Variables.currentUser);
            cartItems=new Gson().fromJson(dis.readUTF(),CartItem[].class);
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
