package com.aladziviesoft.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aladziviesoft.data.R;
import com.aladziviesoft.data.model.ListTaawunModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListTaawunAdapter extends RecyclerView.Adapter<ListTaawunAdapter.ListTaawunHoler> {

    private Context context;
    private ArrayList<ListTaawunModel> arrayList;

    public ListTaawunAdapter(Context context, ArrayList<ListTaawunModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListTaawunHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_list_taawun, viewGroup, false);
        return new ListTaawunHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTaawunHoler listTaawunHoler, int i) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ListTaawunHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.txNama)
        TextView txNama;
        @BindView(R.id.txJumlahUang)
        TextView txJumlahUang;
        @BindView(R.id.txStatus)
        TextView txStatus;
        @BindView(R.id.txTanggal)
        TextView txTanggal;

        public ListTaawunHoler(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
