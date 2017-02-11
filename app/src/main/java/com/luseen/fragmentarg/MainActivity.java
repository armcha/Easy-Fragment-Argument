package com.luseen.fragmentarg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Argument(SimpleFragment.class)
    private String name = "Arman";

    @Argument(SimpleFragment.class)
    private String testName;

    @Argument(SimpleFragment.class)
    private String secondName = "Chatikyan";

    @Argument(SimpleFragment.class)
    private int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 77;
        testName = "TestName";

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ArgFragment.createFragment(this, new SimpleFragment()))
                .commit();
    }
}
