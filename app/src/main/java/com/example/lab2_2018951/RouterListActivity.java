package com.example.lab2_2018951;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2_2018951.databinding.ActivityRouterListBinding;
import com.example.lab2_2018951.model.Router;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import com.example.lab2_2018951.data.DataStore;


public class RouterListActivity extends AppCompatActivity {

    private ActivityRouterListBinding binding;
    private final List<Router> listaRouters = new ArrayList<>();
    private RouterAdapter adapter;
    private Router routerEditado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRouterListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new RouterAdapter(listaRouters, router -> {
            routerEditado = router; // <- guardar el router que vas a editar

            Intent intent = new Intent(RouterListActivity.this, RouterFormActivity.class);
            intent.putExtra("router", router);
            intent.putExtra("modo", "editar");
            startActivityForResult(intent, 200);
        });


        binding.recyclerRouters.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerRouters.setAdapter(adapter);

        actualizarMensaje();


        FloatingActionButton fab = findViewById(R.id.fabAgregar);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, RouterFormActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void actualizarMensaje() {
        if (listaRouters.isEmpty()) {
            binding.recyclerRouters.setVisibility(View.GONE);
            binding.emptyMessage.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerRouters.setVisibility(View.VISIBLE);
            binding.emptyMessage.setVisibility(View.GONE);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Router router = (Router) data.getSerializableExtra("router");
            String modo = data.getStringExtra("modo");

            if ("editar".equals(modo)) {
                for (int i = 0; i < listaRouters.size(); i++) {
                    if (listaRouters.get(i).equals(routerEditado)) {
                        listaRouters.set(i, router);
                        DataStore.getInstance().replaceRouter(routerEditado, router); // <—
                        break;
                    }
                }
            } else {
                listaRouters.add(router);
                DataStore.getInstance().addRouter(router); // <—
            }
            adapter.notifyDataSetChanged();
            actualizarMensaje();
        }
    }

}
