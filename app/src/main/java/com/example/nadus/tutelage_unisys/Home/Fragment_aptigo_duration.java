package com.example.nadus.tutelage_unisys.Home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Registration.Step1;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_aptigo_duration extends Fragment {

    FloatingActionButton fab;
    EditText class_name,date,duration,test_name,test_questions;
    String s_class,s_date,s_duration,s_test,s_number;

    Calligrapher calligrapher;

    public static Fragment_aptigo_duration newInstance() {
        Fragment_aptigo_duration fragment = new Fragment_aptigo_duration();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_aptigo_duration, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        class_name = (EditText) v.findViewById(R.id.class_name);
        date = (EditText) v.findViewById(R.id.date);
        duration = (EditText) v.findViewById(R.id.duration);
        test_name = (EditText) v.findViewById(R.id.test_name);
        test_questions = (EditText) v.findViewById(R.id.test_questions);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_aptigo_questionupload()).addToBackStack(null).commit();
            }
        });
        return v;
    }
}
