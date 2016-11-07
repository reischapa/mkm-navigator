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

public class ChangePageSearchTask extends CSTCreationStub {

	private SearchFragment bHook;

	public ChangePageSearchTask(SearchFragment in, int numb){
		super(in.bDataCST, numb);
		bHook = in;
	}
	
	@Override
	protected void onPostExecute(Void params){
		if (outputCST!=null){
			
			bHook.mCurrentSearchPageNumber = pageNumber;
			
			bHook.bDataCST = new CardSearchTable(outputCST);
			
        	bHook.updateDisplayedCST();
			
		}
		
	}
	

}
