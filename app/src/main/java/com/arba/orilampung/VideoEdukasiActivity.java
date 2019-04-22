package com.arba.orilampung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class VideoEdukasiActivity extends YouTubeBaseActivity {

    YouTubePlayerView mYoutubePlayerView;
    ImageView btnPlay, btnHome;
    YouTubePlayer.OnInitializedListener mOnIntializedListener;
    TextView tv;

    String Key, judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_edukasi);
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        mYoutubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeplay);
        tv = (TextView) findViewById(R.id.tv1);

        Key = getIntent().getExtras().get("URL").toString();
        judul = getIntent().getExtras().get("judul").toString();
        tv.setText(judul);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        mOnIntializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(Key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYoutubePlayerView.initialize(YoutubeConfig.getApiKey(), mOnIntializedListener);
            }
        });
    }
}
