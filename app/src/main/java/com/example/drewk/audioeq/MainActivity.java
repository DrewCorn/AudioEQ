package com.example.drewk.audioeq;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, AudioService.class);
        startService(intent);

        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.song);
//        MediaPlayer mp = new MediaPlayer();
//        try {
//            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "\\Music\\ThatsLife");
//        } catch (IOException e) {
//            System.out.println("yeet");
//        }

        Equalizer eq = new Equalizer(0, mp.getAudioSessionId());
        eq.setEnabled(true);

        mp.start();
        short minFreq = eq.getBandLevelRange()[0];
        short maxFreq = eq.getBandLevelRange()[1];

//        public void addListenerOnSpinnerItemSelection() {
//            Spinner dropdown = findViewById(R.id.spinner1);
//            dropdown.setOnItemSelectedListener(new DropdownOnItemSelectedListener());
//
//        }



        /**
         * making a bunch of seekbars and listeners
         */
        SeekBar seekbar1 = findViewById(R.id.seekBar1);
        seekbar1.setMax(maxFreq - minFreq);
        seekbar1.setProgress((maxFreq - minFreq) / 2);
        seekbar1.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                eq.setBandLevel((short) 0, (short) (minFreq + progress));
            }
        });
        SeekBar seekbar2 = findViewById(R.id.seekBar2);
        seekbar2.setMax(maxFreq - minFreq);
        seekbar2.setProgress((maxFreq - minFreq) / 2);
        seekbar2.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                eq.setBandLevel((short) 1, (short) (minFreq + progress));
            }
        });
        SeekBar seekbar3 = findViewById(R.id.seekBar3);
        seekbar3.setMax(maxFreq - minFreq);
        seekbar3.setProgress((maxFreq - minFreq) / 2);
        seekbar3.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                eq.setBandLevel((short) 2, (short) (minFreq + progress));
            }
        });
        SeekBar seekbar4 = findViewById(R.id.seekBar4);
        seekbar4.setMax(maxFreq - minFreq);
        seekbar4.setProgress((maxFreq - minFreq) / 2);
        seekbar4.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                eq.setBandLevel((short) 3, (short) (minFreq + progress));
            }
        });
        SeekBar seekbar5 = findViewById(R.id.seekBar5);
        seekbar5.setMax(maxFreq - minFreq);
        seekbar5.setProgress((maxFreq - minFreq) / 2);
        seekbar5.setOnSeekBarChangeListener(new SeekBarListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                eq.setBandLevel((short) 4, (short) (minFreq + progress));
            }
        });
        Button addProfile = findViewById(R.id.addProfile);
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Profile Added", Toast.LENGTH_SHORT).show();
            }
            });

        /**
         * creating the spinner and listener
         */

        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.profiles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(1);
        dropdown.setOnItemSelectedListener(new DropdownOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0,  View view, int position, long id) {
                switch (position) {
                    case 0:
                        seekbar1.setProgress((seekbar1.getMax() / 4) * 3);
                        seekbar2.setProgress((seekbar2.getMax() / 5) * 3);
                        break;
                    case 1:
                        seekbar1.setProgress(seekbar1.getMax() / 2);
                        seekbar2.setProgress(seekbar2.getMax() / 2);
                        seekbar3.setProgress(seekbar3.getMax() / 2);
                        seekbar4.setProgress(seekbar4.getMax() / 2);
                        seekbar5.setProgress(seekbar5.getMax() / 2);
                        break;
                    case 2:
                        seekbar4.setProgress((seekbar3.getMax() / 5) * 3);
                        seekbar5.setProgress((seekbar5.getMax() / 4) * 3);
                }
            }
        });

        Log.e("eq", String.valueOf(eq.getBandLevelRange().length));

    }

//    final MediaPlayer player = new MediaPlayer.create(this, )
}
