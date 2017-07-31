package com.mycom.coe.googlemap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker mCoe;
    private Marker mScb;
    private Marker mCentral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng coeLatLng = new LatLng(7.893450, 98.352476);
        mCoe = mMap.addMarker(new MarkerOptions()
                .position(coeLatLng)
                .snippet("Ccomputer Engineering Department, PSU Phuket")
                .title("CoE, Phuket Campus"));
        mCoe.setTag(0);
        mCoe.setDraggable(true);
        mScb = mMap.addMarker(new MarkerOptions().position(
                new LatLng(7.8965542, 98.3551417))
                .snippet("SCB Office description")
                .icon(BitmapDescriptorFactory      // replace default icon marker
                        .fromResource(R.mipmap.ic_launcher_round))
                .title("SCB PSU Phuket Office"));
        mScb.setTag(0);
        mScb.setDraggable(true);

        mCentral = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(7.891670, 98.368040))
                .snippet("Central Office description")
                .icon(BitmapDescriptorFactory   // replace icon color
                        .defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .alpha(0.7f)                    // opacity
                .title("Central Festival Phuket"));
        mCentral.setTag(0);
        mCentral.setDraggable(true);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(coeLatLng).zoom(14).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(this, marker.getTitle() + " Start Drag", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(this, marker.getTitle() + " Stop Drag", Toast.LENGTH_SHORT).show();
    }
}
