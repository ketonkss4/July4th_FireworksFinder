package com.pmdevs.independance.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pmdevs.independance.app.R;
import com.pmdevs.independance.app.module.Locations;

import java.util.List;

/**
 * Created by Administrator on 7/4/2014.
 */
public class LocationsAdapter extends ArrayAdapter<Locations> {




    public LocationsAdapter(Context context, int textViewResourceId, List<Locations> locs) {
        super(context, textViewResourceId, locs);




    }


    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     *
     * This method is responsible for creating row views out of a location object that can be put
     * into our ListView
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        RelativeLayout row = (RelativeLayout) convertView;
        Log.i("StackSites", "getView pos = " + pos);
        if (null == row) {
            //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout) inflater.inflate(R.layout.row, null);
        }

        //Get our View References

        TextView nameTxt = (TextView) row.findViewById(R.id.nameTxt);
        TextView aboutTxt = (TextView) row.findViewById(R.id.aboutTxt);


        //Set the relavent text in our TextViews
        nameTxt.setText(getItem(pos).getCity());
        aboutTxt.setText(getItem(pos).getDescription());


        return row;
    }

}

