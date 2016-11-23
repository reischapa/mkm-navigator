package sheetsoft.com.mkmnavigator.android.backend.PageTypesMKM;

import sheetsoft.com.mkmnavigator.android.backend.PageFramework.ZeroPage;
import sheetsoft.com.mkmnavigator.android.backend.motor.Motor;

/**
 * Created by chapa on 11/23/2016.
 */

public class ProductPageMKM extends ZeroPage {

    public ProductPageMKM(String inputHTML, Motor.PAGETYPES pageType, Motor.MAINSITEURLS pageURL, boolean parseNow) {
        super(inputHTML, pageType, pageURL, parseNow);
    }

    @Override

    public void parse(String htmlIn){

    }

}
