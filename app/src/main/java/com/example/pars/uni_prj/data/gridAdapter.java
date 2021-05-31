package com.example.pars.uni_prj.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pars.uni_prj.R;


public class gridAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private String[] numbersInWords;
    private int[] numberImage;
    private ImageView imageView;
    private TextView textView;

    public gridAdapter(Context c, String[] numbersInWords,int[] numberImage){
        context = c;
        this.numberImage = numberImage;
        this.numbersInWords = numbersInWords;
    }
    @Override
    public int getCount() {
        return numbersInWords.length;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater==null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.grid_view, null);
        }
        imageView = convertView.findViewById(R.id.imageView);
        textView = convertView.findViewById(R.id.textView);
        imageView.setImageResource(numberImage[position]);
        textView.setText(numbersInWords[position]);
        return convertView;
    }
}
