package com.sh.module_243_complete_news_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Page2 extends AppCompatActivity {

    ImageView imageView;
    TextView title, des;
    FloatingActionButton fabButton;



    public static String Title = "";
    public static String Description = "";
    public static Bitmap Mybitmap = null;

    TextToSpeech textToSpeech;



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


        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        des = findViewById(R.id.des);
        fabButton = findViewById(R.id.fabButton);


        title.setText(Title);
        des.setText(Description);

        if (Mybitmap != null){
            imageView.setImageBitmap(Mybitmap);
        }

        textToSpeech = new TextToSpeech(Page2.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = des.getText().toString();

                if (textToSpeech != null && textToSpeech.isSpeaking()) {

                    textToSpeech.stop();
                }
                else{
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });




    }
}