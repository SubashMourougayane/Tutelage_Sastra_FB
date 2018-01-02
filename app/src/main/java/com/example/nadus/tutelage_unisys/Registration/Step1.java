package com.example.nadus.tutelage_unisys.Registration;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.nadus.tutelage_unisys.DataModels.Universities;
import com.example.nadus.tutelage_unisys.FB_Downloads.Retrieve;
import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Splash.Splash;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import am.appwise.components.ni.NoInternetDialog;
import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * Created by nadus on 21-12-2017.
 */

public class Step1 extends AppCompatActivity {
    Calligrapher calligrapher;
    FloatingActionButton next1;
    EditText Username,Email,DOB,Contact,Passkey;
    String uname,umail,udob,ucontact,upass,uinstitution;
    ProgressDialog progressDialog;

    static String UnivName;
    String categ[];
    String ucat;
    NoInternetDialog noInternetDialog;
    Calendar myCalendar;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        progressDialog = new ProgressDialog(Step1.this);

        noInternetDialog = new NoInternetDialog.Builder(Step1.this).setBgGradientStart(getResources().getColor(R.color.colorGray)).setBgGradientCenter(getResources().getColor(R.color.colorGray)).setBgGradientEnd(getResources().getColor(R.color.colorGrayDark)).build();

        Bundle extras = getIntent().getExtras();
        uname = extras.getString("Uname");
        umail = extras.getString("UEmail");
        System.out.println("HAHA"+ uname+" "+umail);

        Username = (EditText)findViewById(R.id.fullname);
        Email = (EditText)findViewById(R.id.email);
        DOB = (EditText)findViewById(R.id.dob);
        Contact = (EditText)findViewById(R.id.contact);
        Passkey = (EditText)findViewById(R.id.passkey);
        Username.setText(uname);
        Email.setText(umail);

        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Step1.this,"GlacialIndifference-Regular.ttf",true);
        next1 = (FloatingActionButton) findViewById(R.id.next1);


        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Processing Information...");
                progressDialog.show();
                uname=Username.getText().toString().trim();
                umail=Email.getText().toString().trim();
                udob=DOB.getText().toString().trim();
                ucontact=Contact.getText().toString().trim();
                upass=Passkey.getText().toString().trim();
                categ = upass.split("_");
                upass = categ[0]+"_"+categ[1];
                if(categ[2].equals("T"))
                {
                    ucat = "Teachers";
                }
                else if (categ[2].equals("S"))
                {
                    ucat = "Students";
                }
                System.out.println("USER CATEGORY IS "+ucat);
                new GetInstution().execute();
            }
        });

        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Step1.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DOB.setText(sdf.format(myCalendar.getTime()));
    }

    class GetInstution extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Object doInBackground(Object[] objects) {

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin").child("Universities");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren())
                    {
                        System.out.println("checking"+ upass + " == " + postsnapshot.getKey());
                        if (upass.equals(postsnapshot.getKey()))
                        {
                            UnivName = postsnapshot.getValue().toString();
                            System.out.println("LOL"+UnivName);
                            Intent i = new Intent(Step1.this, Step2.class);
                            i.putExtra("ColgName",UnivName);
                            i.putExtra("Ucat",ucat);
                            i.putExtra("Uname",uname);
                            i.putExtra("Umail",umail);
                            i.putExtra("Ucontact",ucontact);
                            i.putExtra("Udob",udob);
                            startActivity(i);
                            break;
                        }
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }



}
