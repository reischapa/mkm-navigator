package sheetsoft.com.mkmnavigator.android.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mkmnav.R;

/**
 * Created by chapa on 11/23/2016.
 */

public class WelcomeFragment extends Fragment {


    Context context;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {


        View v = layoutInflater.inflate(R.layout.fragment_welcome_base_layout,container,false);

        return v;
    }



}
