package com.sh.module_243_complete_news_app;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();



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

        listView = findViewById(R.id.listView);


        createTable();




        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);


    }


//    ================================================================
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder")
            View myView = inflater.inflate(R.layout.list_item, viewGroup, false);

            ImageView imageView = myView.findViewById(R.id.imageView);
            TextView cat = myView.findViewById(R.id.cat);
            TextView title = myView.findViewById(R.id.title);
            TextView des = myView.findViewById(R.id.des);

            LinearLayout layItem = myView.findViewById(R.id.layItem);




            HashMap<String, String> hash = arrayList.get(position);

            String category = hash.get("cat");
            String imageURL = hash.get("image_url");
            String title_ = hash.get("title");
            String description = hash.get("des");


            Picasso.get().load(imageURL)
                    .placeholder(R.drawable.mask)
                    .into(imageView);


            cat.setText(category);
            title.setText(title_);
            des.setText(description);



            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            cat.setBackgroundColor(color);


            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Page2.Title = title_;
                    Page2.Description = description;


                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    Page2.Mybitmap = bitmap;

                    startActivity(new Intent(MainActivity.this, Page2.class));


                }
            });






            return myView;
        }
    }

    //    ================================================================

    private void createTable(){
        hashMap = new HashMap<>();
        hashMap.put("cat", "Tech");
        hashMap.put("image_url", "https://ichef.bbci.co.uk/news/1024/cpsprodpb/25a6/live/9833eb40-1efb-11f1-9120-a910fc22c6ac.png.webp");
        hashMap.put("title", "TikTok and Meta risked safety to win algorithm arms race, whistleblowers say");
        hashMap.put("des", "Social media giants made decisions which allowed more harmful content on people's feeds, after internal research into their algorithms showed how outrage fuelled engagement, whistleblowers told the BBC. More than a dozen whistleblowers and insiders have laid bare how the companies took risks with safety on issues including violence, sexual blackmail and terrorism as they battled for users' attention. An engineer at Meta, which owns Facebook and Instagram, described how he had been told by senior management to allow more \"borderline\" harmful content - which includes misogyny and conspiracy theories - in user's feeds to compete with TikTok. \"They sort of told us that it's because the stock price is down,\" the engineer said. A TikTok employee gave the BBC rare access to the company's internal dashboards of user complaints - as well as other evidence of how staff had been instructed to priorities several cases involving politicians over a series of reports of harmful posts featuring children. Decisions were being made to \"maintain a strong relationship\" with political figures to avoid threats of regulation or bans, not because of the risks to users, the TikTok staffer said. The whistleblowers who spoke to the BBC documentary, Inside the Rage Machine, offer a close-up view of how the industry responded following the explosive growth of TikTok, whose highly engaging algorithm for recommending short videos upended social media, leaving rivals scrambling to catch up. A senior Meta researcher, Matt Motyl, said the company's competitor to TikTok, Instagram Reels, was launched in 2020 without sufficient safeguards. Internal research shared with the BBC showed comments on Reels had significantly higher prevalence of bullying and harassment, hate speech, and violence or incitement than elsewhere on Instagram.");
        arrayList.add(hashMap);
    }









}