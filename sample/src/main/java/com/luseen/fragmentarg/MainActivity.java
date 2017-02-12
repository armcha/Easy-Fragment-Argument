package com.luseen.fragmentarg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.easy_fragment_argument.ArgFragment;
import com.luseen.easy_fragment_argument.Argument;

public class MainActivity extends AppCompatActivity {


    @Argument(SimpleFragment.class)
    private String name = "Arman";

    @Argument(SimpleFragment.class)
    private int index;

    @Argument(SimpleFragment.class)
    private Phone nexusPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 77;
        nexusPhone = new Phone("Nexus 6", 7.1f, false);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ArgFragment.createFragment(this, new SimpleFragment()))
                .commit();
    }
}
