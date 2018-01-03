package com.example.nadus.tutelage_unisys.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadus.tutelage_unisys.Adapters.ItemAdapter4;
import com.example.nadus.tutelage_unisys.Adapters.StudentAdapter;
import com.example.nadus.tutelage_unisys.R;

import java.util.ArrayList;

/**
 * Created by HP on 12/29/2017.
 */

public class Fragment_student extends Fragment {
    RecyclerView recyclerView;
    TextView empty;
    FloatingActionButton attendance;
    ItemAdapter5 it5;


    ArrayList<StudentAdapter> list1 = new ArrayList<>();


    public static Fragment_student newInstance() {
        Fragment_student fragment = new Fragment_student();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.class_studendlist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        empty=(TextView)view.findViewById(R.id.empty_text);
        attendance=(FloatingActionButton)view.findViewById(R.id.attendance);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list1.add(new StudentAdapter("Student1"));
        list1.add(new StudentAdapter("Student2"));
        list1.add(new StudentAdapter("Student3"));
        list1.add(new StudentAdapter("Student4"));
        it5=new ItemAdapter5(R.layout.studentcard,list1);
        recyclerView.setAdapter(it5);
        if(list1.isEmpty())
        {
            empty.setVisibility(View.VISIBLE);
            attendance.setVisibility(View.GONE);
        }
        else
        {
           attendance.setVisibility(View.VISIBLE);
           empty.setVisibility(View.GONE);
        }
    }

    class ItemAdapter5 extends RecyclerView.Adapter<ItemAdapter5.ViewHolder>
    {
        private int listItemLayout;
        private ArrayList<StudentAdapter> list;
        public ItemAdapter4.MyClickListener myClickListener;
        public ItemAdapter5(int listlayout, ArrayList<StudentAdapter> tl)
        {
            this.listItemLayout=listlayout;
            this.list=tl;
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

//        Typeface typeFace = Typeface.createFromAsset(getResources().getAssets(), "GlacialIndifference-Regular.ttf");
//        holder.textView.setTypeface(typeFace);
            holder.textView.setText(list.get(position).getStudentName());
        }

        @Override
        public int getItemCount()
        {
            return list==null?0:list.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder
        {

            TextView textView;

            public ViewHolder(View view)
            {
                super(view);


                textView=(TextView)view.findViewById(R.id.Studentname);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {

                    }
                });


            }

        }





    }

}
