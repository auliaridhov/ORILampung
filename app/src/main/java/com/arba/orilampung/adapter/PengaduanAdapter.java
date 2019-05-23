package com.arba.orilampung.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arba.orilampung.BuatAjuanActivity;
import com.arba.orilampung.CustomOnItemClickListener;
import com.arba.orilampung.R;
import com.arba.orilampung.entity.Pengaduan;

import java.util.ArrayList;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.PengaduanViewHolder> {
    private final ArrayList<Pengaduan> listPengaduan = new ArrayList<>();
    private final Activity activity;

    public PengaduanAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Pengaduan> getListPengaduan() {
        return listPengaduan;
    }

    public void setListNotes(ArrayList<Pengaduan> listPengaduan) {

        if (listPengaduan.size() > 0) {
            this.listPengaduan.clear();
        }
        this.listPengaduan.addAll(listPengaduan);

        notifyDataSetChanged();
    }

    public void addItem(Pengaduan pengaduan) {
        this.listPengaduan.add(pengaduan);
        notifyItemInserted(listPengaduan.size() - 1);
    }

    public void updateItem(int position, Pengaduan pengaduan) {
        this.listPengaduan.set(position, pengaduan);
        notifyItemChanged(position, pengaduan);
    }

    public void removeItem(int position) {
        this.listPengaduan.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listPengaduan.size());
    }

    @NonNull
    @Override
    public PengaduanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengaduan, parent, false);
        return new PengaduanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengaduanViewHolder holder, int position) {
        holder.tvTitle.setText(listPengaduan.get(position).getNamapelapor());
        holder.tvDate.setText(listPengaduan.get(position).getTglSkrg());
        holder.tvDescription.setText(listPengaduan.get(position).getNamaInstansiTerlapor());
        holder.cvNote.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, BuatAjuanActivity.class);
                intent.putExtra(BuatAjuanActivity.EXTRA_POSITION, position);
                intent.putExtra(BuatAjuanActivity.EXTRA_NOTE, listPengaduan.get(position));
                activity.startActivityForResult(intent, BuatAjuanActivity.REQUEST_UPDATE);
                activity.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listPengaduan.size();
    }

    class PengaduanViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvDate;
        final CardView cvNote;

        PengaduanViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}