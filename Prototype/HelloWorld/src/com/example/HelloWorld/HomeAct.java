package com.example.HelloWorld;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.view.View.OnClickListener;


public class HomeAct extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button addbtn=(Button)findViewById(R.id.addBtn);
        Button listbtn=(Button)findViewById(R.id.listBtn);
        Button mapbtn=(Button)findViewById(R.id.mapBtn);
        Button randbtn=(Button)findViewById(R.id.randomBtn);

        addbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // doStuff
                Intent intentMain = new Intent(HomeAct.this ,
                        AddAct.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                HomeAct.this.startActivity(intentMain);
            }
        });

        listbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // doStuff
                Intent intentMain = new Intent(HomeAct.this ,
                        ListAct.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                HomeAct.this.startActivity(intentMain);
            }
        });

        mapbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // doStuff
                Intent intentMain = new Intent(HomeAct.this ,
                        MapAct.class);
                intentMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                HomeAct.this.startActivity(intentMain);
            }
        });

        randbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // doStuff

                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeAct.this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Your Random Beer is.....")
                    .setTitle("Random Pick")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intentMain = new Intent(HomeAct.this ,
                                    AddAct.class);
                            intentMain.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            HomeAct.this.startActivity(intentMain);
                        }
                    });

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });
    }



    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
}
