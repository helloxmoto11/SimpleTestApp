package com.example.simpletestapp.ui.preference;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.simpletestapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }
}
