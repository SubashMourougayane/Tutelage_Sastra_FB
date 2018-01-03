package com.example.nadus.tutelage_unisys.Home;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.Adapters.Timeline;
import com.example.nadus.tutelage_unisys.DataModels.UserCreds;
import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Splash.Splash_New;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by nadus on 21-12-2017.
 */

public class Fragment_settings_5 extends Fragment {

    public static Fragment_settings_5 newInstance() {
        Fragment_settings_5 fragment = new Fragment_settings_5();
        return fragment;
    }

    TextView fullname,email,dob,location,number,support,aboutus,signout;
    DatabaseReference databaseReference;
    String univ_name;
    String u_pass,ucat,mailsplit;
    String categ[];
    ProgressDialog progressDialog;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_settings, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Information...");
        progressDialog.show();
        fullname = (TextView) v.findViewById(R.id.fullname);
        email = (TextView) v.findViewById(R.id.email);
        dob = (TextView) v.findViewById(R.id.dob);
        location = (TextView) v.findViewById(R.id.location);
        number = (TextView) v.findViewById(R.id.number);
        support = (TextView) v.findViewById(R.id.support);
        aboutus = (TextView) v.findViewById(R.id.aboutus);
        signout = (TextView) v.findViewById(R.id.signout);

        signout.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmation");
            builder.setMessage("Are you sure to sign out?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    firebaseAuth.signOut();
                    dialogInterface.dismiss();
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("Tutelage", 0).edit();
                    editor.clear();
                    startActivity(new Intent(getActivity(), Splash_New.class));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        SharedPreferences prefs = getActivity().getSharedPreferences("Tutelage", 0);
            univ_name = prefs.getString("univ_name", "");
            u_pass = prefs.getString("u_pass", "");
            mailsplit = prefs.getString("mailsplit","");


        System.out.println("Upass is "+u_pass);
        categ = u_pass.split("_");
        u_pass = categ[0]+"_"+categ[1];
        if(categ[2].equals("T"))
        {
            ucat = "Teachers";
        }
        else if (categ[2].equals("S"))
        {
            ucat = "Students";
        }
        new MyTask().execute();

        return v;
    }

    private class MyTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(univ_name).child("UserCreds").child(ucat).child(mailsplit);
            System.out.println("DB REF is "+databaseReference);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("BOWWw "+dataSnapshot.getKey());
                    UserCreds userCreds = dataSnapshot.getValue(UserCreds.class);
                    fullname.setText(userCreds.getUname());
                    email.setText(userCreds.getUmail());
                    dob.setText(userCreds.getUDOB());
                    number.setText(userCreds.getUcontact());
                    location.setText(userCreds.getUlocation());
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;
        }
    }

}
