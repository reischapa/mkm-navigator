package com.win.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.AsyncTask;

public abstract class ProgramInput {
	
	public static class AsyncTaskHtmlDownloaderWrapper extends AsyncTask<Void,Void,String> {

		public Activity activityReference;
		public URL url;
		public String currentSearch;
		protected String result;
				
		public AsyncTaskHtmlDownloaderWrapper(URL u){
			url = u;
		}

		@Override
		protected String doInBackground(Void... params) {
			return ProgramInput.htmlTextInput(url);
			}

	}
	
	public static String textFileInput(File inname) {
		FileInputStream fis = null;
		String foo = "";
		String bar = "";
		try {
            fis = new FileInputStream(inname)	;
			BufferedInputStream bis = new BufferedInputStream(fis);
		    DataInputStream dis = new DataInputStream(bis);
			while ((foo = dis.readLine())!=null){
		    	if (!foo.isEmpty()){
			    	bar = bar + foo.trim();
				}
			}
			fis.close();
            bis.close();
            dis.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
		return bar;
    }
	
	public static String htmlTextInput(URL url){ // this method does the HTML fetching
		String foo = "";
		String bar = "";
        try {
            InputStream fis = url.openStream()	;
			InputStreamReader bis = new InputStreamReader(fis);
            BufferedReader dis = new BufferedReader(bis);
            while ((foo = dis.readLine())!=null){
				if (!foo.isEmpty()){
					bar = bar + foo.trim();
				}
			}
            fis.close();
            bis.close();
            dis.close();
		} catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Shit");
        }
		return bar;
	}
	

	public static String htmlDownloaderAndroid(URL url){
        try {
		    return new AsyncTaskHtmlDownloaderWrapper(url).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch bloc
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return null;
	}
	
	
	public static String htmlDownloaderAndroid(String in){
		try {
			URL url = new URL(in);
			return new AsyncTaskHtmlDownloaderWrapper(url).get();
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        } catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
        }
		
        return null;
		
    }
	

	public static String htmlDownloaderAndroidHooked(URL url, Activity hook){
	    try {
		    return new AsyncTaskHtmlDownloaderWrapper(url).get();
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (ExecutionException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	
		return null;
    }

}
