package de.hft_stuttgart.sw.projectindoorapp.activities;

import android.content.SharedPreferences;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.preference.Preference;
import android.preference.ListPreference;
import android.view.View;

import java.util.ArrayList;

import de.hft_stuttgart.sw.projectindoorapp.R;
import de.hft_stuttgart.sw.projectindoorapp.models.external.Building;
import de.hft_stuttgart.sw.projectindoorapp.models.external.Floor;
import de.hft_stuttgart.sw.projectindoorapp.models.external.Project;
import de.hft_stuttgart.sw.projectindoorapp.services.BuildingService;
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

    public class SettingsFragment extends PreferenceFragment {
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

                    //Project temp = new Project();
                    //projects.add(temp.setProjectId(5L).setProjectName("foo").setBuildingName("FFUF"));


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
                    projectIdList.setOnPreferenceChangeListener(new ProjectPreferenceChangeListener(preferenceScreen));
                    projectPref.addPreference(projectIdList);
                }
            });
            t.start();
        }
    }

    public class ProjectPreferenceChangeListener implements Preference.OnPreferenceChangeListener {

        final PreferenceScreen preferenceScreen;

        ProjectPreferenceChangeListener(PreferenceScreen preferenceScreen) {
            this.preferenceScreen = preferenceScreen;
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.selected_project_id), (String) o);
            editor.apply();

            final String buildingId = (String) o;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    BuildingService buildingService = new BuildingService();
                    // TODO: set buildingIdentifier of project.
                    Building building = buildingService.getBuildingByBuildingId("1");
                    ListPreference floorListPref;

                    PreferenceCategory projectPref = (PreferenceCategory) preferenceScreen.findPreference("pref_key_proj_info");
                    if (projectPref.findPreference("floor_list_pref") != null) {
                        floorListPref = (ListPreference) projectPref.findPreference("floor_list_pref");
                    } else {
                        floorListPref = new ListPreference(preferenceScreen.getContext());
                    }

                    ArrayList<Floor> floors = (ArrayList<Floor>) building.getBuildingFloors();

                    String ids[] = new String[floors.size()];
                    String entries[] = new String[floors.size()];
                    for (int i = 0; i < floors.size(); i++) {
                        ids[i] = "" + floors.get(i).getFloorLevel();
                        entries[i] = "Floor level: " + floors.get(i).getFloorLevel() + " (BuildingId: " + buildingId + ")";
                    }

                    floorListPref.setKey("floor_list_pref");
                    floorListPref.setTitle("Floor");
                    floorListPref.setSummary("Select the floor of the building.");
                    floorListPref.setEntries(entries);
                    floorListPref.setEntryValues(ids);
                    floorListPref.setOnPreferenceChangeListener(new FloorPreferenceChangeListener());

                    if (projectPref.getPreferenceCount() < 2) {
                        projectPref.addPreference(floorListPref);
                    }
                }
            });
            t.start();

            return true;
        }
    }

    public class FloorPreferenceChangeListener implements Preference.OnPreferenceChangeListener {

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.selected_floor_level), (String) o);
            editor.apply();

            return true;
        }
    }

}