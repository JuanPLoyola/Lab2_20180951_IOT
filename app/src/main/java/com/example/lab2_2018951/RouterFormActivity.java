package com.example.lab2_2018951;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2_2018951.databinding.ActivityRouterFormBinding;
import com.example.lab2_2018951.model.Router;

import java.util.Arrays;

public class RouterFormActivity extends AppCompatActivity {

    private EditText edtMarca, edtModelo, edtVelocidad;
    private Spinner spnEstado;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_router_form);

        edtMarca = findViewById(R.id.edtMarca);
        edtModelo = findViewById(R.id.edtModelo);
        edtVelocidad = findViewById(R.id.edtVelocidad);
        spnEstado = findViewById(R.id.spnEstado);
        btnGuardar = findViewById(R.id.btnGuardar);

        configurarSpinner();

        Router router = (Router) getIntent().getSerializableExtra("router");
        if (router != null) {
            edtMarca.setText(router.getMarca());
            edtModelo.setText(router.getModelo());
            edtVelocidad.setText(router.getVelocidad());

            // Establecer la selección del spinner según el estado del router
            ArrayAdapter adapter = (ArrayAdapter) spnEstado.getAdapter();
            int pos = adapter.getPosition(router.getEstado());
            spnEstado.setSelection(pos);
        }


        btnGuardar.setOnClickListener(v -> {
            String marca = edtMarca.getText().toString();
            String modelo = edtModelo.getText().toString();
            String velocidad = edtVelocidad.getText().toString();
            String estado = spnEstado.getSelectedItem().toString();

            Router nuevoRouter = new Router(marca, modelo, velocidad, estado);
            Intent intent = new Intent();
            intent.putExtra("router", nuevoRouter);

            String modo = getIntent().getStringExtra("modo");
            if (modo == null) modo = "crear";
            intent.putExtra("modo", modo);

            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void configurarSpinner() {
        String[] estados = {"Operativo", "En reparación", "Dado de baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(adapter);
    }
}


