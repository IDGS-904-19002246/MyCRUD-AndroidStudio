package com.example.mycrud.api;

import com.example.mycrud.models.clase_tianguis;

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

public interface api_tia {
    @GET("tianguis.php")
    Call<List<clase_tianguis>> TODOS();

    @FormUrlEncoded
    @POST("tianguis.php")
    Call<String>anadir(
            @Field("nombre")String nombre,
            @Field("ubicacion")String ubicacion,
            @Field("horaInicio")String horaInicio,
            @Field("horaFinal")String horaFinal,
            @Field("organizador")String organizador,
            @Field("estado")String estado
    );
    @PUT("tianguis.php")
    Call<String>modificar(
            @Body clase_tianguis empleado
    );
    @DELETE("tianguis.php/{Id}")
    Call<String>BORRAR(@Query("id") int Id);
}
