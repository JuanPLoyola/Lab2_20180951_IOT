package com.example.lab2_2018951;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2_2018951.databinding.ActivityApListBinding;
import com.example.lab2_2018951.model.AccessPoint;
import java.util.ArrayList;
import java.util.List;

public class ApListActivity extends AppCompatActivity {

    private ActivityApListBinding binding;
    private final List<AccessPoint> lista = new ArrayList<>();
    private ApAdapter adapter;
    private AccessPoint apEditado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ApAdapter(lista, ap -> {
            apEditado = ap;
            Intent i = new Intent(this, ApFormActivity.class);
            i.putExtra("modo", "editar");
            i.putExtra("ap", ap);
            startActivityForResult(i, 501);
        });

        binding.recyclerAps.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerAps.setAdapter(adapter);
        actualizarEmpty();

        binding.fabAgregarAp.setOnClickListener(v -> {
            Intent i = new Intent(this, ApFormActivity.class);
            startActivityForResult(i, 500);
        });
    }

    private void actualizarEmpty() {
        if (lista.isEmpty()) {
            binding.recyclerAps.setVisibility(View.GONE);
            binding.emptyMessageAp.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerAps.setVisibility(View.VISIBLE);
            binding.emptyMessageAp.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            AccessPoint ap = (AccessPoint) data.getSerializableExtra("ap");
            String modo = data.getStringExtra("modo");
            if ("editar".equals(modo)) {
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).equals(apEditado)) {
                        lista.set(i, ap);
                        break;
                    }
                }
            } else {
                lista.add(ap);
            }
            adapter.notifyDataSetChanged();
            actualizarEmpty();
        }
    }
}
