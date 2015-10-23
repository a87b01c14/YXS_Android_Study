package com.antex.layout_gravity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antex.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LayoutGravityActivityFragment extends Fragment {

    public LayoutGravityActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_gravity, container, false);
    }
}
