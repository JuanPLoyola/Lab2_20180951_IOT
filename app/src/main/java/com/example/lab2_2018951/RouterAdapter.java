package com.example.lab2_2018951;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2_2018951.databinding.ItemRouterBinding;
import com.example.lab2_2018951.model.Router;
import java.util.List;


public class RouterAdapter extends RecyclerView.Adapter<RouterAdapter.RouterViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Router router);
    }

    List<Router> lista;
    OnItemClickListener listener;

    public RouterAdapter(List<Router> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RouterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRouterBinding binding = ItemRouterBinding.inflate(inflater, parent, false);
        return new RouterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RouterViewHolder holder, int position) {
        Router router = lista.get(position);
        holder.binding.txtMarca.setText("Marca: " + router.getMarca());
        holder.binding.txtModelo.setText("Modelo: " + router.getModelo());
        holder.binding.txtVelocidad.setText("Velocidad soportada: " + router.getVelocidad());
        holder.binding.txtEstado.setText("Estado: " + router.getEstado());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(router));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class RouterViewHolder extends RecyclerView.ViewHolder {
        ItemRouterBinding binding;

        public RouterViewHolder(ItemRouterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
