package com.example.celebektion.Models;

public class Cake {
    private String CakeName,CakeType,price,imgurl,IntitalValue,Skip,AdminName;

    public Cake(String cakeName, String cakeType, String price, String imgurl, String intitalValue, String skip, String adminName) {
        CakeName = cakeName;
        CakeType = cakeType;
        this.price = price;
        this.imgurl = imgurl;
        IntitalValue = intitalValue;
        Skip = skip;
        AdminName = adminName;
    }

    public Cake() {
    }

    public String getCakeName() {
        return CakeName;
    }

    public void setCakeName(String cakeName) {
        CakeName = cakeName;
    }

    public String getCakeType() {
        return CakeType;
    }

    public void setCakeType(String cakeType) {
        CakeType = cakeType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getIntitalValue() {
        return IntitalValue;
    }

    public void setIntitalValue(String intitalValue) {
        IntitalValue = intitalValue;
    }

    public String getSkip() {
        return Skip;
    }

    public void setSkip(String skip) {
        Skip = skip;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }
}
