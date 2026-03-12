package com.sh.module_229_for_loop_part_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Page2 extends AppCompatActivity {

    EditText starting, ending;
    RadioButton odd, even;
    Button calculate;
    TextView output;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.page2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        starting = findViewById(R.id.starting);
        ending = findViewById(R.id.ending);

        odd = findViewById(R.id.odd);
        even = findViewById(R.id.even);

        calculate = findViewById(R.id.calculate);
        output = findViewById(R.id.output);




        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                output.setText("");

                String startStr = starting.getText().toString().trim();
                String endStr = ending.getText().toString().trim();


                if (!startStr.isEmpty() && !endStr.isEmpty() && (odd.isChecked() || even.isChecked())) {

                    int start = Integer.parseInt(startStr);
                    int end = Integer.parseInt(endStr);


                    if (start < end && odd.isChecked()){
                        for (int i = start; i<=end ; i++){
                            if (i%2 != 0){
                                output.append(i+" ");
                            }
                        }
                    } else if (start < end && even.isChecked()) {
                        for (int i = start; i<=end ; i++){
                            if (i%2 == 0){
                                output.append(i+" ");
                            }
                        }
                    }

                } else {
                    Toast.makeText(Page2.this, "Start & Stop Empty", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
}