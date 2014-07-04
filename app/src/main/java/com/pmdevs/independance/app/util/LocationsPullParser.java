package com.pmdevs.independance.app.util;

import android.content.Context;

import com.pmdevs.independance.app.R;
import com.pmdevs.independance.app.module.Locations;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class LocationsPullParser {

    private static final String LOGTAG = "LOCA";

    private static final String SHOW_SITE = "locs";
    private static final String SHOW_ID = "locId";
    private static final String SHOW_CITY = "locCity";
    private static final String SHOW_STATE = "locState";
    private static final String SHOW_DESC = "Description";
    private static final String SHOW_LOC = "locLocation";


    public  List<Locations> parseXML(Context context) {
        // list of locations to return

        List<Locations> locs = new ArrayList<Locations>();
        Locations curLocation = null;
        String curText = "";

        try {

            //get factory and pull parser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            //open inputStream
            InputStream istrm = context.getResources().openRawResource(R.raw.fireworks_locations);
            //point parser to file
            xpp.setInput(istrm, null);

            //get initial eventType
            int eventType = xpp.getEventType();
            //Loop through pull events until end document reached
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                //react to different event types
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(SHOW_SITE)) {
                            //if starting new <locs> block need new obj to represent it
                            curLocation = new Locations();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        //grab current text to use in end tag event
                        curText = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(SHOW_SITE)) {
                            // if </locs> then done with current location add it to list
                            locs.add(curLocation);
                        } else if (tagname.equalsIgnoreCase(SHOW_ID)) {
                            Integer id = Integer.parseInt(curText);
                            curLocation.setId(id);
                        } else if (tagname.equalsIgnoreCase(SHOW_CITY)) {
                            curLocation.setCity(curText);
                        } else if (tagname.equalsIgnoreCase(SHOW_STATE)) {
                            curLocation.setState(curText);
                        } else if (tagname.equalsIgnoreCase(SHOW_DESC)) {
                            curLocation.setDescription(curText);

                        } else if (tagname.equalsIgnoreCase(SHOW_LOC)) {
                            curLocation.setLocation(curText);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return locs;
    }
}
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            XmlPullParser xpp = factory.newPullParser();
//
//            InputStream stream = context.getResources().openRawResource(R.raw.fireworks_locations);
//            xpp.setInput(stream, null);
//
//            int eventType = xpp.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                if (eventType == XmlPullParser.START_TAG) {
//                    handleStartTag(xpp.getName());
//                } else if (eventType == XmlPullParser.END_TAG) {
//                    currentTag = null;
//                } else if (eventType == XmlPullParser.TEXT) {
//                    handleText(xpp.getText());
//                }
//                eventType = xpp.next();
//            }
//
//        } catch (NotFoundException e) {
//            Log.d(LOGTAG, e.getMessage());
//        } catch (XmlPullParserException e) {
//            Log.d(LOGTAG, e.getMessage());
//        } catch (IOException e) {
//            Log.d(LOGTAG, e.getMessage());
//        }
//
//        return locs;
//    }
//
//    private void handleText(String text) {
//        String xmlText = text;
//        if (currentShow != null && currentTag != null) {
//            if (currentTag.equals(SHOW_ID)) {
//                Integer id = Integer.parseInt(xmlText);
//                currentShow.setId(id);
//            }
//            else if (currentTag.equals(SHOW_CITY)) {
//                currentShow.setCity(xmlText);
//            }
//            else if (currentTag.equals(SHOW_STATE)) {
//                currentShow.setState(xmlText);
//            }
//            else if (currentTag.equals(SHOW_LOC)) {
//                currentShow.setLocation(xmlText);
//            }
//            else if (currentTag.equals(SHOW_DESC)) {
//
//                currentShow.setDescription(xmlText);
//            }
//        }
//    }
//
//    private void handleStartTag(String name) {
//        if (name.equals("locs")) {
//            currentShow = new Locations();
//            locs.add(currentShow);
//        }
//        else {
//            currentTag = name;
//        }
//    }
