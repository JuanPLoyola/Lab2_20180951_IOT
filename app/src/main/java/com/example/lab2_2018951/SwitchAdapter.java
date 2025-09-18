package com.example.lab2_2018951;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2_2018951.databinding.ItemSwitchBinding;
import com.example.lab2_2018951.model.SwitchDevice;
import java.util.List;

public class SwitchAdapter extends RecyclerView.Adapter<SwitchAdapter.VH> {

    public interface OnItemClickListener { void onItemClick(SwitchDevice sw); }

    private final List<SwitchDevice> lista;
    private final OnItemClickListener listener;

    public SwitchAdapter(List<SwitchDevice> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSwitchBinding binding = ItemSwitchBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        SwitchDevice sw = lista.get(position);
        holder.b.txtMarca.setText("Marca: " + sw.getMarca());
        holder.b.txtModelo.setText("Modelo: " + sw.getModelo());
        holder.b.txtPuertos.setText("Cantidad de Puertos: " + sw.getPuertos());
        holder.b.txtTipo.setText("Tipo: " + sw.getTipo());
        holder.b.txtEstado.setText("Estado: " + sw.getEstado());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(sw));
    }

    @Override public int getItemCount() { return lista.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ItemSwitchBinding b;
        VH(ItemSwitchBinding b) { super(b.getRoot()); this.b = b; }
    }
}
