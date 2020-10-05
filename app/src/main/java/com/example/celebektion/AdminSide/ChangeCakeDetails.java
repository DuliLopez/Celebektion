package com.example.celebektion.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.celebektioncakes.AdminHome;
import com.example.celebektioncakes.Models.Cake;
import com.example.celebektioncakes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeCakeDetails extends AppCompatActivity {
    EditText CakeName,caketype,price,imgurl;
    TextView AdminName;
    Button b1;
    String Adminname,Skip,intitalValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_cake_details);
        Intent i1= getIntent();
        final String ID= i1.getStringExtra("ID").toString();
        AdminName=findViewById(R.id.Country);
        CakeName=findViewById(R.id.AddressID);
        caketype=findViewById(R.id.City);
        price=findViewById(R.id.Address1);
        imgurl=findViewById(R.id.address2);
        b1=findViewById(R.id.UpdateAddeess);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData(ID);
            }
        });
        DatabaseReference Read= FirebaseDatabase.getInstance().getReference().child("Cake").child(ID);
        Read.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AdminName.setText(dataSnapshot.child("adminName").getValue().toString());
                CakeName.setText(dataSnapshot.child("cakeName").getValue().toString());
                caketype.setText(dataSnapshot.child("cakeType").getValue().toString());
                price.setText(dataSnapshot.child("price").getValue().toString());
                imgurl.setText(dataSnapshot.child("imgurl").getValue().toString());
                Adminname=dataSnapshot.child("adminName").getValue().toString();
                Skip=dataSnapshot.child("skip").getValue().toString();
                intitalValue=dataSnapshot.child("intitalValue").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void UpdateData(String ID){
        CakeName=findViewById(R.id.AddressID);
        caketype=findViewById(R.id.City);
        price=findViewById(R.id.Address1);
        imgurl=findViewById(R.id.address2);
        DatabaseReference update= FirebaseDatabase.getInstance().getReference().child("Cake").child(ID);
        Cake UpdateCake= new Cake();
        UpdateCake.setCakeName(CakeName.getText().toString());
        UpdateCake.setCakeType(caketype.getText().toString());
        UpdateCake.setPrice(price.getText().toString());
        UpdateCake.setImgurl(imgurl.getText().toString());
        UpdateCake.setIntitalValue(intitalValue);
        UpdateCake.setSkip(Skip);
        UpdateCake.setAdminName(Adminname);
        update.setValue(UpdateCake);
        Intent i1= new Intent(ChangeCakeDetails.this, AdminHome.class);
        startActivity(i1);
    }
}