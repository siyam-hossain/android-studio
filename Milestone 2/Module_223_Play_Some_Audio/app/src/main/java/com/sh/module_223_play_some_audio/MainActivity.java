package com.sh.module_223_play_some_audio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;

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


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when we use local file we use media player . create
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.majbor);
                mediaPlayer.start();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaPlayer = new MediaPlayer();

                try {
                    mediaPlayer.setDataSource("https://file-examples.com/storage/fe68d2756669781429b3e3f/2017/11/file_example_MP3_700KB.mp3");

                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
}