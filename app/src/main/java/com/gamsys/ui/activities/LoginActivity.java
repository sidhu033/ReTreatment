package com.gamsys.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gamsys.R;
import com.gamsys.localdb.DbHandler;
import com.gamsys.localdb.models.Logs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class LoginActivity extends AppCompatActivity {

    private EditText etusername, etpassword;
    private Button btnlogin;
    private TextView tvreg;
    private final String KEY_SUCCESS = "status";
    private ParseContent parseContent;
    private final int LoginTask = 1;
    private PreferenceHelper preferenceHelper;
    private Timer timer;
    private ArrayList<Logs> hashMap;
    private DbHandler dbHandler;

   // public LoginActivity(DashboardFragment dashboardFragment) {
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        parseContent = new ParseContent(this);
        preferenceHelper = new PreferenceHelper(this);
        dbHandler = new DbHandler(this);
        //DbHandler dbHandler=new DbHandler(LoginActivity.this);//Create this object in onCreate() method

       // preferenceHelper = new PreferenceHelper(this);
        //parseContent = new ParseContent(this);
try {
    if (preferenceHelper.getIsLogin()) {
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        this.finish();
    }
}catch (RuntimeException e){
    e.printStackTrace();
}

        etusername = (EditText) findViewById(R.id.etusername);
        etpassword = (EditText) findViewById(R.id.etpassword);

        btnlogin = (Button) findViewById(R.id.btn);
       //tvreg = (TextView) findViewById(R.id.tvreg);

        /*tvreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }




    private void login() throws IOException, JSONException {

        if (!AndyUtils.isNetworkAvailable(LoginActivity.this)) {
            Toast.makeText(LoginActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(LoginActivity.this);
        final HashMap<String, String> map = new HashMap<>();
        map.put(AndyConstants.Params.USERNAME, etusername.getText().toString());
        map.put(AndyConstants.Params.PASSWORD, etpassword.getText().toString());
        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                try {
                    HttpRequest req = new HttpRequest(AndyConstants.ServiceType.LOGIN);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss", result);
                onTaskCompleted(result,LoginTask);
            }
        }.execute();
    }
    /*private void saveDatabase(String username, String password)
    {
        DbHandler dt=new DbHandler(this);
          dt.addUser(username,password);
        Logs logs= new Logs();
    }*/
    private void onTaskCompleted(String response,int task) {
       // String username = etusername.getText().toString();
       // String password = etpassword.getText().toString();
       // String status ="true";

                Log.d("responsejson", response.toString());
        AndyUtils.removeSimpleProgressDialog();  //will remove progress dialog
        switch (task) {
            case LoginTask:

                if (parseContent.isSuccess(response)) {

                    parseContent.saveInfo(response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getString(KEY_SUCCESS).equals("true")) {
                            JSONArray dataArray = jsonObject.getJSONArray("data");

                            dbHandler.open();
                            for (int i = 0; i < dataArray.length(); i++) {

                                JSONObject dataobj = dataArray.getJSONObject(i);
                                Logs logs = new Logs();
                                logs.setUser_id(dataobj.getString("user_id"));
                                logs.setUser_name(dataobj.getString("u_username"));
                                logs.setPassword(dataobj.getString("u_password"));
                                logs.setKey_status(jsonObject.getString(KEY_SUCCESS));
                                dbHandler.insertUser(dataobj.getString("user_id"), dataobj.getString("u_username"), dataobj.getString("u_password"), jsonObject.getString(KEY_SUCCESS));

//                                hashMap.add(logs);
                            }
                           // dbHandler.close();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //  dbHandler.insertUser(username, password, status);
                    Toast.makeText(LoginActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,SplashActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    this.finish();

                }else {
                    Toast.makeText(LoginActivity.this, parseContent.getErrorMessage(response), Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                LoginActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }


}
