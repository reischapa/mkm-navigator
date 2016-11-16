package com.mkmnav.backend.PageTypesMKM;

import com.mkmnav.backend.CardFramework.Card;
import com.mkmnav.backend.Motor;
import com.mkmnav.backend.PageFramework.ZeroPage;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchPage extends ZeroPage implements Serializable{

    //SearchPage identifier
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
        System.out.println(input);
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


    private ArrayList<Card> mAssociatedCards = null; //all cards associated with this instance
    private String mCardNameQuery = null;
    private String mHtmlSourceCode = null;
    private String mRootUrlString;



    public SearchPage(String mCardNameQuery, Motor.MAINSITEURLS site, int mSearchPageNumber, String mHtmlSourceCode){
        super(Motor.PAGETYPES.SEARCH,Motor.MAINSITEURLS.MKM_EU);
        this.mCardNameQuery = mCardNameQuery;
        this.mHtmlSourceCode = mHtmlSourceCode;
        this.mRootUrlString = site.content;
    }

    public SearchPage(SearchPage in){
        super(Motor.PAGETYPES.SEARCH,Motor.MAINSITEURLS.MKM_EU);
        this.mHtmlSourceCode = in.mHtmlSourceCode;
        this.mRootUrlString = in.mRootUrlString;
    }



//    public SearchPageID getSearchPageID(){
//        return this.mSearchPageID;
//    }

    public ArrayList<Card> getAssociatedCards(){
        return this.mAssociatedCards;
    }



    public void parse(){
        Document mHtmlDocument = Jsoup.parse(this.mHtmlSourceCode);
        Elements elems = mHtmlDocument.select("table[class=MKMTable SearchTable] tbody tr");

        String cardName = "";
        String cardNameUrlSuffix = "";
        String cardExpansionImageUrlSuffix = "";
        String cardExpansionName = "";
        String cardPrice = "";


        int i = 0;
        while (i < elems.size()){
            Element elem = elems.get(i);
            Elements finderElement;

            //getting the card name
            finderElement = elem.select("a[href*=/Products/Singles]"); //name
            cardName = finderElement.html();

            //getting the card link
            cardNameUrlSuffix = finderElement.attr("href");

            //getting the expansion link
            Pattern expansionPattern = null;
            finderElement = elem.select("a[href][class*=noborder] span[class*=expansionIcon]");
            String expansionStyleCSS = finderElement.attr("style");
            expansionPattern = Pattern.compile("'\\s*[\\s\\w_'%.\\\\/]*expicons.\\w+\\s*'");//TODO may be problematic
            Matcher match = expansionPattern.matcher(expansionStyleCSS);
            match.find();
            cardExpansionImageUrlSuffix = match.group();
            cardExpansionImageUrlSuffix = cardExpansionImageUrlSuffix.substring(0,cardExpansionImageUrlSuffix.length()).trim();

            //and the expansion name
            expansionPattern = Pattern.compile("'\\s*[\\w\\s]*\\s*'");
            String expCSSOnMouseOverArg = finderElement.attr("onmouseover");
            match= expansionPattern.matcher(expCSSOnMouseOverArg);
            match.find();
            cardExpansionName  = match.group();
            cardExpansionName = cardExpansionName.substring(1,cardExpansionName.length()-1).trim();

            //get background offset of the picture, used for the expansion icon
            expansionPattern = Pattern.compile("background-position\\s*:\\s*-*[0-9]+(px)+\\s*-*[0-9]+(px)+;", Pattern.CASE_INSENSITIVE);
            match = expansionPattern.matcher(expansionStyleCSS);
            match.find();
            ArrayList<Integer> list = SearchPage.getNumericalValuesFromUnits(match.group());

            //get width and height of the area corresponding to the expansion icon
            String[] dimensionNames = {"width", "height"};
            ArrayList<Integer> dimensionValues = new ArrayList<>();
            for (int j = 0;j < dimensionNames.length; j++){
                expansionPattern = Pattern.compile(dimensionNames[j]+"\\s*:\\s*-*[0-9]+(px)+\\s*;", Pattern.CASE_INSENSITIVE);
                match = expansionPattern.matcher(expansionStyleCSS);
                match.find();
                dimensionValues.add(SearchPage.getNumericalValuesFromUnits(match.group()).get(0));
            }

            //getting the card price
            finderElement = elem.select("td:matchesOwn(\\d+[.,]\\d+\\s{0,1}€)"); //price
            cardPrice = finderElement.html();

            //create the card to be added

            Card card = ((cardName!=null)&&(cardExpansionName!=null)) ? new Card(cardName,cardExpansionName) : null;

            //add the other fields if it is not null
            if (card!=null){
                //set the price
                card.setCardPrice(cardPrice);
                if (list.size()==2){
                    card.setXOffset(list.get(0));
                    card.setYOffset(list.get(1));
                }

                if (dimensionValues.size()==2){
                    card.setXSize(dimensionValues.get(0));
                    card.setYSize(dimensionValues.get(1));
                }

                card.setCardUrlString(this.mRootUrlString+cardNameUrlSuffix);
                if (this.mAssociatedCards==null)  this.mAssociatedCards = new ArrayList<>();
                this.mAssociatedCards.add(card);
            }

            i++;
        }


    }















	public static String returnImageExtension(String imgurl, String tag){
		return imgurl.substring(findLastCharInString(imgurl, tag)+1);
	}
	
	//dealing with indices #index
	
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
	
	
	//fragments #frag

	
	
	
	
	
	
	
	
	
	
	
	


