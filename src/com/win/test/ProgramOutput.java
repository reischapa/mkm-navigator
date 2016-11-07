
package com.win.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ProgramOutput {

	public static boolean saveStringToFile(String source, File destination) {
		
		try {
			destination.createNewFile();
			FileWriter fw = new FileWriter(destination);
			fw.write(source.trim());				
			fw.close();
			return true;
		} catch (IOException e) {}
		return false;
		
	}
	
	
	public static boolean htmlToFile(String url, File out) {
		
	    
        URL u =null;
        InputStream is = null;
        int q;
        FileOutputStream fw = null;
        HttpURLConnection ht = null;
        
        try {
			out.createNewFile();
		} catch (IOException e2) {}
        
		try {
			fw = new FileOutputStream(out);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
       
        
        try {
            u = new URL(url);
        } catch (MalformedURLException ex) {}
        
        try {
			ht = (HttpURLConnection) u.openConnection();
		} catch (IOException e1) {}
        
        ht.setRequestProperty("User-Agent",  "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:23.0) Gecko/20131011 Firefox/23.0");
        
        try{
        	
        	
            is = ht.getInputStream();
            
            
            byte[] b = new byte[2048];
            
            while ((q = is.read(b)) != -1){
            	
                	fw.write(b,0,q);
						
                }            
        } catch (IOException e){}
        
        try {
			is.close();
			fw.close();
		} catch (IOException e) {}
        
        return true;
        
	}
	
	public static String allThingsOnline(String url, File root) {
		
	
		URL imgsource = null;
		InputStream is = null;
		byte[] b = new byte[2048];
		FileOutputStream fout = null;
		
		String filename = Engine.returnImageExtention(url,"/");
		String filepath = root + File.separator + filename;
		
		File foo = new File(filepath);
		int count=0;
		try{
			imgsource = new URL(url);
		} catch (MalformedURLException e){}
		try {
			is =  imgsource.openStream();
			fout = new FileOutputStream(foo);
			while ((count = is.read(b, 0, 2048))!=-1){
				fout.write(b, 0, count);
			}
			fout.close();
			is.close();	
		} catch (IOException e) {
			return "void";
		}		
			return filename;
		
		
	}


	
	
}
