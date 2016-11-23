package sheetsoft.com.mkmnavigator.android.backend.motor;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.SimpleTimeZone;

import sheetsoft.com.mkmnavigator.android.backend.PageTypesMKM.SearchPageMKM;

/**
 * Created by chapa on 11-11-2016.
 */

public abstract class Motor {

    //saves all known instances of engines = processed pages




    //the proper user agent needed to get desktop pages
    public static final String mHTTPUserAgent = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";

    //enum with the site urls
    public enum MAINSITEURLS{
        MKM_EU("https://www.magiccardmarket.eu");

        public final String content;
        MAINSITEURLS(String input){
            this.content = input;
        }
    }

    //enum with the page types
    public enum PAGETYPES{
        SEARCH, CARD_LISTINGS;
    }


    //returns the URL of the next search page
    public static String returnSearchUrlString(String card, int pageNumber, MAINSITEURLS url){
        return  MAINSITEURLS.MKM_EU.content + "/?mainPage=showSearchResult&searchFor=" + card + "&resultsPage=" + pageNumber + "" ;
    }

    public static SearchPageMKM generateSearchPage(String htmlIn, MAINSITEURLS url){
        return new SearchPageMKM(htmlIn,"forest");
    }


    public static long getCurrentTimeInMillis(){
        return Calendar.getInstance().getTimeInMillis();
    }


}
