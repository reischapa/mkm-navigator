package sheetsoft.com.mkmnavigator.android.backend.PageTypesMKM;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sheetsoft.com.mkmnavigator.android.backend.ProductFramework.Product;
import sheetsoft.com.mkmnavigator.android.backend.PageFramework.ZeroPage;
import sheetsoft.com.mkmnavigator.android.backend.motor.Motor;

public class SearchPageMKM extends ZeroPage implements Serializable{

    //SearchPageMKM identifier
//    public static class SearchPageID {
//        private String mSearchPageCardQuery;
//        private int mSearchPageNumber;
//        private String mSite;
//        public SearchPageID(String mSearchPageCardQuery, String site, int number) {
//            this.mSearchPageCardQuery = mSearchPageCardQuery;
//            this.mSite = site;
//            this.mSearchPageNumber = number;
//        }
//    } TODO maybe in the future?

    private static ArrayList<Integer> getNumericalValuesFromUnits(String input){
        ArrayList<Integer> output = new ArrayList<>();
        //System.out.println(input);
        Pattern pat = Pattern.compile("[0-9]+\\s*px");
        Matcher mat = pat.matcher(input);
        while(mat.find()){
            Pattern innerPat = Pattern.compile("[0-9]+");
            Matcher innerMat = innerPat.matcher(mat.group());
            if(innerMat.find()){
                //System.out.println(innerMat.group().toString());
                String y = innerMat.group().toString();
                output.add(Integer.parseInt(y));
            }
        }
        return output;
    }

    //END STATIC COMPONENTS
    //non static components


    private String mCardNameQuery = null;
    //private String mHtmlSourceCode = null; TODO to store or not to store?



    public SearchPageMKM( String mHtmlSourceCode, String mCardNameQuery){
        super(mHtmlSourceCode, Motor.PAGETYPES.SEARCH,Motor.MAINSITEURLS.MKM_EU,true);
        this.mCardNameQuery = mCardNameQuery;
    }

    public SearchPageMKM( String mHtmlSourceCode, String mCardNameQuery, boolean parseNow){
        super(mHtmlSourceCode,Motor.PAGETYPES.SEARCH,Motor.MAINSITEURLS.MKM_EU,parseNow);
        this.mCardNameQuery = mCardNameQuery;
    }



    @Override
    public void parse(String inputHTML){

        //this.mHtmlSourceCode = inputHTML; TODO to store or not to store?
        Document mHtmlDocument = Jsoup.parse(inputHTML);



        Elements elems = mHtmlDocument.select("table[class=MKMTable SearchTable] tbody tr");


        if (elems.size()==0){
            this.mAssociatedProducts = null;
            return;
        }

        String cardName = "";
        String cardNameUrlSuffix = "";
        String cardExpansionImageUrlSuffix = "";
        String cardExpansionName = "";
        String cardPrice = "";


        int i = 0;
        while (i < elems.size()){
            Element elem = elems.get(i);
            Elements finderElement;

            //getting the product name
            finderElement = elem.select("a[href*=/Products/Singles]"); //name
            cardName = finderElement.html();

            //getting the product link
            cardNameUrlSuffix = finderElement.attr("href");

            //getting the expansion link

            finderElement = elem.select("a[href][class*=noborder] span[class*=expansionIcon]");
            String expansionStyleCSS = finderElement.attr("style");

            cardExpansionImageUrlSuffix = getFromRegex(expansionStyleCSS, "'\\s*[\\s\\w_'%.\\\\/]*expicons.\\w+\\s*'");

            cardExpansionImageUrlSuffix = cardExpansionImageUrlSuffix.substring(0,cardExpansionImageUrlSuffix.length()).trim();



            String expCSSOnMouseOverArg = finderElement.attr("onmouseover");

            cardExpansionName = getFromRegex(expCSSOnMouseOverArg, "'\\s*[\\w\\s:.-]*\\s*'");
            cardExpansionName = cardExpansionName.substring(1,cardExpansionName.length()-1).trim();


            ArrayList<Integer> list = SearchPageMKM.getNumericalValuesFromUnits(getFromRegex(expansionStyleCSS,"background-position\\s*:\\s*-*[0-9]+(px)+\\s*-*[0-9]+(px)+;"));

            //get width and height of the area corresponding to the expansion icon

            String[] dimensionNames = {"width", "height"};
            ArrayList<Integer> dimensionValues = new ArrayList<>();
            for (int j = 0;j < dimensionNames.length; j++){

                dimensionValues.add(SearchPageMKM.getNumericalValuesFromUnits(getFromRegex(expansionStyleCSS,dimensionNames[j]+"\\s*:\\s*-*[0-9]+(px)+\\s*;")).get(0));

            }

            //getting the product price
            finderElement = elem.select("td:matchesOwn(\\d+[.,]\\d+\\s*€)"); //price
            cardPrice = finderElement.html();

            //create the product to be added

            Product product = ((cardName!=null)&&(cardExpansionName!=null)) ? new Product(cardName,cardExpansionName) : null;

            if (product !=null){
                //set the price
                product.setCardPrice(cardPrice);
                if (list.size()==2){
                    product.setXOffset(list.get(0));
                    product.setYOffset(list.get(1));
                }

                if (dimensionValues.size()==2){
                    product.setXSize(dimensionValues.get(0));
                    product.setYSize(dimensionValues.get(1));
                }

                product.setCardUrlString(this.pageURL.content+cardNameUrlSuffix);
                if (this.mAssociatedProducts ==null)  this.mAssociatedProducts = new ArrayList<>();
                this.mAssociatedProducts.add(product);
            }

            i++;
        }


    }



}
	
	


	
	
	
	
	
	
	
	
	
	
	
	


