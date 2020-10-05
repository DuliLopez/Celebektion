package com.example.celebektion.ClientSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celebektion.HomeActivity;
import com.example.celebektion.Models.Member;
import com.example.celebektion.R;
import com.example.celebektion.Sessions.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeAccuntDetails extends AppCompatActivity {
    EditText fname,lname,dob,email,phone;
    Button EditDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_accunt_details);
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lnamew);
        dob=findViewById(R.id.dob);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phoen);
        EditDetails=findViewById(R.id.EditDetails);
        SessionManagement s1= new SessionManagement(getApplicationContext());
        DatabaseReference ReadData= FirebaseDatabase.getInstance().getReference().child("Member").child(s1.getSessionUsername());
        ReadData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fname.setText(dataSnapshot.child("firstName").getValue().toString());
                lname.setText(dataSnapshot.child("lastName").getValue().toString());
                dob.setText(dataSnapshot.child("dob").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                phone.setText(dataSnapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        EditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditDetal();
            }
        });
    }
    public void EditDetal(){
        SessionManagement s1=new SessionManagement(getApplicationContext());
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lnamew);
        dob=findViewById(R.id.dob);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phoen);
        Member mem= new Member();
        mem.setDOB(dob.getText().toString());
        mem.setFirstName(fname.getText().toString());
        mem.setLastName(lname.getText().toString());
        mem.setEmail(email.getText().toString());
        mem.setPhone(phone.getText().toString());
        mem.setUserName(s1.getSessionUsername());
        mem.setPassword(s1.getSessionpass());
        DatabaseReference updateref= FirebaseDatabase.getInstance().getReference().child("Member").child(s1.getSessionUsername());
        updateref.setValue(mem);
        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        Intent i1= new Intent(this, HomeActivity.class);
        startActivity(i1);
    }

}