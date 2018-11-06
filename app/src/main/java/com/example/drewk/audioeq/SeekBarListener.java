package com.example.drewk.audioeq;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.SeekBar;

public interface SeekBarListener extends SeekBar.OnSeekBarChangeListener {
    default void onStartTrackingTouch(SeekBar seekBar) {
    }

    default void onStopTrackingTouch(SeekBar seekBar) {
    }
}
