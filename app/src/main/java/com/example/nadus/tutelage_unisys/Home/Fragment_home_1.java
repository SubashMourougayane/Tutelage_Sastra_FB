package com.example.nadus.tutelage_unisys.Home;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nadus.tutelage_unisys.Adapters.Timeline;
import com.example.nadus.tutelage_unisys.R;


import java.util.ArrayList;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_home_1 extends Fragment {
    FloatingActionButton fab;
    RecyclerView timeline_recycler;
    ItemAdapter itemAdapter;
    ArrayList<Timeline> timelineArrayList=new ArrayList<>();

    public static Fragment_home_1 newInstance() {
        Fragment_home_1 fragment = new Fragment_home_1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_dashboard, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        timeline_recycler=(RecyclerView)v.findViewById(R.id.timeline_recycler);
        timeline_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        timelineArrayList.add(new Timeline(2));
        timelineArrayList.add(new Timeline(2));
        timelineArrayList.add(new Timeline(2));
        timelineArrayList.add(new Timeline(1));
        timelineArrayList.add(new Timeline(3));
        timelineArrayList.add(new Timeline(1));

        itemAdapter=new ItemAdapter(R.layout.item_class,timelineArrayList);
        timeline_recycler.setAdapter(itemAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_home_excelupload()).addToBackStack(null).commit();
            }
        });
        return v;
    }


    private class ItemAdapter extends RecyclerView.Adapter<ViewHolder>{
        private int listItemLayout;
        private ArrayList<Timeline> list1;
        public ItemAdapter(int listlayout, ArrayList<Timeline> tl)
        {
            this.listItemLayout=listlayout;
            this.list1=tl;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
            final ViewHolder myViewHolder = new ViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            if(list1.get(position).getDuration()>1) {
                LinearLayout li = holder.linearLayout;
                ViewGroup.LayoutParams params = li.getLayoutParams();
                params.height = (int) ((getResources().getDimension(R.dimen.class_item_height)) * (list1.get(position).getDuration())) / 2;
                li.setLayoutParams(params);
            }
        }

        @Override
        public int getItemCount() {
            return list1==null?0:list1.size();
        }
    }
    private class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;

        public ViewHolder(View view)
        {
            super(view);
            linearLayout=(LinearLayout)view.findViewById(R.id.linearView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(),"Clicked "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

}
