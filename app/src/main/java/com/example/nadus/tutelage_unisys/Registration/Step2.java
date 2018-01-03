package com.example.nadus.tutelage_unisys.Registration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nadus.tutelage_unisys.DataModels.UserCreds;
import com.example.nadus.tutelage_unisys.Home.HomeActivity;
import com.example.nadus.tutelage_unisys.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import am.appwise.components.ni.NoInternetDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;

import static com.example.nadus.tutelage_unisys.FB_Uploads.DataBlob.CreateUser;

/**
 * Created by nadus on 21-12-2017.
 */

public class Step2 extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Calligrapher calligrapher;
    FloatingActionButton next2;
    FloatingActionButton profpic;
    CircleImageView prof_pic;
    String Univname,UCat,Uname,Umail,Ucontact,Udob,Passkey,Ulocation;
    NoInternetDialog noInternetDialog;
    public Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        noInternetDialog = new NoInternetDialog.Builder(Step2.this).setBgGradientStart(getResources().getColor(R.color.colorGray)).setBgGradientCenter(getResources().getColor(R.color.colorGray)).setBgGradientEnd(getResources().getColor(R.color.colorGrayDark)).build();

        context = Step2.this;
        Bundle extras = getIntent().getExtras();
        Univname = extras.getString("Univname");
        UCat = extras.getString("Ucat");
        Uname = extras.getString("Uname");
        Umail = extras.getString("Umail");
        Ucontact = extras.getString("Ucontact");
        Udob = extras.getString("Udob");
        Passkey = extras.getString("Upass");
        Ulocation = extras.getString("Ulocation");

        profpic = (FloatingActionButton)findViewById(R.id.profpic);
        prof_pic = (CircleImageView)findViewById(R.id.profile_image);
        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Step2.this,"GlacialIndifference-Regular.ttf",true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        next2 = (FloatingActionButton) findViewById(R.id.next2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Step2.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure all the details are correct?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Step2.this, HomeActivity.class));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        profpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 99);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Location is "+Ulocation);
            // Log.d(TAG, String.valueOf(bitmap));
            prof_pic.setImageBitmap(bitmap);
            UserCreds userCreds = new UserCreds();
            userCreds.setUname(Uname);
            userCreds.setUmail(Umail);
            userCreds.setUDOB(Udob);
            userCreds.setUinstitution(Univname);
            userCreds.setUcontact(Ucontact);
            userCreds.setUpass(Passkey);
            userCreds.setUlocation(Ulocation);



            System.out.println("Umail is "+userCreds.getUmail());
            String mailsplit = UserCreds.Umail.replace(".","_");
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(Step1.UnivName).child("UserCreds").child(UCat).child(mailsplit);
            System.out.println("PATH PASSED IS "+databaseReference);

            SharedPreferences.Editor editor = getSharedPreferences("Tutelage", 0).edit();
            editor.putString("univ_name", Univname);
            editor.putString("u_pass", Passkey);
            editor.putString("mailsplit",mailsplit);
            editor.apply();

            CreateUser(uri,databaseReference,userCreds,context);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
}
