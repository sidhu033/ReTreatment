package com.gamsys.localdb;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.gamsys.localdb.models.Logs;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Redox";//name of the database
    private static final String TABLE_NAME = "treatment_log";//name for the table
    private static final String TB_NAME = "users";//name for the table

    public static final String ID = "id";
    public static final String USERNAME = "username";
    private static final String  PASSWORD ="password";
    public static final String USER_ID = "user_id";
    private static final String  KEY_STATUS = "key_status";



    //String DB_PATH = null;
    public static final String KEY_ID = "key";
    public static final String USER_NAME= "username";
    private static final String COLUMN_STATUS = "column_status";
    public static final String START_TIME = "start_time";
    public static final String R_SYS = "r_sys";
    public static final String R_DIA = "r_dia";
    public static final String R_PULSE = "r_pulse";
    public static final String L_SYS ="l_sys" ;
    public static final String L_DIA = "l_dia";
    public static final String L_PULSE = "l_pulse";
    private static final String  STATUS ="status";

  //  private EditText etusername;
    //private SQLiteDatabase mDb;
    //private static Context mycontext;
    // private FloatingActionMenu getAssets;

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static DbHandler appLocalData;



    public static DbHandler getInstance(Context context){
        if (appLocalData == null) {
            appLocalData = new DbHandler(context.getApplicationContext());
            //appLocalData = OpenHelperManager.getHelper(context, DbHandler.class);
        }
        return appLocalData;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Query to create table in database
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " VARCHAR," + START_TIME + " DATETIME," + R_SYS + " INTEGER," + R_DIA + " INTEGER," + R_PULSE + " INTEGER,"  + L_SYS + " INTEGER," + L_DIA + " INTEGER," + L_PULSE + " INTEGER,"  + COLUMN_STATUS + " INTEGER" +")";
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TB_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  + USERNAME + " VARCHAR," +  PASSWORD + " VARCHAR," + USER_ID + " INTEGER,"  + KEY_STATUS + " VARCHAR" +")";

        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    //Executes once a database change is occurred
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    //---open SQLite DB---
    public DbHandler open() throws SQLException {
        db = this.getWritableDatabase();
        return this;
    }

public void insertUser(String user_id, String username, String password, String key_status)
{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(USER_ID, user_id);
        contentValues.put(KEY_STATUS, key_status);

        db.insert(TB_NAME, null, contentValues);
        db.close();
}

//method to add Login details
    public Logs addUser(String key_status){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TB_NAME,
                new String[]{ID, USER_ID,
                        USERNAME, PASSWORD,
                        KEY_STATUS},
                KEY_STATUS + "=?",
                new String[]{key_status}, null, null, ID + " DESC", null);

        if (cursor != null)
            cursor.moveToFirst();

        Logs contract = new Logs(
                cursor.getInt(cursor.getColumnIndex(ID)),
                cursor.getString(cursor.getColumnIndex(USER_ID)),
                cursor.getString(cursor.getColumnIndex(USERNAME)),
                cursor.getString(cursor.getColumnIndex(PASSWORD)),
                cursor.getString(cursor.getColumnIndex(KEY_STATUS)));

        // close the db connection
        cursor.close();
        return contract;
    }

    //method to add row to table
    public void addMovies(String username, Date starttime, String r_sys, String r_dia, String r_pulse, String l_sys, String l_dia, String l_pulse, int status) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ContentValues values = new ContentValues();
            // values.put(KEY_ID, m.getId());
            values.put(USER_NAME, username);
            values.put(START_TIME, dateFormat.format(starttime));
            values.put(R_SYS, r_sys);
            values.put(R_DIA, r_dia);
            values.put(R_PULSE, r_pulse);
            values.put(L_SYS, l_sys);
            values.put(L_DIA, l_dia);
            values.put(L_PULSE, l_pulse);
            values.put(COLUMN_STATUS, status);


            db.insert(TABLE_NAME, null, values);
            db.close();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }

    }


    //method to list all details from table
    public List<Logs> getAllUsers(String username) {
        List<Logs> logList = new ArrayList<Logs>();
        //etusername = (EditText) findViewById(R.id.etusername);
        String selectQuery =  "SELECT  * FROM " + TABLE_NAME
                + " WHERE " + USER_NAME + " = " + username + " LIMIT 100 "  ; ;//retrieve data from the database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                Logs m = new Logs();
                m.setId(cursor.getInt(0));
                m.setUsername(cursor.getString(1));
                m.setTreatmentStartTimeStamp(new Date());
                m.setRightHandSystolic(cursor.getString(3));
                m.setRightHandDiastolic(cursor.getString(4));
                m.setRightHandPulse(cursor.getString(5));
                m.setLeftHandSystolic(cursor.getString(6));
                m.setLeftHandDiastolic(cursor.getString(7));
                m.setLeftHandPulse(cursor.getString(8));
                logList.add(m);
            } while (cursor.moveToNext());
        }

        return logList;
    }

 /*  public Cursor getNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME ;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }*/

    public boolean updateNameStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS, status);
        db.update(TABLE_NAME, contentValues, KEY_ID + "=" + id, null);
        db.close();
        return true;
    }
    public Cursor getUnsyncedNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 0;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }



}