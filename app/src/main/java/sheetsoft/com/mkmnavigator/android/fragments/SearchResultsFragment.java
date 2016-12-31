package sheetsoft.com.mkmnavigator.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mkmnav.R;

import java.util.ArrayList;

import sheetsoft.com.mkmnavigator.android.backend.ProductFramework.Product;
import sheetsoft.com.mkmnavigator.android.backend.PageTypesMKM.SearchPageMKM;

/**
 * Created by chapa on 15-11-2016.
 */

public class SearchResultsFragment extends Fragment {

    ArrayList<SearchPageMKM> searchPageMKMs = null;

    Context context = null;

    public static final String PARAMS_SEARCH_PAGE_LIST = "com.mkm.sdklfjalsdkfjalsdkfja;ewqwklerj";

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View v;

        v = layoutInflater.inflate(R.layout.fragment_search_base_layout,container,false);


        if (savedInstanceState!=null){
            searchPageMKMs = (ArrayList<SearchPageMKM>) savedInstanceState.getSerializable(PARAMS_SEARCH_PAGE_LIST);
        }

        Bundle b = getArguments();
        if (searchPageMKMs ==null && b!=null){
            searchPageMKMs = (ArrayList<SearchPageMKM>) b.getSerializable(PARAMS_SEARCH_PAGE_LIST);
        }

        if (searchPageMKMs !=null) {
            if (searchPageMKMs.size()>0) {
                ListView lv = (ListView) v.findViewById(R.id.searchFragmentListView);

                SearchPageMKM gg = searchPageMKMs.get(0);

                CardListAdapter ppap = new CardListAdapter(this.getActivity(), gg.getAssociatedProducts());

                lv.setAdapter(ppap);
            } else {
                v.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        } else{
            //TODO no results logic
        }
        return v;
    }



    public class CardListAdapter extends BaseAdapter {

        private Activity activity;
        private ArrayList<Product> mProductList;

        public CardListAdapter(Activity act, ArrayList<Product> list){
            this.activity = act;
            this.mProductList = list;
        }

        @Override
        public int getCount() {
            return mProductList.size();
        }

        @Override
        public Object getItem(int i) {
            return mProductList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            LayoutInflater inflater = activity.getLayoutInflater();

            Product product = (Product) getItem(i);


            View v = inflater.inflate(R.layout.fragment_search_list_element_layout,parent,false);

            TextView tv1 = (TextView) v.findViewById(R.id.searchFragmentListElementLayoutCardName);

            tv1.setText(product.getCardName());

            TextView tv2 = (TextView) v.findViewById(R.id.searchFragmentListElementLayoutCardExpansion);

            tv2.setText(product.getCardExpansion());

            TextView tv3 = (TextView) v.findViewById(R.id.searchFragmentListElementLayoutPriceValue);

            tv3.setText(product.getCardPrice());

            return v;
        }
    }

}
