package com.mkmnav.android.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mkmnav.R;
import com.mkmnav.backend.CardFramework.Card;
import com.mkmnav.backend.PageTypesMKM.SearchPage;

import java.util.ArrayList;

/**
 * Created by chapa on 15-11-2016.
 */

public class SearchResultsFragment extends Fragment {

    ArrayList<SearchPage> searchPages = null;

    Context context = null;

    public static final String IDENTIFIER_SEARCH_FRAGMENT = "com.mkm.sadlkfjal;sdkfja;slkdfjal;sdkfj";

    public static final String PARAMS_SEARCH_PAGE_LIST = "com.mkm.sdklfjalsdkfjalsdkfja;ewqwklerj";

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View v;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            v = layoutInflater.inflate(R.layout.search_activity_fragment_h,container,false);
        } else {
            v = layoutInflater.inflate(R.layout.search_activity_fragment_v,container,false);
        }


        if (savedInstanceState!=null){
            searchPages = (ArrayList<SearchPage>) savedInstanceState.getSerializable(PARAMS_SEARCH_PAGE_LIST);
        }

        Bundle b = getArguments();
        if (searchPages==null && b!=null){
            searchPages = (ArrayList<SearchPage>) b.getSerializable(PARAMS_SEARCH_PAGE_LIST);
        }

        if (searchPages!=null) {

            ListView lv = (ListView) v.findViewById(R.id.searchFragmentListView);

            SearchPage gg = searchPages.get(0);

            CardListAdapter ppap = new CardListAdapter(this.getActivity(),gg.getAssociatedCards());

            lv.setAdapter(ppap);
        }
        return v;
    }



    public class CardListAdapter extends BaseAdapter {

        private Activity activity;
        private ArrayList<Card> mCardList;

        public CardListAdapter(Activity act, ArrayList<Card> list){
            this.activity = act;
            this.mCardList = list;
        }

        @Override
        public int getCount() {
            return mCardList.size();
        }

        @Override
        public Object getItem(int i) {
            return mCardList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            Card card = (Card) getItem(i);


            View v = inflater.inflate(R.layout.search_fragment_list_element_layout,parent,false);

            TextView tv1 = (TextView) v.findViewById(R.id.searchFragmentListElementLayoutCardName);

            tv1.setText(card.getCardName());

            TextView tv2 = (TextView) v.findViewById(R.id.searchFragmentListElementLayoutCardExpansion);

            tv2.setText(card.getCardExpansion());

            TextView tv3 = (TextView) v.findViewById(R.id.searchFragmentListElementLayoutPriceValue);

            tv3.setText(card.getCardPrice());

            return v;
        }
    }

}
