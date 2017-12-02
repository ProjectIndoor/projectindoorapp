package de.hft_stuttgart.sw.projectindoorapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.R;
import de.hft_stuttgart.sw.projectindoorapp.broadcast_receivers.WifiReceiver;
import de.hft_stuttgart.sw.projectindoorapp.models.Position;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, GoogleMap.OnGroundOverlayClickListener {

    private static final String LOG_TAG = "MapActivityLog";
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION = 2;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE = 3;
    private static final int PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE = 4;

    private GoogleMap mMap;
    private GroundOverlayOptions hftMap;
    private Polyline userTrack;


    private static final LatLng hftPosition = new LatLng(48.779845, 9.173471);
    private final List<BitmapDescriptor> mImages = new ArrayList<>();
    private WifiManager wifiManager;
    private WifiReceiver receiver;

    private Marker currentPosition;

    // HFT building boundary.
    private LatLng hftSouthWest = new LatLng(48.779565, 9.173414);// South west corner.
    private LatLng hftNorthEast = new LatLng(48.780150, 9.173494);//  North east corner.
    private LatLngBounds hftBounds = new LatLngBounds(hftSouthWest, hftNorthEast);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Initialize wifi manager and wifi receiver for onCreate.
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receiver = new WifiReceiver(wifiManager, this);

        this.requestPermissions();
    }

    // Request permission for ACCESS_COARSE_LOCATION.
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                //        PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                //        PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_WIFI_STATE},
                        PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE);
            }
            if (checkSelfPermission(Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CHANGE_WIFI_STATE},
                        PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION: {
                Log.i(LOG_TAG, "ACCESS_COARSE_LOCATION granted.");
                break;
            }
            case PERMISSIONS_REQUEST_CODE_ACCESS_FINE_LOCATION: {
                Log.i(LOG_TAG, "ACCESS_FINE_LOCATION granted.");
                break;
            }
            case PERMISSIONS_REQUEST_CODE_ACCESS_WIFI_STATE: {
                Log.i(LOG_TAG, "ACCESS_WIFI_STATE granted.");
                break;
            }
            case PERMISSIONS_REQUEST_CODE_CHANGE_WIFI_STATE: {
                Log.i(LOG_TAG, "CHANGE_WIFI_STATE granted.");
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register wifi receiver with IntentFilter.
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Unregister wifi receiver to save battery.
        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openSettingsScreen();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void openSettingsScreen() {
        Log.d(LOG_TAG, "Opening settings");
        Intent i = new Intent(MapActivity.this, SettingsActivity.class);
        startActivity(i);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onGroundOverlayClick(GroundOverlay groundOverlay) {

        mMap.moveCamera(CameraUpdateFactory.zoomIn());

    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnGroundOverlayClickListener(this);

        // Add a marker in Stuttgart and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hftBounds.getCenter(), 16));
        mImages.clear();

        // Call dummy implementation to add access point markers.
        this.addAccessPointMarkers(mMap);

        // Add Polyline to display dummy track.
        userTrack = mMap.addPolyline(new PolylineOptions().width(5).color(Color.RED));

        // Zoom in, animating the camera.
        // mMap.animateCamera(CameraUpdateFactory.zoomIn());

        // Zoom out to zoom level 10, animating with a duration of 2 seconds.

        mMap.animateCamera(CameraUpdateFactory.zoomTo(19), 4000, null);
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.floor_map));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        // North east corner
        hftMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.floor_map))
                .bearing(64 + 180)
                .position(hftPosition, 58f, 35f);

        mMap.addGroundOverlay(hftMap);
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Log.i(LOG_TAG, "onMyLocationClick");
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
        // TODO : show just the floor map after click
    }

    private void addAccessPointMarkers(GoogleMap map) {
        // Add dummy markers for now.
        map.addMarker(new MarkerOptions().position(hftPosition).title("HFT, Bau 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.wifi)));
        map.addMarker(new MarkerOptions().position(hftSouthWest).title("HFT, Bau 2 - South West").icon(BitmapDescriptorFactory.fromResource(R.drawable.wifi)));
        map.addMarker(new MarkerOptions().position(hftNorthEast).title("HFT, Bau 2 - North East").icon(BitmapDescriptorFactory.fromResource(R.drawable.wifi)));
    }

    public void addPositionToTrack(Position position) {
        LatLng location = new LatLng(position.getLatitude(), position.getLongitude());
        if (currentPosition != null) {
            currentPosition.remove();
        }
        currentPosition = mMap.addMarker(new MarkerOptions()
                .position(location)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.walking)));
        List<LatLng> points = this.userTrack.getPoints();
        points.add(location);
        this.userTrack.setPoints(points);
    }

}

