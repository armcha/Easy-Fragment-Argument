package com.luseen.fragmentarg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.easy_fragment_argument.Argument;
import com.luseen.easy_fragment_argument.ArgumentFragment;


public class SecondFragment extends ArgumentFragment {

    private static final String TAG = "SecondFragment";

    @Argument
    private boolean testBoolean;

    @Argument
    private Phone nexusPhone;

    @Argument
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"boolean " + testBoolean);
        Log.d(TAG,"Phone " + nexusPhone.getModel());
        Log.d(TAG,"int " + index);
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}
