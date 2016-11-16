package com.mkmnav.backend;

import com.mkmnav.backend.PageTypesMKM.SearchPage;

import java.util.HashMap;

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

    public static SearchPage generateSearchPage(String htmlIn, MAINSITEURLS url){

        SearchPage sp = new SearchPage("forest",MAINSITEURLS.MKM_EU,0,htmlIn);
        sp.parse();
        return sp;
    }


}
