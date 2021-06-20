package com.example.simpletestapp.ui.preference;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.simpletestapp.R;
import com.example.simpletestapp.work.NotificationWorker;

import java.util.concurrent.TimeUnit;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String WORK_TAG = "notification-work";
    private WorkManager workManager;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        workManager = WorkManager.getInstance(requireContext());
        if (preference.getKey().equals(getString(R.string.notify))) {
            WorkRequest request = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                    .addTag(WORK_TAG)
                    .build();

            workManager.enqueue(request);
        }

        if (preference.getKey().equals(getString(R.string.set_daily_notification))) {
            SwitchPreference preference1 = (SwitchPreference) preference;
            boolean checked = preference1.isChecked();
            if (checked) {
                WorkRequest periodicRequest =
                        new PeriodicWorkRequest.Builder(
                                NotificationWorker.class,
                                15,
                                TimeUnit.MINUTES)
                                .addTag(WORK_TAG)
                                .build();

                workManager.enqueue(periodicRequest);
            } else workManager.cancelAllWorkByTag(WORK_TAG);
        }
        return super.onPreferenceTreeClick(preference);
    }
}
