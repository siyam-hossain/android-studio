package com.sh.module_126_video_player_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {

    ImageView canvasImg;
    YouTubePlayerView yt_player_view;
    YouTubePlayer yt_player;

    LinearLayout album1, album2, album3, album4, album5;

    String videoId;


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

        canvasImg = findViewById(R.id.canvasImg);

        yt_player_view = findViewById(R.id.yt_player_view);
        getLifecycle().addObserver(yt_player_view);

        album1 = findViewById(R.id.album1);
        album2 = findViewById(R.id.album2);
        album3 = findViewById(R.id.album3);
        album4 = findViewById(R.id.album4);
        album5 = findViewById(R.id.album5);

        yt_player_view.setVisibility(View.GONE);



        yt_player_view.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                yt_player = youTubePlayer;


            }
        });


        album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yt_player != null){
                    canvasImg.setVisibility(View.GONE);
                    yt_player_view.setVisibility(View.VISIBLE);
                    videoId = "ricmaGhkUBs";
                    yt_player.cueVideo(videoId,0);
                }
            }
        });
        album2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yt_player != null){
                    canvasImg.setVisibility(View.GONE);
                    yt_player_view.setVisibility(View.VISIBLE);
                    videoId = "6ttobrfMnyQ";
                    yt_player.cueVideo(videoId,0);
                }
            }
        });
        album3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yt_player != null){
                    canvasImg.setVisibility(View.GONE);
                    yt_player_view.setVisibility(View.VISIBLE);
                    videoId = "Od-6uzcLGqw";
                    yt_player.cueVideo(videoId,0);
                }
            }
        });
        album4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yt_player != null){
                    canvasImg.setVisibility(View.GONE);
                    yt_player_view.setVisibility(View.VISIBLE);
                    videoId = "i7wveOu5hkQ";
                    yt_player.cueVideo(videoId,0);
                }
            }
        });
        album5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yt_player != null){
                    canvasImg.setVisibility(View.GONE);
                    yt_player_view.setVisibility(View.VISIBLE);
                    videoId = "k4n4nVpRacU";
                    yt_player.cueVideo(videoId,0);
                }
            }
        });




    }
}