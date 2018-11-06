package com.example.drewk.audioeq;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, AudioService.class);
        startService(intent);

        SeekBar seekbar1 = findViewById(R.id.seekBar1);
        seekbar1.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            }
        });
        SeekBar seekbar2 = findViewById(R.id.seekBar2);
        seekbar2.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            }
        });
        SeekBar seekbar3 = findViewById(R.id.seekBar3);
        seekbar3.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            }
        });
        SeekBar seekbar4 = findViewById(R.id.seekBar4);
        seekbar4.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            }
        });
        SeekBar seekbar5 = findViewById(R.id.seekBar5);
        seekbar5.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Toast.makeText(MainActivity.this, Integer.toString(progress), Toast.LENGTH_SHORT).show();
            }
        });
        Button addProfile = findViewById(R.id.addProfile);
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Profile Added", Toast.LENGTH_SHORT).show();
            }
            });
        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.song);
        mp.start();

        Equalizer eq = new Equalizer(0, mp.getAudioSessionId());
        eq.setEnabled(true);
        eq.usePreset((short) 4);
        eq.getDescriptor();
        String preset = eq.getPresetName((short) 4);
        Toast.makeText(this, preset, Toast.LENGTH_SHORT).show();
        eq.getBand(3300);

        Log.e("eq", String.valueOf(eq.getBandLevelRange().length));

        Toast.makeText(this, String.valueOf(eq.getBandLevelRange().length), Toast.LENGTH_SHORT).show();


    }

//    final MediaPlayer player = new MediaPlayer.create(this, )
}
