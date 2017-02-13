package com.luseen.fragmentarg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luseen.easy_fragment_argument.Argument;
import com.luseen.easy_fragment_argument.ArgumentFactory;

public class MainActivity extends AppCompatActivity {

    @Argument(SimpleFragment.class)
    private String name = "Arman";

    @Argument(SimpleFragment.class)
    private int index;

    @Argument(SimpleFragment.class)
    private Integer testInteger = 556456;

    @Argument(SimpleFragment.class)
    private boolean testBoolean = true;

    @Argument(SimpleFragment.class)
    private Boolean secondBoolean = false;

    @Argument(SimpleFragment.class)
    private Phone nexusPhone;

    @Argument(SimpleFragment.class)
    private User testUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 77;
        nexusPhone = new Phone("Nexus 6", 7.1f, false);
        testUser = new User("NArek", 484564889, 27);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ArgumentFactory.createFragment(this, new SimpleFragment()))
                .commit();
    }
}
