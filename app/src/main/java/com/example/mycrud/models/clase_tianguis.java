package com.example.mycrud.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class clase_tianguis implements Parcelable {

    public int id_tianguis;
    public String nombre;
    public String ubicacion;

    public String horaInicio;
    public String  horaFinal;
    public String organizador;
    public String estado;

    public clase_tianguis(int id_tianguis, String nombre, String ubicacion, String horaInicio, String horaFinal, String organizador, String estado) {
        this.id_tianguis = id_tianguis;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.organizador = organizador;
        this.estado = estado;
    }

    protected clase_tianguis(Parcel in) {
        id_tianguis = in.readInt();
        nombre = in.readString();
        ubicacion = in.readString();
        horaInicio = in.readString();
        horaFinal = in.readString();
        organizador = in.readString();
        estado = in.readString();
    }

    public static final Creator<clase_tianguis> CREATOR = new Creator<clase_tianguis>() {
        @Override
        public clase_tianguis createFromParcel(Parcel in) {
            return new clase_tianguis(in);
        }

        @Override
        public clase_tianguis[] newArray(int size) {
            return new clase_tianguis[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id_tianguis);
        dest.writeString(nombre);
        dest.writeString(ubicacion);
        dest.writeString(horaInicio);
        dest.writeString(horaFinal);
        dest.writeString(organizador);
        dest.writeString(estado);
    }
}
