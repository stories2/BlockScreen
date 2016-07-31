package com.example.stori.ex139;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by stori on 2016-07-11.
 */
public class FirstActivity extends Activity {

    Button btn_next,btn_dial;
    Intent intent;
    int request_code = 0;

    public void onCreate(Bundle parameter)
    {
        super.onCreate(parameter);

        setContentView(R.layout.firstlayout);

        Log.i("ex139",make_log("on create"));

        btn_next = (Button)findViewById(R.id.btn_next);
        btn_dial = (Button)findViewById(R.id.btn_dial);

        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:114"));
                startActivity(intent);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("key","hello");
                startActivityForResult(intent, request_code);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == request_code && resultCode == RESULT_OK)
        {
            Log.i("ex139", make_log("intent result is : " + data.getStringExtra("result")));
        }
    }

    public void onRestart()
    {
        super.onRestart();

        Log.i("ex139",make_log("on restart"));
    }

    public String make_log(String log)
    {
        return getLocalClassName() + " " + log;
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.i("ex139",make_log("on destroy"));
    }

    public void onStop()
    {
        super.onStop();
        Log.i("ex139",make_log("on stop"));
    }

    public void onPause()
    {
        super.onPause();
        Log.i("ex139",make_log("on pause"));
    }

    public void onResume()
    {
        super.onResume();
        Log.i("ex139",make_log("on resume"));
    }

    public void onStart()
    {
        super.onStart();
        Log.i("ex139",make_log("on start"));
    }
}
