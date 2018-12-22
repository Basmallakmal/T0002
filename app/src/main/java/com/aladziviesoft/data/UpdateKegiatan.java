package com.aladziviesoft.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aladziviesoft.data.adapter.DBAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateKegiatan extends AppCompatActivity {


    @BindView(R.id.upnamakegiatan)
    EditText upnamakegiatan;
    @BindView(R.id.upuangkegiatan)
    EditText upuangkegiatan;
    @BindView(R.id.upsimpankegiatan)
    Button upsimpankegiatan;
    @BindView(R.id.upkembali)
    Button upkembali;
    @BindView(R.id.tempatid)
    TextView tempatid;
    private EditText upjumlahuang;
    private RadioButton uprbutton;
    private DBAdapter koneksi;
    protected Cursor cursor;
    String id;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kegiatan);
        ButterKnife.bind(this);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama_kegiatan");
        koneksi = new DBAdapter(this);


        SQLiteDatabase db = koneksi.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_kegiatan WHERE id = '" + id + "'", null);
        cursor.moveToFirst();
        Log.d("datasss", cursor.toString());

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tempatid.setText(cursor.getString(0));
            upnamakegiatan.setText(cursor.getString(1));
            upuangkegiatan.setText(cursor.getString(2));
        }

        upsimpankegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = koneksi.getWritableDatabase();
                db.execSQL("update data_kegiatan set nama_kegiatan='" +
                        upnamakegiatan.getText().toString() + "', jumlah_uang_kegiatan='" +
                        upuangkegiatan.getText().toString() + "' where id='" + id + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        upkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }


}


