package com.example.lab2_2018951;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2_2018951.model.SwitchDevice;

public class SwitchFormActivity extends AppCompatActivity {

    private EditText edtMarca, edtModelo, edtPuertos;
    private Spinner spnTipo, spnEstado;
    private Button btnGuardar;
    private String modo; // "crear" | "editar"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_form);

        edtMarca = findViewById(R.id.edtMarcaSwitch);
        edtModelo = findViewById(R.id.edtModeloSwitch);
        edtPuertos = findViewById(R.id.edtPuertos);
        spnTipo = findViewById(R.id.spnTipo);
        spnEstado = findViewById(R.id.spnEstado);
        btnGuardar = findViewById(R.id.btnGuardarSwitch);

        configurarSpinners();

        // Si viene en modo edición, rellenar
        SwitchDevice sw = (SwitchDevice) getIntent().getSerializableExtra("switch");
        modo = getIntent().getStringExtra("modo");
        if (modo == null) modo = "crear";
        if (sw != null) {
            edtMarca.setText(sw.getMarca());
            edtModelo.setText(sw.getModelo());
            edtPuertos.setText(String.valueOf(sw.getPuertos()));
            ((ArrayAdapter) spnTipo.getAdapter()).getPosition(sw.getTipo());
            spnTipo.setSelection(((ArrayAdapter) spnTipo.getAdapter()).getPosition(sw.getTipo()));
            spnEstado.setSelection(((ArrayAdapter) spnEstado.getAdapter()).getPosition(sw.getEstado()));
        }

        btnGuardar.setOnClickListener(v -> {
            String marca = edtMarca.getText().toString().trim();
            String modelo = edtModelo.getText().toString().trim();
            String puertosStr = edtPuertos.getText().toString().trim();
            int puertos = puertosStr.isEmpty() ? 0 : Integer.parseInt(puertosStr);
            String tipo = spnTipo.getSelectedItem().toString();
            String estado = spnEstado.getSelectedItem().toString();

            SwitchDevice nuevo = new SwitchDevice(marca, modelo, puertos, tipo, estado);
            Intent r = new Intent();
            r.putExtra("switch", nuevo);
            r.putExtra("modo", modo);
            setResult(RESULT_OK, r);
            finish();
        });
    }

    private void configurarSpinners() {
        String[] tipos = {"Administrable", "No Administrable"};
        String[] estados = {"Operativo", "En reparación", "Dado de baja"};

        ArrayAdapter<String> aTipos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        aTipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipo.setAdapter(aTipos);

        ArrayAdapter<String> aEstados = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        aEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(aEstados);
    }
}
