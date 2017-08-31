package com.ubc.ylkjcjq.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.activitys.AboutUsActivity;


/**
 *
 * @author CJQ
 */
public class HomeMain2Fragment extends Fragment implements View.OnClickListener{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, null);

        view.findViewById(R.id.aboutus).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutus:
                startActivity(new Intent(getContext(), AboutUsActivity.class));
                break;
            default:

                break;
        }
    }
}
