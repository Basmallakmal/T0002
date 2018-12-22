package com.aladziviesoft.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.aladziviesoft.data.adapter.DBCRUD;
import com.aladziviesoft.data.adapter.ListKegiatanAdapter;
import com.aladziviesoft.data.model.ListKegiatanModel;
import com.aladziviesoft.data.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class kegiatan_utama extends AppCompatActivity {

    DBCRUD db;
    final Context c = this;

    @BindView(R.id.rec_kegiatan)
    RecyclerView recKegiatan;
    @BindView(R.id.Swipe)
    SwipeRefreshLayout Swipe;

    LinearLayoutManager layoutManager;
    ListKegiatanAdapter adapter;
    List<ListKegiatanModel> arraylist = new ArrayList<>();

    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_utama);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(kegiatan_utama.this);


        recKegiatan.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recKegiatan.setLayoutManager(layoutManager);

        Swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();

            }
        });

        refresh();

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
            Intent a = new Intent(getApplicationContext(), TambahKegiatan.class);
            startActivity(a);
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh() {
        db = new DBCRUD(kegiatan_utama.this);
        arraylist = db.getdatakegiatan();
        adapter = new ListKegiatanAdapter(arraylist, kegiatan_utama.this);
        recKegiatan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Swipe.setRefreshing(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}
