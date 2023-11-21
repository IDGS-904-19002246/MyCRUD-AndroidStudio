package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mycrud.adaptadors.AdaClientes;
import com.example.mycrud.adaptadors.AdaTianguis;
import com.example.mycrud.api.api_tia;
import com.example.mycrud.api.retro;
import com.example.mycrud.databinding.ActivityTianguisBinding;
import com.example.mycrud.models.clase_tianguis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TianguisActivity extends AppCompatActivity {
    private ActivityTianguisBinding b ;
    //    --------------------------------------------------------------------
    private List<clase_tianguis> lista = new ArrayList<>();
    private AdaTianguis adaptador = new AdaTianguis(TianguisActivity.this, lista);
    private api_tia api_tia = retro.getClient().create(api_tia.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianguis);
        b = ActivityTianguisBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        b.recycler.setAdapter(adaptador);

        b.ADD.setOnClickListener(v -> {
            Intent intent = new Intent(this, TianguisFormActivity.class);
            startActivity(intent);
        });

        b.BACK.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Call<List<clase_tianguis>> call = api_tia.TODOS();
        call.enqueue(new Callback<List<clase_tianguis>>() {
            @Override
            public void onResponse(Call<List<clase_tianguis>> call, Response<List<clase_tianguis>> response) {
                adaptador.actualizarDatos(response.body());
            }
            @Override
            public void onFailure(Call<List<clase_tianguis>> call, Throwable t) {}
        });
    }
}