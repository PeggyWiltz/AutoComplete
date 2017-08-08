package com.example.jackcook.autocomplete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment f1 = MainFragment.create("term", getResources().getColor(android.R.color.holo_orange_dark), new MainFragment.ToolbarBackButtonInterface() {
            @Override
            public void onToolbarBackButton() {

            }
        });

        MainFragment f2 = MainFragment.create("location", getResources().getColor(android.R.color.holo_orange_light), null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.search_term, f1, "testing1")
                .add(R.id.location, f2, "testing2")
                .commit();
    }
}
