package com.aladziviesoft.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aladziviesoft.data.adapter.DBCRUD;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button simpan;
    private EditText nama;
    private EditText jumlahuang;
    private TextView tanggal;
    private RadioGroup rgrub;
    private RadioButton rbutton;
    private DBCRUD koneksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        simpan = (Button) findViewById(R.id.simpan);
        nama = (EditText) findViewById(R.id.nama);
        jumlahuang = (EditText) findViewById(R.id.uang);
        tanggal = (TextView) findViewById(R.id.tanggal);
        rgrub = (RadioGroup) findViewById(R.id.rgrub);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        tanggal.setText(formattedDate);

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
            Intent a = new Intent(getApplicationContext(), ListTaawunAcivity.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    public void actsimpan(View view) {
        String snama = nama.getText().toString();
        String suang = jumlahuang.getText().toString();
        String stanggal = tanggal.getText().toString();
        int selectedid = rgrub.getCheckedRadioButtonId();
        rbutton = (RadioButton) findViewById(selectedid);
        String srbutton = rbutton.getText().toString();

        if (snama.isEmpty() || suang.isEmpty()) {
            Toast.makeText(MainActivity.this, "Masukkan Nama dan Jumlah uang", Toast.LENGTH_SHORT).show();
        } else {
            long id = koneksi.insertData(snama, suang, stanggal, srbutton);
            if (id <= 0) {
                Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                nama.setText("");
                jumlahuang.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                nama.setText("");
                jumlahuang.setText("");
            }
        }
        ListTaawunAcivity.ma.RefreshList();
        finish();
    }

}


