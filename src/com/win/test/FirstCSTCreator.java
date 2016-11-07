package com.win.test;

import java.net.URL;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


///epic amounts of swag achieved
public class FirstCSTCreator extends TableCreationStub {

	private StartActivity at;
	private String cardName;
	private int pageNumber = 1;
	protected boolean flag = false;
	private CardSearchTable cst=null;
	
	public FirstCSTCreator(URL inUrl, String card, StartActivity in, int numb){
		super(inUrl);
		at = in;
		cardName = (card.replace(" ", "+")).toLowerCase();
		if (numb< 1){
			pageNumber = 1;
		} else {
			pageNumber = numb;
		}
	}
	
	public FirstCSTCreator(URL inUrl, String card, StartActivity in){
		super(inUrl);
		at = in;
		cardName = (card.replace(" ", "+")).toLowerCase();
	}
	
	public FirstCSTCreator(String card, StartActivity in, int numb){
		super(Engine.returnSearchUrlObject((card.replace(" ", "+")).toLowerCase(), numb));
		at = in;
		cardName = (card.replace(" ", "+")).toLowerCase();
		if (numb< 1){
			pageNumber = 1;
		} else {
			pageNumber = numb;
		}
	}
	
	public FirstCSTCreator(String card, StartActivity in){
		super(Engine.returnSearchUrlObject((card.replace(" ", "+")).toLowerCase()));
		at = in;
		cardName = (card.replace(" ", "+")).toLowerCase();
	}
	
	
	@Override
	public Void doInBackground(Void... params){
		super.doInBackground(params);
		if (mContentsTableForm!=null){
			cst = new CardSearchTable(mContentsTableForm, cardName);
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void params){
		
		if (cst!=null){
			
			
			if (at instanceof ExpandableForFragments){
				at.expandFragViews();
			}
			
			FragmentManager fm = at.getFragmentManager();
			FragmentTransaction trans = fm.beginTransaction();
			Fragment comp = fm.findFragmentByTag(SearchFragment.SEARCH_FRAGMENT_EXISTENCE_TAG);
			if (comp!=null){
				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
				trans.remove(comp).commit();
			}
			
			SearchFragment f1 = new SearchFragment();
			
			f1.setArguments(cst.createNewInfoBundle(pageNumber));
			f1.recordCurrentCSTInIndex();
			
			
			FragmentTransaction foo = fm.beginTransaction().add(R.id.frag_container1,f1,SearchFragment.SEARCH_FRAGMENT_EXISTENCE_TAG);
			foo.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			
			foo.commit();
			
		}
		
	}
}


