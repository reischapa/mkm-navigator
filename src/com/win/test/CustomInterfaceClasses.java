package com.win.test;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;



public  abstract class CustomInterfaceClasses {

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
	
	
	public static class SearchFragListenerStub implements OnClickListener{
		
		public SearchFragment mHook;
		public IndieCSTCreator spc;
		protected boolean directionOfDelta;
		
		
		public SearchFragListenerStub(SearchFragment at){
			mHook=at;
		}
		
		
		protected int retComp(){
			if (directionOfDelta){
				return mHook.mCurrentSearchPageNumber+1;
			} else if (!directionOfDelta){
				return mHook.mCurrentSearchPageNumber-1;
			} else {
				return mHook.mCurrentSearchPageNumber;
			}
		}
		
		public void kickstart(){
			
			
			
			((ProgressBar) mHook.getView().findViewById(R.id.waiter)).setVisibility(View.VISIBLE);
			spc = new IndieCSTCreator(mHook, retComp());
			spc.execute();
		}
	
		@Override
		public void onClick(View v) {
			if (retComp() >= 1){
	            if (!Engine.isInList(mHook.mGlobalSearchResultsIndices, retComp())){
	            	if ((spc==null) || (spc!=null && spc.getStatus().equals(AsyncTask.Status.FINISHED))){
	                   	kickstart();
	                }	
	            } else if (Engine.isInList(mHook.mGlobalSearchResultsIndices, retComp())) {
	            	mHook.mCurrentSearchPageNumber = retComp();
	            	mHook.bDataCST = new CardSearchTable(mHook.mGlobalSearchResults.get(mHook.mCurrentSearchPageNumber));
	            	RelativeLayout rel =(RelativeLayout) mHook.getView().findViewById(R.id.searchActivityListRel);
	            	rel.removeAllViews();
	            	mHook.bDataCST.createViews(rel);
	            	((TextView) mHook.getActivity().findViewById(R.id.pageID)).setText(mHook.mCurrentSearchPageNumber + "");
	            	mHook.recordCurrentCSTInIndex();
	            	mHook.getView().invalidate();
	            	
	            }
	        }
	    
			
		}
		
	}

	
	
	
	
	
	

	public static class BackButtonPressListener extends SearchFragListenerStub {	
				public BackButtonPressListener(SearchFragment at){
					super(at);
					directionOfDelta = false;
				}
				
	}

	public static class ForwardButtonPressListener extends SearchFragListenerStub{
			public ForwardButtonPressListener(SearchFragment at){
				super(at);
				directionOfDelta = true;
			}
		}

}
