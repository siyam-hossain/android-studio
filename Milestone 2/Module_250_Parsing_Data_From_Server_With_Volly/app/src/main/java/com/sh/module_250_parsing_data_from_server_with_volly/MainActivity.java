package com.sh.module_250_parsing_data_from_server_with_volly;

import static android.view.View.GONE;

import android.os.Bundle;
import android.text.Html;
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

public class MainActivity extends AppCompatActivity {


    TextView textView;
    ProgressBar progressBar;


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
        progressBar = findViewById(R.id.progressBar);






        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.2.104/Apps/data.php";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // response = response.replace("<br>","\n");

                       // textView.setText("Response is: " + response);

                        textView.setText(Html.fromHtml(response, Html.FROM_HTML_MODE_LEGACY));
                        progressBar.setVisibility(GONE);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
                progressBar.setVisibility(GONE);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
}