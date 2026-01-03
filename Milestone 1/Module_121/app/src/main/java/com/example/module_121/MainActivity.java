package com.example.module_121;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button buttonAdd;
    Button buttonSub;
    Button buttonReset;

    String currentVal;


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


        tvResult = findViewById(R.id.tvResult);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSub);
        buttonReset = findViewById(R.id.buttonReset);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res;
                currentVal = tvResult.getText().toString().trim();
                res = Integer.parseInt(currentVal);
                res++;

                currentVal = String.valueOf(res);

                tvResult.setText(currentVal);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int res;
                currentVal = tvResult.getText().toString().trim();
                res = Integer.parseInt(currentVal);
                res--;

                if (res < 0){

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Warning")
                            .setMessage("You reach the minimum value.")
                            .setPositiveButton("OK", (dialog, which) -> {
                                dialog.dismiss();
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    tvResult.setText("0");
                }
                else{
                    currentVal = String.valueOf(res);
                    tvResult.setText(currentVal);

                }
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResult.setText("0");
            }
        });

    }
}