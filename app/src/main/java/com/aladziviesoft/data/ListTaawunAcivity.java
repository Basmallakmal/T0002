package com.aladziviesoft.data;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aladziviesoft.data.adapter.DBAdapter;
import com.aladziviesoft.data.adapter.DBCRUD;
import com.aladziviesoft.data.adapter.ListTaawunAdapter;
import com.aladziviesoft.data.model.ListTaawunModel;
import com.aladziviesoft.data.utils.AndLog;
import com.aladziviesoft.data.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTaawunAcivity extends AppCompatActivity {
    Menu menu;
    protected Cursor cursor;
    DBAdapter dbcenter;
    DBCRUD db;
    public static ListTaawunAcivity ma;
    @BindView(R.id.rec_list_taawun)
    RecyclerView recListTaawun;
    final Context c = this;
    private static SQLiteDatabase debe;
    int totalharga;

    LinearLayoutManager layoutManager;
    ListTaawunAdapter adapter;
    List<ListTaawunModel> arrayList = new ArrayList<>();
    @BindView(R.id.Swipe)
    SwipeRefreshLayout Swipe;
    @BindView(R.id.tempattotaldata)
    TextView tempattotaldata;
    @BindView(R.id.tempattotallunas)
    TextView tempattotallunas;
    @BindView(R.id.tempattotalulunas)
    TextView tempattotalulunas;
    @BindView(R.id.tempattotaltlunas)
    TextView tempattotaltlunas;
    @BindView(R.id.tempattotalutlunas)
    TextView tempattotalutlunas;
    @BindView(R.id.totaldata)
    TextView totaldata;
    @BindView(R.id.totallunas)
    TextView totallunas;
    @BindView(R.id.totalulunas)
    TextView totalulunas;
    @BindView(R.id.totaltidaklunas)
    TextView totaltidaklunas;
    @BindView(R.id.totalutidaklunas)
    TextView totalutidaklunas;

    SessionManager sessionManager;

    String idd;
    String namakegd;
    String uangkegd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_taawun_acivity);
        ButterKnife.bind(this);

        recListTaawun.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recListTaawun.setLayoutManager(layoutManager);

        sessionManager = new SessionManager(ListTaawunAcivity.this);


        idd = getIntent().getStringExtra("id");
        AndLog.ShowLog("aaa",idd);
                sessionManager.setIdKegiatan(idd);
        namakegd = getIntent().getStringExtra("nama_kegiatan");
        uangkegd = getIntent().getStringExtra("jumlah_uang_kegiatan");
        dbcenter = new DBAdapter(this);


        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_kegiatan WHERE id = '" + idd + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            totalulunas.setVisibility(View.INVISIBLE);
            tempattotallunas.setVisibility(View.INVISIBLE);
            tempattotaltlunas.setVisibility(View.INVISIBLE);
            totalutidaklunas.setVisibility(View.INVISIBLE);
            tempattotalutlunas.setVisibility(View.INVISIBLE);
            totaltidaklunas.setText(cursor.getString(0));
            totaltidaklunas.setVisibility(View.INVISIBLE);
            totaldata.setText("Nama Kegiatan : " + cursor.getString(1));
            tempattotaldata.setVisibility(View.INVISIBLE);
            totallunas.setText("Uang : " + cursor.getString(2));
            tempattotalulunas.setVisibility(View.INVISIBLE);
        }

        Swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                db = new DBCRUD(ListTaawunAcivity.this);
//                arrayList = db.getData();
//                adapter = new ListTaawunAdapter(arrayList, ListTaawunAcivity.this);
//                recListTaawun.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
                refresh();
            }
        });
        refresh();
        cekstatus();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Intent a = new Intent(getApplicationContext(), MainActivity.class);
            a.putExtra("id_kegiatan", totaltidaklunas.getText().toString());
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh() {
        db = new DBCRUD(c);
        int datalunas = db.getdatalunas();
        int banyakdata = db.getBanyakData();
        int uanglunas = db.getuanglunas();
        int datatidaklunas = db.getdatatidaklunas();
        int uangtidaklunas = db.getuangtidaklunas();
        tempattotaldata.setText(String.valueOf(banyakdata));
        tempattotallunas.setText(String.valueOf(datalunas));
        tempattotalulunas.setText(String.valueOf(uanglunas));
        tempattotaltlunas.setText(String.valueOf(datatidaklunas));
        tempattotalutlunas.setText(String.valueOf(uangtidaklunas));


        db = new DBCRUD(ListTaawunAcivity.this);
        arrayList = db.getData();
        adapter = new ListTaawunAdapter(arrayList, ListTaawunAcivity.this);
        recListTaawun.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Swipe.setRefreshing(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void cekstatus() {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(c);
        View mView = layoutInflaterAndroid.inflate(R.layout.item_list_taawun, null);
        TextView txstatus = (TextView) mView.findViewById(R.id.txStatus);
        String status = txstatus.getText().toString();
        String a = "Lunas";
        if (status.equals(a)) {
            txstatus.setTextColor(Color.GREEN);
        } else {
            txstatus.setTextColor(Color.RED);
        }
    }
}
