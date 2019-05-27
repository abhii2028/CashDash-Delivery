package com.cashdashdelivery.finalproject.Activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cashdashdelivery.finalproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class TrackingActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    Geocoder coder;
    List<Address> address;
    LatLng p1;

    LinearLayout mapLayout;
    DatabaseReference mdatabaseTrack;
    String addloc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        mdatabaseTrack = FirebaseDatabase.getInstance().getReference("TrackingAddress");
        mdatabaseTrack.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                addloc = dataSnapshot.getValue(String.class);
                Toast.makeText(TrackingActivity.this, addloc, Toast.LENGTH_SHORT).show();
                locationChanged(map);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mapLayout = findViewById(R.id.mapLayout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        coder = new Geocoder(TrackingActivity.this);
        p1 = null;


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

    }

    public void locationChanged(GoogleMap googleMap)
    {
        try {
            address = coder.getFromLocationName(addloc, 5);
            Address loc = address.get(0);
            p1 = new LatLng(loc.getLatitude(), loc.getLongitude() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.addMarker(new MarkerOptions().position(p1).title(addloc));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(p1, 15));
    }

}
