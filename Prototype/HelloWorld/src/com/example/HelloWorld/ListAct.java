package com.example.HelloWorld;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.view.View.OnClickListener;

public class ListAct extends Activity{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setImageResource(R.drawable.datatable);

    }
}