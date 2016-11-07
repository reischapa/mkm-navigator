package com.win.test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class MT {
	

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
		ArrayList hm = MT.findSubStringLocations(tested, f);
		
		Integer foo =  (Integer) hm.get(hm.size()-1);
		
		if (foo==null){
			return -1;
		} else if (foo!=null){
			return foo;
		}
		
		return -1;
		
	}
	

	public static String returnExtention(String imgurl, String tag){
		return imgurl.substring(MT.findLastCharInString(imgurl, tag)+1);
		
	}
	
		
	
	public static ArrayList returnSubstringLocationIndexes(String tested, String f){
		char[] elem = tested.toCharArray();
		ArrayList hm = new ArrayList(10);
		char holder;
		
		int locationInString = 0;
		int index =0;
		
		String trueregex = "<" + f;
		String falseregex = "</" + f;
		
		for (char el : elem){
			
			if ((elem.length-locationInString)>=falseregex.length()){
				
				if (el== '<'){
					String Holder2 = tested.substring(locationInString,locationInString+falseregex.length()-1);
					
					if (Holder2.equals(falseregex)){
						hm.add(locationInString);
						index++;
					} else if (Holder2.substring(0,Holder2.length()-1).equals(trueregex)){
						hm.add(locationInString);
						index++;
					}
					
				}
			} 
			locationInString++;
		}
		
		if (hm.isEmpty()){
			hm.add(0);
		}
		
		
		return hm;
	}
	
	
	
	public static String contain(String t){
		
		char ref = t.charAt(0);
		
		char[] u = t.toCharArray();
		
		int step = 0;
		for (char w : u){
			if (step!=0){
				if (w == ref){
					return t.substring(1,step);
				}
			}
			step++;
		}
		
		
		return t;
	}
	
	
	public static ArrayList contain(String t, String beg, String end){
		
		int step = 0;
		ArrayList res = new ArrayList();
		
		ArrayList wat = MT.returnSubstringLocationIndexes(t, beg);
		
		Iterator iter = wat.iterator();
		
		while (iter.hasNext()){
			int y = (Integer) iter.next();
			String h = t.substring(y+beg.length()+1);
			while (step<=h.length()-end.length()){
				String temp = h.substring(step,step+end.length());
				if (temp.equals(end)){
					res.add(h.substring(1,step+end.length()));
				}
				step++;
			}
			
			
		}
		
		
		return res;
	}

	
	public static String gen(int num){
		
		Random rand = new Random();
		
		char[] symbols = new char[36];
		
		for (int idx = 0; idx < 10; ++idx)
		      symbols[idx] = (char) ('0' + idx);
		
		for (int idx = 10; idx < 36; ++idx)
		      symbols[idx] = (char) ('a' + idx - 10);
		
		char[] buf = new char[num];
		
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
	
}
	
	
	
