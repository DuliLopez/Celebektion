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

import com.example.celebektion.ClientSide.ViewCakeDetails;
import com.example.celebektion.HomeActivity;
import com.example.celebektion.Models.Cake;
import com.example.celebektion.Models.Cart;
import com.example.celebektion.R;
import com.example.celebektion.Sessions.SessionManagement;
import com.example.celebektion.ViewHolder.CakeViewHolder;
import com.example.celebektion.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewCart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewCart extends Fragment {
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private String SGrandTotal;
    private int GrandTotal=0;
    private TextView Gtotal;
    Button Chgeckout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewCart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewCart.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewCart newInstance(String param1, String param2) {
        ViewCart fragment = new ViewCart();
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
        View v = inflater.inflate(R.layout.fragment_view_cart, container, false);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Cart");
        recyclerView = v.findViewById(R.id.Recview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        Chgeckout=v.findViewById(R.id.Checkout);
        Chgeckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "You Have Sucessfully Placed the Order", Toast.LENGTH_SHORT).show();
                OrderComplete();
            }
        });
        Gtotal=v.findViewById(R.id.GrandTot);
        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(ProductsRef, Cart.class)
                        .build();
        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter =
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {
                        holder.Product.setText(model.getCakeName());
                        holder.Price.setText(model.getPrice());
                        holder.Quantity.setText(model.getQty());
                        String price=model.getPrice();
                        int pri=Integer.parseInt(price);
                        String Qty=model.getQty();
                        int qt=Integer.parseInt(Qty);
                        int total=pri*qt;
                        price=Integer.toString(total);
                        holder.Total.setText(price);
                        GrandTotal=GrandTotal+total;
                        SGrandTotal=Integer.toString(GrandTotal);
                        Gtotal.setText(SGrandTotal);
                        final int finalQt1 = total;
                        final String CakeName1=model.getCakeID();
                        final String CakID=model.getCakeName();
                        holder.Remove1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    RemoveCartItem(CakID, GrandTotal-finalQt1);
                            }
                        });
                        holder.UpdateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UpdateItems(CakeName1);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                        CartViewHolder holder = new CartViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
        return v;

    }
    public void RemoveCartItem(String CakeID,int tot){
        String total;
        total=Integer.toString(tot);
        SessionManagement s1=new SessionManagement(getContext());
        DatabaseReference delteref= FirebaseDatabase.getInstance().getReference().child("Cart").child(CakeID);
        DatabaseReference delteref1= FirebaseDatabase.getInstance().getReference().child("Order").child(s1.getSessionUsername()).child(CakeID);
        delteref.removeValue();
        delteref1.removeValue();

        Toast.makeText(getContext(), "Item Removed", Toast.LENGTH_SHORT).show();
        Gtotal.setText(total);
        GrandTotal=tot;
    }
    public void UpdateItems(String ID){
        Intent i1= new Intent (getContext(),ViewCakeDetails.class);
        i1.putExtra("ID",ID);
        startActivity(i1);
    }
    public void OrderComplete(){
        String total;
        SessionManagement s1=new SessionManagement(getContext());
        DatabaseReference delteref= FirebaseDatabase.getInstance().getReference().child("Cart");
        delteref.removeValue();
        Intent i1= new Intent(getContext(), HomeActivity.class);
        startActivity(i1);
        Gtotal.setText("0");
    }
}