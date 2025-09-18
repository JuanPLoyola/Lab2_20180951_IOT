package com.example.lab2_2018951;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2_2018951.databinding.ActivityApFormBinding;
import com.example.lab2_2018951.model.AccessPoint;

public class ApFormActivity extends AppCompatActivity {

    private ActivityApFormBinding binding;
    private String modo = "crear";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configurarSpinners();

        // Cargar si venimos en modo edición
        AccessPoint ap = (AccessPoint) getIntent().getSerializableExtra("ap");
        String m = getIntent().getStringExtra("modo");
        if (m != null) modo = m;
        if (ap != null) {
            binding.edtMarca.setText(ap.getMarca());
            binding.edtAlcance.setText(String.valueOf(ap.getAlcance()));
            binding.spnFrecuencia.setSelection(((ArrayAdapter) binding.spnFrecuencia.getAdapter()).getPosition(ap.getFrecuencia()));
            binding.spnEstado.setSelection(((ArrayAdapter) binding.spnEstado.getAdapter()).getPosition(ap.getEstado()));
        }

        binding.btnGuardar.setOnClickListener(v -> {
            String marca = binding.edtMarca.getText().toString().trim();
            String frecuencia = binding.spnFrecuencia.getSelectedItem().toString();
            String alcanceStr = binding.edtAlcance.getText().toString().trim();
            int alcance = alcanceStr.isEmpty() ? 0 : Integer.parseInt(alcanceStr);
            String estado = binding.spnEstado.getSelectedItem().toString();

            AccessPoint nuevo = new AccessPoint(marca, frecuencia, alcance, estado);
            Intent r = new Intent();
            r.putExtra("ap", nuevo);
            r.putExtra("modo", modo);
            setResult(RESULT_OK, r);
            finish();
        });
    }

    private void configurarSpinners() {
        String[] frecuencias = {"2.4 GHz", "5 GHz", "Dual Band"};
        String[] estados = {"Operativo", "En reparación", "Dado de baja"};

        ArrayAdapter<String> aFreq = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, frecuencias);
        aFreq.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnFrecuencia.setAdapter(aFreq);

        ArrayAdapter<String> aEst = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        aEst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spnEstado.setAdapter(aEst);
    }
}
