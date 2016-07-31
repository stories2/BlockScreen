package com.example.stori.ex139;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by stori on 2016-07-11.
 */
public class SecondActivity extends Activity {

    Intent intent;
    TextView txt_result;

    public void onCreate(Bundle parameter)
    {
        super.onCreate(parameter);

        setContentView(R.layout.secondlayout);

        Log.i("ex139",make_log("on create"));

        txt_result = (TextView)findViewById(R.id.txt_result);

        intent = getIntent();
        String result = intent.getStringExtra("key");
        Log.i("ex139",make_log("key : " + result));

        txt_result.setText(result);
    }

    public void onBackPressed()
    {
        Intent intent_result = new Intent();
        intent_result.putExtra("result","bye");
        setResult(RESULT_OK, intent_result);

        super.onBackPressed();
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
