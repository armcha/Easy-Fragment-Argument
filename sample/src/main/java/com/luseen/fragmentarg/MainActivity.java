package com.luseen.fragmentarg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.luseen.easy_fragment_argument.Argument;
import com.luseen.easy_fragment_argument.ArgumentFactory;

public class MainActivity extends AppCompatActivity {

    @Argument(FirstFragment.class)
    private String name = "Test name";

    @Argument(FirstFragment.class)
    private User testUser;

    @Argument(SecondFragment.class)
    private boolean testBoolean = true;

    @Argument(SecondFragment.class)
    private Phone nexusPhone;

    @Argument(SecondFragment.class)
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = 77;
        nexusPhone = new Phone("Nexus 6", 7.1f, false);
        testUser = new User("Google", 123456789, 23);

        Fragment fragment = ArgumentFactory.createFragment(this, new FirstFragment());
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.second_container, ArgumentFactory.createFragment(this, new SecondFragment()))
                .commit();
    }
}
