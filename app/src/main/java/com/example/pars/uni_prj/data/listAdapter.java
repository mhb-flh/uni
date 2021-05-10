package com.example.pars.uni_prj.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pars.uni_prj.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.myViewHolder> {

    private List<items> items;
    Context context;

    public listAdapter(List<items> items, Context context) {
        this.items = (items == null) ? new ArrayList<>() : items;
        this.context = context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new myViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position) {
        myViewHolder.Bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView title;
        public TextView price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.irecycler_img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);

        }

        public void Bind(items items) {
            Picasso.get().load(items.getImage()).into(img);
            title.setText(items.getTitle());
            price.setText(items.getPrice());
        }

    }
}
