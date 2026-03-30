package com.sh.module_258_0_complex_json_parsing;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @SuppressLint("MissingInflatedId")
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



        textView = findViewById(R.id.textView);


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        String url = "http://192.168.2.100/Apps/module_258_complex_json_parsing.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Log.d("Server response", jsonObject.toString());

                try {

                    String name = jsonObject.getString("name");
                    String age = jsonObject.getString("age");

                    textView.append(name);
                    textView.append("\n");
                    textView.append(age);
                    textView.append("\n\n");

                    //=============(extract video json array)================
                    JSONArray jsonArray = jsonObject.getJSONArray("videos");

                    for (int i = 0; i<jsonArray.length(); i++){

                        JSONObject jsonVideoObject = jsonArray.getJSONObject(i);

                        String title = jsonVideoObject.getString("title");
                        String video_id = jsonVideoObject.getString("video_id");

                        textView.append("Video "+(i+1)+": ");
                        textView.append("title: "+title+"\n");
                        textView.append("Video id: "+video_id+"\n\n");

                    }



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );

        requestQueue.add(jsonObjectRequest);




    }
}