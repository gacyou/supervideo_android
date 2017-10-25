package com.supervideo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Gacyou on 2017/10/17.
 */

public class SuperVideoFragment extends Fragment {

    Context mContext;
    Activity mActivity;

    LinearLayout loadingView;
    ListView mlv;

    int MenuId;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View V = inflater.inflate(R.layout.activity_supervideo, container, false);
        mContext = V.getContext();
        loadingView = (LinearLayout) V.findViewById(R.id.loading);
        mlv = (ListView) V.findViewById(R.id.supervideo_list);
        MenuId = getArguments().getInt("MenuId");
        return V;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
