package com.pmdevs.independance.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pmdevs.independance.app.module.Locations;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 7/2/2014.
 */
public class ListEvents extends ListActivity {

    private static final String LOGTAG = "LOCA";
    LocationDataSource dataSource;
    private static final String DB_PATH = "data/data/<com.pmdevs.independance.app>/databases/<db name>";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_event);
        doDBCheck();
        Log.i(LOGTAG,"database destroyed");

        dataSource = new LocationDataSource(this);
        dataSource.open();
        createData();

        List<Locations> locs = dataSource.findAll();
        if (locs.size() == 0) {
            createData();
            Log.i(LOGTAG, "Data Created");
            locs = dataSource.findAll();
        }


        ArrayAdapter<Locations> adapter = new ArrayAdapter<Locations>(this,
                android.R.layout.simple_list_item_1, locs);
        setListAdapter(adapter);

        ListView listView = new ListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    private void doDBCheck()
    {
        try{
            File file = new File(DB_PATH);
            file.delete();
            Log.i(LOGTAG,"File Destroyed");
        }catch(Exception ex)
        {}
    }
    private void createData() {
        Locations locations = new Locations();
        locations.setCity("Minneapolis ");
        locations.setState(",MN");
        locations.setDescription("Red, White and BOOM celebrates Independence Day with music, entert" +
                "ainment and family fun on the Minneapolis Riverfront on July 3 and 4. Fireworks are at 10 p.m. " +
                "on July 4. (NOTE: Powderhorn Park, Minneapolis will still host a Fourth of July celebration — with " +
                "live music, children’s activities, and local food vendors — but will NOT feature a fireworks display this year");
        locations.setLocation("221 SE Main St\n" +
                "Minneapolis, MN ");

        locations = dataSource.create(locations);
        Log.i(LOGTAG, "Locations put with id:" + locations.getId());


        locations.setCity("Albert Lea ");
        locations.setState(",MN");
        locations.setDescription("On July 3, come see the largest parade in Southern Minnesota at 6 p.m. The parade has over 100 units and will travel Bridge and Fountain Streets.\n" +
                "July 4 is one of the largest fireworks display in Southern Minnesota and Northern Iowa. Watch the beautiful reflection off of Fountain Lake at 10 p.m..\n" +
                "Prior to the fireworks, the 4th Of July celebration will include:\n" +
                "Community Band concert at 8 p.m. at the Gazebo.\n" +
                "Bayside Ski Show at 2 p.m. at Edgewater Park.\n" +
                "Car Show from 4 – 7 p.m. on N. Broadway.");
        locations.setLocation(" Albert Lea, MN");

        locations = dataSource.create(locations);
        Log.i(LOGTAG, "Locations put with id:" + locations.getId());

    }
}
