package com.win.test;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class StartActivity extends Activity implements ExpandableForFragments {
	public static final String CARD_NAME= "com.win.test.CARD_NAME";
	public static final String CARD_ID = "com.win.test.CARD_ID";
	public static final String CARD_TABLE = "com.win.test.CARD_TABLE";
	public static Activity potatoes; // why must thou needst to debug?, Blogger.java??
	
	private String displayedText="";
	private boolean bIsFragContainerDisplayed = false;
	
	
	public InterfaceClasses.ActionBarSearchListener mSearchActionBarListenerInstance = new InterfaceClasses.ActionBarSearchListener(this); //LOOK AT ME IM USING INTERFACES LIKE A BAUSSSS

	// SETTINGS NAMES
	public static final String SAVE_PAGE_FLAG = "com.win.test.SAVE_PAGES_FLAG";
	public static final String FRAG_DISP_SURV_GUARANTEE = "com.win.test.FRAG_DISP_SURV_GUARANTEE";
	
	//SETTINGS VARIABLES
	private SharedPreferences mSharedPrefs;
	private boolean mNameVar = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		mSharedPrefs = getSharedPreferences(Engine.PREFS_NAME, Context.MODE_PRIVATE);
		mNameVar = mSharedPrefs.getBoolean(StartActivity.SAVE_PAGE_FLAG, false); //GODDAMMIT, ITS STATIC IDIOT!!!@!!!!@#@#@(*#%234956987234
		
		 //important step, using settings manager for the first time hueheuehuehuehueuehueueehueuhh uim a banana and a strawberry
		
		if (savedInstanceState!=null){
			displayedText = savedInstanceState.getString("deb");
			bIsFragContainerDisplayed = savedInstanceState.getBoolean(FRAG_DISP_SURV_GUARANTEE);
		}
		
		potatoes = this;
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	setContentView(R.layout.start_activity_layout_h);
        } else {
        	setContentView(R.layout.start_activity_layout_v);
        }
		
		try {
			Engine.mainSiteURL = new URL(Engine.mainSiteString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		
		TextView tv = (TextView) findViewById(R.id.testing);
		tv.setText(displayedText);
		if (tv.getText().toString().isEmpty()){
			Blogger.displayToStartActivity("Saving pages is now: " + mNameVar);
		}
		
		isFragViewExpanded();
		
		invalidateOptionsMenu();
		
	}
	
	
	//menu dealings #menu
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case (R.id.action_toggle):
				mNameVar = !mNameVar;
				saveCurrentSettings();
				Blogger.displayToStartActivity("Saving pages is now: " + mNameVar);
				invalidateOptionsMenu();
				return true;
			case (R.id.action_search):
				
				item.expandActionView();
				EditText et = (EditText) findViewById(R.id.action_bar_search_box);
				
				et.setOnEditorActionListener(mSearchActionBarListenerInstance);
				
				InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			    inputMethodManager.toggleSoftInputFromWindow(et.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
				
				Button but = (Button) findViewById(R.id.action_bar_search_button);
				but.setOnClickListener(mSearchActionBarListenerInstance);
				
				return true;
				
			case (R.id.action_clear_text):
				TextView tv = (TextView) findViewById(R.id.testing);
				tv.setText("");
				hideFragViews();
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu){
		MenuItem item = menu.findItem(R.id.action_search);
		
		if (item.getActionView()==null){
			item.setActionView(R.layout.search_action_view);
		}
		
		menu.findItem(R.id.action_toggle).setChecked(mNameVar);
		return true;
	}
	
	//savedInstanceBundles
	@Override
	public void onSaveInstanceState(Bundle savedInstancestate){
		TextView tv = (TextView) findViewById(R.id.testing);
		String two = (String) tv.getText();
		savedInstancestate.putString("deb", two);
		savedInstancestate.putBoolean(FRAG_DISP_SURV_GUARANTEE, bIsFragContainerDisplayed);
		super.onSaveInstanceState(savedInstancestate);
	}
	
	
	@Override
	protected void onPause(){
		super.onPause();
		saveCurrentSettings();
	}
	
	public void onBackPressed(){
		if (bIsFragContainerDisplayed){
			hideFragViews();
		} else {
			super.onBackPressed();
		}
	}
	
	//methods not related to app lifecycle #notcycle
	
	private void saveCurrentSettings(){
		mSharedPrefs = getSharedPreferences(Engine.PREFS_NAME, Context.MODE_PRIVATE);
		mSharedPrefs.edit().putBoolean(StartActivity.SAVE_PAGE_FLAG, mNameVar).commit();
		
	}

	public boolean expandFragViews(){
		FrameLayout fl = (FrameLayout) findViewById(R.id.frag_container1);
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	fl.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        	fl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
        }
		fl.invalidate();
		bIsFragContainerDisplayed = true;
		return true;
	}
	
	public boolean hideFragViews(){
		FrameLayout fl = (FrameLayout) findViewById(R.id.frag_container1);
		fl.removeAllViews();
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	fl.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 0));
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        	fl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 0));
        }
		
		
		
		fl.invalidate();
		bIsFragContainerDisplayed = false;
		return true;
	}
	
	
	private void isFragViewExpanded(){
		if (bIsFragContainerDisplayed){
			expandFragViews();
		} else {
			hideFragViews();
		}
	}
	
	
}
