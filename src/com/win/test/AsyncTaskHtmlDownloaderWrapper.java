package com.win.test;

import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;

public class AsyncTaskHtmlDownloaderWrapper extends AsyncTask<Void,Void,String> {

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