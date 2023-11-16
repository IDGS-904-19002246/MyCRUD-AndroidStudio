package com.example.mycrud;

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
import com.example.mycrud.models.ClsClientes;
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
//    private GoogleMap mMap;
//    private static final LatLng MI_UBICACION = new LatLng(-34, 151);
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
            } else {
                if (extras == null) {
                    Toast.makeText(this, "" + (0 + Integer.parseInt(b.Cp.getText().toString())), Toast.LENGTH_SHORT).show();
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
//    public boolean validarEmail(String email) {
//        if (email.isEmpty()) {
//            return false;
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//    public void getMunicipios(String E){
//        Call<ClsMunicipios> call_mun = api.MUNICIPIOS(E);
//        call_mun.enqueue(new Callback<ClsMunicipios>() {
//            @Override
//            public void onResponse(Call<ClsMunicipios> call, Response<ClsMunicipios> response) {
//                list_municipios = response.body().municipios;
//                ArrayAdapter<String> adapterMunicipios = new ArrayAdapter<>(FormActivity.this, android.R.layout.simple_spinner_item, list_municipios);
//                adapterMunicipios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                b.Municipio.setAdapter(adapterMunicipios);
//            }
//            @Override
//            public void onFailure(Call<ClsMunicipios> call, Throwable t) {}
//        });
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_municipios);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        b.Municipio.setAdapter(adapter);
//    }
//

    }
    public void insertar(String nombre , String apellidoP, String apellidoM, String telefono, int salario, String puesto, String estado){
        Call<String> call = api.anadir(
                nombre, apellidoP, apellidoM, telefono, salario, puesto, estado
        );
    }
    public void actualizar(int id, String nombre , String apellidoP, String apellidoM, String telefono, int salario, String puesto, String estado){
        Call<String> call = api.modificar(new clase_empleados(
                id, nombre, apellidoP, apellidoM, telefono, salario, puesto, estado
                )
        );
    }

}