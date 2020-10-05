package com.example.celebektion.Navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celebektioncakes.Models.Cake;
import com.example.celebektioncakes.R;
import com.example.celebektioncakes.Sessions.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCake#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCake extends Fragment {

    private Button SaveButton;
    private EditText CakeName,CakeType,price,imgurl;
    private TextView AdminName;
    private DatabaseReference addRef;
    private DatabaseReference Readref;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCake() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCake.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCake newInstance(String param1, String param2) {
        AddCake fragment = new AddCake();
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
        final View v= inflater.inflate(R.layout.fragment_add_cake, container, false);
        SaveButton= v.findViewById(R.id.UpdateAddeess);
        AdminName=v.findViewById(R.id.Country);
        SessionManagement s1= new SessionManagement(getContext());
        AdminName.setText(s1.getAdminSession());
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewCake(v);

            }
        });
        return (v);
    }

    public  void AddNewCake(View v){

        CakeName=v.findViewById(R.id.AddressID);
        CakeType=v.findViewById(R.id.City);
        price=v.findViewById(R.id.Address1);
        imgurl=v.findViewById(R.id.address2);

        addRef= FirebaseDatabase.getInstance().getReference().child("Cake");
        Readref=FirebaseDatabase.getInstance().getReference().child("CakeInitials").child("Cake0");
        Readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                if(dataSnapshot1.hasChildren()){
                    String IniialValue=dataSnapshot1.child("InititailID").getValue().toString();
                    int ID=Integer.parseInt(IniialValue);
                    ID++;
                    IniialValue=Integer.toString(ID);
                    final String finalIniialValue = IniialValue;
                    Cake cake = new Cake();
                    cake.setCakeName(CakeName.getText().toString());
                    cake.setCakeType(CakeType.getText().toString());
                    cake.setPrice(price.getText().toString());
                    cake.setImgurl(imgurl.getText().toString());
                    cake.setAdminName(AdminName.getText().toString());
                    cake.setIntitalValue(finalIniialValue);
                    cake.setSkip("0");
                    addRef.child(cake.getIntitalValue()).setValue(cake);
                    Toast.makeText(getContext(), "Cake Added", Toast.LENGTH_SHORT).show();
                    DatabaseReference ChageRef= FirebaseDatabase.getInstance().getReference().child("CakeInitials").child("Cake0");
                    ID++;
                    String Idx;
                    Idx=Integer.toString(ID);
                    ChageRef.child("InititailID").setValue(Idx);
                    ClearControls();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void ClearControls(){
        CakeName.setText("");
        CakeType.setText("");
        price.setText("");
        imgurl.setText("");
    }
}