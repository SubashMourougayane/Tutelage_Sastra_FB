package com.example.nadus.tutelage_unisys.Home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.Adapters.ItemAdapter4;
import com.example.nadus.tutelage_unisys.R;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_attendance_2 extends Fragment {

    FloatingActionButton fab;
    Calligrapher calligrapher;
    TextView empty_text;
    RecyclerView recyclerView;
    ItemAdapter4 it4;
    ArrayList<ClassAdapter> list = new ArrayList<>();

    public static Fragment_attendance_2 newInstance()
    {
        Fragment_attendance_2 fragment = new Fragment_attendance_2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_attendance, container, false);
            list.clear();
        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        empty_text = (TextView) v.findViewById(R.id.empty_text);
        list.add(new ClassAdapter("First Year",new String[]{"EVS","EM","TD"}));
        list.add(new ClassAdapter("Second Year",new String[]{"DAA","DS","OOPD"}));
        list.add(new ClassAdapter("Third Year",new String[]{"ES","SE"}));

        it4=new ItemAdapter4(R.layout.classcard,list);
        recyclerView.setAdapter(it4);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_attendance_excelupload()).addToBackStack(null).commit();
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
    @Override
    public void onResume() {
        super.onResume();
        it4.setOnItemClickListener(new ItemAdapter4.MyClickListener() {
            @Override
            public void onItemClick(int position, View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_student()).addToBackStack(null).commit();
            }
        });
    }

}
