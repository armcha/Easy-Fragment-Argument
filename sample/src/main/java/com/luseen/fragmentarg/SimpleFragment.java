package com.luseen.fragmentarg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.easy_fragment_argument.Argument;
import com.luseen.easy_fragment_argument.ArgumentFragment;

public class SimpleFragment extends ArgumentFragment {

    @Argument
    private String name;

    @Argument
    private int index;

    @Argument
    private Phone nexusPhone;

    @Argument
    private User testUser;

    @Argument
    private Integer testInteger;

    @Argument
    private boolean testBoolean;

    @Argument
    private Boolean secondBoolean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("NAME ", "" + name);
        Log.e("INDEX ", "" + index);
        Log.e("nexusPhone", "getModel " + nexusPhone.getModel());
        Log.e("nexusPhone", "getAndroidVersion " + nexusPhone.getAndroidVersion());
        Log.e("USER", "testUser " + testUser.getUserName());
        Log.e("testInteger", "testInteger " + testInteger);
        Log.e("testBoolean", "testBoolean " + testBoolean);
        Log.e("secondBoolean", "secondBoolean " + secondBoolean);
    }
}
