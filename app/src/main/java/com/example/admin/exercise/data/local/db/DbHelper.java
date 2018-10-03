package com.example.admin.exercise.data.local.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.admin.exercise.model.ApplicationContext;
import com.example.admin.exercise.model.DatabaseInfo;
import com.example.admin.exercise.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbHelper extends SQLiteOpenHelper {
    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_USER_ID = "id";
    private static final String USER_COLUMN_USER_NAME = "usr_name";
    private static final String USER_COLUMN_USER_ADDRESS = "usr_address";
    private static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    private static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";

    @Inject
    public DbHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version){
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreateStatements(db);
    }

    private void tableCreateStatements(SQLiteDatabase db) {
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS"+
            USER_TABLE_NAME+"("
            +USER_COLUMN_USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            +USER_COLUMN_USER_NAME + " VARCHAR(20),"
            +USER_COLUMN_USER_ADDRESS+"VARCHAR(50),"
            +USER_COLUMN_USER_CREATED_AT+"VARCHAR(10) DEFAULT"+getCurrentTimeStamp()+ ","
            +USER_COLUMN_USER_UPDATED_AT+"VARCHAR(10) DEFAULT"+getCurrentTimeStamp()+")");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public User getUser(Long userId) throws Resources.NotFoundException,
            NullPointerException{
        Cursor cursor = null;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                    +USER_TABLE_NAME
                    +" WHERE "
                    +USER_COLUMN_USER_ID
                    + " = ?",new String[]{userId + ""}
            );
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            }
            else {
                throw new Resources.NotFoundException("User with id "+userId+ " does not exists");
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            throw e;
        }finally {
          if(cursor != null)
              cursor.close();
        }
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis()/1000);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);
        onCreate(db);
    }
    public Long insertUser(User user) throws Exception{
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME,user.getName());
            contentValues.put(USER_COLUMN_USER_ADDRESS,user.getAddress());
            contentValues.put(USER_COLUMN_USER_ADDRESS,user.getAddress());

            return db.insert(USER_TABLE_NAME,null,contentValues);
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
