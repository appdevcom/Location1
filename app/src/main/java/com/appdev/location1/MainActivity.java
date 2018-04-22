package com.appdev.location1;

import android.Manifest;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    /* implements EasyPermissions.PermissionCallbacks */
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int RC_LOCATION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationAsk();

    }






    @AfterPermissionGranted(RC_LOCATION)
    public void locationAsk() {
        String[] perms = { Manifest.permission.ACCESS_FINE_LOCATION };
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permissions, do the thing!
            //Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show();

            getMyLocation();
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_location), RC_LOCATION, perms);

        }
    }

    public void getMyLocation() throws SecurityException {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        TextView textView1 = (TextView) findViewById(R.id.textView1);
                        String text = "Lat: " + Double.toString(location.getLatitude()) + "\nLong:" + Double.toString(location.getLongitude());
                        textView1.setText(text);

                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
}
