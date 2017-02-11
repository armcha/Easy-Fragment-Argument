package com.luseen.fragmentarg;


import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

public abstract class BlankFragment extends Fragment {

    @CallSuper
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ArgFragment.receiveArguments(this);
    }
}