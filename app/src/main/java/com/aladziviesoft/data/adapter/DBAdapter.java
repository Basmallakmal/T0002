package com.aladziviesoft.data.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;




public class DBAdapter extends SQLiteOpenHelper{



    public static final String TABLE_NAME = "data_pembayaran";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA = "nama_pembayar";
    public static final String COLUMN_UANG = "jumlah_uang";
    public static final String COLUMN_TANGGAL = "tanggal_bayar";
    public static final String COLUMN_STATUS = "status";
    private static final String db_name ="pembayaran.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID +" integer primary key autoincrement, "
            + COLUMN_NAMA+ " varchar(50) not null, "
            + COLUMN_UANG+ " varchar(50) not null, "
            + COLUMN_TANGGAL+ " timestamp not null, "
            + COLUMN_STATUS+ " varchar(50) not null);";

    public DBAdapter(Context context) {
        super(context, db_name, null, db_version);

            }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBAdapter.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

}
