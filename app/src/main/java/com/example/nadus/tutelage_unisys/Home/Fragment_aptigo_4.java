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
import com.example.nadus.tutelage_unisys.Registration.Step1;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_aptigo_4  extends Fragment {

    FloatingActionButton fab;
    Calligrapher calligrapher;
    TextView empty_text;
    RecyclerView recyclerView;
    ArrayList<String> list = new ArrayList<String>();

    public static Fragment_aptigo_4 newInstance() {
        Fragment_aptigo_4 fragment = new Fragment_aptigo_4();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_aptigo, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        empty_text = (TextView) v.findViewById(R.id.empty_text);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_aptigo_duration()).addToBackStack(null).commit();
            }
        });

        if(list.isEmpty())
        {
            recyclerView.setVisibility(View.GONE);
            empty_text.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView.setVisibility(View.VISIBLE);
            empty_text.setVisibility(View.GONE);
        }
        return v;
    }
}
