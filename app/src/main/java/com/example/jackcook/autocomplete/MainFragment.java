package com.example.jackcook.autocomplete;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {
    public interface ToolbarBackButtonInterface {
        public void onToolbarBackButton();
    }

    View rootView;
    ConstraintLayout constraintLayout;
    EditText editText1;
    TextView recycleView;
    RecyclerView recyclerView;
    Button buttonShow;
    Button buttonHide;
    EditText editText2;
    RelativeLayout editTextLine;
    private ImageView backButton;
    private String text;
    private int bg;

    private ConstraintSet showRecyclerViewConstraintSet = new ConstraintSet();
    private ConstraintSet hideRecyclerViewConstraintSet = new ConstraintSet();
    private ConstraintSet constraintSetClosed = new ConstraintSet();

    private ToolbarBackButtonInterface backButtonListener;

    public static MainFragment create(String text, int bg, ToolbarBackButtonInterface backButtonListener) {
        MainFragment fragment = new MainFragment();
        fragment.backButtonListener = backButtonListener;
        fragment.text = text;
        fragment.bg = bg;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initUIReferences();

        recycleView.setText("this is the only text \n\n\n\nanother line");

        recycleView.setBackgroundColor(bg);
        editText1.setHint(text);
        if (backButtonListener == null) {
            backButton.setVisibility(View.INVISIBLE);
        }

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIt();
            }
        });

        buttonHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideIt();
            }
        });

        initConstraints();
        return rootView;
    }

    private void initUIReferences() {
        constraintLayout = (ConstraintLayout) rootView.findViewById(R.id.layout);
        editText1 = (EditText) rootView.findViewById(R.id.edittext1);
        recycleView = (TextView) rootView.findViewById(R.id.textview);
        buttonShow = (Button) rootView.findViewById(R.id.buttonshow);
        buttonHide = (Button) rootView.findViewById(R.id.buttonhide);
        editTextLine = (RelativeLayout) rootView.findViewById(R.id.edittextline);
        backButton = (ImageView) rootView.findViewById(R.id.backbutton);
    }

    private void initConstraints() {

        constraintSetClosed.clone(getContext(), R.layout.fragment_hidden_main); // get constraints from layout

        hideRecyclerViewConstraintSet.clone(constraintLayout);

        showRecyclerViewConstraintSet.clone(constraintLayout);
    }

    private void hideIt() {
        TransitionManager.beginDelayedTransition(constraintLayout);
        constraintSetClosed.applyTo(constraintLayout);
    }

    private void showIt() {
        showRecyclerViewConstraintSet.applyTo(constraintLayout);

    }
}
