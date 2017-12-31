package com.example.nadus.tutelage_unisys.Splash;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Registration.Step1;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import me.anwarshahriar.calligrapher.Calligrapher;
public class Splash extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener
{
    Handler handler=new Handler();
    boolean visible;
    TransitionManager transitionManager=new TransitionManager();
    GoogleApiClient gac;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    Calligrapher calligrapher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Splash.this,"GlacialIndifference-Regular.ttf",true);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions);
        final Button sigin=(Button)transitionsContainer.findViewById(R.id.signin);
        handler.postDelayed(new Runnable() {
            @Override
            public void run()
            {
                transitionManager.beginDelayedTransition(transitionsContainer);
                visible = !visible;
                sigin.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        }, 2000);
        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 99);
            }
        });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(getApplicationContext(),"Welcome "+account.getGivenName()+" !",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Step1.class));
        } catch (ApiException e) {
            Toast.makeText(getApplicationContext(),"Login failed try again",Toast.LENGTH_SHORT).show();
        }
    }
}
