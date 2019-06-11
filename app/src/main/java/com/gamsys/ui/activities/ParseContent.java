package com.gamsys.ui.activities;

import android.app.Activity;

import com.gamsys.localdb.DbHandler;
import com.gamsys.localdb.models.Logs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ParseContent {

    private final String KEY_SUCCESS = "status";
    private final String KEY_MSG = "message";
    private final String KEY_AddressList = "addressList";
    private final String KEY_DATA = "Data";
    private  ArrayList<HashMap<String, String>> hashMap;
    private Activity activity;
    PreferenceHelper preferenceHelper;
    private DbHandler dbHandler;

    ArrayList<HashMap<String, String>> arraylist;

    public ParseContent(Activity activity) {
        this.activity = activity;
        preferenceHelper = new PreferenceHelper(activity);

    }

    public boolean isSuccess(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString(KEY_SUCCESS).equals("true")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorMessage(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(KEY_MSG);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public void saveInfo(String response) {
        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    JSONObject dataobj = dataArray.getJSONObject(i);
                    Logs logs = new Logs();
                    logs.setUser_id(dataobj.getString("user_id"));
                    logs.setUser_name(dataobj.getString("u_username"));
                    logs.setPassword(dataobj.getString("u_password"));
                    logs.setKey_status(jsonObject.getString(KEY_SUCCESS));
                    preferenceHelper.putName(dataobj.getString(AndyConstants.Params.NAME));
                    preferenceHelper.putHobby(dataobj.getString(AndyConstants.Params.PASSWORD));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
