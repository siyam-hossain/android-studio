package com.example.module_143_inline_collapsible_banner_ads;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {


    FrameLayout ad_view_container;
    Button inlineBanner;

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


        inlineBanner = findViewById(R.id.inlineBanner);

        initialize();
        loadBanner();

        inlineBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inline = new Intent(MainActivity.this, Inline_Adaptive_Banner.class);
                startActivity(inline);
            }
        });



    }


    public void initialize(){
        new Thread(
                () -> {
                    // Initialize the Google Mobile Ads SDK on a background thread.
                    MobileAds.initialize(this, initializationStatus -> {});
                })
                .start();
    }
    public AdSize getAdSize(){

//        Display display = getWindowManager().getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        display.getMetrics(outMetrics);
//
//        float widthPixels = outMetrics.widthPixels;
//        float density = outMetrics.density;
//
//        int adWidth = (int) (widthPixels/density);
//
//        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth);



//        adView.setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, 360));
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, 360);

    }
    public void loadBanner(){

        ad_view_container = findViewById(R.id.ad_view_container);

        AdView adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ads));
        adView.setAdSize(getAdSize());

        ad_view_container.removeAllViews();
        ad_view_container.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}