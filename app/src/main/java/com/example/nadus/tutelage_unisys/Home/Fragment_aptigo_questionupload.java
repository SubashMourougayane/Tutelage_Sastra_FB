package com.example.nadus.tutelage_unisys.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
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

import com.example.nadus.tutelage_unisys.Adapters.AssessmentQuestionAdapter;
import com.example.nadus.tutelage_unisys.Adapters.ClassAdapter;
import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Registration.Step1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
import java.util.Arrays;
import java.util.Iterator;

import me.anwarshahriar.calligrapher.Calligrapher;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_aptigo_questionupload extends Fragment {

    Button updir,confirm,upload,excel_upload;
    ListView list;
    Calligrapher calligrapher;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String univ_name;
    StringBuilder cls;
    DatabaseReference databaseReference;
    private static final String TAG = "MainActivity";

    private String[] FilePathString;
    private String[] FileNameString;
    private File[] listFile;
    File file;
    String[] subjects;

    Button onsdCard;




    ArrayList<AssessmentQuestionAdapter> list1 = new ArrayList<>();

    ArrayList<String> pathHistory;
    String lastDirectory;
    int count = 0;
    ArrayList<String> stringArrayList = new ArrayList<>();
    private String mailsplit;


    public static Fragment_aptigo_questionupload newInstance() {
        Fragment_aptigo_questionupload fragment = new Fragment_aptigo_questionupload();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_aptigo_excel, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        updir = (Button) v.findViewById(R.id.updir);
        confirm = (Button) v.findViewById(R.id.confirm);
        upload = (Button) v.findViewById(R.id.upload);
        excel_upload = (Button) v.findViewById(R.id.excel_upload);
        list = (ListView)v.findViewById(R.id.list);

        upload.setVisibility(View.GONE);


        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        univ_name = prefs.getString("univ_name", "");
        //u_pass = prefs.getString("u_pass", "");
        mailsplit = prefs.getString("mailsplit","");

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
        confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                upload.setVisibility(View.VISIBLE);
                new MyTask().execute();
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
//
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
            System.out.println("Size"+sheet.getLastRowNum());
            cls=new StringBuilder();
            row1=sheet.getRow(0);
            for(int cellIndex=0;cellIndex<sheet.getRow(0).getLastCellNum();cellIndex++)
            {
                if(cellIndex==sheet.getRow(0).getLastCellNum())
                {
                    cls.append(getCellAsString(row1, cellIndex, formulaEvaluator));
                }
                else {
                    if(!getCellAsString(row1, cellIndex, formulaEvaluator).equals(""))
                    {

                        cls.append("@" + getCellAsString(row1, cellIndex, formulaEvaluator));
                    }
                }

            }
            for (int rowIndex=2; rowIndex<rowsCount;rowIndex++)
            {
                row=sheet.getRow(rowIndex);
                if(row !=null)
                {
                    int cellLast = sheet.getRow(rowIndex).getLastCellNum();
                    String s1=getCellAsString(row,0,formulaEvaluator);
                    for (int cellIndex = 1; cellIndex < cellLast; cellIndex++)
                    {
                        Cell cell=row.getCell(cellIndex);
                        if(cell !=null)
                        {
                            String dect=getCellAsString(row,cellIndex,formulaEvaluator);
                            if(!dect.equals(""))
                            {
                                stringArrayList.add(""+dect);
                            }
                        }
                    }
                    Log.d(TAG, "STRING LIST=" + stringArrayList.size());
                    Log.d(TAG, "Question = " + s1);

                    subjects = new String[stringArrayList.size()];
                    stringArrayList.toArray(subjects);

                    for(String s : stringArrayList)
                    {
                        System.out.println("Options are "+s);
                    }
                    //list1.add(new ClassAdapter(s, subjects));

//                    ;
                    if(stringArrayList.size()==5)
                    {
                        String op1,op2,op3,op4;
                        op1=stringArrayList.get(0);
                        op2=stringArrayList.get(1);
                        op3=stringArrayList.get(2);
                        op4=stringArrayList.get(3);
                        String corop=stringArrayList.get(4).toString();
                        list1.add(new AssessmentQuestionAdapter(op1,op2,op3,op4,corop,s1));
                        //System.out.println("List is --> "+list1.get(0).getQuestion()+list1.get(0).getCorrectOP().toString());
                        //System.out.println(""+ list1);
                    }
                }

                stringArrayList.clear();
                System.out.println("cls"+cls);

            }
//            for(int in=0;in<list1.size();in++)
//            {
//                System.out.println("List is --> "+list1.get(in).getQuestion()+list1.get(in).getCorrectOP().toString());
//                for(String sd:list1.get(0).getOptions())
//                {
//                    System.out.println(""+sd.toString());
//                }
//            }
//            for (String s : stringArrayList) {
//                System.out.println("values :" + s);
//            }
            System.out.println("Assessment Question Size="+list1.size());
//            System.out.println("ClassAdapter_Size=" + list1.size());
//            System.out.println("ClassAdapter_Value" + list1.get(1).getA().toString());
            Iterator<AssessmentQuestionAdapter> iterator = list1.iterator();


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

    private class MyTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String cls_string = cls.toString();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(univ_name).child("Assessments").child(mailsplit).child(cls_string);
            databaseReference.setValue(list1);
            return null;
        }
    }


//
}
