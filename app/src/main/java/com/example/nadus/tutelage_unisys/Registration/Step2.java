package com.example.nadus.tutelage_unisys.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.nadus.tutelage_unisys.R;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Step2 extends AppCompatActivity {

    Calligrapher calligrapher;
    FloatingActionButton next2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

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
                startActivity(new Intent(Step2.this,Step3.class));
            }
        });

    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
}
