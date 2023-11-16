package com.example.mycrud.api;

import com.example.mycrud.models.clase_empleados;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface api_inter {
    @GET("t_empleados.php")
    Call<List<clase_empleados>> TODOS();

    @FormUrlEncoded
    @POST("t_empleados.php")
    Call<String>anadir(
            @Field("nombre")String nombre,
            @Field("apellidoP")String apellidoP,
            @Field("apellidoM")String apellidoM,
            @Field("telefono")String telefono,
            @Field("salario")int salario,
            @Field("puesto")String puesto,
            @Field("estado")String estado
    );
    @PUT("t_empleados.php")
    Call<String>modificar(
            @Body clase_empleados empleado
    );
    @DELETE("t_empleados.php/{Id}")
    Call<String> BORRAR(@Query("id") int Id);
}
