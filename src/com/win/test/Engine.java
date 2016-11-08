package com.win.test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public abstract class Engine {
	
	// toutes les fields #fields

	
	private static int startIdOffset = 0;
	public static String lastSearch = "/0";
	public static CardSearchTable lastSearchCST = null;
	public static String mainSiteString = "http://www.magiccardmarket.eu";
	public static File cachedSearches = new File("/storage/sdcard1");
	public static boolean loadPagesFromStorage = false;
	public static URL mainSiteURL;
	public static final String PREFS_NAME = "PEANUT_BUTTER";
	
	
	
	//dealing with the search tables #tables
	
	public static String returnExpansionName(String in){
		in = Parser.returnNestedParameter(in, "onmouseover");
		in = Parser.cleanFunctions(in);
		
		return in;
	}
	
	
	public static int returnExResID(String regex){
		if (regex.equals("Future Sight")){
			return R.drawable.beta;
		}
		return R.drawable.alpha;
	}
	
	public static int returnNextIdOffset(){
		startIdOffset += 1000;
		return startIdOffset;
	}
	
	public static void resetStartIdOffset(){
		startIdOffset = 0;
	}
	
	public static boolean checkIdIntegrity(int input , Activity a){
		if (a.findViewById(input)!=null){
			return false;
		}
		return true;
	}

	public static boolean checkIdIntegrity(int input , View a){
		if (a.findViewById(input)!=null){
			return false;
		}
		return true;
	}
	
	
	//dealing with files #files

	
	
	public static File returnCachedSearchFileNames(String cardName, int pageNumber){
		return new File(Engine.cachedSearches  + File.separator  + "MKMSEARCH_" + cardName + "_" + pageNumber);
	}

	
	
	
	//dealing with URLs and network #url
	
	
//	public static boolean isOnline(Activity hook){
//		ConnectivityManager cm = (ConnectivityManager) hook.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo info = cm.getActiveNetworkInfo();
//		if (info!=null && info.isConnectedOrConnecting()){
//			return true;
//		}
//		return false;
//	}
	
	
	public static URL returnSearchUrlObject(String card){
		try {
			return new URL(Engine.returnSearchUrlString(card));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static URL returnSearchUrlObject(String card, int pageNumber){
		try {
			return new URL(returnSearchUrlString(card, pageNumber));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String returnSearchUrlString(String card){
		return mainSiteString + "/?mainPage=showSearchResult&searchFor=" + card + "&resultsPage=0" ;
	}

	public static String returnSearchUrlString(String card, int pageNumber){
		if (pageNumber < 1){
			pageNumber = 1;
		}
		return mainSiteString + "/?mainPage=showSearchResult&searchFor=" + card + "&resultsPage=" + --pageNumber + "" ;
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
	
	
	public static ArrayList<Integer> returnSpinnerFiller(int inputMax){
		ArrayList<Integer> foo = new ArrayList<Integer>(inputMax);
		for (int i = 1; i <= inputMax; i++){
			foo.add(i);
		}
		
		return foo;
	}

	// oh the hughmanatee and the strings #strings
	
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

	
	
	
	
	
	
	
	
	
	
	
	


