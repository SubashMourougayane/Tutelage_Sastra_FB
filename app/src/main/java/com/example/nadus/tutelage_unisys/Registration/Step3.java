package com.example.nadus.tutelage_unisys.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nadus.tutelage_unisys.Home.HomeActivity;
import com.example.nadus.tutelage_unisys.R;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Step3 extends AppCompatActivity {

    Calligrapher calligrapher;
    FloatingActionButton next3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step3);

        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Step3.this,"GlacialIndifference-Regular.ttf",true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        next3 = (FloatingActionButton) findViewById(R.id.next3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Step3.this,HomeActivity.class));
            }
        });
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
}
