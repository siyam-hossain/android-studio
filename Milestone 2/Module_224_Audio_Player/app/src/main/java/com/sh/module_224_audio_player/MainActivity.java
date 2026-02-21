package com.sh.module_224_audio_player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imgPlay1;
    MediaPlayer mediaPlayer;


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

        imgPlay1 = findViewById(R.id.imgPlay1);


        imgPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imgPlay1.getTag() != null && imgPlay1.getTag().toString().contains("NOT_PLAYING")){

                    if (mediaPlayer != null){
                        mediaPlayer.release();
                    }

                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ya_adameen);
                    mediaPlayer.start();

                    imgPlay1.setImageResource(R.drawable.outline_pause_circle_24);
                    imgPlay1.setTag("PLAYING");



                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            imgPlay1.setImageResource(R.drawable.outline_play_circle_24);
                            imgPlay1.setTag("NOT_PLAYING");
                        }
                    });

                }
                else {
                    if (mediaPlayer != null){
                        mediaPlayer.release();
                        imgPlay1.setImageResource(R.drawable.outline_play_circle_24);
                        imgPlay1.setTag("NOT_PLAYING");
                    }
                }
            }
        });

    }
}