package sheetsoft.com.mkmnavigator.android.backend.PageFramework;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sheetsoft.com.mkmnavigator.android.backend.ProductFramework.Product;
import sheetsoft.com.mkmnavigator.android.backend.motor.Motor;

/**
 * Created by chapa on 15-11-2016.
 */

public abstract class ZeroPage {

    protected Motor.PAGETYPES pageType;
    protected Motor.MAINSITEURLS pageURL;
    protected ArrayList<Product> mAssociatedProducts = null;


    public ZeroPage(String inputHTML, Motor.PAGETYPES pageType, Motor.MAINSITEURLS pageURL, boolean parseNow){
        this.pageType = pageType;
        this.pageURL = pageURL;
        if (parseNow) parse(inputHTML);
    }

//    public ZeroPage(ZeroPage input){
//        this.pageType = input.getPageType();
//        this.pageURL = input.getPageURL();
//        this.mAssociatedProducts = input.getAssociatedProducts();
//    }
// TODO implemented to reduce memory required for storage and transport of pages
//  Dunno if it will be required

    public final ArrayList<Product> getAssociatedProducts(){return this.mAssociatedProducts;}

    public final Motor.PAGETYPES getPageType(){
        return pageType;
    }
    public final Motor.MAINSITEURLS getPageURL(){
        return pageURL;
    }

    public void parse(String inputHTML){}


    public String getFromRegex(String input, String pattern){
        Pattern pat = Pattern.compile(pattern);
        Matcher mat = pat.matcher(input);
        mat.find();
        String out = mat.group();
        return out;
    }


}
