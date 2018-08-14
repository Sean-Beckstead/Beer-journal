package com.example.HelloWorld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created with IntelliJ IDEA.
 * User: sean.beckstead
 * Date: 10/10/13
 * Time: 8:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class MapAct extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        ImageView imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setImageResource(R.drawable.map);



    }
}
