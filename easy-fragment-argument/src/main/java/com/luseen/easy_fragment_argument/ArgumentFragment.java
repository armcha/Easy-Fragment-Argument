package com.luseen.easy_fragment_argument;


import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

public abstract class ArgumentFragment extends Fragment {

    @CallSuper
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ArgFragment.onAttach(this);
    }
}