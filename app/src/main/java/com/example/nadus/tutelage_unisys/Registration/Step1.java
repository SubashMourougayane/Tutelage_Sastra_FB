package com.example.nadus.tutelage_unisys.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nadus.tutelage_unisys.R;

import me.anwarshahriar.calligrapher.Calligrapher;

/**
 * Created by nadus on 21-12-2017.
 */

public class Step1 extends AppCompatActivity {

    Calligrapher calligrapher;
    FloatingActionButton next1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Step1.this,"GlacialIndifference-Regular.ttf",true);

        next1 = (FloatingActionButton) findViewById(R.id.next1);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Step1.this,Step2.class));
            }
        });
    }
}
