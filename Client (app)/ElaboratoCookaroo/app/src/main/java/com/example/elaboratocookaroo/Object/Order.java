package com.example.elaboratocookaroo.Object;

public class Order {
    private String cliente,tempoconsegna;
    private float costototale;
    private CartItem[] cartItems;
    public Order(String cliente,String tempoconsegna,float costototale,CartItem[] cartItems){
        this.cliente=cliente;
        this.tempoconsegna=tempoconsegna;
        this.costototale=costototale;
        this.cartItems=cartItems;
    }
}
