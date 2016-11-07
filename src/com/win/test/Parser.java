package com.win.test;

import java.util.ArrayList;

public abstract class Parser {
	
	public static String removeTag(String info, String initTag, String finalTag){//WORKS
		char[] shit = info.toCharArray();
		int main = 0;
		int pos = 0;
		String res = "";
		for (pos=0 ; pos < shit.length ; pos++){
			main = 0;
			if (pos<info.length()-initTag.length()){
				String tested = info.substring(pos,pos+initTag.length());
				if (tested.equals(initTag)){
					main = pos+initTag.length();
					String drep="";
					while (!drep.equals(finalTag)){
						drep = info.substring(main,main+finalTag.length());
						main++;
					}
					main = main+finalTag.length();
					
					
					if (main>=info.length()){
						res = res + info;
					} else {
						res = res + info.substring(0, pos+1) + info.substring(main);
					}
					return res;
					
				}	
			}
		}
		return null;
	}
	
	public static String getHeaderContents(String info, String initTag, String finalTag){//WORKS
		char[] shit = info.toCharArray();
		int main = 0;
		int pos = 0;
		String res = "";
		for (pos=0 ; pos < shit.length ; pos++){
			main = 0;
			if (pos<info.length()-initTag.length()){
				String tested = info.substring(pos,pos+initTag.length());
				if (tested.equals(initTag)){
					main = pos+initTag.length();
					String drep="";
					while (!drep.equals(finalTag)){
						drep = info.substring(main,main+finalTag.length());
						main++;
					}
					main = main+finalTag.length();
					res = info.substring(pos, main-1);
					break;
				}	
			}
		}
		return res;
	}
	
	public static String removeSelectedTagsFromHTML(String info){
		while (info.indexOf("<script")!=-1){
			info = removeTag(info,"<script","/script>");
		}
		
		while (info.indexOf("<noscript")!=-1){
			info = removeTag(info,"<noscript","/noscript>");
		}
		
		return info;
	}

	

	public static String returnAllDivs(String info){
		String initTag = "<div";
		String finalTag = "/div>";
		char[] shit = info.toCharArray();
		int main = 0;
		int pos = 0;
		String res = "";
		int count1 = 0;
		int count2 = 0;
		int foo = 0;
		String n;
		for (pos=0 ; pos < (shit.length - finalTag.length()-1) ; pos++){
				main = pos+finalTag.length();
				String tested = info.substring(pos,main);
				foo=pos;
				if (tested.equals(initTag + " ") || tested.equals(initTag + ">")){
					count1++;
					foo=pos;
					pos = pos + finalTag.length();
					main = pos + finalTag.length();
					while (count2!=count1){
						n = info.substring(pos, main);
						if (n.equals(initTag + " ") || n.equals(initTag + ">")) {
							count1++;
						} else if (n.equals(finalTag)){
							count2++;
						}
						pos++;
						main = pos + finalTag.length();
					}					
					res = res + info.substring(foo, main-1);
				}	
			}
		return res;
	}
	
	
	public static String returnNthDiv(String info, int nth){
		return returnNthTagDebug(info, "<div", "/div>", nth);
	}

	public static String returnFirstDiv(String info){
		return returnFirstTag(info, "<div", "/div>");
	}

	
	
	public static String returnAllSameTags(String info, String initTag, String finalTag){
		char[] shit = info.toCharArray();
		int main = 0;
		int pos = 0;
		String res = "";
		int count1 = 0;
		int count2 = 0;
		int foo = 0;
		String n;
		for (pos=0 ; pos < (shit.length - finalTag.length()-1) ; pos++){
				main = pos+finalTag.length();
				String tested = info.substring(pos,main);
				foo=pos;
				if (tested.equals(initTag + " ") || tested.equals(initTag + ">")){
					count1++;
					foo=pos;
					pos = pos + finalTag.length();
					main = pos + finalTag.length();
					while (count2!=count1){
						n = info.substring(pos, main);
						if (n.equals(initTag + " ") || n.equals(initTag + ">")) {
							count1++;
						} else if (n.equals(finalTag)){
							count2++;
						}
						pos++;
						main = pos + finalTag.length();
					}					
					res = res + info.substring(foo, main-1);
				}	
			}
		return res;
	}
	
	
	public static String returnFirstTag(String info , String initTag, String finalTag){
		
		int main = 0;
		int pos = 0;
		String res = "";
		int count1 = 0;
		int count2 = 0;
		int foo = 0;
		String n;
		for (pos=0 ; pos < (info.length() - finalTag.length()-1) ; pos++){
				main = pos+finalTag.length();
				String tested = info.substring(pos,main);
				foo=pos;
				if (tested.equals(initTag + " ") || tested.equals(initTag + ">")){
					count1++;
					foo=pos;
					pos = pos + finalTag.length();
					main = pos + finalTag.length();
					while (count2!=count1){
						n = info.substring(pos, main);
						if (n.equals(initTag + " ") || n.equals(initTag + ">")) {
							count1++;
						} else if (n.equals(finalTag)){
							count2++;
						}
						pos++;
						main = pos + finalTag.length();
					}					
					res = info.substring(foo, main-1);
					return res;
				}	
			}
		return null;
	}
	
//	public static String specialReturnFirstTag(String input, String initTag, String finalTag){
//		
//		if ((input.indexOf(initTag)!=-1) & (input.indexOf(finalTag)!=-1)){
//			return input.substring(input.indexOf(initTag), input.indexOf(finalTag) + finalTag.length());
//		} else {
//			return null;
//		}
//	} A FOSSIL FOR THE GENERATIONS TO COME


