package com.sh.module_130_android_animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button, button1;
    ImageView anime;


    Animation my_anim;



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


        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        anime = findViewById(R.id.anime);

        my_anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_anim);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                animation code
                anime.startAnimation(my_anim);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SecondPage.class);
                startActivity(myIntent);
            }
        });

    }
}