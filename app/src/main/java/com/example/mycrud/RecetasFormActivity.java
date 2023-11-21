package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mycrud.api.api_rec;
import com.example.mycrud.api.retro;
import com.example.mycrud.databinding.ActivityRecetasFormBinding;
import com.example.mycrud.models.clase_recetas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecetasFormActivity extends AppCompatActivity {
    private ActivityRecetasFormBinding b;
    public static final String KEY = "rec";
    public clase_recetas receta;
    api_rec api_rec = retro.getClient().create(api_rec.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas_form);
        b = ActivityRecetasFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            receta = extras.getParcelable(KEY);

            b.nombre.setText(receta.nombre);
            b.descripcion.setText(receta.descripcion);
            b.costoAprox.setText(String.valueOf(receta.costoAprox));
            b.paisOrigen.setText(receta.paisOrigen);
            b.creador.setText(receta.creador);
            b.estado.setText(receta.estado.toString());
        }

        b.btnGuardar.setOnClickListener(v -> {
            if (b.nombre.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita nombre", Toast.LENGTH_SHORT).show();
            } else {
                if (extras == null) {
                    insertar(
                        b.nombre.getText().toString(),
                        b.descripcion.getText().toString(),
                        Integer.parseInt(b.costoAprox.getText().toString()),
                        b.paisOrigen.getText().toString(),
                        b.creador.getText().toString(),
                        b.estado.getText().toString()
                    );
                } else {
                    actualizar(
                        b.nombre.getText().toString(),
                        b.descripcion.getText().toString(),
                        Integer.parseInt(b.costoAprox.getText().toString()),
                        b.paisOrigen.getText().toString(),
                        b.creador.getText().toString(),
                        b.estado.getText().toString(),
                        receta.id_receta
                    );
                }
            }
        });
    }

    public void insertar(String nombre, String descripcion, int costoAprox, String paisOrigen, String creador, String estado){
        Call<String> call = api_rec.anadir(nombre, descripcion, costoAprox, paisOrigen, creador, estado);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(RecetasFormActivity.this, "Receta Añadida", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecetasFormActivity.this, RecetasActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }
    public void actualizar(String nombre, String descripcion, int costoAprox, String paisOrigen, String creador, String estado, int id){
        clase_recetas c = new clase_recetas(id, nombre, descripcion, costoAprox, paisOrigen, creador, estado);
        Call<String> call = api_rec.modificar(c);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(RecetasFormActivity.this, "Recetas Editado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecetasFormActivity.this, RecetasActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }
}