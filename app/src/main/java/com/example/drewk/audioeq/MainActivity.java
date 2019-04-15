package com.example.drewk.audioeq;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    List<Profile> profiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(MainActivity.this);
        RealmResults<Profile> profileRealm = Realm.getDefaultInstance().where(Profile.class).findAll();
        profiles = Realm.getDefaultInstance().copyFromRealm(profileRealm);
        ProfileAdapter adapter = new ProfileAdapter(profiles, MainActivity.this);


        Intent intent = new Intent(this, AudioService.class);
        startService(intent);

        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.song);

        Equalizer eq = new Equalizer(0, mp.getAudioSessionId());
        eq.setEnabled(true);

        mp.start();
        short minFreq = eq.getBandLevelRange()[0];
        short maxFreq = eq.getBandLevelRange()[1];


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
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);


        Button addProfile = findViewById(R.id.addProfile);
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_layout, null);
                View popupLayout = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_layout, null);

                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Add Profile");
                popup.setView(popupLayout);

                EditText textbox = popupLayout.findViewById(R.id.textBox);
                popup.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = textbox.getText().toString();
                        Profile newProfile = new Profile(name, seekbar1.getProgress(), seekbar2.getProgress(),
                                seekbar3.getProgress(), seekbar4.getProgress(), seekbar5.getProgress());
                        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm Realm) {
                                Realm.copyToRealm(newProfile);

                            }
                        });
                        List<Profile> temp = Realm.getDefaultInstance().where(Profile.class).findAll();
                        adapter.setData(temp);
                        dropdown.setSelection(temp.size() - 1);

                        Toast.makeText(MainActivity.this, "Profile Added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                popup.show();
            }
        });

        Button deleteProfile = findViewById(R.id.deleteProfile);
        deleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Delete Profile");
                popup.setMessage("aRe YoU sUrE?");

                popup.setPositiveButton("Do it.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm Realm) {
//                                Realm.copyToRealm(profiles);
                                profileRealm.deleteFromRealm(dropdown.getSelectedItemPosition());
                            }
                        });
                        List<Profile> temp = Realm.getDefaultInstance().where(Profile.class).findAll();
                        adapter.setData(temp);
                        dropdown.setSelection(1);

                        Toast.makeText(MainActivity.this, "Profile Yeeted", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                popup.setNegativeButton("Wait no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                popup.show();
            }
        });

        /*
         * creating the spinner and listener
         */

        if (profiles.size() == 0) {
            Profile bassBoost = new Profile("Bass Boost", seekbar1.getMax() / 4 * 3, seekbar2.getMax() / 5 * 3,
                    seekbar3.getMax() / 2, seekbar3.getMax() / 2, seekbar3.getMax() / 2);
            Profile flat = new Profile("Flat", seekbar1.getProgress(), seekbar2.getProgress(),
                    seekbar3.getProgress(), seekbar4.getProgress(), seekbar5.getProgress());
            Profile trebleBoost = new Profile("Treble Boost", seekbar1.getProgress(), seekbar2.getProgress(),
                    seekbar3.getProgress(), seekbar3.getMax() / 5 * 3, seekbar5.getMax() / 4 * 3);
            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm Realm) {
                    Realm.copyToRealm(bassBoost);
                    Realm.copyToRealm(flat);
                    Realm.copyToRealm(trebleBoost);
                }
            });
            profiles = Realm.getDefaultInstance().where(Profile.class).findAll();
            adapter.setData(profiles);
        }

        dropdown.setAdapter(adapter);
        dropdown.setSelection(1);
        dropdown.setOnItemSelectedListener(new DropdownOnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id) {
                List<Profile> tempList = Realm.getDefaultInstance().where(Profile.class).findAll();
                Profile temp = tempList.get(position);
                seekbar1.setProgress((int) temp.getProgress1());
                seekbar2.setProgress((int) temp.getProgress2());
                seekbar3.setProgress((int) temp.getProgress3());
                seekbar4.setProgress((int) temp.getProgress4());
                seekbar5.setProgress((int) temp.getProgress5());


            }
        });

        Log.e("eq", String.valueOf(eq.getBandLevelRange().length));

    }

}
