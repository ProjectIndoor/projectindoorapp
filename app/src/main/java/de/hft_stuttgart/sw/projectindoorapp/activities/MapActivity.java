package de.hft_stuttgart.sw.projectindoorapp.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hft_stuttgart.sw.projectindoorapp.R;
import de.hft_stuttgart.sw.projectindoorapp.broadcast_receivers.WifiReceiver;
import de.hft_stuttgart.sw.projectindoorapp.models.AccessPoint;
import de.hft_stuttgart.sw.projectindoorapp.models.Position;
import de.hft_stuttgart.sw.projectindoorapp.models.RSSISignal;
import io.realm.Realm;
import io.realm.RealmResults;

public class MapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMyLocationClickListener,
        GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, GoogleMap.OnGroundOverlayClickListener {

    private static final String LOG_TAG = "MapActivityLog";
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_LOCATION = 1;
    private static boolean recordWifiInformation = false;

    private GoogleMap mMap;
    private GroundOverlayOptions hftMap;
    private Polyline userTrack;



    private static final String FILE_NAME = "wifiLog.txt";


    public Realm realm;

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
        Realm.init(this);
        realm = Realm.getDefaultInstance();

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

    /*
    private void toggleRecordWifiButtonText() {
        MenuItem wifiRecordOption = findViewById(R.id.nav_wifi_recording);
        if (recordWifiInformation) {
            wifiRecordOption.setTitle(R.string.record_wifi_data_stop);
        } else {
            wifiRecordOption.setTitle(R.string.record_wifi_data);
        }
    }
    */

    // Request permission for ACCESS_COARSE_LOCATION.
    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_CODE_ACCESS_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE_ACCESS_LOCATION: {
                Log.i(LOG_TAG, "ACCESS_LOCATION granted.");
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearRealm();
    }

    private void clearRealm() {
        final RealmResults<AccessPoint> accessPointResults = realm.where(AccessPoint.class).findAll();
        final RealmResults<RSSISignal> signalResults = realm.where(RSSISignal.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                signalResults.deleteAllFromRealm();
                accessPointResults.deleteAllFromRealm();
            }
        });
        realm.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register wifi receiver with IntentFilter.
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        // Repeatedly start wifi scanning.
        final Handler wifiHandler = new Handler();
        final Runnable startWifiScan = new Runnable() {
            @Override
            public void run() {
                Log.i(LOG_TAG, "Start wifi scan...");
                wifiManager.startScan();
                wifiHandler.postDelayed(this, 5000);
            }
        };
        wifiHandler.post(startWifiScan);
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

        if (id == R.id.nav_wifi_recording) {
            Log.i(LOG_TAG, "wifi recording");
            recordWifiInformation = !recordWifiInformation;
            clearRealm();
        } else if (id == R.id.nav_share) {
            //share(item);
        } else if (id == R.id.sharebtn) {
            Log.i(LOG_TAG, "nav_send");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        Log.i(LOG_TAG, "onGroundOverlayClick ...");

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
        Log.i(LOG_TAG, "onMapReady ...");
        mMap = googleMap;

        // Register a listener to respond to clicks on GroundOverlays.
        mMap.setOnGroundOverlayClickListener(this);

        // Add a marker in hft bau 2 and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hftBounds.getCenter(), 16));
        mImages.clear();

        // Call dummy implementation to add access point markers.
        this.addAccessPointMarkers(mMap);

        // Add Polyline to display track.
        userTrack = mMap.addPolyline(new PolylineOptions().width(5).color(Color.RED));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(19), 4000, null);
        mImages.add(BitmapDescriptorFactory.fromResource(R.drawable.floor_map));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Don't display GPS location of user.
            mMap.setMyLocationEnabled(false);
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

    public void share (View view) throws IOException {

        // TODO: create file, write WIFI lines in it and share it.


       // File file = new File( context.getFilesDir() +"/" + "wifiLog.txt");
       // File file = new File(Environment.getExternalStorageDirectory() + "/" + "Email-Ghap/wifiLog.txt");
        File fileDir = new File(getFilesDir() + File.separator + "export" + File.separator);
        //fileDir.mkdirs();
        //File file = new File(fileDir, "export.txt");



        RealmResults<RSSISignal> signals = realm.where(RSSISignal.class).findAll();

        try {




            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("*/txt");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, fileDir);
            startActivity(Intent.createChooser(sharingIntent, "share file with"));






        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i(LOG_TAG, "-------");
        // TODO: add line without WIFI at beginning, when new block of wifi data starts.
        for (RSSISignal signal : signals) {
            Log.i(LOG_TAG, signal.toString());
        }
        Log.i(LOG_TAG, "-------");
    }



        public void share2(View view) {

            canWriteOnExternalStorage();

            File dir = Environment.getExternalStorageDirectory();
            //File fileDir = new File(getFilesDir() + File.separator + "export" + File.separator);
            File fileDir = new File(dir.getAbsolutePath()+"/export/");
            fileDir.mkdirs();

            //File file = new File(fileDir, "export.txt");
            // if (!file.getParentFile().exists())
            //file.getParentFile().mkdirs();

            File file = new File(dir,"export.txt");

            Log.i(LOG_TAG, "-------");
            try {
                FileOutputStream fos = new FileOutputStream(file);

                RealmResults<RSSISignal> signals = realm.where(RSSISignal.class).findAll();

                int radioMapElement = 0;

                for (RSSISignal signal : signals) {
                    if (radioMapElement != signal.getRadioMapElement().getId()) {
                        radioMapElement = signal.getRadioMapElement().getId();
                        fos.write("\r\n".getBytes());
                    }
                    // WIFI;2.795;4985.268;eduroam;00:0b:86:27:35:90;-77
                    fos.write("WIFI;0.0;0.0;".getBytes());
                   // fos.write((signal.getAccessPoint().getSSID() + ";").getBytes());
                    fos.write((signal.getAccessPoint().getMacAddress() + ";").getBytes());
                    fos.write((signal.getSignalStrength() + "\r\n").getBytes());

                }

                fos.close();
                Toast.makeText(getBaseContext(), "File saved successfully!",
                        Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Log.i(LOG_TAG, "--> FileNotFoundException");
                return;
            } catch (IOException e) {
                Log.i(LOG_TAG, "--> IOException");
                return;
            }


            // TODO: create file, write WIFI lines in it and share it.


            // TODO: add line without WIFI at beginning, when new block of wifi data starts.
            Log.i(LOG_TAG, "-------");
            Intent intent = new Intent();
            intent.setDataAndType(Uri.fromFile(new File(fileDir, "export.txt")), "text/plain");
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(Intent.createChooser(intent, "CSV-Reader"));
        }

    public static boolean canWriteOnExternalStorage() {
        // get the state of your external storage
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // if storage is mounted return true
            Log.i(LOG_TAG,"the storage is mounted *****");
            return true;
        }
        return false;
    }


}
