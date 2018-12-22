package com.aladziviesoft.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aladziviesoft.data.adapter.DBCRUD;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TambahKegiatan extends AppCompatActivity {

    @BindView(R.id.namakegiatan)
    EditText namakegiatan;
    @BindView(R.id.uangkegiatan)
    EditText uangkegiatan;
    @BindView(R.id.upsimpankegiatan)
    Button upsimpankegiatan;
    DBCRUD koneksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kegiatan);
        ButterKnife.bind(this);


        koneksi = new DBCRUD(this);
        koneksi.open();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite2) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void OnClick(View view){
        String nama = namakegiatan.getText().toString();
        String uang = uangkegiatan.getText().toString();
        if (nama.isEmpty() || uang.isEmpty()) {
            Toast.makeText(TambahKegiatan.this, "Masukkan Nama dan Jumlah uang", Toast.LENGTH_SHORT).show();
        } else {
            long id = koneksi.insertDataKegiatan(nama, uang);
            if (id <= 0) {
                Toast.makeText(TambahKegiatan.this, "Gagal", Toast.LENGTH_SHORT).show();
                namakegiatan.setText("");
                uangkegiatan.setText("");
            } else {
                Toast.makeText(TambahKegiatan.this, "Berhasil", Toast.LENGTH_SHORT).show();
                namakegiatan.setText("");
                uangkegiatan.setText("");
            }
        }
        finish();
    }
}
