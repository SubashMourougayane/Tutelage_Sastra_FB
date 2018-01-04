package com.example.nadus.tutelage_unisys.Home;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.Adapters.ItemAdapter4;
import com.example.nadus.tutelage_unisys.DataModels.MyClasses;
import com.example.nadus.tutelage_unisys.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_attendance_2 extends Fragment {

    HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
    ArrayList<String> Nodes=new ArrayList<>();
    String univName,mailSplit;
    DatabaseReference fb_db;
    FloatingActionButton fab;
    Calligrapher calligrapher;
    TextView empty_text;
    RecyclerView recyclerView;
    ItemAdapter4 it4;
    ArrayList<ClassAdapter> list = new ArrayList<>();
    SharedPreferences sharedPreferences;
    ArrayList<String> subjects_dummy=new ArrayList<>();
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
        sharedPreferences = getActivity().getSharedPreferences("Tutelage",0);
        univName = sharedPreferences.getString("univ_name","");
        mailSplit = sharedPreferences.getString("mailsplit","");
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        empty_text = (TextView) v.findViewById(R.id.empty_text);
        //subjects_dummy.add("physics");
        new MyClassRetreival().execute();
       // list.add(new ClassAdapter("First Year",subjects_dummy));
//        list.add(new ClassAdapter("Second Year",new String[]{"DAA","DS","OOPD"}));
//        list.add(new ClassAdapter("Third Year",new String[]{"ES","SE"}));
        it4=new ItemAdapter4(R.layout.classcard,list);
        recyclerView.setAdapter(it4);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_attendance_excelupload()).addToBackStack(null).commit();
            }
        });

//

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        it4.setOnItemClickListener(new ItemAdapter4.MyClickListener() {
            @Override
            public void onItemClick(int position, View v)
            {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_student()).addToBackStack(null).commit();
            }
        });
    }
    public class MyClassRetreival extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            fb_db = FirebaseDatabase.getInstance().getReference()
                    .child("Users").child(univName).child("MyTimeTable").child(mailSplit);
            System.out.println("FB DB IS "+fb_db);
            ArrayList<ArrayList<String>> SubjList  = new ArrayList<>();
            ArrayList<String> ClassNames = new ArrayList<>();
            fb_db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("GET KEY "+dataSnapshot.getKey()+" "+dataSnapshot.getChildren());
                    for (DataSnapshot postsnapshot:dataSnapshot.getChildren())
                    {
                        System.out.println("GET KEY 222 "+postsnapshot.getKey());
                        ClassNames.add(postsnapshot.getKey());
                        MyClasses myClasses = postsnapshot.getValue(MyClasses.class);
                        System.out.println("MYCLASSES "+myClasses.getSubjlist());
                        SubjList.add(myClasses.getSubjlist());
//                        list.add(new ClassAdapter(postsnapshot.getKey(),myClasses.getSubjlist()));
                        System.out.println("LISTTTTTTT "+list);


                    }
                    for (int i=0;i<ClassNames.size();i++)
                    {

                        list.add(new ClassAdapter(ClassNames.get(i),SubjList.get(i)));
                        System.out.println("LIST VALUES ARE "+list.get(i).getClassname() +" , "+ list.get(i).getSubjects() );

                    }
                    ItemAdapter4 itemAdapter4=new ItemAdapter4(R.layout.classcard,list);
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
                    recyclerView.setAdapter(itemAdapter4);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }
    }

}
