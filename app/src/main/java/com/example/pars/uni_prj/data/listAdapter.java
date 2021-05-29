package com.example.pars.uni_prj.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pars.uni_prj.R;
import com.example.pars.uni_prj.main.buy;
import com.example.pars.uni_prj.main.firstPage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.myViewHolder> implements Filterable {


    private static ClickListener clickListener;
    private final List<Items> items;
    private Context context;

    public listAdapter(List<Items> Items, Context context) {
        this.items = (Items == null) ? new ArrayList<>() : Items;
        this.context = context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);

        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position) {
        myViewHolder.Bind(items.get(position));

        myViewHolder.itemView.setOnClickListener(view -> {

            //pass data to another activity
            int id = items.get(position).getId(); // get Id
            String img = items.get(position).getImage();
            String imageName = items.get(position).getTitle();
            String price = items.get(position).getPrice();
            Intent intent = new Intent(context, buy.class);
            intent.putExtra("pos", id); // Pass Id
            intent.putExtra("img", img);
            intent.putExtra("imageName", imageName);
            intent.putExtra("price", price);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);


        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }


    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Items> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(items);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Items item : items) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            items.clear();
            items.addAll((Collection<? extends Items>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView img;
        public TextView title;
        public TextView price;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            img = itemView.findViewById(R.id.recycler_img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);

        }

        public void Bind(Items items) {
            //PicassoClient.downloadImage(context, items.getImage(), img);
            Picasso.get().load(items.getImage()).into(img);
            title.setText(items.getTitle());
            price.setText(items.getPrice());
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);

        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        listAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }


}
