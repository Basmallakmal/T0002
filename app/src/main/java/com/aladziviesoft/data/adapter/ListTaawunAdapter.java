package com.aladziviesoft.data.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aladziviesoft.data.R;
import com.aladziviesoft.data.UpdateActivity;
import com.aladziviesoft.data.model.ListTaawunModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTaawunAdapter extends RecyclerView.Adapter<ListTaawunAdapter.ListTaawunHoler> {

    DBAdapter dbcenter;


    private List<ListTaawunModel> arrayList;
    private Context context;

    public ListTaawunAdapter(List<ListTaawunModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override
    public ListTaawunHoler onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list_taawun, viewGroup, false);
        return new ListTaawunHoler(view);
    }

    @Override
    public void onBindViewHolder(ListTaawunHoler listTaawunHoler, final int i) {
        listTaawunHoler.txId.setText(arrayList.get(i).getId());
        listTaawunHoler.txNama.setText(arrayList.get(i).getNama());
        listTaawunHoler.txJumlahUang.setText(arrayList.get(i).getJumlahUang());
        listTaawunHoler.txTanggal.setText(arrayList.get(i).getTanggal());
        listTaawunHoler.txStatus.setText(arrayList.get(i).getStatus());
        if (listTaawunHoler.txStatus.getText().equals("Lunas")) {
            listTaawunHoler.txStatus.setTextColor(Color.GREEN);
        } else {
            listTaawunHoler.txStatus.setTextColor(Color.RED);
        }
        listTaawunHoler.txidkegiatan.setText(arrayList.get(i).getIdKegiatan());
        listTaawunHoler.cardv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] item = {"Update", "Delete"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pilih Aksi");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on item[which]
                        Intent intent;
                        switch (which) {
                            case 0:
                                item[0] = "Update";
                                intent = new Intent(context, UpdateActivity.class);
                                intent.putExtra("_id", arrayList.get(i).getId());
                                context.startActivity(intent);
                                break;
                            case 1:
                                item[1] = "Delete";
                                dbcenter = new DBAdapter(context);
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from data_pembayaran where _id = '" + arrayList.get(i).getId() + "'");

                                break;

                        }
                    }
                });
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ListTaawunHoler extends RecyclerView.ViewHolder {

        @BindView(R.id.txId)
        TextView txId;
        @BindView(R.id.txNama)
        TextView txNama;
        @BindView(R.id.txJumlahUang)
        TextView txJumlahUang;
        @BindView(R.id.txStatus)
        TextView txStatus;
        @BindView(R.id.txTanggal)
        TextView txTanggal;
        @BindView(R.id.txidkegiatan)
        TextView txidkegiatan;
        @BindView(R.id.cardv)
        CardView cardv;

        public ListTaawunHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
