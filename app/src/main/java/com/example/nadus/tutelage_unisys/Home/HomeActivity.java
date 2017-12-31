package com.example.nadus.tutelage_unisys.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.nadus.tutelage_unisys.R;
import com.example.nadus.tutelage_unisys.Registration.Step1;

import me.anwarshahriar.calligrapher.Calligrapher;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Calligrapher calligrapher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        calligrapher = new Calligrapher(this);
        calligrapher.setFont(HomeActivity.this,"GlacialIndifference-Regular.ttf",true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = Fragment_home_1.newInstance();
                            break;
                        case R.id.navigation_attendance:
                            selectedFragment = Fragment_attendance_2.newInstance();
                            break;
                        case R.id.navigation_soul:
                            selectedFragment = Fragment_soul_3.newInstance();
                            break;
                        case R.id.navigation_aptigo:
                            selectedFragment = Fragment_aptigo_4.newInstance();
                            break;
                    }
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment);
                    transaction.commit();
                    return true;
                }
            });
            //Manually displaying the first fragment - one time only
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, Fragment_home_1.newInstance());
            transaction.commit();

            //Used to select an item programmatically
            //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
}
