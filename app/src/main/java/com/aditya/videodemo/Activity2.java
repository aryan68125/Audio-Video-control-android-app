package com.aditya.videodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class Activity2 extends AppCompatActivity {
    MediaPlayer mediaPlayer;  //initializing the media player
    AudioManager audioManager; //it enables us to manage the audio
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE); //setting up the audio manager
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); //it allows us to change the volume during music playback
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); //it will set up the seeker with respect to the current music system volume
        mediaPlayer = MediaPlayer.create(this,R.raw.adrenaline);//setting up the media player
        SeekBar VolumeSeekBar = (SeekBar) findViewById(R.id.VolumeseekBar); //setting up the seekbar
        VolumeSeekBar.setMax(maxVolume); //will set the max volume on volumeSeekBar it is optimize to work on any device
        VolumeSeekBar.setProgress(currentVolume); //will set the current volume on volumeSeekBar
        VolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() //it will listen when the seekbar value changes
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
             audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0); //it will change the volume during music playback
                //the volume will change according to the progress of the seek bar
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar ScrubSeekBar = (SeekBar) findViewById(R.id.ScrubseekBar); //setting up scrub seek bar
        /*
        setting up the maximum value in scrubSeekBar
        the max value should be equal to the length of the song that is being played and our song is in the mediaPlayer and .getDuration() returns the length of the song
         */
        ScrubSeekBar.setMax(mediaPlayer.getDuration());
        ScrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() //it will listen when the seekbar value changes
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mediaPlayer.seekTo(progress); //this will allow the user to scrub through the song
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        /*
        we want our scrubSeekBar to update as our song is being played in real time
        we can use timer it allows us to do something in our app in every second or every minute or every hour
         */
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //here we will define what will happen every second
                ScrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,100); //1000 milliseconds = 1 second

    }
    public void Play(View view)
    {
        mediaPlayer.start();
    }
    public void Pause(View view)
    {
        mediaPlayer.pause();
    }
}