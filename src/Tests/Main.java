package Tests;

import com.win.test.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by chapa on 11/8/2016.
 */

public class Main {
    public static void main(String... args){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://en.wikipedia.org/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements newsHeadlines = doc.select("#mp-itn b a");






    }
}
