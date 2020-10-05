package com.example.celebektion.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.celebektioncakes.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminViewCakeInside extends AppCompatActivity {
    TextView AdminName,CakeName,caketype,price,imgurl;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_cake_inside);
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
                ChangeDetails(ID);
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void ChangeDetails(String ID){
        Intent i1= new Intent (AdminViewCakeInside.this,ChangeCakeDetails.class);
        i1.putExtra("ID",ID);
        startActivity(i1);

    }
}