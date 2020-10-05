package com.example.celebektion.Sessions;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.celebektion.Models.Admin;
import com.example.celebektion.Models.Member;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Session_UserName="session";
    String Sess_AdminNAme="Session_User";
    String Session_User_LastName="SessionUser";
    String Session_Tech_username="SessionUser";
    String Session_Pass_word="pass";

    String ViewKey="1";


    public SessionManagement(Context context){
        sharedPreferences=context.getSharedPreferences(Session_UserName,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void SaveSession(Member user){
        String Username=user.getUserName();
        String pass=user.getPassword();

        editor.putString(Session_UserName,Username).commit();
        editor.putString(Session_Pass_word,pass).commit();

    }
    public String getSessionpass(){
        return sharedPreferences.getString(Session_Pass_word,"null");

    }

    public String getSessionUsername(){
        return sharedPreferences.getString(Session_UserName,"null");

    }


    public void SavedminSession(Admin admin){
        String Username=admin.getAdminName();
        editor.putString(Sess_AdminNAme,Username).commit();

    }
    public String getAdminSession(){
        return sharedPreferences.getString(Sess_AdminNAme,"null");

    }

    public void SaveSessioNkey(String key){


        editor.putString(ViewKey,key).commit();


    }
    public String getSessionkey(){
        return sharedPreferences.getString(ViewKey,"null");

    }


}
