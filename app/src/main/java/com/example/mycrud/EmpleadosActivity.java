package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;


import com.example.mycrud.adaptadors.AdaClientes;
import com.example.mycrud.api.api_inter;
import com.example.mycrud.api.retro;
import com.example.mycrud.databinding.ActivityEmpleadosBinding;
import com.example.mycrud.models.clase_empleados;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadosActivity extends AppCompatActivity {
    private ActivityEmpleadosBinding b ;
    //    --------------------------------------------------------------------
    private List<clase_empleados> lista = new ArrayList<>();
    private AdaClientes adaptador  = new AdaClientes(EmpleadosActivity.this, lista);
    api_inter api_emp = retro.getClient().create(api_inter.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
        b = ActivityEmpleadosBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        b.recycler.setAdapter(adaptador);

        b.ADD.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmpleadosFormActivity.class);
            startActivity(intent);
        });

        Call<List<clase_empleados>> call = api_emp.TODOS();
        call.enqueue(new Callback<List<clase_empleados>>() {
            @Override
            public void onResponse(Call<List<clase_empleados>> call, Response<List<clase_empleados>> response) {
                adaptador.actualizarDatos(response.body());
            }

            @Override
            public void onFailure(Call<List<clase_empleados>> call, Throwable t) {}
        });
    }
}