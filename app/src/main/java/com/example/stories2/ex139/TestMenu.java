package com.example.stories2.ex139;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by stories2 on 2016-07-31.
 */
public class TestMenu extends Activity
{

    Button buttonOfControlProgram;
    boolean isServiceAlive;

    public void onCreate(Bundle onCreateBundle)
    {
        super.onCreate(onCreateBundle);
        setContentView(R.layout.test_menu_layout);

        init();

        buttonOfControlProgram.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    if(isServiceAlive)//이미 서비스가 실행 중임
                    {
                        Intent intentOfStartService = new Intent("AlwaysTopWithNoneTouch");
                        intentOfStartService.setPackage(getPackageName());
                        stopService(intentOfStartService);

                        buttonOfControlProgram.setText("프로그램 시작");

                        isServiceAlive = false;

                        Log.d("ex139","Service Closed In TestMenu");
                    }
                    else//서비스가 죽어 있는 경우
                    {
                        Intent intentOfStartService = new Intent("AlwaysTopWithNoneTouch");
                        intentOfStartService.setPackage(getPackageName());
                        startService(intentOfStartService);
                        isServiceAlive = true;

                        buttonOfControlProgram.setText("프로그램 종료");
                    }
                }
                catch (Exception e)
                {
                    Log.d("ex139","Error In TestMenu "+e.getMessage());
                }
            }
        });
    }



    public void init()
    {
        isServiceAlive = false;
        buttonOfControlProgram = (Button)findViewById(R.id.btn_program);
    }

}
