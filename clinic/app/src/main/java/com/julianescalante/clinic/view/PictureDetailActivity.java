package com.julianescalante.clinic.view;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.julianescalante.clinic.R;

public class PictureDetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng Concepcion = new LatLng(-32.480657569916694, -58.23387698128835);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Concepcion, 13));

        Bundle bundle = getIntent().getExtras();

        double latitud = bundle.getDouble("latitude");
        double longitud = bundle.getDouble("longitude");
        String nombres = bundle.getString("nombre");

        Log.d("VALOR", String.valueOf(latitud));
        LatLng coordenadas = new LatLng(latitud, longitud);

        MarkerOptions coordenadas2 = new MarkerOptions().title(nombres).position(coordenadas);
        map.addMarker(coordenadas2);
    }
}
