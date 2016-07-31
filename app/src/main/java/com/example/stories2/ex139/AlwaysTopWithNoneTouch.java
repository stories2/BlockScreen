package com.example.stories2.ex139;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.PrintWriter;

/**
 * Created by stories2 on 2016-07-31.
 */
public class AlwaysTopWithNoneTouch extends Service implements Runnable
{
    LayoutInflater layout;
    View view;
    WindowManager.LayoutParams layoutParams;
    WindowManager windowManager;
    ResourceManager resourceManager;
    ImageView imageView;
    boolean appDestroyed;
    int bitmapFrame;
    long startTime, endTime;
    Thread layoutUpdateThread;

    public AlwaysTopWithNoneTouch()
    {
        super();
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public void CreateNewClass()
    {
        resourceManager = new ResourceManager(this.getApplicationContext());
        imageView = new ImageView(this);
        layoutUpdateThread = new Thread(this);
    }

    public void SetLayoutParams()
    {
        layoutParams = new WindowManager.LayoutParams
        (
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
            PixelFormat.TRANSLUCENT
        );
    }

    public void SetLayoutPosition(int x, int y)
    {
        layoutParams.x = x;
        layoutParams.y = y;
    }

    public boolean isNotEmpty(Bitmap target)
    {
        return target != null;
    }

    public void UpdateImageView(int bitmapNumber)
    {
        Bitmap reimuBitmap = resourceManager.GetBitmapIndividualize(bitmapNumber);
        if(isNotEmpty(reimuBitmap))
        {
            imageView.setImageBitmap(reimuBitmap);
        }
        windowManager.updateViewLayout(imageView, layoutParams);
    }

    public void init()
    {
        bitmapFrame = 0;
        appDestroyed = false;
        CreateNewClass();

        layout = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layout.inflate(R.layout.always_top_view_none_touch, null);
        resourceManager.LoadReimuBitmap("md", 56);

        //imageView.setImageBitmap(resourceManager.getBitmapIndividualize(0));

        SetLayoutParams();

        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        SetLayoutPosition(0, 0);

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(imageView, layoutParams);
        //windowManager.addView(view, layoutParams);

        UpdateImageView(bitmapFrame);

        layoutUpdateThread.start();
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        init();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        try
        {
            appDestroyed = true;
            windowManager.removeView(imageView);
            //windowManager.removeView(view);
            System.gc();
        }
        catch (Exception e)
        {
            Log.d("ex139","Error in AlwaysTopWithNoneTouch onDestroy " + e.getMessage());
        }
        super.onDestroy();
        stopSelf();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent)
    {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent)
    {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args)
    {
        super.dump(fd, writer, args);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while(appDestroyed == false)
        {
            try
            {
                Thread.sleep(1);
                endTime = System.currentTimeMillis();
                if(endTime - startTime > 1000 / 24)
                {
                    UpdateProcess();
                }
            }
            catch (Exception e)
            {
                Log.d("ex139", "Error in AlwaysTopWithNoneTouch run " + e.getMessage());
            }
        }
    }

    public void UpdateProcess()
    {
        bitmapFrame = (bitmapFrame + 1) % resourceManager.GetBitmapLimit();
        startTime = endTime;
        Message msg = BitmapChangeHandler.obtainMessage();
        BitmapChangeHandler.sendMessage(msg);
    }

    Handler BitmapChangeHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            try
            {
                if(appDestroyed == false)
                {
                    UpdateImageView(bitmapFrame);
                }
            }
            catch (Exception e)
            {
                Log.d("ex139", "Error in AlwaysTopWithNoneTouch BitmapChangeHandler " + e.getMessage());
            }
        }
    };
}
