package com.blogspot.owntasks.amarrafkhata.ownjasonpart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.blogspot.owntasks.amarrafkhata.R;


public class Details extends AppCompatActivity {

    TextView title, detail, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = findViewById(R.id.mytitle);
        detail = findViewById(R.id.mydetails);
        date = findViewById(R.id.mytime);


        String _title = getIntent().getStringExtra("TITLE");
        String _detail = getIntent().getStringExtra("DETAIL");
        String _date = getIntent().getStringExtra("DATE");


        title.setText(_title);
        detail.setText(_detail);
        date.setText(_date);

    }
}
