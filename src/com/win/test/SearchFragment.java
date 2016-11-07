package com.win.test;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


public class SearchFragment extends Fragment  {
	

    public CardSearchTable bDataCST = null;
    
	public int mCurrentSearchPageNumber = 1;
	
	
	public HashMap<Integer,CSTSerializable> mGlobalSearchResults = new HashMap<Integer,CSTSerializable>();
	public ArrayList<Integer> mGlobalSearchResultsIndices = new ArrayList<Integer>();
	public InterfaceClasses.ForwardButtonPressListener mForwardListener;
	public InterfaceClasses.BackButtonPressListener mBackListener;

	public static final String CST_SURVIVAL_GUARANTEE = "com.win.test.CST_TRANSFER";
	public static final String CST_PAGE_NUMBER_SURVIVAL_GUARANTEE = "com.win.test.CST_PAGE_NUMBER_SURVIVAL_GUARANTEE";
	public static final String CST_PREV_PAGE_SURVIVAL_GUARANTEE = "com.win.test.CST_PREV_PAGE_SURVIVAL_GUARANTEE";
    public static final String CST_PREV_PAGE_INDICES_SURV_GUARANTEE = "com.win.test.CST_PREV_PAGE_INDICES_SURV_GUARANTEE";
    public static final String SEARCH_FRAGMENT_EXISTENCE_TAG  = "com.win.test.SEARCH_FRAGMENT_TRANSACTION_ID";
    
    
   
    
    public enum cake{
    	one("com.win.test.SEARCH_FRAGMENT_TRANSACTION_ID");
    	private cake(String in){
    		b = in;
    	}
    	String b;
    	
    }
    
    
    private View mFinalView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup root, Bundle savedInstanceState) {
		
        
		if (savedInstanceState!=null){
//			bDataCST = new CardSearchTable((CSTSerializable) savedInstanceState.getSerializable(SearchFragment.CST_SURVIVAL_GUARANTEE));
//			mCurrentSearchPageNumber = savedInstanceState.getInt(CST_PAGE_NUMBER_SURVIVAL_GUARANTEE);
//			mGlobalSearchResults = (HashMap<Integer, CSTSerializable>) savedInstanceState.getSerializable(CST_PREV_PAGE_SURVIVAL_GUARANTEE);
//			mGlobalSearchResultsIndices = (ArrayList<Integer>) savedInstanceState.getSerializable(CST_PREV_PAGE_INDICES_SURV_GUARANTEE);
			setArguments(savedInstanceState);
		}
			

			//recordCurrentCSTInIndex(1); this will need to be checked
		
        if (bDataCST.maxSearchPage==1){
        	mFinalView = inflater.inflate(R.layout.search_layout_simple, root, false);
            bDataCST.createViews((RelativeLayout)mFinalView.findViewById(R.id.searchActivityListRel));
            
            
        } else {
            
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            	mFinalView = inflater.inflate(R.layout.search_activity_layout_v, root, false);
            } else {
            	mFinalView = inflater.inflate(R.layout.search_activity_layout_h, root, false);
            }

            
            bDataCST.createViews((RelativeLayout)mFinalView.findViewById(R.id.searchActivityListRel));
            TextView tv = ((TextView) mFinalView.findViewById(R.id.pageID));
            tv.setText(mCurrentSearchPageNumber + "");
            
            Button next = (Button) mFinalView.findViewById(R.id.NextButton);
            Button prev = (Button) mFinalView.findViewById(R.id.PreviousButton);
            
//            if (mCurrentSearchPageNumber<=1){
//                prev.setEnabled(false);
//            }
//            
//            if (mCurrentSearchPageNumber>= bDataCST.maxSearchPage){
//                next.setEnabled(false);
//            }
            
            mForwardListener = new InterfaceClasses.ForwardButtonPressListener(this);
            mBackListener = new InterfaceClasses.BackButtonPressListener(this);
            next.setOnClickListener(mForwardListener);
            prev.setOnClickListener(mBackListener);
            populateSpinner();

        }
        return mFinalView;
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putSerializable(SearchFragment.CST_SURVIVAL_GUARANTEE, bDataCST.returnSerializableSelf());
		savedInstanceState.putInt(CST_PAGE_NUMBER_SURVIVAL_GUARANTEE, mCurrentSearchPageNumber);
		savedInstanceState.putSerializable(CST_PREV_PAGE_SURVIVAL_GUARANTEE, mGlobalSearchResults);
		savedInstanceState.putSerializable(CST_PREV_PAGE_INDICES_SURV_GUARANTEE, mGlobalSearchResultsIndices);
		
	}


	@Override
	public void setArguments(Bundle savedInstanceState){
		bDataCST = new CardSearchTable((CSTSerializable) savedInstanceState.getSerializable(SearchFragment.CST_SURVIVAL_GUARANTEE));
		mCurrentSearchPageNumber = savedInstanceState.getInt(CST_PAGE_NUMBER_SURVIVAL_GUARANTEE);
		mGlobalSearchResults = (HashMap<Integer, CSTSerializable>) savedInstanceState.getSerializable(CST_PREV_PAGE_SURVIVAL_GUARANTEE);
		mGlobalSearchResultsIndices = (ArrayList<Integer>) savedInstanceState.getSerializable(CST_PREV_PAGE_INDICES_SURV_GUARANTEE);
		//super.setArguments(savedInstanceState);
		
	}
	
	
	@Override
	public void onPause(){
		super.onPause();
	}
	
	public boolean recordCurrentCSTInIndex(final int mNewSearchPageNumber){
		
		mGlobalSearchResultsIndices.add(mNewSearchPageNumber);
		mGlobalSearchResults.put(mNewSearchPageNumber, bDataCST.returnSerializableSelf());
		
		return true;
	}
	
	public boolean recordCurrentCSTInIndex(){
		
		mGlobalSearchResultsIndices.add(mCurrentSearchPageNumber);
		mGlobalSearchResults.put(mCurrentSearchPageNumber, bDataCST.returnSerializableSelf());
		
		return true;
	}
	
	//methods not related to app lifecycle #notCycle
	
	
	public void updateDisplayedCST(){
		
		RelativeLayout rel =(RelativeLayout) getView().findViewById(R.id.searchActivityListRel);
    	rel.removeAllViews();
    	bDataCST.createViews(rel);
    	
    	recordCurrentCSTInIndex();
    	
    	updateIndicatorsStatus();
    	
    	getView().invalidate();
    	
		  
	}
	
	private void updateIndicatorsStatus(){
		((TextView) getView().findViewById(R.id.pageID)).setText(mCurrentSearchPageNumber + "");
    	ProgressBar pb = (ProgressBar) getView().findViewById(R.id.waiter);
    	pb.setVisibility(ProgressBar.INVISIBLE);
	}
	
	
	public void populateSpinner(){
		Spinner heli = (Spinner) mFinalView.findViewById(R.id.spinner);
		ArrayAdapter<Integer> arr = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, Engine.returnSpinnerFiller(bDataCST.maxSearchPage));
		heli.setAdapter(arr);
		heli.setOnItemSelectedListener(new InterfaceClasses.SearchFragListenerStub(this));
		
	}
	
	
	
	
}
