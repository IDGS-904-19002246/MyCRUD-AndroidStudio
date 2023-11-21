package com.example.mycrud.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class clase_recetas implements Parcelable {
        public int id_receta;
        public String nombre;
        public String descripcion;
        public int costoAprox;
        public String paisOrigen;
        public String creador;
        public String estado;

        public clase_recetas(int id_receta, String nombre, String descripcion, int costoAprox, String paisOrigen, String creador, String estado) {
                this.id_receta = id_receta;
                this.nombre = nombre;
                this.descripcion = descripcion;
                this.costoAprox = costoAprox;
                this.paisOrigen = paisOrigen;
                this.creador = creador;
                this.estado = estado;
        }

        protected clase_recetas(Parcel in) {
                id_receta = in.readInt();
                nombre = in.readString();
                descripcion = in.readString();
                costoAprox = in.readInt();
                paisOrigen = in.readString();
                creador = in.readString();
                estado = in.readString();
        }

        public static final Creator<clase_recetas> CREATOR = new Creator<clase_recetas>() {
                @Override
                public clase_recetas createFromParcel(Parcel in) {
                        return new clase_recetas(in);
                }

                @Override
                public clase_recetas[] newArray(int size) {
                        return new clase_recetas[size];
                }
        };

        @Override
        public int describeContents() {
                return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
                dest.writeInt(id_receta);
                dest.writeString(nombre);
                dest.writeString(descripcion);
                dest.writeInt(costoAprox);
                dest.writeString(paisOrigen);
                dest.writeString(creador);
                dest.writeString(estado);
        }
}
