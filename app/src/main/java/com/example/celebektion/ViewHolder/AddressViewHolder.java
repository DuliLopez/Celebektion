package com.example.celebektion.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.celebektion.Interfaces.ItemClickListner;
import com.example.celebektion.R;

public class AddressViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public  TextView AddressID;
    public  TextView City;
    public  TextView Province;
    public  Button UpdateButton,Remove1;
    private ItemClickListner listner;


    public AddressViewHolder(View itemView)
    {
        super(itemView);
        AddressID=itemView.findViewById(R.id.AddressID);
        City=itemView.findViewById(R.id.City);
        Province=itemView.findViewById(R.id.Province);
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