	public static String returnNthTagDebug(String info, String initTag ,String finalTag ,int nth){
		int foo = 1;
		int pos = 0;
		int len = initTag.length();
		for (pos=0 ; pos < info.length() ; pos++){
				if ((info.substring(pos,pos+len)).equals(initTag)){
					if (foo!=nth){
						foo++;
					} else {
						return returnFirstTag(info.substring(pos-1), initTag, finalTag);
					}
				}	
			
		}
		
		return null;	
		
	}
	
	public static int countNumberOfTags(String info ,String initTag){
		int foo = 0;
		int len = initTag.length();
		for (int pos=0 ; pos < info.length()-len ; pos++){
				if ((info.substring(pos,pos+len)).equals(initTag)){
					foo++;
				}	
			
		}
		if (foo==0){
			return -1;
		} else {
			return foo;
		}
		
	}
	
	


	public static String returnNestedParameter(String info, String param){ //returns value of the first parameter within the first tag
		int pos = info.indexOf(param);
		pos = pos + param.length();
		info = info.substring(pos);
		int main;
		for (main=0; main < info.length() -param.length() ; main++){
			if (info.charAt(main)=='\"'){
				return info.substring(0,main);
			}
		}
		
		return "";

		
		
	
	}



	public static String returnInnerContents(String info){ /*returns content between <tag and /tag>*/
		
		
		
		int bar = info.indexOf('>');
		
		if (bar == -1){
			return info;
		}
		
		int foo  = info.indexOf('<');
		
		if (foo>0){
			info = info.substring(0,foo) + info.substring(bar+1);;
		} else {
			info = info.substring(bar+1);
		}
		
		char[] shit = info.toCharArray();
		
		for (int step = shit.length-1; step>0; step--){
			
				if (shit[step]=='<'){
					int y = info.lastIndexOf('>');
					String t = "";
					if (y<info.length()-1){
						t  = info.substring(0,step) + info.substring(y+1);
					} else {
						t  = info.substring(0,step);
					}
					
					info = returnInnerContents(t);
					return info;
				}
			
		}
		
		return info;
	}


	public static String killAll(String info){  // kills all <> content in a String
		return returnInnerContents(info);
	}

	


	public static String priceFormatter(String in){
		int place = in.indexOf("&");
		if (place != -1){
			in = in.substring(0,place);
			return in.trim() + " " + "\u20ac";
		} else {
			return in;
		}
	}


	public static String cleanFunctions(String in){ //returns arguments to javascript and other functions
		int foo = in.indexOf("'");
		if (foo==-1){
			foo = in.indexOf("\"");
		}
		
		in = in.substring(foo+1);
		
		foo = in.indexOf("\')");
		in = in.substring(0, foo);
		
		
		return in;
	}
	
	
	//Parsing tables... #tables
	
	public static ArrayList<ArrayList<String>> returnParsedTable(
			String inputSourceCode, String initrowTag, String finalrowTag, String initcolTag,String finalcolTag){
		
		ArrayList<ArrayList<String>> tableRows = new ArrayList<ArrayList<String>>(30);
		
		int count = countNumberOfTags(inputSourceCode, initrowTag);
		
		int foo = 1;
		
		while (foo<=count){
			
			String bench = returnNthTagDebug(inputSourceCode, initrowTag, finalrowTag, foo);
			
			int benchCount = countNumberOfTags(bench, initcolTag);
			
			int bar = 1 ;
			ArrayList<String> holster = new ArrayList<String>(8);
			
			while (bar<=benchCount){
				
				holster.add(returnNthTagDebug(bench, initcolTag, finalcolTag, bar));
				bar++;
			}
			
			tableRows.add(holster);
				foo++;
		}
		
		
		
		return tableRows;
		
	}
	
	
	

	
	
}
