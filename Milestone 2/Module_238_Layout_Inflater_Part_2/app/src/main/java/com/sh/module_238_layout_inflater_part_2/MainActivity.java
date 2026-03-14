package com.sh.module_238_layout_inflater_part_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    LinearLayout inflate_layout;
    Button add, remove;

    LayoutInflater layoutInflater;
    ImageView imageView;



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

        inflate_layout = findViewById(R.id.inflate_layout);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);

//        wrong process: image view crash
//        imageView = findViewById(R.id.imageView);


        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View myView = layoutInflater.inflate(R.layout.inflate_it, main);

        imageView = findViewById(R.id.imageView);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutInflater.inflate(R.layout.inflate_it, inflate_layout);


            }
        });


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflate_layout.removeAllViews();
            }
        });



    }
}