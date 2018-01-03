package com.example.nadus.tutelage_unisys.Home;

import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.Adapters.AssessmentCardAdapter;
import com.example.nadus.tutelage_unisys.Adapters.AssessmentItemAdapter;
import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Registration.Step1;

import java.io.File;
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
    ArrayList<AssessmentCardAdapter> list = new ArrayList<AssessmentCardAdapter>();
    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFile;
    File file;
    String[] subjects;

    Button onsdCard,updir;


    ArrayList<ClassAdapter> list1 = new ArrayList<ClassAdapter>();

    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;
    ArrayList<String> stringArrayList = new ArrayList<>();

    ArrayList<ClassAdapter> uploadData=new ArrayList<>();

    public static Fragment_aptigo_4 newInstance() {
        Fragment_aptigo_4 fragment = new Fragment_aptigo_4();
        return fragment;
    }

    ProgressDialog progressDialog;
    AssessmentItemAdapter assessmentItemAdapter;

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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Tests...");
        progressDialog.show();

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        empty_text = (TextView) v.findViewById(R.id.empty_text);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_aptigo_questionupload()).addToBackStack(null).commit();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        progressDialog.show();
       // new MyTask().execute();

        list.add(new AssessmentCardAdapter("Unit Test 1","Data Structures"+" | "+"IV-A","02:30","12/08/18","40"+" \\ "+"60","Open"));
        list.add(new AssessmentCardAdapter("Unit Test 2","Platform Technology"+" | "+"III-A","03:00","13/10/18","47"+" \\ "+"60","Open"));
        list.add(new AssessmentCardAdapter("Unit Test 3","Mathematics 4"+" | "+"II-C","01:30","12/11/18","49"+" \\ "+"60","Open"));
        list.add(new AssessmentCardAdapter("Unit Test 4","Communicative English"+" | "+"III-B","03:00","12/11/18","56"+" \\ "+"60","Open"));

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
        assessmentItemAdapter=new AssessmentItemAdapter(R.layout.assessment_card,list);
        recyclerView.setAdapter(assessmentItemAdapter);
        progressDialog.dismiss();

    }

    @Override
    public void onResume()
    {
        super.onResume();
        assessmentItemAdapter.setOnItemClickListener(new AssessmentItemAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(getActivity(), "Clicked "+position, Toast.LENGTH_SHORT).show();
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new Fragment_aptigo_testinfo()).addToBackStack(null).commit();
            }
        });

    }
}
