package com.example.celebektion.Navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.celebektion.ClientSide.ChangeAccuntDetails;
import com.example.celebektion.MainActivity;
import com.example.celebektion.Models.Member;
import com.example.celebektion.R;
import com.example.celebektion.Sessions.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileView extends Fragment {
    TextView fname,lname,dob,email,phone;
    Button EditDetails,RemoveAccoutn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileView.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileView newInstance(String param1, String param2) {
        ProfileView fragment = new ProfileView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_profile_view, container, false);
        fname=v.findViewById(R.id.fname);
        lname=v.findViewById(R.id.lnamew);
        dob=v.findViewById(R.id.dob);
        email=v.findViewById(R.id.email);
        phone=v.findViewById(R.id.phoen);
        EditDetails=v.findViewById(R.id.EditDetails);
        RemoveAccoutn=v.findViewById(R.id.Deleteaccount);
        RemoveAccoutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAccount();
            }
        });
        SessionManagement s1= new SessionManagement(getContext());
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
                EditDetal(v);
            }
        });



        return v;
    }


    public void EditDetal(View v){
        Intent i1= new Intent(getContext(), ChangeAccuntDetails.class);
        startActivity(i1);
    }

    public void DeleteAccount(){
        SessionManagement s1 = new SessionManagement(getContext());
        DatabaseReference DeleteAccount = FirebaseDatabase.getInstance().getReference().child("Member").child(s1.getSessionUsername());
        DeleteAccount.removeValue();
        Intent i1= new Intent(getContext(), MainActivity.class);
        startActivity(i1);
    }
}