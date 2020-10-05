package com.example.celebektion.AdminNavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.celebektion.AdminSide.AdminViewCakeInside;
import com.example.celebektion.Models.Cake;
import com.example.celebektion.R;
import com.example.celebektion.ViewHolder.AdminCakeViewHolder;
import com.example.celebektion.ViewHolder.CakeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHome extends Fragment {
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

    public AdminHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminHome.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminHome newInstance(String param1, String param2) {
        AdminHome fragment = new AdminHome();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_home, container, false);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Cake");
        recyclerView = v.findViewById(R.id.Recview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<Cake> options =
                new FirebaseRecyclerOptions.Builder<Cake>()
                        .setQuery(ProductsRef, Cake.class)
                        .build();
        FirebaseRecyclerAdapter<Cake, AdminCakeViewHolder> adapter =
                new FirebaseRecyclerAdapter<Cake, AdminCakeViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminCakeViewHolder holder, final int position, @NonNull Cake model)
                    {

                            holder.txtProductName.setText(model.getCakeName());
                            final String intval=model.getIntitalValue();
                            holder.b1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i1= new Intent(getContext(), AdminViewCakeInside.class);
                                    i1.putExtra("ID",intval);
                                    startActivity(i1);
                                }
                            });
                            holder.b2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    DelteCakes(intval);
                                }
                            });
                            //  holder.txtProductDescription.setText(model.getAdminName());
                            //  holder.txtProductPrice.setText("Price = " + model.getPrice() + "$");
                         Picasso.get().load(model.getImgurl()).into(holder.imageView);

                    }
                    @NonNull
                    @Override
                    public AdminCakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cakes_layout_admin, parent, false);
                        AdminCakeViewHolder holder = new AdminCakeViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        return v;
    }

    public void DelteCakes(String ID){
        DatabaseReference deleteref=FirebaseDatabase.getInstance().getReference().child("Cake").child(ID);
        deleteref.removeValue();
        Toast.makeText(getContext(), "Cake Removed", Toast.LENGTH_SHORT).show();

    }
}

