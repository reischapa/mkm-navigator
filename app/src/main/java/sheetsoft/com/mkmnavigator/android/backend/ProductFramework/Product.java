package sheetsoft.com.mkmnavigator.android.backend.ProductFramework;

import java.io.Serializable;

/**
 * Created by chapa on 09-11-2016.
 */

public class Product implements Serializable {


    private String mCardName = "";
    private String mCardExpansion = "";
    private String mCardPrice = "";
    private String mCardUrlString = "";
    private int mYOffset = 0;
    private int mXOffset = 0;
    private int mYSize = 0;
    private int mXSize = 0;

    //private CardID mCardID = null;



    public Product(String mCardName, String mCardExpansion){
        this.mCardName = mCardName;
        this.mCardExpansion = mCardExpansion;
        //this.mCardID = new CardID(mCardName,mCardExpansion);
    }

    public Product(String mCardName, String mCardExpansion, String mCardPrice){
        this.mCardName = mCardName;
        this.mCardExpansion = mCardExpansion;
        this.mCardPrice = mCardPrice;
        //this.mCardFullUrlString = fullUrl;
        //this.mCardID = new CardID(mCardName,mCardExpansion);
    }


    public Product(String mCardName, String mCardExpansion, String mCardPrice, int mYOffset, int mXOffset){
        this(mCardName,mCardExpansion,mCardPrice);
        this.mYOffset=mYOffset;
        this.mXOffset=mXOffset;
    }

    public String getCardName() {
        return mCardName;
    }

    public String getCardExpansion() {
        return mCardExpansion;
    }

    public String getCardPrice() {
        return mCardPrice;
    }

    public int getYOffset() {
        return mYOffset;
    }

    public int getXOffset() {
        return mXOffset;
    }

    public int getYSize() {
        return mYSize;
    }

    public int getXSize() {
        return mXSize;
    }

    public String getCardUrlString() {
        return mCardUrlString;
    }

    public void setCardUrlString(String mCardUrlString) {
        this.mCardUrlString = mCardUrlString;
    }


    public void setCardPrice(String mCardPrice) {
        this.mCardPrice = mCardPrice;
    }

    public void setYOffset(int mYOffset) {
        this.mYOffset = mYOffset;
    }

    public void setXOffset(int mXOffset) {
        this.mXOffset = mXOffset;
    }

    public void setYSize(int mYSize) {
        this.mYSize = mYSize;
    }

    public void setXSize(int mXSize) {
        this.mXSize = mXSize;
    }
}
