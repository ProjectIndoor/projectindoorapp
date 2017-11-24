package de.hft_stuttgart.sw.projectindoorapp.activities;

import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import de.hft_stuttgart.sw.projectindoorapp.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"Adding settings fragment");
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Log.d(TAG, "Adding preferences");
            addPreferencesFromResource(R.xml.preferences);

        }
    }


}