package com.example.module_203_user_input;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button submit;
    EditText name, number, password;
    TextView tvDisplay;

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


        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        password = findViewById(R.id.password);

        tvDisplay = findViewById(R.id.tvDisplay);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = "Name: "+ name.getText().toString()+"\n"+"Number: "+ number.getText().toString()+"\n"+"Password: "+password.getText().toString();
                tvDisplay.setText(temp);
            }
        });




    }
}