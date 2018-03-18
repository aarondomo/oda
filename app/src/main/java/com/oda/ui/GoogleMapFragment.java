package com.oda.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.oda.R;
import com.oda.utils.AlertDialogBuilder;



public class GoogleMapFragment extends Fragment implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST = 100;

    private MapView mapView;
    private GoogleMap googleMap;

    private Button complaintLocationButton;

    private OnLocationSelected onLocationSelected;


    private FusedLocationProviderClient mFusedLocationClient;
    private LatLng locationMarker;


    public interface OnLocationSelected {
        public void saveLocation(LatLng coordinates);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_map, container, false);


        complaintLocationButton = view.findViewById(R.id.button_map_complaint_location);
        setUpSaveLocationButtonListener();

        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        setLastKnownLocation();

        return view;
    }

    private void setUpSaveLocationButtonListener(){
        complaintLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OnLocationSelected)getActivity()).saveLocation(locationMarker);
            }
        });
    }

    private void setLastKnownLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_PERMISSION_REQUEST);

        } else {

            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                locationMarker = new LatLng(location.getLatitude(), location.getLongitude());
                                setMarkerInMap(locationMarker);
                            }
                        }
                    });
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setLastKnownLocation();
            } else {
                AlertDialogBuilder.createNeutralAlertDialog(getContext(), "No podemos acceder a tu ubicacion",
                        "Por favor localiza la direcion en el mapa");
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        setMapClickListener();

        // Add a marker in Quito, Ecuador, and move the camera.
        if(locationMarker == null){
            locationMarker = new LatLng(-0.22, -78.52);
        }

        setMarkerInMap(locationMarker);
    }

    private void setMapClickListener(){
        // Setting a click event handler for the map
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Clears the previously touched position
                googleMap.clear();

                // Animating to the touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                googleMap.addMarker(markerOptions);
            }
        });
    }

    private void setMarkerInMap(LatLng position){
        if(googleMap != null){
            googleMap.addMarker(new MarkerOptions().position(locationMarker));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationMarker));
            googleMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        }
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
