package com.aladziviesoft.data.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.aladziviesoft.data.ListTaawunAcivity;
import com.aladziviesoft.data.R;
import com.aladziviesoft.data.UpdateKegiatan;
import com.aladziviesoft.data.model.ListKegiatanModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListKegiatanAdapter extends RecyclerView.Adapter<ListKegiatanAdapter.Holder> {

    DBAdapter dbcenter;


    private List<ListKegiatanModel> arrayList;
    private Context context;

    public ListKegiatanAdapter(List<ListKegiatanModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list_kegiatan, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.Idkegiatan.setText(arrayList.get(i).getIdKegiatan());
        holder.Namakegiatan.setText(arrayList.get(i).getNamaKegiatan());
        holder.Jumlahuangkegiatan.setText(arrayList.get(i).getJumlahUangKegiatan());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] item = {"Detail","Update", "Delete"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Pilih Aksi");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on item[which]
                        Intent intent;
                        switch (which) {
                            case 0:
                                item[0] = "Detail";
                                intent = new Intent(context, ListTaawunAcivity.class);
                                intent.putExtra("id", arrayList.get(i).getIdKegiatan());
                                intent.putExtra("nama_kegiatan", arrayList.get(i).getNamaKegiatan());
                                intent.putExtra("jumlah_uang_kegiatan", arrayList.get(i).getJumlahUangKegiatan());
                                context.startActivity(intent);
                            break;

                            case 1:
                                item[0] = "Update";
                                intent = new Intent(context, UpdateKegiatan.class);
                                intent.putExtra("id", arrayList.get(i).getIdKegiatan());
                                context.startActivity(intent);
                                break;

                            case 2:
                                item[1] = "Delete";
                                dbcenter = new DBAdapter(context);
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from data_kegiatan where id = '" + arrayList.get(i).getIdKegiatan() + "'");
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

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.Namakegiatan)
        TextView Namakegiatan;
        @BindView(R.id.Jumlahuangkegiatan)
        TextView Jumlahuangkegiatan;
        @BindView(R.id.Idkegiatan)
        TextView Idkegiatan;
        @BindView(R.id.cardview)
        CardView cardview;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
