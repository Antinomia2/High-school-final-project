package com.example.elaboratocookaroo.Request;

import com.example.elaboratocookaroo.Object.FoodInfo.FoodItem;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FoodList extends Thread {
    private String ip;
    private int port;
    private String tipologia;
    private FoodItem[] foodItems;
    public FoodList(String ip,int port,String tipologia) {
        this.ip=ip;
        this.port=port;
        this.tipologia=tipologia;
    }
    public FoodItem[] getFoodItems() {
        return foodItems;
    }
    public void run(){
        Socket s=null;
        try{
            s=new Socket(ip,port);
            DataInputStream dis=new DataInputStream(new BufferedInputStream(s.getInputStream()));
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("FoodList");
            dos.writeUTF(tipologia);
            foodItems=new Gson().fromJson(dis.readUTF(),FoodItem[].class);
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
