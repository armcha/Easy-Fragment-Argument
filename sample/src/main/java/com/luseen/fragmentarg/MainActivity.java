package com.luseen.fragmentarg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.easy_fragment_argument.ArgFragment;
import com.luseen.easy_fragment_argument.Argument;

public class MainActivity extends AppCompatActivity {


    @Argument(SimpleFragment.class)
    private String secondName = "Arman";

    @Argument(SimpleFragment.class)
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 77;

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ArgFragment.createFragment(this, new SimpleFragment()))
                .commit();

//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.container, ArgFragment.createFragment(this, new SecondFragment()))
//                .commit();
    }
}
