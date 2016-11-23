package sheetsoft.com.mkmnavigator.android.backend.motor;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by chapa on 17-11-2016.
 */

public class OldMethods {

    public static String returnImageExtension(String imgurl, String tag){
        return imgurl.substring(findLastCharInString(imgurl, tag)+1);
    }

    public static boolean isInList(ArrayList<?> input, final Object comparison){
        for (int i = 0; i < input.size(); i++ ){
            if (input.get(i).equals(comparison)){
                return true;
            }
        }
        return false;
    }

    public static String genRandomString(int length){

        Random rand = new Random();

        char[] symbols = new char[36];

        for (int idx = 0; idx < 10; ++idx)
              symbols[idx] = (char) ('0' + idx);

        for (int idx = 10; idx < 36; ++idx)
              symbols[idx] = (char) ('a' + idx - 10);

        char[] buf = new char[length];

        for (int idx = 0; idx < buf.length; ++idx) {
            double e = rand.nextDouble();

                if (e<0.5){
                     buf[idx] = Character.toUpperCase(symbols[rand.nextInt(symbols.length)]);
                } else {
                    buf[idx] = Character.toLowerCase(symbols[rand.nextInt(symbols.length)]);
                }


        };
        return new String(buf);
    }

    public static ArrayList<Integer> findSubStringLocations(String tested, String f){
        char[] elem = tested.toCharArray();
        ArrayList hm = new ArrayList();
        char holder;

        int locationInString = 0;
        int index =0;

        for (char el : elem){

            if ((elem.length-locationInString)>=f.length()){

                if (el== f.charAt(0)){
                    String Holder2 = tested.substring(locationInString,locationInString+f.length());
                    //System.out.println(Holder2);
                    if (Holder2.equals(f)){
                        hm.add(locationInString);
                        //System.out.println(tested.substring(locationInString));
                        //System.out.println(hm.get(index).toString());
                    }
                    index++;
                }
            }
            locationInString++;
        }



        return hm;
    }

    public static int findLastCharInString(String tested, String f){
        ArrayList hm = findSubStringLocations(tested, f);

        Integer foo =  (Integer) hm.get(hm.size()-1);

        if (foo==null){
            return -1;
        } else if (foo!=null){
            return foo;
        }

        return -1;

    }

    public static String retrieveSiteStatus(URL u){
        HttpURLConnection ht;
        String temp = null;
        try {
            ht = (HttpURLConnection) u.openConnection();
            temp = ht.getResponseMessage();
            ht.disconnect();
        } catch (MalformedURLException k) {}
          catch (IOException l) {}
        return temp;
    }

    public static String retrieveSiteStatus(String url){
        String temp = null;
        URL r;
        try {
            r = new URL(url);
            return retrieveSiteStatus(r);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
