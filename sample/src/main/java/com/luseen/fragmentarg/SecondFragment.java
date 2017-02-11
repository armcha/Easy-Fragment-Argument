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

    @Argument
    private String secondString;

    @Argument
    private boolean testBoolean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("secondString ", "SecondFragment||| " + secondString);
        Log.e("testBoolean ", "SecondFragment||| " + testBoolean);
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}
