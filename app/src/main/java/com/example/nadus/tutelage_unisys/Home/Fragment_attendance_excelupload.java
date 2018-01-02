package com.example.nadus.tutelage_unisys.Home;

import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import me.anwarshahriar.calligrapher.Calligrapher;


public class Fragment_attendance_excelupload extends Fragment
{

    Button confirm,upload,excel_upload;
    ListView list1;
    private static final String TAG = "ExcelUpload";
    Calligrapher calligrapher;
    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFile;
    File file;
    String[] subjects;

    Button onsdCard,updir;
    ListView internalstorage;

    ArrayList<ClassAdapter> list = new ArrayList<ClassAdapter>();

    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;
    ArrayList<String> stringArrayList = new ArrayList<>();

    ArrayList<ClassAdapter> uploadData;

    public static Fragment_attendance_excelupload newInstance() {
        Fragment_attendance_excelupload fragment = new Fragment_attendance_excelupload();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_attendance_excel, container, false);
        CheckFilePermission();

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        updir = (Button) v.findViewById(R.id.updir);
        confirm = (Button) v.findViewById(R.id.confirm);
        upload = (Button) v.findViewById(R.id.upload);
        excel_upload = (Button) v.findViewById(R.id.excel_upload);
        list1 = (ListView)v.findViewById(R.id.list);

        upload.setVisibility(View.GONE);
        
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload.setVisibility(View.VISIBLE);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if(lastDirectory.equals(adapterView.getItemAtPosition(i)))
                {
                    Log.d(TAG,"InternalStorage: Selected a file for upload: "+lastDirectory);
                    //readExcelData(lastDirectory);
                }
                else
                {
                    count++;
                    pathHistory.add(count,(String)adapterView.getItemAtPosition(i));
                    //checkInternalStorage();
                    Log.d(TAG, "InternalStorage: "+pathHistory.get(count));
                }
            }
        });


        return v;
    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    private void CheckFilePermission()
    {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
        {
            int permissionCheck = getActivity().checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck = getActivity().checkSelfPermission("Manifest.permission.WRITE_EXTERNAL-STORAGE");

            if(permissionCheck != 0)
            {
                this.requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
            }
            else
            {
                Log.d(TAG , "CheckPermissions: No Need to Check Permission. SDK version < LOLLIPOP");
            }
        }
    }
    private void toastMessage(String Message)
    {
        Toast.makeText(getActivity(),Message, Toast.LENGTH_SHORT).show();
    }
}
