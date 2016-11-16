package com.mkmnav.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;


import com.mkmnav.R;

import com.mkmnav.android.fragments.SearchResultsFragment;
import com.mkmnav.android.parallelTaskDefinitions.AT_HTMLDownloader;
import com.mkmnav.android.parallelTaskStorage.ParallelTaskStorage;
import com.mkmnav.backend.CACertificateStrings;
import com.mkmnav.backend.Motor;
import com.mkmnav.backend.ProgramInput;
import com.mkmnav.backend.PageTypesMKM.SearchPage;

import java.util.ArrayList;

public class StartActivity extends Activity implements
		AT_HTMLDownloader.HTMLDownloader_InputCallback<Bundle>,
		AT_HTMLDownloader.HTMLDownloader_ExecutorCallback<Bundle>,
		AT_HTMLDownloader.HTMLDownloader_ReturnerCallback<Bundle>{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);


		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
        	setContentView(R.layout.search_activity_layout_h);
        } else {
        	setContentView(R.layout.search_activity_layout_v);
        }

		FragmentManager fragMan = getFragmentManager();

		boolean isSearchFragmentAttached = fragMan.popBackStackImmediate(SearchResultsFragment.IDENTIFIER_SEARCH_FRAGMENT,FragmentManager.POP_BACK_STACK_INCLUSIVE);

		if (ParallelTaskStorage.htmlDownloader==null) {
			ParallelTaskStorage.htmlDownloader = new AT_HTMLDownloader<Void, Bundle>(this, this, this);
			ParallelTaskStorage.htmlDownloader.execute();
		}


		
	}


	@Override
	public Bundle HTMLDownloader_InputCallback() {

			Log.w("TAGG", "Input");

			Bundle b = new Bundle();

			b.putString("one", Motor.returnSearchUrlString("forest", 0, null));
			b.putSerializable("two", CACertificateStrings.MKM);

			return b;

	}

	@Override
	public Bundle HTMLDownloader_ExecutorCallback(Bundle args) {

		String y = ProgramInput.getHTMLFromURLThroughHTTPS((String) args.get("one"), (CACertificateStrings) args.get("two"));

		Log.w("TAGG", "ON FINIHSED");

		Bundle b = new Bundle();
		b.putString("three", y);
		return b;
	}

	@Override
	public void HTMLDownloader_ReturnerCallback(Bundle out) {

		//retrieves the HTML and creates the searchpage instance
		//and also creates the associated fragment, and attaches it to the framelayout
		String h = out.getString("three");

		SearchPage sp = Motor.generateSearchPage(h,null);

		ArrayList<SearchPage> spList = new ArrayList<>();

		spList.add(sp);

		SearchResultsFragment frag = new SearchResultsFragment();

		Bundle b = new Bundle();
		b.putSerializable(SearchResultsFragment.PARAMS_SEARCH_PAGE_LIST,spList);

		frag.setArguments(b);

		FragmentManager fragman = getFragmentManager();

		fragman.beginTransaction().add(R.id.frag_container1, frag,SearchResultsFragment.IDENTIFIER_SEARCH_FRAGMENT).commit();

		ParallelTaskStorage.htmlDownloader = new AT_HTMLDownloader<Void, Bundle>(null,null,null); //TODO change this

	}




}
