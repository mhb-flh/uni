package com.example.pars.uni_prj.http;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pars.uni_prj.data.Items;
import com.example.pars.uni_prj.data.listAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataParser extends AsyncTask<Void, Void, Integer> {

    private Context c;
    private String jsonData;
    private RecyclerView recyclerView;

    ProgressDialog pd;
    ArrayList<Items> items = new ArrayList<>();

    public DataParser(Context c, String jsonData, RecyclerView recyclerView) {
        this.c = c;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//
//        pd=new ProgressDialog(c);
//        pd.setTitle("Parse");
//        pd.setMessage("Parsing...Please wait");
//        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

//        pd.dismiss();

        if (result == 0) {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();
        } else {
            //BIND DATA TO RECYCLER
            listAdapter adapter = new listAdapter(items, c);
            recyclerView.setAdapter(adapter);
        }
    }

    private int parseData() {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;

            this.items.clear();
            Items item;

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);

                int id = jo.getInt("id");
                String name = jo.getString("imageName");
                String image = jo.getString("image");
                String price = jo.getString("price");

                item = new Items();

                item.setId(id);
                item.setTitle(name);
                item.setImage(image);
                item.setPrice(price);

                items.add(item);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}