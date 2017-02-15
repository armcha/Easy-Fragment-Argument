package com.luseen.fragmentarg;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luseen.easy_fragment_argument.Argument;
import com.luseen.easy_fragment_argument.ArgumentFactory;
import com.luseen.easy_fragment_argument.ArgumentFragment;


public class SecondFragment extends Fragment {

    private static final String TAG = "SecondFragment";

    @Argument
    private boolean testBoolean;

    @Argument
    private Phone nexusPhone;

    @Argument
    private int index;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ArgumentFactory.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "boolean " + testBoolean);
        Log.d(TAG, "Phone " + nexusPhone.getModel());
        Log.d(TAG, "int " + index);

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("Second Fragment received arguments\n\n boolean-" + testBoolean + "\n Phone-"
                + nexusPhone.getModel() + "\n int-" + index);
        return rootView;
    }
}
