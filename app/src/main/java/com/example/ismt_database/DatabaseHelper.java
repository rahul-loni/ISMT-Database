package com.example.ismt_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="id";
    public static final String COL2="name";
    public static final String COL3="marks";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT ,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(sqLiteDatabase);
    }
    public boolean insertData(String id, String name, String marks){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2 ,name);
        contentValues.put(COL3,marks);
        Long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
 public Cursor getAllData(){
      SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
      Cursor cursor=sqLiteDatabase.rawQuery("select * from " +TABLE_NAME,null);
      return  cursor;
   }
   public boolean updateData(String id,String name,String marks){
   SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
   ContentValues contentValues=new ContentValues();
   contentValues.put(COL1 ,id);
   contentValues.put(COL2,name);
   contentValues.put(COL3,marks);
   sqLiteDatabase.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
   return true;
   }
   public Integer deleteData(String id){
SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
return sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{id});
   }
}
