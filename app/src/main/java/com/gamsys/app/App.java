package com.gamsys.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.gamsys.localdb.DbHandler;

/**
 * Created by gaurav on 5/25/18.
 */

public class App extends Application
{
  public static  Context context;
  private DbHandler dbHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();

    }
}
