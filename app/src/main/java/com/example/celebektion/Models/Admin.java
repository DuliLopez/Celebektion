package com.example.celebektion.Models;

public class Admin {
    private String AdminName,Password;

    public Admin() {
    }

    public Admin(String adminName, String password) {
        AdminName = adminName;
        Password = password;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
