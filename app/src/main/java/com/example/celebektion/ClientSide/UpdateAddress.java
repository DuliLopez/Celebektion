package com.example.celebektion.ClientSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.celebektioncakes.HomeActivity;
import com.example.celebektioncakes.Models.Address;
import com.example.celebektioncakes.R;
import com.example.celebektioncakes.Sessions.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateAddress extends AppCompatActivity {
    TextView Country,Province,City,Addres1,Address2;
    Button Update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        Update=findViewById(R.id.UpdateAddeess);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateAddress();
            }
        });
        Intent i1 =getIntent();
        String ID=i1.getStringExtra("ID");
        SessionManagement s1= new SessionManagement(getApplicationContext());
        DatabaseReference readRef= FirebaseDatabase.getInstance().getReference().child("Address").child(s1.getSessionUsername()).child(ID);
        readRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SessionManagement s1= new SessionManagement(getApplicationContext());
                String ID;
                ID=s1.getSessionkey();
                int newID=Integer.parseInt(ID);
                if(newID == 2){
                    Country=findViewById(R.id.Country);
                    Province=findViewById(R.id.AddressID);
                    City=findViewById(R.id.City);
                    Addres1=findViewById(R.id.Address1);
                    Address2=findViewById(R.id.address2);
                    Country.setText(dataSnapshot.child("country").getValue().toString());
                    Province.setText(dataSnapshot.child("province").getValue().toString());
                    City.setText(dataSnapshot.child("city").getValue().toString());
                    Addres1.setText(dataSnapshot.child("address1").getValue().toString());
                    Address2.setText(dataSnapshot.child("address2").getValue().toString());
                }
                else{
                    s1.SaveSessioNkey("2");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void UpdateAddress(){


        Country=findViewById(R.id.Country);
        Province=findViewById(R.id.AddressID);
        City=findViewById(R.id.City);
        Addres1=findViewById(R.id.Address1);
        Address2=findViewById(R.id.address2);
        Intent i1 =getIntent();
        String ID=i1.getStringExtra("ID");
        SessionManagement s1= new SessionManagement(getApplicationContext());
        s1.SaveSessioNkey("2");
        DatabaseReference UpdateRef=FirebaseDatabase.getInstance().getReference().child("Address").child(s1.getSessionUsername()).child(ID);
        Address a1= new Address();
        a1.setCountry(Country.getText().toString());
        a1.setProvince(Province.getText().toString());
        a1.setCity(City.getText().toString());
        a1.setAddress1(Addres1.getText().toString());
        a1.setAddress2(Address2.getText().toString());
        a1.setID(ID);
        UpdateRef.setValue(a1);
        Intent i12= new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i12);
    }
}