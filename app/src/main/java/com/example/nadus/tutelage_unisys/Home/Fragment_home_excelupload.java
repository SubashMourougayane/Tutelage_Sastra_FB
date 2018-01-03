package com.example.nadus.tutelage_unisys.Home;

import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.Switch;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.DataModels.TimeTable;
import com.example.nadus.tutelage_unisys.DataModels.Universities;
import com.example.nadus.tutelage_unisys.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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
import java.nio.FloatBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_home_excelupload extends Fragment {

    Button confirm,upload,excel_upload;
    ListView list;
    private static final String TAG = "home_excelUpload";
    Calligrapher calligrapher;
    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFile;
    File file;
    String[] subjects;

    Button onsdCard,updir;

    SharedPreferences preferences;
    DatabaseReference fb_db;
    ArrayList<ClassAdapter> list1 = new ArrayList<ClassAdapter>();
    ArrayList<String> timings=new ArrayList<>();
    ArrayList<String> Mon=new ArrayList<>();
    ArrayList<String> Tues=new ArrayList<>();
    ArrayList<String> Weds=new ArrayList<>();
    ArrayList<String> Thurs=new ArrayList<>();
    ArrayList<String> Fri=new ArrayList<>();
    ArrayList<String> Sat=new ArrayList<>();
    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;
    ArrayList<String> stringArrayList = new ArrayList<>();
    String UnivName,Mail_Split;
    public static Fragment_home_excelupload newInstance() {
        Fragment_home_excelupload fragment = new Fragment_home_excelupload();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_dashboard_excel, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);
        updir = (Button) v.findViewById(R.id.updir);
        confirm = (Button) v.findViewById(R.id.confirm);
        upload = (Button) v.findViewById(R.id.upload);
        excel_upload = (Button) v.findViewById(R.id.excel_upload);
        preferences = getActivity().getSharedPreferences("Tutelage",0);
        UnivName = preferences.getString("univ_name","");
        Mail_Split = preferences.getString("mailsplit","");
        list = (ListView)v.findViewById(R.id.list);
        fb_db = FirebaseDatabase.getInstance().getReference();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"LOL",Toast.LENGTH_SHORT).show();
                TimeTable timeTable = new TimeTable();
                timeTable.setMonday(Mon);
                timeTable.setTuesday(Tues);
                timeTable.setWednesday(Weds);
                timeTable.setThursday(Thurs);
                timeTable.setFriday(Fri);
                timeTable.setSaturday(Sat);
                timeTable.setTimings(timings);



                System.out.print("UPLOAD TASK"+ timeTable.getMonday());
                fb_db = fb_db.child("Users").child(UnivName).child("TimeTable")
                        .child("Teachers").child(Mail_Split);
                System.out.println("BOWWW "+fb_db);
                fb_db.setValue(timeTable);
            }
        });
        upload.setVisibility(View.GONE);
        excel_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                count =0;
                pathHistory = new ArrayList<String>();
                pathHistory.add(count,System.getenv("EXTERNAL_STORAGE"));
                Log.d(TAG, "BTNOnSDCard: "+pathHistory.get(count));
                checkInternalStorage();
