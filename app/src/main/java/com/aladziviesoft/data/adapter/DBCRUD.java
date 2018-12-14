package com.aladziviesoft.data.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBCRUD {

    private SQLiteDatabase database;

    //inisialisasi kelas DBAdapter
    private DBAdapter DBAdapter;

    //ambil semua nama kolom
    private String[] allColumns = { DBAdapter.COLUMN_ID,
            DBAdapter.COLUMN_NAMA, DBAdapter.COLUMN_UANG,DBAdapter.COLUMN_TANGGAL, DBAdapter.COLUMN_STATUS};

    //DBAdapter diinstantiasi pada constructor
    public DBCRUD(Context context)
    {
        DBAdapter = new DBAdapter(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = DBAdapter.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        DBAdapter.close();
    }

    //method untuk create/insert barang ke database
    public long insertData(String namabayar, String jumlahuang, String tanggalbayar, String sstatus)
    {
        SQLiteDatabase dbb = DBAdapter.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBAdapter.COLUMN_NAMA, namabayar);
        contentValues.put(DBAdapter.COLUMN_UANG, jumlahuang);
        contentValues.put(DBAdapter.COLUMN_TANGGAL, tanggalbayar);
        contentValues.put(DBAdapter.COLUMN_STATUS, sstatus);
        long id = dbb.insert(DBAdapter.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = DBAdapter.getWritableDatabase();
        String[] columns = {DBAdapter.COLUMN_ID,DBAdapter.COLUMN_NAMA,DBAdapter.COLUMN_UANG,DBAdapter.COLUMN_TANGGAL,DBAdapter.COLUMN_STATUS};
        Cursor cursor =db.query(DBAdapter.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(DBAdapter.COLUMN_ID));
            String nama =cursor.getString(cursor.getColumnIndex(DBAdapter.COLUMN_NAMA));
            String uang =cursor.getString(cursor.getColumnIndex(DBAdapter.COLUMN_UANG));
            String tanggal =cursor.getString(cursor.getColumnIndex(DBAdapter.COLUMN_TANGGAL));
            String status =cursor.getString(cursor.getColumnIndex(DBAdapter.COLUMN_STATUS));
            buffer.append(cid+ "   " + nama + "   " + uang +"   "+tanggal+"     "+status+" \n");
        }
        return buffer.toString();
    }

}
