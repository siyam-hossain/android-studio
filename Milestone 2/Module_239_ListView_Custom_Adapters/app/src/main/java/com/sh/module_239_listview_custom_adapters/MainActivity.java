package com.sh.module_239_listview_custom_adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        listview = findViewById(R.id.listview);


        MyAdapter myAdapter = new MyAdapter();

        listview.setAdapter(myAdapter);

    }


    private class MyAdapter extends BaseAdapter{

        LayoutInflater layoutInflater;



        @Override
        public int getCount() {

            //how many times you want to inflate: calling getView method 5 times
            return 10;
        }


//==========================================no need for learn at this time==========================================
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
//==========================================+++++++++++++++++++++++++++++++==========================================

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder")
            View myview = layoutInflater.inflate(R.layout.list_item, viewGroup, false);

            ImageView imageView = myview.findViewById(R.id.imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Hey There, item number is: "+(position+1), Toast.LENGTH_SHORT).show();
                }
            });

            return myview;
        }
    }




}