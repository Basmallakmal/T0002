package com.aladziviesoft.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aladziviesoft.data.adapter.DBAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.upnama)
    EditText upnama;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.upuang)
    EditText upuang;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.uptanggal)
    TextView uptanggal;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.uplunas)
    RadioButton uplunas;
    @BindView(R.id.uptidaklunas)
    RadioButton uptidaklunas;
    @BindView(R.id.uprgrub)
    RadioGroup uprgrub;
    @BindView(R.id.upsimpan)
    Button upsimpan;
    @BindView(R.id.upkembali)
    Button upkembali;
    @BindView(R.id.textView9)
    TextView textView9;

    private EditText upjumlahuang;
    private RadioButton uprbutton;
    private DBAdapter koneksi;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);


        koneksi = new DBAdapter(this);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        uptanggal.setText(formattedDate);

        SQLiteDatabase db = koneksi.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_pembayaran WHERE nama_pembayar = '" +
                getIntent().getStringExtra("nama_pembayar") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            textView9.setText(cursor.getString(0));
            upnama.setText(cursor.getString(1));
            upuang.setText(cursor.getString(2));
            if (cursor.getString(4).equals("Lunas")) {
                uplunas.setChecked(true);
            } else {
                uptidaklunas.setChecked(true);
            }
        }

        upsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                int selectedid = uprgrub.getCheckedRadioButtonId();
                uprbutton = (RadioButton) findViewById(selectedid);

                SQLiteDatabase db = koneksi.getWritableDatabase();
                db.execSQL("update data_pembayaran set nama_pembayar='" +
                        upnama.getText().toString() + "', jumlah_uang='" +
                        upuang.getText().toString() + "', tanggal_bayar='" +
                        uptanggal.getText().toString() + "', status='" +
                        uprbutton.getText().toString() + "' where _id='" +
                        textView9.getText().toString() + "'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                ListTaawunAcivity.ma.RefreshList();
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


