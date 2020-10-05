package com.example.celebektion.Models;

public class Orders {
    private  String Cakename,Quantity,Total,CustomerName,OrderID;

    public Orders() {
    }

    public Orders(String cakename, String quantity, String total, String customerName, String orderID) {
        Cakename = cakename;
        Quantity = quantity;
        Total = total;
        CustomerName = customerName;
        OrderID = orderID;
    }

    public String getCakename() {
        return Cakename;
    }

    public void setCakename(String cakename) {
        Cakename = cakename;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }
}
