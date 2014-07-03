package com.pmdevs.independance.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 7/1/2014.
 */
public class JsoupParser {
    static final String LOCATION_PAGE = "http://minnesota.cbslocal.com/where-to-view-fireworks-in-2014/#parkrapids";

    public static String getLocations() throws Exception {
        String result = "";
        Document doc = Jsoup.connect(LOCATION_PAGE).get();
        Elements locations = doc.select("a[name]").attr("name","");
        if (locations.size()>0){
            result = locations.get(1).text();
        }


        return result;
    }
}
