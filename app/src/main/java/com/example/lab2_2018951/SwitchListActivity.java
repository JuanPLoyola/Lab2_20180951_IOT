package com.example.lab2_2018951;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2_2018951.databinding.ActivitySwitchListBinding;
import com.example.lab2_2018951.model.SwitchDevice;
import java.util.ArrayList;
import java.util.List;


import com.example.lab2_2018951.data.DataStore;

public class SwitchListActivity extends AppCompatActivity {

    private ActivitySwitchListBinding binding;
    private final List<SwitchDevice> lista = new ArrayList<>();
    private SwitchAdapter adapter;
    private SwitchDevice switchEditado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwitchListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new SwitchAdapter(lista, sw -> {
            switchEditado = sw;
            Intent i = new Intent(this, SwitchFormActivity.class);
            i.putExtra("modo", "editar");
            i.putExtra("switch", sw);
            startActivityForResult(i, 301);
        });

        binding.recyclerSwitches.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerSwitches.setAdapter(adapter);

        actualizarEmpty();

        binding.fabAgregarSwitch.setOnClickListener(v -> {
            Intent i = new Intent(this, SwitchFormActivity.class);
            startActivityForResult(i, 300); // crear
        });
    }

    private void actualizarEmpty() {
        if (lista.isEmpty()) {
            binding.recyclerSwitches.setVisibility(View.GONE);
            binding.emptyMessageSwitch.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerSwitches.setVisibility(View.VISIBLE);
            binding.emptyMessageSwitch.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            SwitchDevice sw = (SwitchDevice) data.getSerializableExtra("switch");
            String modo = data.getStringExtra("modo");
            if ("editar".equals(modo)) {
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).equals(switchEditado)) {
                        lista.set(i, sw);
                        DataStore.getInstance().replaceSwitch(switchEditado, sw); // <—
                        break;
                    }
                }
            } else {
                lista.add(sw);
                DataStore.getInstance().addSwitch(sw); // <—
            }
            adapter.notifyDataSetChanged();
            actualizarEmpty();
        }
    }
}
