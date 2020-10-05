package com.example.celebektion.ClientSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celebektion.Models.Cart;
import com.example.celebektion.R;
import com.example.celebektion.Sessions.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCakeDetails extends AppCompatActivity {
    private TextView CakeName,CakeSize,Price;
    private EditText Msg,qty;
    Button b1;
    String id;
    private  String cakename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cake_details);
        Intent i1=getIntent();
        id=i1.getStringExtra("ID").toString();
        CakeName=findViewById(R.id.textView11);
        Price=findViewById(R.id.textView18);
        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCartDetails(id,cakename,Price.getText().toString());

            }
        });
        DatabaseReference readData= FirebaseDatabase.getInstance().getReference().child("Cake").child(id);
        readData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CakeName.setText(dataSnapshot.child("cakeName").getValue().toString());
                Price.setText(dataSnapshot.child("price").getValue().toString());
                cakename=dataSnapshot.child("cakeName").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void AddCartDetails(final String ID, final String Name, final String Price){
        Msg=findViewById(R.id.editTextTextPersonName2);
        qty=findViewById(R.id.editTextTextPersonName6);
        SessionManagement s1= new SessionManagement(getApplicationContext());
        final DatabaseReference InsertOrder= FirebaseDatabase.getInstance().getReference().child("Order").child(s1.getSessionUsername()).child(Name);
        final DatabaseReference InsertCar= FirebaseDatabase.getInstance().getReference().child("Cart").child(Name);
        DatabaseReference Readref= FirebaseDatabase.getInstance().getReference().child("Cart").child(Name);
        Readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    String qty1=dataSnapshot.child("qty").getValue().toString();
                    Cart cartDetails = new Cart();
                    cartDetails.setMsg(Msg.getText().toString());
                    cartDetails.setQty(qty.getText().toString());
                    cartDetails.setCakeName(Name);
                    cartDetails.setCakeID(ID);
                    cartDetails.setPrice(Price);
                    InsertCar.setValue(cartDetails);
                    InsertOrder.setValue(cartDetails);
                    Toast.makeText(ViewCakeDetails.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                    // Intent i1= new Intent(ViewCakeDetails.this, HomeView.class);
                    //    startActivity(i1);

                }
                else{
                    Cart cartDetails = new Cart();
                    cartDetails.setMsg(Msg.getText().toString());
                    cartDetails.setQty(qty.getText().toString());
                    cartDetails.setCakeName(Name);
                    cartDetails.setCakeID(ID);
                    cartDetails.setPrice(Price);
                    InsertCar.setValue(cartDetails);
                    InsertOrder.setValue(cartDetails);
                        Toast.makeText(ViewCakeDetails.this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}