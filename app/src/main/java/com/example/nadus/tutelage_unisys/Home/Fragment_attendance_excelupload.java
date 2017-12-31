package com.example.nadus.tutelage_unisys.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.nadus.tutelage_unisys.R;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_attendance_excelupload extends Fragment {

    Button updir,confirm,upload,excel_upload;
    ListView list;
    Calligrapher calligrapher;

    public static Fragment_attendance_excelupload newInstance() {
        Fragment_attendance_excelupload fragment = new Fragment_attendance_excelupload();
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
        View v = inflater.inflate(R.layout.home_attendance_excel, container, false);

        calligrapher = new Calligrapher(getActivity());
        calligrapher.setFont(getActivity(),"GlacialIndifference-Regular.ttf",true);

        updir = (Button) v.findViewById(R.id.updir);
        confirm = (Button) v.findViewById(R.id.confirm);
        upload = (Button) v.findViewById(R.id.upload);
        excel_upload = (Button) v.findViewById(R.id.excel_upload);
        list = (ListView)v.findViewById(R.id.list);

        upload.setVisibility(View.GONE);
        
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload.setVisibility(View.VISIBLE);
            }
        });
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),"Successfully Uploaded",Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(),Admin_Home.class));
//
//
//            }
//        });

//        CheckFilePermission();
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                lastDirectory = pathHistory.get(count);
//                if(lastDirectory.equals(adapterView.getItemAtPosition(i)))
//                {
//                    Log.d(TAG,"InternalStorage: Selected a file for upload: "+lastDirectory);
//                    readExcelData(lastDirectory);
//                }
//                else
//                {
//                    count++;
//                    pathHistory.add(count,(String)adapterView.getItemAtPosition(i));
//                    checkInternalStorage();
//                    Log.d(TAG, "InternalStorage: "+pathHistory.get(count));
//                }
//            }
//        });
//
//        excel_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                list.setVisibility(View.VISIBLE);
//                count =0;
//                pathHistory = new ArrayList<String>();
//                pathHistory.add(count,System.getenv("EXTERNAL_STORAGE"));
//                Log.d(TAG, "BTNOnSDCard: "+pathHistory.get(count));
//                checkInternalStorage();
//                updir.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//        updir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(count ==0)
//                {
//                    Log.d(TAG, "btnup dir: you have reached max hight..");
//                }
//                else
//                {
//                    pathHistory.remove(count);
//                    count--;
//                    checkInternalStorage();
//                    Log.d(TAG,"btnupdir: "+pathHistory.get(count));
//                }
//            }
//        });

//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog.setMessage("Uploading...");
//                progressDialog.show();
//                //System.out.println("Bow "+xyValues.getList_question());
//                new MyTask().execute();
//                //qi.setValues();
//            }
//        });

        return v;
    }

