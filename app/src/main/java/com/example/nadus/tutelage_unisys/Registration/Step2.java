package com.example.nadus.tutelage_unisys.Registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;


import com.example.nadus.tutelage_unisys.DataModels.UserCreds;
import com.example.nadus.tutelage_unisys.FB_Uploads.DataBlob;
import com.example.nadus.tutelage_unisys.Home.HomeActivity;
import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Utils.ImageFilePath;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import me.anwarshahriar.calligrapher.Calligrapher;

import static com.example.nadus.tutelage_unisys.FB_Uploads.DataBlob.CreateUser;
import static com.example.nadus.tutelage_unisys.FB_Uploads.DataBlob.UserDetailsUpload;

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
    String ColgName,UCat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        pref = getSharedPreferences("Tutelage",0);
        editor = pref.edit();

        Bundle extras = getIntent().getExtras();
         ColgName = extras.getString("ColgName");
        UCat = extras.getString("Ucat");
        profpic = (FloatingActionButton)findViewById(R.id.profpic);
        prof_pic = (CircleImageView)findViewById(R.id.profile_image);
        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Step2.this,"GlacialIndifference-Regular.ttf",true);
        System.out.println("IN NEXT "+pref.getString("UInstitution",""));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        next2 = (FloatingActionButton) findViewById(R.id.next2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataBlob.userCreateFlag==1) {
                    editor.putBoolean("isLogin",Boolean.TRUE);
                    startActivity(new Intent(Step2.this, HomeActivity.class));
                }
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
            // Log.d(TAG, String.valueOf(bitmap));
              prof_pic.setImageBitmap(bitmap);
            UserCreds userCreds = new UserCreds();
            userCreds.setUname(pref.getString("Uname",""));
            userCreds.setUmail(pref.getString("UEmail",""));
            userCreds.setUDOB(pref.getString("Udob",""));
            userCreds.setUinstitution(ColgName);
            userCreds.setUcontact(pref.getString("Ucontact",""));
//                UserCreds.Uname = pref.getString("Uname","");
//                UserCreds.Umail = pref.getString("Umail","");
//                UserCreds.UDOB = pref.getString("Udob","");
//                UserCreds.Uinstitution = ColgName;
            String mailsplit = UserCreds.Umail.replace(".com","");
            String path = "Users/"+pref.getString("UInstitution","")+"/UserCreds/"+UCat+"/"+mailsplit+"/";
            System.out.println("PATH PASSED IS "+path);
            CreateUser(uri,path,userCreds);

        }
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
}
