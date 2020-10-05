package com.example.celebektion.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.celebektioncakes.Interfaces.ItemClickListner;
import com.example.celebektioncakes.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public  TextView Product;
    public  TextView Price;
    public  TextView Quantity;
    public  TextView Total;
    public  Button UpdateButton,Remove1;
    private ItemClickListner listner;


    public CartViewHolder(View itemView)
    {
        super(itemView);
        Product=itemView.findViewById(R.id.AddressID);
        Price=itemView.findViewById(R.id.City);
        Quantity=itemView.findViewById(R.id.Province);
        Total=itemView.findViewById(R.id.Total);
        Remove1=itemView.findViewById(R.id.RemoveButton);
        UpdateButton=itemView.findViewById(R.id.UpdateButton);


    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}