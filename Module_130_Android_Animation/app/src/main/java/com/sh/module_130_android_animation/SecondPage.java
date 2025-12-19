package com.sh.module_130_android_animation;

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

public class SecondPage extends AppCompatActivity {

    Button button;
    ImageView anime;


    Animation my_anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.second_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        anime = findViewById(R.id.anime);

//        my_anim = AnimationUtils.loadAnimation(SecondPage.this, R.anim.fade_in);
//        my_anim = AnimationUtils.loadAnimation(SecondPage.this, R.anim.left_to_right);
        my_anim = AnimationUtils.loadAnimation(SecondPage.this, R.anim.zoom_in);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anime.startAnimation(my_anim);
            }
        });

    }
}