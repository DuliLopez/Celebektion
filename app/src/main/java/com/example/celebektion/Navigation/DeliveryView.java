package com.example.celebektion.Navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celebektioncakes.ClientSide.AddDelivery;
import com.example.celebektioncakes.ClientSide.UpdateAddress;
import com.example.celebektioncakes.ClientSide.ViewCakeDetails;
import com.example.celebektioncakes.HomeActivity;
import com.example.celebektioncakes.Models.Address;
import com.example.celebektioncakes.Models.Cart;
import com.example.celebektioncakes.R;
import com.example.celebektioncakes.Sessions.SessionManagement;
import com.example.celebektioncakes.ViewHolder.AddressViewHolder;
import com.example.celebektioncakes.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliveryView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveryView extends Fragment {
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button AddDeliver1;

    public DeliveryView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeliveryView.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliveryView newInstance(String param1, String param2) {
        DeliveryView fragment = new DeliveryView();
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
        View v= inflater.inflate(R.layout.fragment_delivery_view, container, false);
        AddDeliver1=v.findViewById(R.id.AddDel);
        AddDeliver1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getContext(), AddDelivery.class);
                startActivity(i1);

            }
        });
        SessionManagement s1= new SessionManagement(getContext());

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Address").child(s1.getSessionUsername());
        recyclerView = v.findViewById(R.id.Recview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<Address> options =
                new FirebaseRecyclerOptions.Builder<Address>()
                        .setQuery(ProductsRef, Address.class)
                        .build();
        FirebaseRecyclerAdapter<Address, AddressViewHolder> adapter =
                new FirebaseRecyclerAdapter<Address, AddressViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AddressViewHolder holder, int position, @NonNull final Address model) {
                        holder.AddressID.setText(model.getID());
                        holder.City.setText(model.getCity());
                        holder.Province.setText(model.getProvince());
                        holder.Remove1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                RemoveCartItem(model.getID());
                            }
                        });
                        holder.UpdateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UpdateItems(model.getID());
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_list_layout, parent, false);
                        AddressViewHolder holder = new AddressViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        return v;

    }
    public void RemoveCartItem(String ID){
        SessionManagement s1= new SessionManagement(getContext());
        s1.SaveSessioNkey("10");
        DatabaseReference delteref= FirebaseDatabase.getInstance().getReference().child("Address").child(s1.getSessionUsername()).child(ID);
        delteref.removeValue();
        Intent i1= new Intent(getContext(), HomeActivity.class);
        startActivity(i1);
        Toast.makeText(getContext(), "Item Removed", Toast.LENGTH_SHORT).show();

    }
    public void UpdateItems(String ID){
        Intent i1= new Intent (getContext(), UpdateAddress.class);
        i1.putExtra("ID",ID);
        startActivity(i1);
    }
}