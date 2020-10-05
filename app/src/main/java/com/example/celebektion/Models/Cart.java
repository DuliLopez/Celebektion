package com.example.celebektion.Models;

public class Cart {
    private String CakeID,CakeName,Qty,msg,price;

    public Cart() {
    }

    public Cart(String cakeID, String cakeName, String qty, String msg, String price) {
        CakeID = cakeID;
        CakeName = cakeName;
        Qty = qty;
        this.msg = msg;
        this.price = price;
    }

    public String getCakeID() {
        return CakeID;
    }

    public void setCakeID(String cakeID) {
        CakeID = cakeID;
    }

    public String getCakeName() {
        return CakeName;
    }

    public void setCakeName(String cakeName) {
        CakeName = cakeName;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
