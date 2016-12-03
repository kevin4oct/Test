package com.geren.test.test.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.geren.test.test.R;

/**
 * Created by Kevin on 2016/12/3.
 */
public class ActionFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = ActionFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_action,container,false);

        TextView tv = (TextView) layout.findViewById(R.id.tv_enter);

        tv.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(), "并没有什么卵用", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(getContext(), MapActivity.class));
    }
}
