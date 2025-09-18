package com.example.lab2_2018951;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2_2018951.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRouters.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RouterListActivity.class);
            startActivity(intent);
        });

        binding.btnSwitches.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SwitchListActivity.class);
            startActivity(intent);
        });

        binding.btnAccessPoints.setOnClickListener(v ->
                startActivity(new Intent(this, ApListActivity.class)));

        binding.btnReportes.setOnClickListener(v ->
                startActivity(new Intent(this, ReportActivity.class)));
    }
}
