package com.pmdevs.independance.app.util;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;

import com.pmdevs.independance.app.R;
import com.pmdevs.independance.app.module.Locations;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LocationsPullParser {

    private static final String LOGTAG = "LOCA";

    private static final String SHOW_ID = "showId";
    private static final String SHOW_CITY = "showCity";
    private static final String SHOW_STATE = "showState";
    private static final String SHOW_LOC = "showLocation";
    private static final String SHOW_DESC = "showDescription";

    private Locations currentShow  = null;
    private String currentTag = null;
    List<Locations> locs = new ArrayList<Locations>();

    public List<Locations> parseXML(Context context) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            InputStream stream = context.getResources().openRawResource(R.raw.fireworks_locations);
            xpp.setInput(stream, null);

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    handleStartTag(xpp.getName());
                } else if (eventType == XmlPullParser.END_TAG) {
                    currentTag = null;
                } else if (eventType == XmlPullParser.TEXT) {
                    handleText(xpp.getText());
                }
                eventType = xpp.next();
            }

        } catch (NotFoundException e) {
            Log.d(LOGTAG, e.getMessage());
        } catch (XmlPullParserException e) {
            Log.d(LOGTAG, e.getMessage());
        } catch (IOException e) {
            Log.d(LOGTAG, e.getMessage());
        }

        return locs;
    }

    private void handleText(String text) {
        String xmlText = text;
        if (currentShow != null && currentTag != null) {
            if (currentTag.equals(SHOW_ID)) {
                Integer id = Integer.parseInt(xmlText);
                currentShow.setId(id);
            }
            else if (currentTag.equals(SHOW_CITY)) {
                currentShow.setCity(xmlText);
            }
            else if (currentTag.equals(SHOW_STATE)) {
                currentShow.setState(xmlText);
            }
            else if (currentTag.equals(SHOW_LOC)) {
                currentShow.setLocation(xmlText);
            }
            else if (currentTag.equals(SHOW_DESC)) {

                currentShow.setDescription(xmlText);
            }
        }
    }

    private void handleStartTag(String name) {
        if (name.equals("locs")) {
            currentShow = new Locations();
            locs.add(currentShow);
        }
        else {
            currentTag = name;
        }
    }
}
