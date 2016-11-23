package sheetsoft.com.mkmnavigator.android.activities;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.mkmnav.R;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import sheetsoft.com.mkmnavigator.android.backend.PageTypesMKM.SearchPageMKM;
import sheetsoft.com.mkmnavigator.android.fragments.SearchResultsFragment;
import sheetsoft.com.mkmnavigator.android.fragments.WaitFragment;
import sheetsoft.com.mkmnavigator.android.fragments.WelcomeFragment;
import sheetsoft.com.mkmnavigator.android.parallelTaskStorage.AT_HTMLDownloader;
import sheetsoft.com.mkmnavigator.android.parallelTaskStorage.ParallelTaskStorage;
import sheetsoft.com.mkmnavigator.android.backend.CACertificateStrings;
import sheetsoft.com.mkmnavigator.android.backend.ProgramInput;
import sheetsoft.com.mkmnavigator.android.backend.motor.Motor;

public class StartActivity extends FragmentActivity implements AT_HTMLDownloader.HTMLDownloader_IER_Callbacks<String,Bundle>{
    //TODO change to proper activity type for action bar


    public static final String NUMBEROFFRAGS="dlsakfjlads;kfjal;ksdfjl";
    public static final String CURRENTFRAG="DLKFJSLKFJKSDFJKLDSJFL";
    public static final String CURRENTHTML="sdlk;fja;ldskfjal;skdfjlas;";
    public static final String CURRENTHTMLSAVETIME="SDKLFSJDLFKJSL:DFJKSDL:FKJSDL:KFJSDL:KFJSDKL:FJKL";

    public static final long SAVEDHTMLTIMEOUT=120000; // THIS WILL BE AN ACTUAL PREFERENCE IN THE FUTURE

    private final int POS_WELCOME_FRAGMENT=0;
    private final int POS_RESULTS_FRAGMENT=1;
    private final int POS_PRODUCT_FRAGMENT=2;

    private int mNumberOfTotalFragments = POS_WELCOME_FRAGMENT;
    public int getNumberOfTotalFragments(){return mNumberOfTotalFragments;}
    private void setNumberOfTotalFragments(int num) {this.mNumberOfTotalFragments = num;}


    private int mCurrentFragmentIndex = POS_WELCOME_FRAGMENT;
    public int getCurrentFragmentIndex(){return mCurrentFragmentIndex;}
    public void setCurrentFragmentIndex(int mCurrentFragmentIndex){this.mCurrentFragmentIndex = mCurrentFragmentIndex;}


    private boolean showProgressBarInWelcomeFragment = false;


