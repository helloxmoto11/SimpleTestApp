package com.example.simpletestapp.ui.preference;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.simpletestapp.R;
import com.example.simpletestapp.work.NotificationWorker;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String WORK_TAG = "notification-work";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.notify))) {
            WorkRequest request = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                    .addTag(WORK_TAG)
                    .build();

            WorkManager.getInstance(requireContext()).enqueue(request);


        }
        return super.onPreferenceTreeClick(preference);
    }
}
