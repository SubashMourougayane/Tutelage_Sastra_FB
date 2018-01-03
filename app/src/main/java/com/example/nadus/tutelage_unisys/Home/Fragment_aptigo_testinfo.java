package com.example.nadus.tutelage_unisys.Home;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.Adapters.AssessmentQuestionAdapter;
import com.example.nadus.tutelage_unisys.Adapters.AssessmentResultCardAdapter;
import com.example.nadus.tutelage_unisys.Adapters.AssessmentResultItemAdapter;
import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.R;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.content.ContentValues.TAG;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_aptigo_testinfo extends Fragment {

    Calligrapher calligrapher;
    RecyclerView recyclerView;
    ArrayList<AssessmentResultCardAdapter> list1 = new ArrayList<AssessmentResultCardAdapter>();
    ProgressDialog progressDialog;
    AssessmentResultItemAdapter assessmentResultItemAdapter;
    ArrayList<String> stringArrayList = new ArrayList<>();
    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFile;
    File file;
    ListView list;
    String[] subjects;


    Button onsdCard,updir;
    Button confirm,upload,excel_upload1;


    ArrayList<AssessmentQuestionAdapter> list2 = new ArrayList<>();

    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;

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
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Results...");
        progressDialog.show();



        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        progressDialog.show();
         list = (ListView) view.findViewById(R.id.list);
        // new MyTask().execute();

        progressDialog.dismiss();

    }

}
