package com.example.mycrud;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mycrud.api.api_inter;
import com.example.mycrud.api.retro;
import com.example.mycrud.databinding.ActivityFormBinding;
import com.example.mycrud.models.clase_empleados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    private ActivityFormBinding b;
    public static final String CLI_KEY = "cli";
    public clase_empleados cliente;
    public List<String> list_municipios = new ArrayList<>();
    public List<String> list_estados = new ArrayList<>(Arrays.asList("Ciudad de México","Aguascalientes","Baja California","Baja California Sur","Campeche","Coahuila de Zaragoza","Colima","Chiapas","Chihuahua","Durango","Guanajuato","Guerrero","Hidalgo","Jalisco","México","Michoacán de Ocampo","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo","San Luis Potosí","Sinaloa","Sonora","Tabasco","Tamaulipas","Tlaxcala","Veracruz de Ignacio de la Llave","Yucatán","Zacatecas"));
    public String str_estado, str_municipio;
    api_inter api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        b = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        Bundle extras = getIntent().getExtras();
        api = retro.getClient().create(api_inter.class);


//      --------------------------------------------------------------------------
        b.btnGuardar.setOnClickListener(v -> {
            if (b.nombre.getText().toString().isEmpty() || b.nombre.getText().toString().equals(" ")) {
                Toast.makeText(this, "Se necesita nombre", Toast.LENGTH_SHORT).show();
            } else if (b.telefono.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita Telefono valido", Toast.LENGTH_SHORT).show();
            } else if (b.telefono.getText().toString().trim().length() != 10) {
                Toast.makeText(this, "Se necesita Telefono valido", Toast.LENGTH_SHORT).show();
            } else if (b.salario.getText().toString().isEmpty()) {
                Toast.makeText(this, "Se necesita un salario valido", Toast.LENGTH_SHORT).show();
            } else if (b.puesto.getText().toString().isEmpty() || b.puesto.getText().toString().equals(" ")) {
                Toast.makeText(this, "Se necesita un puesto valido", Toast.LENGTH_SHORT).show();
            } else if (b.estado.getText().toString().isEmpty() || b.estado.getText().toString().equals(" ")) {
                Toast.makeText(this, "Se necesita un estado valido", Toast.LENGTH_SHORT).show();
            } else {
                if (extras == null) {

                    insertar(
                            b.nombre.getText().toString(),
                            b.apellidoP.getText().toString(),
                            b.apellidoM.getText().toString(),
                            b.telefono.getText().toString(),
                            Integer.parseInt(b.salario.getText().toString()),
                            b.puesto.getText().toString(),
                            b.estado.getText().toString()

                    );

                    Toast.makeText(this, "Empleado insertado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    actualizar(
                            cliente.getId_empleado(),
                            b.nombre.getText().toString(),
                            b.apellidoP.getText().toString(),
                            b.apellidoM.getText().toString(),
                            b.telefono.getText().toString(),
                            Integer.parseInt(b.salario.getText().toString()),
                            b.puesto.getText().toString(),
                            b.estado.getText().toString()

                    );
                    Toast.makeText(this, "Empleado editado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });


        if (extras != null) {
            cliente = extras.getParcelable(CLI_KEY);

                    b.nombre.setText(cliente.getNombre());
                    b.apellidoP.setText(cliente.getApellidoP());
                    b.apellidoM.setText(cliente.getApellidoM());
                    b.telefono.setText(cliente.getTelefono());
                    b.puesto.setText(cliente.getPuesto());
                    b.estado.setText(cliente.getEstado());
                    b.salario.setText(String.valueOf(cliente.getSalario()));
        }


//    private boolean validarTelefono(int telefono) {
//        String str_telefono = String.valueOf(telefono).trim();
//        if (str_telefono.isEmpty()) {
//            return false;
//        } else if (str_telefono.length() != 10){
//            return false;
//        } else if (telefono <=0) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    }
    public void insertar(String nombre , String apellidoP, String apellidoM, String telefono, int salario, String puesto, String estado){
        Call<String> call = api.anadir(
                nombre, apellidoP, apellidoM, telefono, salario, puesto, estado
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    public void actualizar(int id, String nombre , String apellidoP, String apellidoM, String telefono, int salario, String puesto, String estado){
        Call<String> call = api.modificar(new clase_empleados(
                id, nombre, apellidoP, apellidoM, telefono, salario, puesto, estado
                )
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }



}