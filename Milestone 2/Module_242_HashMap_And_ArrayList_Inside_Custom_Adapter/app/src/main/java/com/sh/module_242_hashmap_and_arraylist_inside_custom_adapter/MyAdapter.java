package com.sh.module_242_hashmap_and_arraylist_inside_custom_adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    ArrayList<HashMap<String,Object>> arrayList = new ArrayList<>();
    HashMap<String, Object> hashMap;

    TextView tvName, tvMobile;
    ImageView imageView;




//    constructor
    public MyAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);




        hashMap = new HashMap<>();
        hashMap.put("name", "Hasan");
        hashMap.put("mobile","019XXXXXXXXXXX");
        hashMap.put("img",R.drawable.anime);


        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Alice");
        hashMap.put("mobile","018XXXXXXXXXXXX");
        hashMap.put("img",R.drawable.black);

        arrayList.add(hashMap);

    }


    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {




        @SuppressLint("ViewHolder")
        View myView = layoutInflater.inflate(R.layout.list_item, viewGroup, false);

        tvName = myView.findViewById(R.id.tvName);
        tvMobile = myView.findViewById(R.id.tvMobile);
        imageView = myView.findViewById(R.id.imageView);


        HashMap<String, Object> hash = arrayList.get(position);
        String name = hash.get("name").toString();
        String mobile = hash.get("mobile").toString();
        int img = (int) hash.get("img");

        tvName.setText(name);
        tvMobile.setText(mobile);
        imageView.setImageResource(img);


        return myView;
    }
}
