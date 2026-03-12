package com.sh.module_229_for_loop_part_1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    LinearLayout myLayout;
    Button nextButton;

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

        myLayout = findViewById(R.id.myLayout);
        myLayout.setPadding(80, 50, 80, 50);


        for (int i=1; i <= 20; i++){
            Button button = new Button(MainActivity.this);

            button.setText("Button: "+i);


            myLayout.addView(button);
        }

        Button mybutton = new Button(MainActivity.this);
        mybutton.setText("Next Page");
        mybutton.setBackgroundColor(Color.BLUE);
        mybutton.setTextColor(Color.WHITE);
        mybutton.setId(View.generateViewId());

        myLayout.addView(mybutton);


        nextButton = findViewById(mybutton.getId());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Page2.class);
                startActivity(intent);
            }
        });




    }
}