package com.example.homework5_sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class UsersDBManager extends AppCompatActivity {

    private static final String TABLE_NAME = "users_info";
    private static SQLiteDatabase users_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public UsersDBManager(String dir) {
        //  db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/my.db3", null);
        //如果数据库不存在，就创建它
        users_db = SQLiteDatabase.openOrCreateDatabase(dir + "/users.db3", null);
        Log.d("zk", "CreateSuccessful ! ");
    }

    public boolean insert(Users user) {
        ContentValues temp = new ContentValues();
        temp.put("Name", user.getName());
        temp.put("Age", user.getAge());
        temp.put("Date", Long.toString( user.getRegDate().getTime()));
        Log.d("zk", Long.toString( user.getRegDate().getTime()));
        try {
            users_db.execSQL("create table if not EXISTS " + TABLE_NAME + " (ID integer primary key autoincrement, " +
                    "Name varchar(30) NOT NULL, " +
                    "Age integer, " +
                    "Date varchar(30))");
            Log.d("zk", "CreateTableSuccessful ! ");
        } catch (SQLException e) {
            Log.d("zk", "CreateTableFailed ! ");
            return  false;
        }
        long tag = -1;
        tag = users_db.insert(TABLE_NAME, null, temp);
        Log.d("zk", "insertSuccessful ! tag =  "+tag);
        if (tag == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Users[] covertToUsers(Cursor cursor) {
        int length = cursor.getCount();
        if (length == 0 || !cursor.moveToFirst()) {
            return null;
        }else {
            Users[] resultUsers = new Users[length];
            for (int i = 0; i <length; i++) {
                resultUsers[i] = new Users();
                resultUsers[i].setId(cursor.getInt(0));
                Log.d("zk", "" + cursor.getColumnIndexOrThrow("ID"));
                resultUsers[i].setName(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
                Log.d("zk", "" + cursor.getColumnIndexOrThrow("Name"));
                //测试Cursor的下标
                resultUsers[i].setAge(cursor.getInt(2));
                Log.d("zk", "" + cursor.getColumnIndexOrThrow("Age"));
                Date temp = new Date(cursor.getLong(3));
                resultUsers[i].setRegDate(temp);
                Log.d("zk", "" + cursor.getColumnIndexOrThrow("Date"));
                cursor.moveToNext();
            }
            cursor.close();
            return resultUsers;
        }
    }

    public Users[] query(int id) {
        if (id <= -1) {
            Toast.makeText(this, "请输入正确的ID", Toast.LENGTH_SHORT);
            return null;
        } else {
            Cursor result = users_db.query(TABLE_NAME, new String[]{"ID", "Name", "Age", "Date"}, "ID = " + id, null, null, null, null);
            return covertToUsers(result);
        }
    }

    public Users[] queryAll() {
        Cursor result = users_db.rawQuery("SELECT * " +
                "FROM " + TABLE_NAME , null);
        return covertToUsers(result);
    }

    public boolean update(int id, String name, int age) {
        if (id < 0) {
            return false;
        } else {
            try {
                users_db.execSQL("update " + TABLE_NAME + " set Name =" + "'"+name+"'" + " where ID = " + id + ";");
                users_db.execSQL("update " + TABLE_NAME + " set Age =" + age + " where ID = " + id + ";");
                return true;
            } catch (SQLException e) {
                return false;
            }

        }
    }

    public boolean delete(int id) {
        if (id < 0) {
            return false;
        } else {
            try {
                users_db.execSQL("delete from " + TABLE_NAME + " where ID = " + id + ";");
                return true;
            } catch (SQLException e) {
                return false;
            }
        }
    }

    public boolean deleteAll() {
        try {
            users_db.execSQL("delete from " + TABLE_NAME + ";");
            users_db.execSQL("DELETE FROM users_info;");
            int de;
           de= users_db.delete(TABLE_NAME, null, null);
            Log.d("zk", "已执行删除"+de);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (users_db != null) {
            users_db.close();
        }
    }
}
