package com.win.test;

import java.net.URL;

import android.os.AsyncTask;

public class TableCreationStub extends AsyncTask<Void, String, Void> {

	protected String htmlSourceCode = null;
	protected int mCardTableIndex = 1;
	protected URL mInUrl = null;
	protected Table mContentsTableForm = null;
	
	public TableCreationStub(URL inSite){
		mInUrl = inSite;
	}
	
	public TableCreationStub(URL inSite, int cardinal){
		this(inSite);
		mCardTableIndex = cardinal;
	}
	
	public TableCreationStub(String inCode){
		htmlSourceCode = inCode;
	}
	
	public TableCreationStub(String inCode, int cardinal){
		this(inCode);
		mCardTableIndex = cardinal;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
			
			publishProgress("Downloading source HTML...");
			htmlSourceCode = ProgramInput.htmlTextInput(mInUrl);
			publishProgress("Downloading finished.");
				
			if (Parser.returnFirstTag(htmlSourceCode, "<table", "/table>")!=null){
				publishProgress("Creating new table object...");
				mContentsTableForm = new Table(htmlSourceCode);
				publishProgress("Creation completed.");
				
			} else {
				publishProgress("No results found.");
			}
			
			
			return null;		
	}

	@Override
	protected void onProgressUpdate(String... input){
		Blogger.displayToStartActivity(input[0]);
	}
}