//                        Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();

            }
        });
        updir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count ==0)
                {
                    Log.d(TAG, "btnup dir: you have reached max hight..");
                    // Toast.makeText(getActivity(), "Reached Max Height", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pathHistory.remove(count);
                    count--;
                    checkInternalStorage();
                    Log.d(TAG,"btnupdir: "+pathHistory.get(count));
                    //  Toast.makeText(getActivity(), "btnupdir", Toast.LENGTH_SHORT).show();
                }

            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lastDirectory = pathHistory.get(count);
                if(lastDirectory.equals(adapterView.getItemAtPosition(i)))
                {
                    Log.d(TAG,"InternalStorage: Selected a file for upload: "+lastDirectory);
                    timings.clear();
                    Mon.clear();
                    Tues.clear();
                    Weds.clear();
                    Thurs.clear();
                    Fri.clear();
                    Sat.clear();
                    readExcelData(lastDirectory);


                }
                else
                {
                    count++;
                    pathHistory.add(count,(String)adapterView.getItemAtPosition(i));
                    checkInternalStorage();
                    Log.d(TAG, "InternalStorage: "+pathHistory.get(count));
                }
            }
        });


        return v;
    }
    private void readExcelData(String filePath)
    {

//        new Runnable() {
//            @Override
//            public void run() {
//                if(!getActivity().isFinishing()) {


        Log.d(TAG, "ReadExccelData: Reading Excel File:");
        // Toast.makeText(getActivity(), "ReadExcelData", Toast.LENGTH_SHORT).show();

        File inputfile = new File(filePath);

        try {
            InputStream inputStream = new FileInputStream(inputfile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            StringBuilder sb = new StringBuilder();

            Row row, row1;
            int column = sheet.getRow(0).getLastCellNum();
            Cell cell1;
            System.out.println("row=" + rowsCount);
            row1=sheet.getRow(0);
            for(int cellIndex=0;cellIndex<sheet.getRow(0).getLastCellNum();cellIndex++)
            {
                if(!row1.getCell(cellIndex).equals("")) {
                    timings.add(getCellAsString(row1, cellIndex, formulaEvaluator));
                }
            }
            for (int rowIndex=1; rowIndex<rowsCount&&rowIndex<=7;rowIndex++)
            {
                row=sheet.getRow(rowIndex);
                if(row !=null)
                {
                    int cellLast = sheet.getRow(rowIndex).getLastCellNum();
                    for (int cellIndex = 0; cellIndex < cellLast; cellIndex++)
                    {
                        Cell cell=row.getCell(cellIndex);
                        if(cell !=null)
                        {
                            String dect=getCellAsString(row,cellIndex,formulaEvaluator);
                            if(!dect.equals(""))
                            {
                               // stringArrayList.add(""+dect+"("+rowIndex+","+cellIndex+")");
                                switch(rowIndex)
                                {
                                    case 1:Mon.add(""+dect+","+cellIndex);
                                                break;
                                    case 2:Tues.add(""+dect+","+cellIndex);
                                        break;
                                    case 3:Weds.add(""+dect+","+cellIndex);
                                        break;
                                    case 4:Thurs.add(""+dect+","+cellIndex);
                                        break;
                                    case 5:Fri.add(""+dect+","+cellIndex);
                                        break;
                                    case 6:Sat.add(""+dect+","+cellIndex);
                                            break;
                                        default:break;
                                }
                            }
                        }
                    }
                }
                Log.d(TAG,"STRING LIST="+stringArrayList.size());

                subjects=new String[stringArrayList.size()];
                stringArrayList.toArray(subjects);
            }
            System.out.println("MONDAY "+Mon);

            System.out.println("Timings="+timings.size());
            System.out.println("Monday--"+Mon.size());
            System.out.println("Thursday--"+Thurs.size());
           // System.out.println("ClassAdapter_Value" + list1.get(1).getA().toString());
            Iterator<ClassAdapter> iterator = list1.iterator();


            Log.d(TAG, "readExcelData: STRINGBUILDER: " + sb.toString());
//            parseStringBuilder(sb);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "readExcelData: FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "readExcelData: IOException: " + e.getMessage());
        }
//                }
//            }
//        };

    }

//    private void parseStringBuilder(StringBuilder sb)
//    {
//        Log.d(TAG, "parseStringBuilder: Started parsing..");
//
//        String[] row = sb.toString().split(":");
//        for(int i=0;i<row.length;i++)
//        {
//            String[] columns = row[i].split(",");
//            try
//            {
//                String x = (columns[0]);
////                String y = (columns[1]);
//
//                String cellInfo = "(x,y): ("+x+")";
//                Log.d(TAG, "ParseStringBuilder: Data from row: " +cellInfo);
//
//                // uploadData.add(new ClassAdapter(x));
//            }
//            catch(NumberFormatException e)
//            {
//                Log.e(TAG, "parseStringBuilder: NumberFormatException: "+e.getMessage());
//            }
//        }
//        //printDataToLog();
//    }


    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator)
    {
        String value="";
        try
        {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType())
            {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell))
                    {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    }
                    else
                    {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    break;
                default:
                    break;
            }
        }
        catch(NullPointerException e)
        {
            Log.e(TAG, "getCEllAsString: NullPointerException: " + e.getMessage());
        }
        return  value;
    }

    private void checkInternalStorage() {
        Log.d(TAG,"CheckInternalStorage.");
        try
        {
            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                //toastMessage("No SD Card Found");
            }
            else
            {
                file = new File(pathHistory.get(count));
                Log.d(TAG, "CheckExternalStorage: Directory Path: " + pathHistory.get(count));
            }

            listFile = file.listFiles();
            FilePathString = new String[listFile.length];
            FileNameString = new String[listFile.length];

            for(int i=0; i<listFile.length;i++)
            {
                FilePathString[i]=listFile[i].getAbsolutePath();
                FileNameString[i]=listFile[i].getName();
            }

            for(int i=0;i<listFile.length;i++)
            {
                Log.d("Files","FileName: "+ listFile[i].getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,FilePathString);
            list.setAdapter(adapter);
        }
        catch(NullPointerException e)
        {
            Log.e(TAG,"CheckInternalStorage: NULLPOINTEREXCEPTION "+e.getMessage());
        }

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


}