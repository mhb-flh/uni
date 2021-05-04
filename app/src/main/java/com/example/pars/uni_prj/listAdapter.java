package com.example.pars.uni_prj;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.myViewHolder> {

    List<items> items;

    public listAdapter(List<com.example.pars.uni_prj.items> items) {
        this.items = (items==null) ? new ArrayList<items>() : items;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position) {
        myViewHolder.img.setImageResource(items.get(position).getPicture());
        myViewHolder.product.setText(items.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView product;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.irecycler_img);
            product=itemView.findViewById(R.id.product);
        }


    }
}
