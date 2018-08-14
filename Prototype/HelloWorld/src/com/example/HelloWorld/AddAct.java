package com.example.HelloWorld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.view.View.OnClickListener;

public class AddAct extends Activity{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Button submitbtn=(Button)findViewById(R.id.submitBtn);

        submitbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // doStuff like submit form and save to database
                finish();
            }
        });
    }
}