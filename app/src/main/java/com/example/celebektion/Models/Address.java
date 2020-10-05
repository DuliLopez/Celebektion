package com.example.celebektion.Models;

public class Address {
    private  String Country,Province,City,Address1,Address2,ID;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Address(String country, String province, String city, String address1, String address2, String ID) {
        Country = country;
        Province = province;
        City = city;
        Address1 = address1;
        Address2 = address2;
        this.ID = ID;
    }

    public Address() {
    }
}
