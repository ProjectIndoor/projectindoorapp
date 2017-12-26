package de.hft_stuttgart.sw.projectindoorapp.activities;

import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.ListPreference;
import android.view.View;

import java.util.ArrayList;

import de.hft_stuttgart.sw.projectindoorapp.R;
import de.hft_stuttgart.sw.projectindoorapp.models.external.Project;
import de.hft_stuttgart.sw.projectindoorapp.services.ProjectService;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Adding settings fragment");
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

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            final PreferenceScreen preferenceScreen = this.getPreferenceScreen();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    ProjectService projectService = new ProjectService();
                    ArrayList<Project> projects = projectService.getAllProjects();
                    PreferenceCategory projectPref = (PreferenceCategory) preferenceScreen.findPreference("pref_key_proj_info");
                    ListPreference projectIdList = new ListPreference(preferenceScreen.getContext());
                    projectIdList.setKey("project_id_list");
                    projectIdList.setTitle("Project");
                    projectIdList.setSummary("Select the localization project.");

                    // Get project ids and names from projects list.
                    String projectNames[] = new String[projects.size()];
                    String projectIds[] = new String[projects.size()];
                    Log.i(TAG, "Projects:");
                    for (int i = 0; i < projects.size(); i++) {
                        Log.i(TAG, projects.get(i).getProjectId() + " => " + projects.get(i).getProjectName());
                        projectIds[i] = "" + projects.get(i).getProjectId();
                        projectNames[i] = projects.get(i).getProjectId() + ": " + projects.get(i).getProjectName() + " (Building: " + projects.get(i).getBuildingName() + ")";
                    }

                    // Set drop down entries and initialize preferences.
                    projectIdList.setEntries(projectNames);
                    projectIdList.setEntryValues(projectIds);
                    projectPref.addPreference(projectIdList);
                }
            });
            t.start();
        }
    }

}