package com.example.stories2.ex139;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by stories2 on 2016-07-31.
 */
public class ResourceManager {

    Context classNeedContext;
    Bitmap[] reimuBitmap;
    int loadedBitmapSize;

    public ResourceManager(Context classNeedContext)
    {
        this.classNeedContext = classNeedContext;
    }

    public int GetBitmapLimit()
    {
        return loadedBitmapSize;
    }

    public Bitmap GetBitmapIndividualize(int bitmapNumber)
    {
        return reimuBitmap[bitmapNumber];
    }

    public boolean LoadReimuBitmap(String fileName, int limit)
    {
        loadedBitmapSize = limit + 1;
        reimuBitmap = new Bitmap[loadedBitmapSize];
        String path = "@drawable/" + fileName;
        int i, bitmapID;
        try
        {
            for(i = 0; i <= limit ; i += 1)
            {
                bitmapID = classNeedContext.getResources().getIdentifier(path + i, "drawable", classNeedContext.getPackageName());
                reimuBitmap[i] = BitmapFactory.decodeResource(classNeedContext.getResources(), bitmapID);
            }
            return true;
        }
        catch (Exception e)
        {
            Log.d("ex139", "Error in ResourceManager LoadReimuBitmap " + e.getMessage());
        }
        return false;
    }
}
