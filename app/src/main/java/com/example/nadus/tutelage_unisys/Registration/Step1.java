package com.example.nadus.tutelage_unisys.Registration;

import android.annotation.SuppressLint;
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
import android.widget.EditText;

import com.example.nadus.tutelage_unisys.DataModels.Universities;
import com.example.nadus.tutelage_unisys.FB_Downloads.Retrieve;
import com.example.nadus.tutelage_unisys.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import me.anwarshahriar.calligrapher.Calligrapher;


/**
 * Created by nadus on 21-12-2017.
 */

public class Step1 extends AppCompatActivity {
    Calligrapher calligrapher;
    FloatingActionButton next1;
    EditText Username,Email,DOB,Contact,Passkey;
    String uname,umail,udob,ucontact,upass,uinstitution;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static String UnivName;
    String categ[];
    String ucat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);
        Firebase.setAndroidContext(getApplicationContext());
        pref = getSharedPreferences("Tutelage",0);
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
                System.out.println("USER CATEGORUY IS "+ucat);
                new GetInstution().execute();
                editor=pref.edit();
                editor.putString("Uname",uname);
                editor.putString("UEmail",umail);
                editor.putString("Udob",udob);
                editor.putString("Ucontact",ucontact);
                editor.putString("Ucat",ucat);
                editor.commit();
            }
        });
    }
    class GetInstution extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Object doInBackground(Object[] objects) {

            Firebase fb_db = new Firebase("https://tutelage-d619f.firebaseio.com/Admin/Universities/");
            fb_db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postsnapshot : dataSnapshot.getChildren())
                    {
                        System.out.println("checking"+ upass + " == " + postsnapshot.getName());
                        if (upass.equals(postsnapshot.getName()))
                        {
                            UnivName = postsnapshot.getValue().toString();
                            System.out.println("LOL"+UnivName);
                            SharedPreferences pref = getSharedPreferences("Tutelage",0);
                            SharedPreferences.Editor editor;
                            editor=pref.edit();
                            editor.putString("UInstitution",UnivName);
                            editor.commit();
                            Intent i = new Intent(Step1.this, Step2.class);
                            i.putExtra("ColgName",postsnapshot.getName());
                            i.putExtra("Ucat",ucat);
                            startActivity(i);
                            break;


                        }
                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }







}
