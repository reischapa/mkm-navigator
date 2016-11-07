package com.win.test;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;



public  abstract class InterfaceClasses {

	public static class ActionBarSearchListener implements OnEditorActionListener, OnClickListener{ ///omfediga;dsgj; aits an interface //#INTERFACE WHORE
																			//access Sat through non static field in start activity instance
		private final StartActivity at;
		public FirstCSTCreator SAT;
		
		public ActionBarSearchListener(StartActivity in){
			at = in;
		}
		
		private void kickstart(){
			EditText et = (EditText) at.findViewById(R.id.action_bar_search_box);
			String search = et.getText().toString();
			SAT = new FirstCSTCreator(search, at);
			SAT.execute();
		}
		
		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			if (actionId == EditorInfo.IME_ACTION_SEARCH){
				if ((SAT==null) || (SAT!=null && SAT.getStatus().equals(AsyncTask.Status.FINISHED))){
	            	kickstart();
	            }	
			}
			return true;
		}

		@Override
		public void onClick(View v) {
			if ((SAT==null) || (SAT!=null && SAT.getStatus().equals(AsyncTask.Status.FINISHED))){
            	kickstart();
            }	
		}
	
		
	}
	
	
	public static class SearchFragListenerStub implements OnClickListener, OnItemSelectedListener{
		
		public SearchFragment mHook;
		public ChangePageSearchTask spc;
		protected int directionOfDelta = 0;
		
		public SearchFragListenerStub(SearchFragment at){
			mHook=at;
		}
		
		
		protected int retComp(){
			if (directionOfDelta==1){
				return mHook.mCurrentSearchPageNumber+1;
			} else if (directionOfDelta==-1){
				return mHook.mCurrentSearchPageNumber-1;
			} else {
				return mHook.mCurrentSearchPageNumber;
			}
		}
		
		
		private void kickstart(int newSearchNumber){
			((ProgressBar) mHook.getView().findViewById(R.id.waiter)).setVisibility(View.VISIBLE);
			spc = new ChangePageSearchTask(mHook, newSearchNumber);
			spc.execute();
		}
		
		private void execute(int newSearchNumber){
			if ((newSearchNumber >= 1) && (newSearchNumber<=mHook.bDataCST.maxSearchPage)){
	            if (!Engine.isInList(mHook.mGlobalSearchResultsIndices, newSearchNumber)){
	            	if ((spc==null) || (spc!=null && spc.getStatus().equals(AsyncTask.Status.FINISHED))){
	                   	kickstart(newSearchNumber);
	                }	
	            } else if (Engine.isInList(mHook.mGlobalSearchResultsIndices, newSearchNumber)) {
	            	mHook.mCurrentSearchPageNumber =newSearchNumber;
	            	mHook.bDataCST = new CardSearchTable(mHook.mGlobalSearchResults.get(mHook.mCurrentSearchPageNumber));
	            	mHook.updateDisplayedCST();
	            	
	            }
	        }
		}
	
		@Override
		public void onClick(View v) {
			
			execute(retComp());
	    			
		}


		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			execute(arg2+1);
			
		}


		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	
	
	
	
	

	public static class BackButtonPressListener extends SearchFragListenerStub implements OnClickListener, OnItemSelectedListener {	
				public BackButtonPressListener(SearchFragment at){
					super(at);
					directionOfDelta = -1;
				}
				
	}

	public static class ForwardButtonPressListener extends SearchFragListenerStub implements OnClickListener, OnItemSelectedListener{
			public ForwardButtonPressListener(SearchFragment at){
				super(at);
				directionOfDelta = 1;
			}
		}

}
