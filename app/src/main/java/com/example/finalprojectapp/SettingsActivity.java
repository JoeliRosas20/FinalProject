package com.example.finalprojectapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
        Log.d(TAG, "inside onCreate method of SettingsActivity");

    }

    private static Preference.OnPreferenceChangeListener onPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            Log.d("SAPref", "Inside the listener");
            String stringValue = newValue.toString();
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);
                Log.d("index", Integer.toString(index));
                preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
            } else {
                preference.setSummary(stringValue);
            }
            Log.d("SAPref", "Leaving the listener");
            return true;
        }
    };


    static void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(onPreferenceChangeListener);
        onPreferenceChangeListener.onPreferenceChange(preference, PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), ""));


    }
}
