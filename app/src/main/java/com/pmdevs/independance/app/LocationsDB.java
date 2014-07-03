package com.pmdevs.independance.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 7/2/2014.
 */
public class LocationsDB extends SQLiteOpenHelper {

    private static final String LOGTAG = "LOCA";

    private static final String DATABASE_NAME = "locations.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_SHOWS = "shows";
    public static final String COLUMN_ID = "showId";
    public static final String COLUMN_CITY = "cityName";
    public static final String COLUMN_STATE = "stateName";
    public static final String COLUMN_LOC = "eventLocation";
    public static final String COLUMN_DESC = "description";



    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SHOWS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CITY + " TEXT, " +
                    COLUMN_STATE + " TEXT, " +
                    COLUMN_LOC + " TEXT, " +
                    COLUMN_DESC + " TEXT " +
                    ")";


    public LocationsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOGTAG, "Table has been created");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_SHOWS);
        onCreate(db);
    }
}
