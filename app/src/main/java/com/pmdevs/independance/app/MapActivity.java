package com.pmdevs.independance.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Administrator on 6/29/2014.
 */
public class MapActivity extends Activity {
    private MapView mapView;
    private FragmentManager mFragmentMngr;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        mapView = new MapView();
        mFragmentMngr = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentMngr.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, mapView);
        fragmentTransaction.commit();

//        MapView mapObj = new MapView();
//        mapObj.setArguments(getIntent().getExtras());
//        getFragmentManager().beginTransaction().add(
//                android.R.id.content, mapObj).commit();
//        Intent intent = new Intent();
//        Bundle info = intent.getExtras();
//        String loc = null;
//        if (info != null){
//             loc = info.getString("LOC");
//        }
//
//        Bundle frag = new Bundle();
//        frag.putString("value",loc);
//
//
//        MapView mapObj = new MapView();
//        mapObj.setArguments(frag);
//        fragmentTransaction.replace(R.id.fragment_container,frag);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}