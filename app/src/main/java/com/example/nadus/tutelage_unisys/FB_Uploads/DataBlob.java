package com.example.nadus.tutelage_unisys.FB_Uploads;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.DataModels.UserCreds;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
/**
 * Created by msuba on 12/31/2017.
 */

public class DataBlob
{
    private static FirebaseStorage storage;
    private static StorageReference storageReference;
    private static Firebase fb_db;
    public static void CreateUser(Uri filePath, DatabaseReference databaseReference, UserCreds userCreds)
    {
        if(filePath != null)
        {
            //fb_db = new Firebase("https://tutelage-d619f.firebaseio.com/").child(FBPath);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            StorageReference ref = storageReference.child(databaseReference+ UserCreds.Umail);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            databaseReference.setValue(userCreds);
                           // userCreateFlag = 1;
//                            return Boolean.TRUE;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            System.out.println("PROGRESS IS "+progress );
                        }
                    });
        }

    }
    public static void UserDetailsUpload(UserCreds userCreds,String FBpath)
    {

    }
}
