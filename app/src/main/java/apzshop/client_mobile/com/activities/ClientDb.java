package apzshop.client_mobile.com.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.Nullable;

public class ClientDb extends SQLiteOpenHelper {

public static final String DBNAME = "Login.db";

    public ClientDb(Context context)
    {
        super(context, "Login.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int i, int i1) {
        Mydb.execSQL("drop Table if exists users");
    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = Mydb.insert("Users",null,contentValues);
        return result != -1;
    }
    public Boolean checkusername(String username){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = Mydb.rawQuery("select * from users where username = ?",new String[] {username});
        return cursor.getCount() > 0;

    }
    public Boolean checkuserpassword(String username,String password){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = Mydb.rawQuery("Select * from users where username = ? and password = ?",new String[]{username,password});
        return cursor.getCount() > 0;
    }
}
