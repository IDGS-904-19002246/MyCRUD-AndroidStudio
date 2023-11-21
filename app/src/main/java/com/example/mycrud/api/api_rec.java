package com.example.mycrud.api;

import com.example.mycrud.models.clase_recetas;

import java.sql.Time;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface api_rec {
    @GET("recetas.php")
    Call<List<clase_recetas>> TODOS();

    @FormUrlEncoded
    @POST("recetas.php")
    Call<String>anadir(
            @Field("nombre")String nombre,
            @Field("descripcion")String descripcion,
            @Field("costoAprox")int costoAprox,
            @Field("paisOrigen")String paisOrigen,
            @Field("creador")String creador,
            @Field("estado")String estado
    );
    @PUT("recetas.php")
    Call<String>modificar(
            @Body clase_recetas empleado
    );
    @DELETE("recetas.php/{Id}")
    Call<String>BORRAR(@Query("id") int Id);

}
