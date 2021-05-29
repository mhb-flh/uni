package com.example.pars.uni_prj.data;

import android.content.Context;
import android.widget.ImageView;

import com.example.pars.uni_prj.R;
import com.squareup.picasso.Picasso;

class PicassoClient {

    public static void downloadImage(Context c,String imageUrl,ImageView img)
    {
        Items items = new Items();
        if(imageUrl.length() > 0)
            Picasso.get().load(items.getImage()).into(img);
        else Picasso.get().load(R.drawable.ic_diamond).into(img);
    }

}
