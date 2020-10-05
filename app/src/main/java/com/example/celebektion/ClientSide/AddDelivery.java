package com.example.celebektion.ClientSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celebektioncakes.HomeActivity;
import com.example.celebektioncakes.Models.Address;
import com.example.celebektioncakes.R;
import com.example.celebektioncakes.Sessions.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDelivery extends AppCompatActivity {

    TextView Country,Province,City,Addres1,Address2;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery);


        add = findViewById(R.id.SaveAddress);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement s1 = new SessionManagement(getApplicationContext());
                final DatabaseReference insertRWef= FirebaseDatabase.getInstance().getReference().child("Address").child(s1.getSessionUsername());
                DatabaseReference RedAddress= FirebaseDatabase.getInstance().getReference().child("AddressIni").child("FirstID");
                RedAddress.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Country=findViewById(R.id.Country);
                        Province=findViewById(R.id.AddressID);
                        City=findViewById(R.id.City);
                        Addres1=findViewById(R.id.Address1);
                        Address2=findViewById(R.id.address2);
                        String ID = dataSnapshot.child("InitailID").getValue().toString();
                        int IDx=Integer.parseInt(ID);
                        IDx++;
                        String newID;
                        newID=Integer.toString(IDx);
                        Address s1= new Address();
                        s1.setCountry(Country.getText().toString());
                        s1.setProvince(Province.getText().toString());
                        s1.setCity(City.getText().toString());
                        s1.setAddress1(Addres1.getText().toString());
                        s1.setAddress2(Address2.getText().toString());
                        s1.setID(newID);
                        insertRWef.child(s1.getID()).setValue(s1);
                        DatabaseReference insert= FirebaseDatabase.getInstance().getReference().child("AddressIni").child("FirstID");
                        IDx++;
                        newID=Integer.toString(IDx);
                        insert.child("InitailID").setValue(newID);
                        Toast.makeText(AddDelivery.this, "Data Added", Toast.LENGTH_SHORT).show();
                        Intent i12= new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i12);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}