package com.example.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mycrud.api.api_tia;
import com.example.mycrud.api.retro;
import com.example.mycrud.databinding.ActivityTianguisFormBinding;
import com.example.mycrud.models.clase_tianguis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TianguisFormActivity extends AppCompatActivity {
    private ActivityTianguisFormBinding b;
    public static final String KEY = "tia";
    public clase_tianguis tianguis;
    api_tia api_tia = retro.getClient().create(api_tia.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianguis_form);
        b = ActivityTianguisFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        Bundle extras = getIntent().getExtras();

        b.horaInicio.setIs24HourView(true);
        b.horaFinal.setIs24HourView(true);

        if (extras != null) {
            tianguis = extras.getParcelable(KEY);

            b.horaInicio.setHour(Integer.parseInt(tianguis.horaInicio.substring(0,2)));
            b.horaInicio.setMinute(Integer.parseInt(tianguis.horaInicio.substring(3,5)));

            b.horaFinal.setHour(Integer.parseInt(tianguis.horaFinal.substring(0,2)));
            b.horaFinal.setMinute(Integer.parseInt(tianguis.horaFinal.substring(3,5)));

            b.nombre.setText(tianguis.nombre.toString());
            b.ubicacion.setText(tianguis.ubicacion.toString());
            b.organizador.setText(tianguis.organizador.toString());
            b.estado.setText(tianguis.estado.toString());
        }

        b.btnGuardar.setOnClickListener(v -> {
            if (b.nombre.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita nombre", Toast.LENGTH_SHORT).show();
            }else if(b.ubicacion.getText().toString().isEmpty()){
                Toast.makeText(this, "Se necesita ubicacion", Toast.LENGTH_SHORT).show();
            }else if(b.organizador.getText().toString().isEmpty()){
                Toast.makeText(this, "Se necesita organizador", Toast.LENGTH_SHORT).show();
            }else if(b.estado.getText().toString().isEmpty()){
                Toast.makeText(this, "Se necesita estado", Toast.LENGTH_SHORT).show();

            } else {
                if (extras == null) {
                    insertar(
                            b.nombre.getText().toString(),
                            b.ubicacion.getText().toString(),
                            getTime(b.horaInicio.getHour(),b.horaInicio.getMinute()),
                            getTime(b.horaFinal.getHour(),b.horaFinal.getMinute()),
                            b.organizador.getText().toString(),
                            b.estado.getText().toString()
                    );
                } else {
                    actualizar(
                            b.nombre.getText().toString(),
                            b.ubicacion.getText().toString(),
                            getTime(b.horaInicio.getHour(),b.horaInicio.getMinute()),
                            getTime(b.horaFinal.getHour(),b.horaFinal.getMinute()),
                            b.organizador.getText().toString(),
                            b.estado.getText().toString(),
                            tianguis.id_tianguis
                    );
                }
            }
        });
    }


    public void insertar(String nombre, String ubicacion, String horaInicio, String horaFinal, String organizador, String estado){
        Call<String> call = api_tia.anadir(nombre, ubicacion, horaInicio, horaFinal, organizador, estado);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(TianguisFormActivity.this, "Tianguis Añadido", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TianguisFormActivity.this, TianguisActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }
    public void actualizar(String nombre, String ubicacion, String horaInicio, String horaFinal, String organizador, String estado, int id){
        clase_tianguis c = new clase_tianguis(id, nombre, ubicacion, horaInicio, horaFinal, organizador, estado);
        Call<String> call = api_tia.modificar(c);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(TianguisFormActivity.this, "Tianguis Editado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TianguisFormActivity.this, TianguisActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }

    public String getTime(int h, int m){
        if (h <= 9){
            if (m <= 9){
                return "0"+h+":0"+m+":00";
            }else{
                return "0"+h+":"+m+":00";
            }
        }else{
            if (m <= 9){
                return h+":0"+m+":00";
            }else{
                return h+":"+m+":00";
            }
        }
    }
}