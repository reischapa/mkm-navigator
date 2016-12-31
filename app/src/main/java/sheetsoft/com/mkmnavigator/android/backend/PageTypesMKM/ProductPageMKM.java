package sheetsoft.com.mkmnavigator.android.backend.PageTypesMKM;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import sheetsoft.com.mkmnavigator.android.backend.PageFramework.ZeroPage;
import sheetsoft.com.mkmnavigator.android.backend.ProductFramework.Product;
import sheetsoft.com.mkmnavigator.android.backend.motor.Motor;

/**
 * Created by chapa on 11/23/2016.
 */

public class ProductPageMKM extends ZeroPage {

    public ProductPageMKM(Product inputCardName, String inputHTML){
        super(inputHTML, Motor.PAGETYPES.PRODUCT,Motor.MAINSITEURLS.MKM_EU, false);
    }


    private String productPageURLString = "";



    @Override
    public void parse(String htmlIn){

        Document document = Jsoup.parse(htmlIn);
        Elements elems;


        String productURLString = null;

        elems = document.select("img[class*=prodImage][");



    }

}
