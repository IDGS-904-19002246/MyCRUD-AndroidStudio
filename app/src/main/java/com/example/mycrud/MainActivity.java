package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

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
    //    private RecyclerView recycler;
    //    --------------------------------------------------------------------
    private List<clase_empleados> lista;
    private AdaClientes adaptador;
    api_inter api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        lista = new ArrayList<>();

        b.ADD.setOnClickListener(v -> {
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
        });


        api = retro.getClient().create(api_inter.class);
        Call<List<clase_empleados>> call = api.TODOS();

        adaptador = new AdaClientes(MainActivity.this, lista);
        b.recycler.setAdapter(adaptador);
        call.enqueue(new Callback<List<clase_empleados>>() {
            @Override
            public void onResponse(Call<List<clase_empleados>> call, Response<List<clase_empleados>> response) {
                lista = response.body();
                adaptador = new AdaClientes(MainActivity.this, lista);
                b.recycler.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<List<clase_empleados>> call, Throwable t) {

            }
        });


    }
}