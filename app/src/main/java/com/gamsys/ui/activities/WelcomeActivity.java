package com.gamsys.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gamsys.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tvname,tvhobby;
    private Button btnlogout;
    private Timer timer;
    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferenceHelper = new PreferenceHelper(this);

        //tvhobby = (TextView) findViewById(R.id.tvhobby);
        tvname = (TextView) findViewById(R.id.tvname);
        btnlogout = (Button) findViewById(R.id.btn);

        //tvname.setText("Welcome "+ preferenceHelper.getName());
//        tvhobby.setText("Your hobby is "+preferenceHelper.getHobby());

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferenceHelper.putIsLogin(false);
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                preferenceHelper.putIsLogin(false);
               // Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                //startActivity(intent);
                WelcomeActivity.this.finish();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                WelcomeActivity.super.onBackPressed();
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
    /*@Override
    protected void onPause() {
        super.onPause();

        timer = new Timer();
        Log.i("Main", "Invoking logout timer");
        LogOutTimerTask logoutTimeTask = new LogOutTimerTask();
        timer.schedule(logoutTimeTask, 3000); //auto logout in 5 minutes
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer != null) {
            timer.cancel();
            Log.i("Main", "cancel timer");
            timer = null;
        }
    }

    private class LogOutTimerTask extends TimerTask {

        @Override
        public void run() {

            //redirect user to login screen
            preferenceHelper.putIsLogin(false);
            Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            WelcomeActivity.this.finish();
        }
    }*/

}
