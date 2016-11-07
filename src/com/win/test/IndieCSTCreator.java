package com.win.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class IndieCSTCreator extends CSTCreationStub {
	
	
	private Activity at;
	private SearchFragment bHook;
	
	
	public IndieCSTCreator(SearchFragment in, int numb){
		super(in.bDataCST, numb);
		at = in.getActivity();
		bHook = in;
	}
	
	
	
	@Override
	protected void onPostExecute(Void params){
		if (outputCST!=null){
//			FragmentManager fm = at.getFragmentManager();
//			FragmentTransaction trans = fm.beginTransaction();
//			Fragment comp = fm.findFragmentByTag(SearchFragment.SEARCH_FRAGMENT_TRANSACTION_ID);
//			if (comp!=null){
//				//trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//				trans.remove(comp).commit();
//			}
//			
//			SearchFragment f1 = new SearchFragment();
//			
//			Bundle bar = bHook.getArguments();
//			
//			
//			f1.setArguments(CSTSerializable.createNewInfoBundle(outputCST, pageNumber, bar.getSerializable(SearchFragment.CST_PREV_PAGE_SURVIVAL_GUARANTEE), bar.getSerializable(SearchFragment.CST_PREV_PAGE_INDICES_SURV_GUARANTEE)));
////			
//			f1.recordCurrentCSTInIndex();
//		
//			FragmentTransaction foo = fm.beginTransaction().add(R.id.frag_container1,f1,SearchFragment.SEARCH_FRAGMENT_TRANSACTION_ID);
//			foo.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//			foo.commit();
			
			bHook.mCurrentSearchPageNumber = pageNumber;
			
			bHook.bDataCST = new CardSearchTable(outputCST);
        	RelativeLayout rel =(RelativeLayout) bHook.getView().findViewById(R.id.searchActivityListRel);
        	rel.removeAllViews();
        	bHook.bDataCST.createViews(rel);
        	((TextView) bHook.getActivity().findViewById(R.id.pageID)).setText(bHook.mCurrentSearchPageNumber + "");
        	bHook.recordCurrentCSTInIndex();
        	
        	ProgressBar pb = (ProgressBar) bHook.getView().findViewById(R.id.waiter);
        	pb.setVisibility(ProgressBar.INVISIBLE);
        	
        	
        	bHook.getView().invalidate();
			
			
			
			
		}
		
	}
	

}
