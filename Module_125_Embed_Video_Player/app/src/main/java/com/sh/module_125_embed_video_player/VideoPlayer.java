package com.sh.module_125_embed_video_player;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoPlayer extends AppCompatActivity {

    WebView webView;
    public static String videoUrl = "";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.video_player);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(VideoPlayer.this, "Welcome to Video Player",Toast.LENGTH_SHORT).show();

//        videoUrl = "https://www.youtube.com/embed/6T9HV0a5Wmk";

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(videoUrl);

//        webView.loadUrl("https://www.youtube.com/embed/4CP2-om4Y6Q");
//        webView.loadUrl("https://www.youtube.com/embed/4CP2-om4Y6Q?si=A2PzxtUs2ThofisU");




    }
}