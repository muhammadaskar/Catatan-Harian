package com.askar.catatanharian.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.askar.catatanharian.DB.Entity.Catatan;
import com.askar.catatanharian.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.MyViewHolder> {

    private Context context;
    private List<Catatan> catatanList;

    private ClickListener listener;

    public interface ClickListener {
        public void onClick(View view, int position);
    }

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public CatatanAdapter(List<Catatan> catatanList) {
        this.catatanList = catatanList;
    }

    public CatatanAdapter(Context context, List<Catatan> catatanList) {
        this.context = context;
        this.catatanList = catatanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_catatan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Date date = new Date();
        String stringDate = DateFormat.getDateTimeInstance().format(date);
        Catatan catatan = catatanList.get(position);
        holder.textViewJudul.setText(catatan.getNamaFile());
        holder.textViewBody.setText(stringDate);
    }

    @Override
    public int getItemCount() {
        return catatanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJudul;
        TextView textViewBody;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewJudul = itemView.findViewById(R.id.tv_catatan_satu);
            textViewBody = itemView.findViewById(R.id.sub_catatan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
