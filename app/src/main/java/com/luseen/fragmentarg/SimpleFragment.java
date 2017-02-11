package com.luseen.fragmentarg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SimpleFragment extends BlankFragment {

    @Argument
    private String name;

    @Argument
    private String testName;

    @Argument
    private String secondName;

    @Argument
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("NAME ", "" + name);
        Log.e("testName ", "" + testName);
        Log.e("secondName ", "" + secondName);
        Log.e("INDEX ", "" + index);
    }
}
