package com.sh.module_258_1_complex_json_parsing;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    TextView pdName, price, rating;
    ImageView imageView;

    public static int numberOfCard;


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


        gridView = findViewById(R.id.gridView);

        MyAdapter myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);








    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return numberOfCard;
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

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.item_card, viewGroup, false);


            TextView pdName, price, rating;
            ImageView imageView;


            pdName = myView.findViewById(R.id.pdName);
            price = myView.findViewById(R.id.price);
            rating = myView.findViewById(R.id.rating);

            imageView = myView.findViewById(R.id.imageView);



            //============================(volley)====================================
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            String url = "https://dummyjson.com/products";


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {

                        JSONArray products = response.getJSONArray("products");

                        numberOfCard = products.length();

                        for (int i=0; i<numberOfCard; i++){

                            JSONObject jsonObject = products.getJSONObject(i);

                            String title = jsonObject.getString("title");
                            String price_ = jsonObject.getString("price");
                            String thumbUrl = jsonObject.getString("thumbnail");
                            String rating_ = jsonObject.getString("rating");


                            pdName.setText(title);
                            price.setText(price_);
                            rating.setText(rating_);
                        }


                    } catch (JSONException e) {

                        throw new RuntimeException(e);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });

            queue.add(jsonObjectRequest);
            //============================================================================



            return myView;
        }
    }

}