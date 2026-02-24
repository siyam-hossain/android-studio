package com.sh.module_225_custom_pdf_viewer;

import static android.view.View.GONE;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

public class MainActivity extends AppCompatActivity {

    PDFView pdfView;
    LottieAnimationView animationView;

    public static String assetName = "";

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


        pdfView = findViewById(R.id.pdfView);
        animationView = findViewById(R.id.animationView);


        pdfView.setVisibility(View.INVISIBLE);
        animationView.setVisibility(View.VISIBLE);



        pdfView.fromAsset(assetName)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int i) {

//                        adding custom delay
                        pdfView.postDelayed(()->{


                            pdfView.setVisibility(View.VISIBLE);
                            animationView.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this, "Loaded", Toast.LENGTH_SHORT).show();


                        },2000);

                    }
                })
                .load();


    }
}