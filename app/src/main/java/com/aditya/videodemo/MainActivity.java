package com.aditya.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
        //setting up the path of the video that we want to use with our videoView

         /*
             android.resource will look for video resources inside the android resource area
             getPackageName() will allow us to access our resource area i.e resources for this particular application
             so we will add our package name to our video path { "/" + R.raw.sample_i } it is the video name
        */
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample_i);

        /*
             MediaController allows us to control the playback of video that are being played in the app
        */
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView); //anchors the media controller to the videoView
        videoView.setMediaController(mediaController); //this will allow MediaController to control the video that is played

        videoView.start();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }
}