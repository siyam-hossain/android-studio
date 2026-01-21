package com.sh.module_213_show_error_on_input_field;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnBMI;
    TextView tvResult;
    EditText edFeet, edInch, edWeight;



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

        btnBMI = findViewById(R.id.btnBMI);
        tvResult = findViewById(R.id.tvResult);
        edFeet = findViewById(R.id.edFeet);
        edInch = findViewById(R.id.edInch);
        edWeight = findViewById(R.id.edWeight);



        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edWeight.getText().toString().isEmpty() && !edFeet.getText().toString().isEmpty() && !edInch.getText().toString().isEmpty()){

                    Double weight = Double.parseDouble(edWeight.getText().toString());
                    Double feet = Double.parseDouble(edFeet.getText().toString());
                    Double inch = Double.parseDouble(edInch.getText().toString());

                    Double height = feet*0.3048 + inch*0.0254;

                    Double result = weight / Math.pow(height,2);

                    tvResult.setText(result.toString());

                }
                else{
                    if (edWeight.getText().toString().isEmpty()){
                        edWeight.setError("Please enter weight");
                    }
                    if (edFeet.getText().toString().isEmpty()){
                        edFeet.setError("Please enter height in feet");
                    }
                    if (edInch.getText().toString().isEmpty()){
                        edInch.setError("Please enter height in inch");
                    }
                    Toast.makeText(MainActivity.this, "All input field requires value", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}