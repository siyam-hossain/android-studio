package com.example.module_143_inline_collapsible_banner_ads;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowMetrics;
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

public class Inline_Adaptive_Banner extends AppCompatActivity {

    FrameLayout ad_view_container;

    Button collapsable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.inline_adaptive_banner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        collapsable = findViewById(R.id.collapsable);

        initialize();
        loadBanner();

        collapsable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inline = new Intent(Inline_Adaptive_Banner.this, Collapsable_Banner.class);
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
    public int getAdWidth() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int adWidthPixels = displayMetrics.widthPixels;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = this.getWindowManager().getCurrentWindowMetrics();
            adWidthPixels = windowMetrics.getBounds().width();
        }

        float density = displayMetrics.density;
        return (int) (adWidthPixels / density);
    }
    public void loadBanner(){

        ad_view_container = findViewById(R.id.ad_view_container);

        AdView adView = new AdView(this);
        adView.setAdUnitId(getString(R.string.banner_ads));
        adView.setAdSize(AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(this, getAdWidth()));

        ad_view_container.removeAllViews();
        ad_view_container.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

}