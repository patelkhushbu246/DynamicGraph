package com.example.dynamicgraph;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="populationdb";
    private static final String TABLE_WORLD="world_tbl";
    private static final String KEY_ID="ID";
    private static final String KEY_XVALUE="Year";
    private static final String KEY_YVALUE="Percentage";
    public DBHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_WORLD+"("+KEY_ID+" INTEGER PRIMARY KEY ,"+KEY_XVALUE+" TEXT,"+KEY_YVALUE+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_WORLD+"");
        onCreate(db);
    }

    public long addvalue(String year,String perce){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Year",year);
        cv.put("Percentage",perce);
        long res=db.insert(TABLE_WORLD,null,cv);
        db.close();
        return res;
    }
    public ArrayList<Value> retrive(){
        SQLiteDatabase db=this.getWritableDatabase();
        String strqry="select * from "+TABLE_WORLD;
        Cursor c=db.rawQuery(strqry,null);
        ArrayList<Value> arrayList=new ArrayList<>();
        if (c.moveToFirst()){
            do {
                Value v =new Value();
                v.setId(c.getInt(c.getColumnIndex("ID")));
                v.setXvalue(c.getString(c.getColumnIndex("Year")));
                arrayList.add(v);
            }while (c.moveToNext());
        }
        db.close();
        return arrayList;
    }
    public ArrayList<Entry> getDataValues(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(TABLE_WORLD,null,null,null,null,null,null);
        ArrayList<Entry> arr=new ArrayList<>();
        while (c.moveToNext()){
            /*Value v=new Value();
            v.setId(c.getInt(0));
            v.setXvalue(c.getString(1));
            v.setYvalue(c.getString(2));*/
            arr.add(new Entry(c.getFloat(0),c.getFloat(1),c.getFloat(2)));
        }
        db.close();
        return arr;
    }
    public ArrayList<BarEntry> getBarDataValues(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(TABLE_WORLD,null,null,null,null,null,null);
        ArrayList<BarEntry> arr=new ArrayList<>();
        while (c.moveToNext()){
            /*Value v=new Value();
            v.setId(c.getInt(0));
            v.setXvalue(c.getString(1));
            v.setYvalue(c.getString(2));*/
            arr.add(new BarEntry(c.getFloat(0),c.getFloat(1),c.getFloat(2)));
        }
        db.close();
        return arr;
    }
    public ArrayList<PieEntry> getPieDataValues(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query(TABLE_WORLD,null,null,null,null,null,null);
        ArrayList<PieEntry> arr=new ArrayList<>();
        while (c.moveToNext()){
            arr.add(new PieEntry(Float.parseFloat(c.getString(0)),c.getString(1),c.getString(2)));
        }
        db.close();
        return arr;
    }
}
