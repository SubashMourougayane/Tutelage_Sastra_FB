package com.example.nadus.tutelage_unisys.Home;


import android.content.SharedPreferences;
import android.icu.text.TimeZoneNames;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nadus.tutelage_unisys.Adapters.Timeline;
import com.example.nadus.tutelage_unisys.DataModels.TimeTable;
import com.example.nadus.tutelage_unisys.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_home_1 extends Fragment {
    FloatingActionButton fab;
    RecyclerView timeline_recycler;
    ItemAdapter itemAdapter;
    DatabaseReference fb_db;
    String CurUser,Univ_name;
    ArrayList<Timeline> timelineArrayList=new ArrayList<>();
    SharedPreferences preferences;
    ArrayList<String> CurTimeTable,Timing;
    String dayOfTheWeek;
    TimeTable timeTable;
    TextView nextTime,nextClass;
    String[] Next_Schedule;
    Long CurTime;
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
        preferences = getActivity().getSharedPreferences("Tutelage",0);
        CurUser = preferences.getString("mailsplit","");
        Univ_name = preferences.getString("univ_name","");
        nextTime = (TextView)v.findViewById(R.id.nextTime);
        nextClass = (TextView)v.findViewById(R.id.nextClass);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);



        new getTimeTable().execute();
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        timeline_recycler=(RecyclerView)v.findViewById(R.id.timeline_recycler);
        timeline_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));


//        final String time = "3:15";
//
//        try {
//            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
//            final Date dateObj = sdf.parse(time);
//            System.out.println(dateObj);
//            System.out.println(new SimpleDateFormat("K:mm").format(dateObj));
//        } catch (final ParseException e) {
//            e.printStackTrace();
//        }
        timelineArrayList.add(new Timeline(1));
        timelineArrayList.add(new Timeline(1));
        timelineArrayList.add(new Timeline(1));
        timelineArrayList.add(new Timeline(1));
        timelineArrayList.add(new Timeline(1));
        timelineArrayList.add(new Timeline(1));



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
        private ArrayList<String> timeTableList;
        private ArrayList<String> timeList;
        public ItemAdapter(int listlayout, ArrayList<Timeline> tl, ArrayList<String> t2,ArrayList<String> t3)
        {
            this.listItemLayout=listlayout;
            this.list1=tl;
            this.timeTableList=t2;
            this.timeList=t3;
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
//            if(list1.get(position).getDuration()>1) {
//                LinearLayout li = holder.linearLayout;
//                ViewGroup.LayoutParams params = li.getLayoutParams();
//                params.height = (int) ((getResources().getDimension(R.dimen.class_item_height)) * (list1.get(position).getDuration())) / 2;
//                li.setLayoutParams(params);
//            }
            String[] period_split=timeTableList.get(position).split(",");
            holder.Subject.setText(period_split[0]);
            holder.Classname.setText(period_split[1]);
//
            String time=timeList.get(Integer.parseInt(period_split[2]));
         String[] st_end_time=time.split("-");
         holder.startTime.setText(st_end_time[0]);
         holder.endTime.setText(st_end_time[1]);
            System.out.println("GUNPOWER"+period_split.length);
        }

        @Override
        public int getItemCount()
        {
            return timeTableList==null?0:timeTableList.size();
        }
    }
    private class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout linearLayout;
        TextView Classname,Subject,startTime,endTime;
        public ViewHolder(View view)
        {
            super(view);
            linearLayout=(LinearLayout)view.findViewById(R.id.linearView);
            Classname=(TextView)view.findViewById(R.id.name);
            Subject=(TextView)view.findViewById(R.id.subject);
            startTime=(TextView)view.findViewById(R.id.startTime);
            endTime=(TextView)view.findViewById(R.id.endTime);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Toast.makeText(getActivity(),"Clicked "+getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    public class getTimeTable extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            System.out.println("RETREIVEING SCHEDULE");
            fb_db =  FirebaseDatabase.getInstance().getReference()
                    .child("Users").child(Univ_name).child("TimeTable").child("Teachers");
            fb_db.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    System.out.println("POST SNAP 1"+dataSnapshot.getChildren());

                    for (com.google.firebase.database.DataSnapshot postSnapshot:dataSnapshot.getChildren())
                    {
                        if (postSnapshot.getKey().equals(CurUser))
                        {
                             timeTable = postSnapshot.getValue(TimeTable.class);
                            Timing = timeTable.getTimings();
                            if (dayOfTheWeek.equals("Monday"))
                            {
                                CurTimeTable = timeTable.getMonday();
                            }
                            if (dayOfTheWeek.equals("Tuesday"))
                            {
                                CurTimeTable = timeTable.getTuesday();
                            }
                            if (dayOfTheWeek.equals("Wednesday"))
                            {
                                CurTimeTable = timeTable.getWednesday();
                            }
                            if (dayOfTheWeek.equals("Thursday"))
                            {
                                CurTimeTable = timeTable.getThursday();
                            }
                            if (dayOfTheWeek.equals("Friday"))
                            {
                                CurTimeTable = timeTable.getFriday();
                            }
                            if (dayOfTheWeek.equals("Saturday"))
                            {
                                CurTimeTable = timeTable.getSaturday();
                            }
                            System.out.println("LENGTH IS "+CurTimeTable.size());


                        }
                    }
                    DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
                    System.out.println("LLOLOLOLOL "+dateFormat);
                    Calendar cal = Calendar.getInstance();
                    TimeZone timeZone = cal.getTimeZone();
                    int Hour = Calendar.HOUR_OF_DAY;
                    System.out.print("CURRENT HER IS "+Hour);
                    try
                    {
                        for (int i=0;i<CurTimeTable.size();i++)
                        {
                            String[] period_split=CurTimeTable.get(i).split(",");
                            System.out.println("Period Split "+period_split[0]);
                            String next_period = period_split[0]+" @ "+period_split[1];
                            String time1=Timing.get(Integer.parseInt(period_split[2]));
                            // time 1 split panannum
                            String[] hr_Array = time1.split(":");
                            int Hr = Integer.parseInt(hr_Array[0]);
                            System.out.println("TIMIMNG IS "+time1);
                            if (Calendar.AM==1)
                            {
                                System.out.println("ITS MRNG");
                                if (Hr>Hour)
                                {
                                    Next_Schedule = time1.split("-");
                                    nextTime.setText(Next_Schedule[0]);
                                    nextClass.setText(next_period);
                                    break;
                                }
                            }
                            else
                            {
                                Hour = Hour-12;
                                System.out.println("ITS NOON");
                                if (Hr>Hour)
                                {
                                    Next_Schedule = time1.split("-");
                                    nextTime.setText(Next_Schedule[0]);
                                    nextClass.setText(next_period);
                                    break;
                                }
                            }


                        }

                    }catch (Exception e)
                    {
                        Toast.makeText(getActivity(),"Please load your timeTable",Toast.LENGTH_SHORT).show();
                    }


                    itemAdapter=new ItemAdapter(R.layout.item_class,timelineArrayList,CurTimeTable,Timing);
                    timeline_recycler.setAdapter(itemAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;
        }
    }


}
