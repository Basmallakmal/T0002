package com.aladziviesoft.data.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aladziviesoft.data.model.ListKegiatanModel;
import com.aladziviesoft.data.model.ListTaawunModel;

import java.util.ArrayList;
import java.util.List;

public class DBCRUD {

    private SQLiteDatabase database;

    //inisialisasi kelas DBAdapter
    private DBAdapter DBAdapter;

    //ambil semua nama kolom
    private String[] allColumns = {DBAdapter.COLUMN_ID,
            DBAdapter.COLUMN_NAMA, DBAdapter.COLUMN_UANG, DBAdapter.COLUMN_TANGGAL, DBAdapter.COLUMN_STATUS};

    //DBAdapter diinstantiasi pada constructor
    public DBCRUD(Context context) {
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
    public long insertData(String namabayar, String jumlahuang, String tanggalbayar, String sstatus, String IdKegi) {
        SQLiteDatabase dbb = DBAdapter.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBAdapter.COLUMN_NAMA, namabayar);
        contentValues.put(DBAdapter.COLUMN_UANG, jumlahuang);
        contentValues.put(DBAdapter.COLUMN_TANGGAL, tanggalbayar);
        contentValues.put(DBAdapter.COLUMN_STATUS, sstatus);
        contentValues.put(DBAdapter.COLUMN_ID_KEG, IdKegi);
        long id = dbb.insert(DBAdapter.TABLE_NAME, null, contentValues);
        return id;
    }

    //method untuk create/insert barang ke database
    public long insertDataKegiatan(String namabayar, String jumlahuang) {
        SQLiteDatabase dbb = DBAdapter.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBAdapter.NAMA_KEGIATAN, namabayar);
        contentValues.put(DBAdapter.JUMLAH_UANG, jumlahuang);
        long iidd = dbb.insert(DBAdapter.TABLE_NAME2, null, contentValues);
        return iidd;
    }

    public List<ListTaawunModel> getData() {
        List<ListTaawunModel> list = new ArrayList<>();
        String query = "SELECT * FROM " + DBAdapter.TABLE_NAME + " WHERE " + DBAdapter.COLUMN_ID_KEG ;
        SQLiteDatabase db = DBAdapter.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ListTaawunModel listmodel = new ListTaawunModel(cursor.getString(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                list.add(listmodel);
            }
            while (cursor.moveToNext());

        }
        return list;
    }

    public List<ListKegiatanModel> getdatakegiatan(){
        List<ListKegiatanModel> listt = new ArrayList<>();
        String query = " SELECT * FROM " + DBAdapter.TABLE_NAME2;
        SQLiteDatabase db = DBAdapter.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do{
                ListKegiatanModel listmodell = new ListKegiatanModel(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                listt.add(listmodell);
            }
            while (cursor.moveToNext());

        }
        return listt;
    }



    public int getBanyakData() {
        int count = 0;
        String SQL = "SELECT SUM (" + DBAdapter.COLUMN_UANG + ") FROM data_pembayaran";
        SQLiteDatabase db = this.DBAdapter.getReadableDatabase();
        Cursor hasil = db.rawQuery(SQL, null);
        if (hasil.moveToFirst()) {
            count = hasil.getInt(0);
        }
        while (hasil.moveToNext());
        return count;
    }

    public int getdatalunas(){
        int b ;
        String countQuery = "SELECT * FROM " + DBAdapter.TABLE_NAME + " WHERE status = 'Lunas'" ;
        SQLiteDatabase db = this.DBAdapter.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        b = cursor.getCount();
        cursor.close();
        return b;
    }

    public int getuanglunas(){
        int count = 0;
        String SQL = "SELECT SUM (" + DBAdapter.COLUMN_UANG + ") FROM data_pembayaran WHERE status = 'Lunas'";
        SQLiteDatabase db = this.DBAdapter.getReadableDatabase();
        Cursor hasil = db.rawQuery(SQL, null);
        if (hasil.moveToFirst()) {
            count = hasil.getInt(0);
        }
        while (hasil.moveToNext());
        return count;
    }

    public int getdatatidaklunas(){
        int b ;
        String countQuery = "SELECT * FROM " + DBAdapter.TABLE_NAME + " WHERE status = 'Tidak Lunas'" ;
        SQLiteDatabase db = this.DBAdapter.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        b = cursor.getCount();
        cursor.close();
        return b;
    }

    public int getuangtidaklunas(){
        int count = 0;
        String SQL = "SELECT SUM (" + DBAdapter.COLUMN_UANG + ") FROM data_pembayaran WHERE status = 'Tidak Lunas'";
        SQLiteDatabase db = this.DBAdapter.getReadableDatabase();
        Cursor hasil = db.rawQuery(SQL, null);
        if (hasil.moveToFirst()) {
            count = hasil.getInt(0);
        }
        while (hasil.moveToNext());
        return count;
    }

}
