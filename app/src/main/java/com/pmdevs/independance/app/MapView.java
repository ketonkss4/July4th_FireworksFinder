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
    public static String newString = "Plymouth";
    MarkerOptions markerOptions;
    LatLng latLng;

    private static final String LOGTAG = "LOCA";
    LocationDataSource dataSource;

    public MapView() {
    }

    public String getShownIndex() {
        String loc = getActivity().getIntent().getStringExtra("LOC");
        return loc;

    }

    @Override
    public void onStart() {
        super.onStart();
        getMap().setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(MAIN_LOCATION, 8);
        getMap().animateCamera(update);

        dataSource = new LocationDataSource(getActivity());


        try {
            geoLocate();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void geoLocate () throws IOException {


        Geocoder gc = new Geocoder(getActivity());
//        try{
//            newString = JsoupParser.getLocations();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (newString !=null) {
//            Toast.makeText(getActivity(), newString, Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(getActivity(), "String was null", Toast.LENGTH_SHORT).show();
//        }
        if(newString!=null) {

            String location = getShownIndex();

            List<Address> list = gc.getFromLocationName(location, 1);

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
            if (add == null) {
                getMap().animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        } else {
            Toast.makeText(getActivity(), "Location not Found", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        dataSource.close();
    }
//    private void createData() {
//        Locations locations = new Locations();
//        locations.setCity("Minneapolis");
//        locations.setState("MN");
//        locations.setDescription("Red, White and BOOM celebrates Independence Day with music, entert" +
//                "ainment and family fun on the Minneapolis Riverfront on July 3 and 4. Fireworks are at 10 p.m. " +
//                "on July 4. (NOTE: Powderhorn Park, Minneapolis will still host a Fourth of July celebration — with " +
//                "live music, children’s activities, and local food vendors — but will NOT feature a fireworks display this year");
//        locations.setLocation("221 SE Main St\n" +
//                "Minneapolis, MN ");
//
//        locations = dataSource.create(locations);
//        Log.i(LOGTAG, "Locations put with id:" + locations.getId());
//    }
}

