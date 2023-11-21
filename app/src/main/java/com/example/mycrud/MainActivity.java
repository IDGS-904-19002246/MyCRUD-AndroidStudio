package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mycrud.adaptadors.AdaClientes;
import com.example.mycrud.databinding.ActivityMainBinding;
//import com.example.mycrud.models.ClsClientes;

import java.util.ArrayList;
import java.util.List;


import com.example.mycrud.api.api_inter;
import com.example.mycrud.api.retro;
import com.example.mycrud.models.clase_empleados;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.EMP.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmpleadosActivity.class);
            startActivity(intent);
        });

        b.REC.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecetasActivity.class);
            startActivity(intent);
        });

        b.TIA.setOnClickListener(v -> {
            Intent intent = new Intent(this, TianguisActivity.class);
            startActivity(intent);
        });

    }
}