package com.sh.module_253_json_parsing_in_android;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button loadData;
    ProgressBar progressBar;
    TextView tvName, tvMobile, tvEmail, tvAddress;


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

        loadData = findViewById(R.id.loadData);
        progressBar = findViewById(R.id.progressBar);
        tvName = findViewById(R.id.tvName);
        tvMobile = findViewById(R.id.tvMobile);
        tvEmail = findViewById(R.id.tvEmail);
        tvAddress = findViewById(R.id.tvAddress);


        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(VISIBLE);

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "http://192.168.2.102/Apps/json_parse.json";


                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressBar.setVisibility(GONE);

                                Log.d("Server response",response);


                                // ===========================================================

                                try {

                                    JSONObject jsonObject = new JSONObject(response);



                                    String name = jsonObject.getString("name");
                                    String mobile = jsonObject.getString("mobile");
                                    String email = jsonObject.getString("email");
                                    String address = jsonObject.getString("address");


                                    tvName.setText(name);
                                    tvMobile.setText(mobile);
                                    tvEmail.setText(email);
                                    tvAddress.setText(address);



                                } catch (JSONException e) {

                                    throw new RuntimeException(e);

                                }

                                // ===========================================================


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loadData.setText("Volly Error");
                        progressBar.setVisibility(VISIBLE);

                    }
                });

                queue.add(stringRequest);


            }
        });



    }
}