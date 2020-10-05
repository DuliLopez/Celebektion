package com.example.celebektion.ViewHolder;
import com.example.celebektioncakes.Interfaces.ItemClickListner;
import com.example.celebektioncakes.R;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class CakeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;
    public Button b1;


    public CakeViewHolder(View itemView)
    {
        super(itemView);

        b1=(Button) itemView.findViewById(R.id.ViewButton);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name);
        imageView = (ImageView) itemView.findViewById(R.id.product_image);
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