    private ViewPager mViewPager = null;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_start_base_layout);

        ListView lv = (ListView) findViewById(R.id.startActivityNavigationDrawerListView);
        ArrayList<String> values = new ArrayList<>();
        values.add("Search");
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
        lv.setAdapter(adap);
        lv.setOnItemClickListener(new StartActivityNavBarOnItemSelectedWrapper(this));


        if (mViewPager==null) {
            mViewPager = (ViewPager) findViewById(R.id.startActivityViewPager);
            mViewPager.setAdapter(new StartActivityViewPagerAdapter(getSupportFragmentManager(), this));
            mViewPager.addOnPageChangeListener(new StartActivityOnViewPagerPageChangeListener(this));
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String b = prefs.getString(CURRENTHTML, null);
        long longtime = prefs.getLong(CURRENTHTMLSAVETIME,0);

        if ((Motor.getCurrentTimeInMillis()-longtime)>StartActivity.SAVEDHTMLTIMEOUT){
            b = null;
            this.setNumberOfTotalFragments(POS_RESULTS_FRAGMENT);
            mViewPager.getAdapter().notifyDataSetChanged();
        }

        if (ParallelTaskStorage.StartActivitySearchHTMLFetcher ==null){
            ParallelTaskStorage.StartActivitySearchHTMLFetcher = new AT_HTMLDownloader<>(this,this,this);
        }

        if (b!=null){
            ParallelTaskStorage.StartActivitySearchHTMLFetcher.setContent(b);
            this.setNumberOfTotalFragments(POS_RESULTS_FRAGMENT);
            mViewPager.getAdapter().notifyDataSetChanged();
        }


	}


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putShort(NUMBEROFFRAGS,(short) this.getNumberOfTotalFragments());
        savedInstanceState.putShort(CURRENTFRAG,(short) this.getCurrentFragmentIndex());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        this.setNumberOfTotalFragments(savedInstanceState.getShort(NUMBEROFFRAGS));
        this.setCurrentFragmentIndex(savedInstanceState.getShort(CURRENTFRAG));

        if (mViewPager==null) {
            mViewPager = (ViewPager) findViewById(R.id.startActivityViewPager);
            mViewPager.setAdapter(new StartActivityViewPagerAdapter(getSupportFragmentManager(), this));
        }
        mViewPager.getAdapter().notifyDataSetChanged();
        mViewPager.setCurrentItem(this.getCurrentFragmentIndex());
    }

    @Override
    public void onBackPressed(){
        if (mViewPager.getCurrentItem()==0){
            super.onBackPressed();
        } else{
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
        }
    }

    public void startSearchForCard(String query){

        if (ParallelTaskStorage.StartActivitySearchHTMLFetcher !=null){
            if (ParallelTaskStorage.StartActivitySearchHTMLFetcher.getStatus() != AsyncTask.Status.FINISHED){
                ParallelTaskStorage.StartActivitySearchHTMLFetcher.cancel(true);
            }
        }

        ParallelTaskStorage.StartActivitySearchHTMLFetcher = new AT_HTMLDownloader<>(this,this,this);
        ParallelTaskStorage.StartActivitySearchHTMLFetcher.execute(query);

    }






	@Override
	public Bundle HTMLDownloader_InputCallback() {

			Bundle b = new Bundle();

			b.putSerializable("two", CACertificateStrings.MKM);

            this.showProgressBarInWelcomeFragment = true;
            this.runOnUiThread(new NotifyDataSetChangedInMainUIThread(this));

            this.mViewPager.setCurrentItem(POS_WELCOME_FRAGMENT);

			return b;

	}

    public class NotifyDataSetChangedInMainUIThread implements Runnable {
        StartActivity at;
        public NotifyDataSetChangedInMainUIThread(StartActivity at) { super(); this.at = at; }
        @Override public void run() { at.mViewPager.getAdapter().notifyDataSetChanged(); }
    }

	@Override
	public Bundle HTMLDownloader_ExecutorCallback(Bundle args, AsyncTask refTask, String... asyncArgs) {

        String y = "";

        boolean loadweb = true;

        if (loadweb) {

            HttpsURLConnection connection = ProgramInput.setupHTTPSURLConnection(Motor.returnSearchUrlString(asyncArgs[0], 0, Motor.MAINSITEURLS.MKM_EU), (CACertificateStrings) args.get("two"));
            BufferedReader br = ProgramInput.getBufferedReaderFromHTTPSConnection(connection);
            String u;
            while ((u = ProgramInput.getHTMLWithSSLLineByLine(br)) != null) {
                y += u;
            }
        } else {

            InputStream inputStream = getResources().openRawResource(R.raw.mkmforest);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String u;
            try {
                while ((u = bufferedReader.readLine()) != null) {
                    y += u;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

		Bundle b = new Bundle();
        //save the string inside the asynctask
        ParallelTaskStorage.StartActivitySearchHTMLFetcher.setContent(y);
        //save it to settings
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(CURRENTHTML,y).apply();
        //save the time at which the string was saved
        long u = Motor.getCurrentTimeInMillis();
        PreferenceManager.getDefaultSharedPreferences(this).edit().putLong(CURRENTHTMLSAVETIME,Motor.getCurrentTimeInMillis()).commit();

		return b;
	}

	@Override
	public void HTMLDownloader_ReturnerCallback(Bundle out) {

        // does not need the bundle, so the bundle can actually be null

//        if (out == null) return;

        //update the existing fragment
        //cant be done in background

        this.setNumberOfTotalFragments(POS_RESULTS_FRAGMENT+1);

        this.mViewPager.getAdapter().notifyDataSetChanged();

        this.mViewPager.setCurrentItem(POS_RESULTS_FRAGMENT);

        this.showProgressBarInWelcomeFragment = false;

        this.mViewPager.getAdapter().notifyDataSetChanged();


	}

    private class StartActivityNavBarOnItemSelectedWrapper implements AdapterView.OnItemClickListener{

        private StartActivity at;

        public StartActivityNavBarOnItemSelectedWrapper(StartActivity at) {
            this.at = at;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String name = ((EditText) at.findViewById(R.id.startActivitySearchResultsEditText)).getText().toString();
            at.startSearchForCard(name);
            DrawerLayout lv = (DrawerLayout) at.findViewById(R.id.startActivityDrawerLayout);

            lv.closeDrawer(Gravity.LEFT);



        }
    }



    private class StartActivityViewPagerAdapter extends FragmentStatePagerAdapter {
        private StartActivity at;
        public StartActivityViewPagerAdapter(FragmentManager fm, StartActivity at){
            super(fm);
            this.at=at;
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch (position){
                case POS_WELCOME_FRAGMENT:
                    return at.returnWelcomeActivity();
                case POS_RESULTS_FRAGMENT:
                    String n = ParallelTaskStorage.StartActivitySearchHTMLFetcher.getContent();
                    if (n==null){
                        return new WaitFragment();
                    }

                    SearchPageMKM sp = Motor.generateSearchPage(n,null);

		            ArrayList<SearchPageMKM> spList = new ArrayList<>();

		            spList.add(sp);

		            SearchResultsFragment frag = new SearchResultsFragment();

		            Bundle b = new Bundle();
		            b.putSerializable(SearchResultsFragment.PARAMS_SEARCH_PAGE_LIST,spList);
		            frag.setArguments(b);
                    return frag;
            }

            return new WaitFragment();
        }

        @Override
        public int getCount() {return at.getNumberOfTotalFragments();}

        @Override
        public int getItemPosition(Object object){ return PagerAdapter.POSITION_NONE;}

    }

    public Fragment returnWelcomeActivity(){
        return this.showProgressBarInWelcomeFragment ? new WaitFragment() : new WelcomeFragment();
    }



    public class StartActivityOnViewPagerPageChangeListener implements ViewPager.OnPageChangeListener{

        private StartActivity at;

        public StartActivityOnViewPagerPageChangeListener(StartActivity at) {
            super();
            this.at = at;
        }

        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
        @Override public void onPageSelected(int position) {at.setCurrentFragmentIndex(position);}
        @Override public void onPageScrollStateChanged(int state) {}
    }









}
