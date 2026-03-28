package com.sh.module_255_jsonarray_request_in_android;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    //============(youtube)======================
    YouTubePlayerView youtube_player_view;
    YouTubePlayer youTubePlayer;
    boolean isPlayerReady = false;



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


        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);

        //=============(youtube)=============================
        youtube_player_view = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youtube_player_view);

        youtube_player_view.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer player) {
                super.onReady(player);


                MainActivity.this.youTubePlayer = player;
                isPlayerReady = true;
            }
        });
        // =======================================================


        progressBar.setVisibility(VISIBLE);
        // ======================(json array request instead of string request)============

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://192.168.2.100/Apps/module_254_json_array.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {


                try {

                    progressBar.setVisibility(GONE);


                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        String video_id = jsonObject.getString("video_id");
                        String thumbnail = jsonObject.getString("thumbnail");
                        String artist_name = jsonObject.getString("artist_name");
                        String views_count = jsonObject.getString("views_count");

                        hashMap = new HashMap<>();
                        hashMap.put("title",title);
                        hashMap.put("video_id",video_id);
                        hashMap.put("artist_name",artist_name);
                        hashMap.put("views_count",views_count);
                        hashMap.put("thumbnail",thumbnail);

                        //============(vital point)=============================
                        arrayList.add(hashMap);

                    }

                    //===========(setting adapter in listview)=====================
                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);


                } catch (JSONException e) {

                    throw new RuntimeException(e);


                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(VISIBLE);
            }


        }
        );

        requestQueue.add(arrayRequest);


    }

    //================================(Adapter for layout inflater)===================================
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

            LayoutInflater layoutInflater = getLayoutInflater();
            @SuppressLint("ViewHolder")
            View myView = layoutInflater.inflate(R.layout.list_item, viewGroup, false);

            TextView title, artistName, viewsCount;


            title = myView.findViewById(R.id.title);
            artistName = myView.findViewById(R.id.artistName);
            viewsCount = myView.findViewById(R.id.viewsCount);

            ImageView imageView = myView.findViewById(R.id.imageView);
            LinearLayout layItem = myView.findViewById(R.id.layItem);


            //========(Extract data from arraylist with help of hashmap)===================
            HashMap<String, String> hash = arrayList.get(position);

            String TITLE = hash.get("title");
            String VIDEO_ID = hash.get("video_id");
            String ARTIST_NAME = hash.get("artist_name");
            String VIEWS_COUNT = hash.get("views_count");
            String IMAGE_URL = hash.get("thumbnail");

            //=================(set the data onto the xml)====================
            title.setText(TITLE);
            artistName.setText(ARTIST_NAME);
            viewsCount.setText(VIEWS_COUNT);

            //====================(Picaso)========================
            Picasso.get().load(IMAGE_URL)
                    .placeholder(null)
                    .into(imageView);


            //==============(click event on every item list)=================
            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isPlayerReady &&  youTubePlayer != null){
                        youTubePlayer.loadVideo(VIDEO_ID, 0f);
                    }else {
                        Toast.makeText(MainActivity.this,"Player is not ready yet", Toast.LENGTH_SHORT).show();
                    }

                }
            });



            return myView;
        }
    }
}

