package com.example.lab2_2018951;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2_2018951.databinding.ItemApBinding;
import com.example.lab2_2018951.model.AccessPoint;
import java.util.List;

public class ApAdapter extends RecyclerView.Adapter<ApAdapter.VH> {

    public interface OnItemClickListener { void onItemClick(AccessPoint ap); }

    private final List<AccessPoint> lista;
    private final OnItemClickListener listener;

    public ApAdapter(List<AccessPoint> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemApBinding b = ItemApBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(b);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        AccessPoint ap = lista.get(position);
        holder.b.txtMarca.setText("Marca: " + ap.getMarca());
        holder.b.txtFrecuencia.setText("Frecuencia: " + ap.getFrecuencia());
        holder.b.txtAlcance.setText("Alcance: " + ap.getAlcance() + " metros");
        holder.b.txtEstado.setText("Estado: " + ap.getEstado());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(ap));
    }

    @Override public int getItemCount() { return lista.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ItemApBinding b;
        VH(ItemApBinding b) { super(b.getRoot()); this.b = b; }
    }
}
