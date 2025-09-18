package com.example.lab2_2018951;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2_2018951.data.DataStore;
import com.example.lab2_2018951.databinding.ActivityReportBinding;
import com.example.lab2_2018951.model.AccessPoint;
import com.example.lab2_2018951.model.Router;
import com.example.lab2_2018951.model.SwitchDevice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReportActivity extends AppCompatActivity {

    private ActivityReportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1) Juntar todo por estado
        Map<String, List<String>> grupos = agruparPorEstado();

        // 2) Pintar secciones: Operativo, En reparación, Dado de baja (en ese orden)
        String[] orden = {"Operativo", "En reparación", "Dado de baja"};

        LinearLayout container = binding.containerSecciones;
        container.removeAllViews();

        for (String estado : orden) {
            List<String> items = grupos.get(estado);
            if (items == null || items.isEmpty()) continue;

            // Título: "Operativos (5)"
            TextView titulo = new TextView(this);
            titulo.setText(estado + " (" + items.size() + ")");
            titulo.setTypeface(null, Typeface.BOLD);
            titulo.setPadding(0, dp(8), 0, dp(4));
            container.addView(titulo);

            // Lista de líneas
            for (String linea : items) {
                TextView tv = new TextView(this);
                tv.setText(linea);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                tv.setLayoutParams(lp);
                container.addView(tv);
            }

            // Separación entre secciones
            TextView sep = new TextView(this);
            sep.setText("\n");
            container.addView(sep);
        }
    }

    private Map<String, List<String>> agruparPorEstado() {
        Map<String, List<String>> map = new LinkedHashMap<>();
        // Garantiza llaves en el orden deseado
        map.put("Operativo", new ArrayList<>());
        map.put("En reparación", new ArrayList<>());
        map.put("Dado de baja", new ArrayList<>());

        // Routers
        for (Router r : DataStore.getInstance().getRouters()) {
            String linea = "Router " + r.getMarca() + " " + r.getModelo();
            map.get(r.getEstado()).add(linea);
        }
        // Switches
        for (SwitchDevice s : DataStore.getInstance().getSwitches()) {
            String linea = "Switch " + s.getMarca() + " " + s.getModelo();
            map.get(s.getEstado()).add(linea);
        }
        // APs
        for (AccessPoint ap : DataStore.getInstance().getAccessPoints()) {
            String linea = "AP " + ap.getMarca() + " " /* modelo no existe */ + ap.getFrecuencia();
            // Si quieres mostrar solo marca + “frecuencia” como en tu mock, queda así.
            map.get(ap.getEstado()).add(linea);
        }
        return map;
    }

    private int dp(int v){
        float d = getResources().getDisplayMetrics().density;
        return (int) (v * d);
    }
}
