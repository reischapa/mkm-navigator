package tests;


import com.mkmnav.backend.CACertificateStrings;
import com.mkmnav.backend.ProgramInput;

/**
 * Created by chapa on 11/8/2016.
 */

public class Main {
    public static void main(String... args){

        //[class*=navBarBottomText] span:matchesOwn(Showing*)

        //System.setProperty("javax.net.ssl.trustStore", "mkm.jks");


//        try {
//            Document doc = Jsoup.connect("https://www.magiccardmarket.eu/?mainPage=showSearchResult&searchFor=forest")
//                       .userAgent("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36")
//                        .get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //String htmIn = ProgramInput.getHTMLFromFile(new File("mkm_forest.html"));



        String foo = "https://www.magiccardmarket.eu/?mainPage=showSearchResult&searchFor=forest";

        String res = ProgramInput.getHTMLFromURLThroughHTTPS(foo, CACertificateStrings.MKM);

        System.out.print(res);

        //String htmIn = AndroidProgramInput.htmlDownloaderAndroid("https://www.magiccardmarket.eu/?mainPage=showSearchResult&searchFor=forest");

        //Motor.process(htmIn);





        //Separate android interface code from log
    }







}
