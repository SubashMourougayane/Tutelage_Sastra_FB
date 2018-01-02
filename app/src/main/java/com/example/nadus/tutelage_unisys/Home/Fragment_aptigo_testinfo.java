package com.example.nadus.tutelage_unisys.Home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadus.tutelage_unisys.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_aptigo_testinfo extends Fragment {

    Calligrapher calligrapher;
    RecyclerView recyclerView;
    ArrayList<String> list = new ArrayList<String>();

    public static Fragment_aptigo_testinfo newInstance() {
        Fragment_aptigo_testinfo fragment = new Fragment_aptigo_testinfo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_aptigo_testinfo, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        return v;
    }
}
