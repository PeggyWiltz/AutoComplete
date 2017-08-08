package com.example.jackcook.autocomplete;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Button hideLocationButton;
    ConstraintLayout constraintLayout;
    RelativeLayout locationLayout;
    MainFragment f2;

    private ConstraintSet hideLocationViewConstraintSet = new ConstraintSet();
    private ConstraintSet showLocationViewConstraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment f1 = MainFragment.create("term", getResources().getColor(android.R.color.holo_orange_dark), new MainFragment.ToolbarBackButtonInterface() {
            @Override
            public void onToolbarBackButton() {

            }
        });

        findViewById(R.id.hide_loc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideLocationView();
            }
        });

        f2 = MainFragment.create("location", getResources().getColor(android.R.color.holo_orange_light), null);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.search_term, f1, "testing1")
                .add(R.id.location, f2, "testing2")
                .commit();

        constraintLayout = (ConstraintLayout) findViewById(R.id.layout);
        locationLayout = (RelativeLayout) findViewById(R.id.location);

        hideLocationViewConstraintSet.clone(constraintLayout);
        showLocationViewConstraintSet.clone(constraintLayout);
    }

    private void hideLocationView() {
        TransitionManager.beginDelayedTransition(constraintLayout);
//        hideLocationViewConstraintSet.applyTo(constraintLayout);

        f2.constraintLayout.setVisibility(f2.constraintLayout.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

    }
}
