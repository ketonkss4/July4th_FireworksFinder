package com.pmdevs.independance.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pmdevs.independance.app.module.Locations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 7/2/2014.
 */
public class LocationDataSource {

    private static final String LOGTAG = "LOCA";
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            LocationsDB.COLUMN_ID,
            LocationsDB.COLUMN_CITY,
            LocationsDB.COLUMN_STATE,
            LocationsDB.COLUMN_LOC,
            LocationsDB.COLUMN_DESC,




    };

    public LocationDataSource(Context context) {
        dbhelper = new LocationsDB(context);

    }
    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhelper.getWritableDatabase();
    }
    public void close(){
        Log.i(LOGTAG,"Database Closed");
        dbhelper.close();
    }
    public Locations create(Locations locations){
        ContentValues values = new ContentValues();
        values.put(LocationsDB.COLUMN_CITY, locations.getCity());
        values.put(LocationsDB.COLUMN_STATE, locations.getState());

        values.put(LocationsDB.COLUMN_LOC, locations.getLocation());
        values.put(LocationsDB.COLUMN_DESC, locations.getDescription());

        long insertId = database.insert(LocationsDB.TABLE_SHOWS, null,values );
        locations.setId(insertId);
        return locations;


    }
    public List<Locations> findAll() {
        List<Locations> locs = new ArrayList<Locations>();

        Cursor cursor = database.query(LocationsDB.TABLE_SHOWS,allColumns,null,null,null,null,null);
        Log.i(LOGTAG, "Returned " + cursor.getCount()+ " rows");
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                Locations locations = new Locations();


                locations.setCity(cursor.getString(cursor.getColumnIndex(LocationsDB.COLUMN_CITY)));
                locations.setState(cursor.getString(cursor.getColumnIndex(LocationsDB.COLUMN_STATE)));
                locations.setLocation(cursor.getString(cursor.getColumnIndex(LocationsDB.COLUMN_LOC)));
                locations.setDescription(cursor.getString(cursor.getColumnIndex(LocationsDB.COLUMN_DESC)));
                locations.setId(cursor.getLong(cursor.getColumnIndex(LocationsDB.COLUMN_ID)));
                locs.add(locations);

            }
        }
        return locs;
    }
}
