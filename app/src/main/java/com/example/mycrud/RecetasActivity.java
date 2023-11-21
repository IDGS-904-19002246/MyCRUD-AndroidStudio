package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.mycrud.adaptadors.AdaRecetas;
import com.example.mycrud.api.api_rec;
import com.example.mycrud.api.retro;
import com.example.mycrud.databinding.ActivityRecetasBinding;
import com.example.mycrud.models.clase_recetas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetasActivity extends AppCompatActivity {
    private ActivityRecetasBinding b ;
    //    --------------------------------------------------------------------
    private List<clase_recetas> lista = new ArrayList<>();
    private AdaRecetas adaptador = new AdaRecetas(RecetasActivity.this, lista);
    api_rec api_rec = retro.getClient().create(api_rec.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);
        b = ActivityRecetasBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.recycler.setLayoutManager(new LinearLayoutManager(this));
        b.recycler.setAdapter(adaptador);

        b.ADD.setOnClickListener(v -> {
            Intent intent = new Intent(this, RecetasFormActivity.class);
            startActivity(intent);
        });

        b.BACK.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Call<List<clase_recetas>> call = api_rec.TODOS();
        call.enqueue(new Callback<List<clase_recetas>>() {
            @Override
            public void onResponse(Call<List<clase_recetas>> call, Response<List<clase_recetas>> response) {
                adaptador.actualizarDatos(response.body());
            }
            @Override
            public void onFailure(Call<List<clase_recetas>> call, Throwable t) {}
        });
    }
}