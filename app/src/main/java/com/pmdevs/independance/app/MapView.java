package com.pmdevs.independance.app;


import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 6/29/2014.
 */
public class MapView extends MapFragment {
    LatLng MAIN_LOCATION = new LatLng(44.9833, -93.2667);
    public String newString ="1456 West 78th Street, Chanhassen, MN" ;
    MarkerOptions markerOptions;
    LatLng latLng;

    public MapView() {
    }

    @Override
    public void onStart() {
        super.onStart();
        getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(MAIN_LOCATION, 8);
        getMap().animateCamera(update);
        try {
            geoLocate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void geoLocate () throws IOException {


        Geocoder gc = new Geocoder(getActivity());

        List<Address> list = gc.getFromLocationName(newString, 1);



        Address add = list.get(0);
        String locality = add.getLocality();
        Toast.makeText(getActivity(), locality, Toast.LENGTH_LONG).show();



        latLng = new LatLng(add.getLatitude(), add.getLongitude());
        CameraUpdate update2 = CameraUpdateFactory.newLatLngZoom(latLng, 8);
        getMap().animateCamera(update2);

        String addressText = String.format("%s, %s",
                add.getMaxAddressLineIndex() > 0 ? add.getAddressLine(0) : "",
                add.getCountryName());

        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(addressText);



        getMap().addMarker(markerOptions);

        // Locate the first location
        if(add==null)
            getMap().animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }


}

