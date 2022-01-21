package java.eu.ase.exemplumaps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.eu.ase.exemplumaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

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

        mMap.setMinZoomPreference(14);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        LatLng ASE = new LatLng(44.44776573708286, 26.09677586954647);
        mMap.addMarker(new MarkerOptions().position(ASE).title("Marker la ASE"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ASE));

        LatLng AFI = new LatLng(44.43083911488468, 26.051911969546143);
        mMap.addMarker(new MarkerOptions().position(AFI).title("Marker la AFI"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(AFI));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ASE).title("ASE").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        PolylineOptions plo = new PolylineOptions();
        plo.add(ASE);
        plo.add(AFI);
        plo.color(Color.RED);
        plo.width(20);

        mMap.addPolyline(plo);

        Marker m = mMap.addMarker(markerOptions);
        m.showInfoWindow();
    }
}