package com.sh.module_135_animation_with_lottie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button upload, changeLottie;
    LottieAnimationView animationView;


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

        imageView = findViewById(R.id.imageView);
        upload = findViewById(R.id.upload);
        changeLottie = findViewById(R.id.changeLottie);
        animationView = findViewById(R.id.animationView);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(MainActivity.this)
                        .load("https://images.pexels.com/photos/1704488/pexels-photo-1704488.jpeg?_gl=1*117l42c*_ga*MzczNDY1NDgyLjE3NjYyMDg3NTE.*_ga_8JE65Q40S6*czE3NjYyMDg3NTEkbzEkZzEkdDE3NjYyMDg3NTMkajU4JGwwJGgw")
                        .error(R.drawable.f1)
                        .centerCrop()
                        .placeholder(R.drawable.border)
                        .into(imageView)
                ;
            }
        });

        changeLottie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.setAnimation(R.raw.loading2);
                animationView.playAnimation();
//                animationView.setRepeatCount(LottieDrawable.INFINITE);
                animationView.setRepeatCount(5);
            }
        });

    }
}