//    private class MyTask extends AsyncTask<String,Integer,String>
//    {
//        @Override
//        protected String doInBackground(String... strings) {
//            tname= dummy.getBd()+"@"+dummy.getBt()+"@"+dummy.getTdur()+"@"+dummy.getName()+"@"+dummy.getCount1();
//            String url_new = url+"Test/"+dummy.getTdept()+"/"+dummy.getTyear()+"/";
//            System.out.println("New URL "+url_new);
//            fb = new Firebase(url_new);
//            for(int i=1;i<=xyValues.getList_question().size();i++)
//            {
//                ArrayList<String> temp = new ArrayList<>();
//                temp.add(xyValues.getList_question().get(i-1));
//                temp.add(xyValues.getList_option1().get(i-1));
//                temp.add(xyValues.getList_option2().get(i-1));
//                temp.add(xyValues.getList_option3().get(i-1));
//                temp.add(xyValues.getList_option4().get(i-1));
//                temp.add(xyValues.getList_correct().get(i-1));
//                Admin_FB admin_fb = new Admin_FB();
//                admin_fb.setQuestion(temp.get(0));
//                admin_fb.setC1(temp.get(1));
//                admin_fb.setC2(temp.get(2));
//                admin_fb.setC3(temp.get(3));
//                admin_fb.setC4(temp.get(4));
//                admin_fb.setCorrect(temp.get(5));
//                System.out.println("-----> "+temp);
//                fb.child(tname).child(String.valueOf(i)).setValue(admin_fb);
//            }
//            progressDialog.dismiss();
//
//            return null;
//        }
//    }
//
//    private void checkInternalStorage() {
//        Log.d(TAG,"CheckInternalStorage.");
//        try
//        {
//            if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
//            {
//                toastMessage("No SD Card Found");
//            }
//            else
//            {
//                file = new File(pathHistory.get(count));
//                Log.d(TAG, "CheckExternalStorage: Directory Path: " + pathHistory.get(count));
//            }
//
//            listFile = file.listFiles();
//            FilePathString = new String[listFile.length];
//            FileNameString = new String[listFile.length];
//
//            for(int i=0; i<listFile.length;i++)
//            {
//                FilePathString[i]=listFile[i].getAbsolutePath();
//                FileNameString[i]=listFile[i].getName();
//            }
//
//            for(int i=0;i<listFile.length;i++)
//            {
//                Log.d("Files","FileName: "+ listFile[i].getName());
//            }
//
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,FilePathString);
//            internalstorage.setAdapter(adapter);
//        }
//        catch(NullPointerException e)
//        {
//            Log.e(TAG,"CheckInternalStorage: NULLPOINTEREXCEPTION "+e.getMessage());
//        }
//    }
//
//    private void CheckFilePermission()
//    {
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
//        {
//            int permissionCheck = getActivity().checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
//            permissionCheck = getActivity().checkSelfPermission("Manifest.permission.WRITE_EXTERNAL-STORAGE");
//
//            if(permissionCheck != 0)
//            {
//                this.requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
//            }
//            else
//            {
//                Log.d(TAG , "CheckPermissions: No Need to Check Permission. SDK version < LOLLIPOP");
//            }
//        }
//    }
//
//    private void readExcelData(String filePath)
//    {
//        Log.d(TAG, "ReadExccelData: Reading Excel File:");
//        System.out.println("BOWWW "+filePath);
//
//        File inputfile = new File(filePath);
//
//        try
//        {
//            InputStream inputStream = new FileInputStream(inputfile);
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            int rowsCount = sheet.getPhysicalNumberOfRows();
//            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//            //StringBuilder sb = new StringBuilder();
//            System.out.println("Rows count is ----> "+rowsCount);
//            int c;
//            for(int r=1;r<11;r++)
//            {
//                ques_list.clear();
//                Row row = sheet.getRow(r);
//                int cellCount = row.getPhysicalNumberOfCells();
//                System.out.println("Cell count is ----> "+cellCount);
//                for(c=0;c<cellCount;c++)
//                {
//                    if(c>6)
//                    {
//                        Log.e(TAG,"readExcelData: ERROR: Excel file format INcorrect");
//                        Toast.makeText(getActivity(),"Excel File Incorrect",Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    else
//                    {
//                        String value = getCellAsString(row,c,formulaEvaluator);
//                        String cellInfo = "r:" +r+ ";" + "c:" +c+ ";" + "v:" +value;
//                        //set here
//                        ques_list.add(value);
//                        Log.d(TAG, "ReadDataFromExcel: " +cellInfo);
//                        //sb.append(value +" ");
//                    }
//                }
//                //sb.append(":");
//                //add 5 array list
//                //System.out.println("OWWW "+ques_list);
//                arraylist_question.add(ques_list.get(0));
//                arraylist_option1.add(ques_list.get(1));
//                arraylist_option2.add(ques_list.get(2));
//                arraylist_option3.add(ques_list.get(3));
//                arraylist_option4.add(ques_list.get(4));
//                arraylist_correct.add(ques_list.get(5));
//                //   arraylist_number.add(++count);
////                System.out.println("11111Value retd "+arraylist_question);
////                System.out.println("11111Value op1 "+arraylist_option1);
////                System.out.println("11111Value op2 "+arraylist_option2);
////                System.out.println("11111Value op3 "+arraylist_option3);
////                System.out.println("11111Value op4 "+arraylist_option4);
//            }
//            System.out.println("ooooValue retd "+arraylist_question);
//            System.out.println("ooooValue op1 "+arraylist_option1);
//            System.out.println("ooooValue op2 "+arraylist_option2);
//            System.out.println("ooooValue op3 "+arraylist_option3);
//            System.out.println("ooooValue op4 "+arraylist_option4);
//            System.out.println("ooooValue correct "+arraylist_correct);
//
//            // xyValues.flag=true;
//            //new XYValues(arraylist_question,arraylist_option1,arraylist_option2,arraylist_option3,arraylist_option4);
//            xyValues.setList_question(arraylist_question);
//            xyValues.setList_option1(arraylist_option1);
//            xyValues.setList_option2(arraylist_option2);
//            xyValues.setList_option3(arraylist_option3);
//            xyValues.setList_option4(arraylist_option4);
//            xyValues.setList_correct(arraylist_correct);
//            //  xyValues.setList_number(arraylist_number);
//            //System.out.println("List is "+ques_list.toString());
////            xyValues.setQuestion(ques_list.get(0));
////            xyValues.setOption1(ques_list.get(1));
////            xyValues.setOption2(ques_list.get(2));
////            xyValues.setOption3(ques_list.get(3));
////            xyValues.setOption4(ques_list.get(4));
//
//
//            //Log.d(TAG, "readExcelData: STRINGBUILDER: "+sb.toString());
//            //Toast.makeText(getActivity(),sb.toString(), Toast.LENGTH_SHORT).show();
//            //parseStringBuilder(sb);
//
//
//        }
//        catch(FileNotFoundException e)
//        {
//            Log.e(TAG, "readExcelData: FileNotFoundException: "+ e.getMessage());
//        }
//        catch (IOException e)
//        {
//            Log.e(TAG, "readExcelData: IOException: " + e.getMessage());
//        }
//    }
//
//    private void parseStringBuilder(StringBuilder sb)
//    {
//        Log.d(TAG, "parseStringBuilder: Started parsing..");
//
//        String[] row = sb.toString().split(":");
//        System.out.println("Row length "+row.length);
//        for(int i=0;i<row.length;i++)
//        {
//            String[] columns = row[i].split(",");
//            System.out.println("Row is "+i+" ----> "+row[i].toString());
//            System.out.println("Col is "+columns.toString());
//            try
//            {
////                String ques = columns[0];
////                String op1 = columns[1];
////                String op2 = columns[2];
////                String op3 = columns[3];
////                String op4 = columns[4];
//                //String cellInfo = "(x,y): ("+x+","+y+")";
//                //Log.d(TAG, "ParseStringBuilder: Data from row: " +cellInfo);
////
////                System.out.println("zzzzzzzzzzzzzz "+ques);
////                System.out.println("zzzzzzzzzzzzzz "+op1);
////                System.out.println("zzzzzzzzzzzzzz "+op2);
////                System.out.println("zzzzzzzzzzzzzz "+op3);
////                System.out.println("zzzzzzzzzzzzzz "+op4);
////
////                uploadData.add(new XYValues(ques,op1,op2,op3,op4));
//            }
//            catch(NumberFormatException e)
//            {
//                Log.e(TAG, "parseStringBuilder: NumberFormatException: "+e.getMessage());
//            }
//        }
//        // printDataToLog();
//    }
//
//    private void printDataToLog()
//    {
//        Log.d(TAG, "Printing Log DATA....");
//        for(int i=0;i<uploadData.size();i++)
//        {
////            double x = uploadData.get(i).getX();
////            double y = uploadData.get(i).getY();
//
//            // Log.d(TAG, "PrintingDataToLog: (x,y): ("+x+","+y+")");
//        }
//    }
//
//    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator)
//    {
//        String value="";
//        try
//        {
//            Cell cell = row.getCell(c);
//            CellValue cellValue = formulaEvaluator.evaluate(cell);
//            switch (cellValue.getCellType())
//            {
//                case Cell.CELL_TYPE_BOOLEAN:
//                    value = ""+cellValue.getBooleanValue();
//                    break;
//                case Cell.CELL_TYPE_NUMERIC:
//                    double numericValue = cellValue.getNumberValue();
//                    if(HSSFDateUtil.isCellDateFormatted(cell))
//                    {
//                        double date = cellValue.getNumberValue();
//                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
//                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
//                    }
//                    else
//                    {
//                        value = ""+numericValue;
//                    }
//                    break;
//                case Cell.CELL_TYPE_STRING:
//                    value = ""+cellValue.getStringValue();
//                    break;
//                default:
//            }
//        }
//        catch(NullPointerException e)
//        {
//            Log.e(TAG, "getCEllAsString: NullPointerException: " + e.getMessage());
//        }
//        return  value;
//    }
//
//    private void toastMessage(String Message)
//    {
//        Toast.makeText(getActivity(),Message, Toast.LENGTH_SHORT).show();
//    }
}
