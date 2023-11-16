package com.example.mycrud.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class clase_empleados implements Parcelable {

    public int id_empleado;
    public String nombre;
    public String apellidoP;
    public String apellidoM;
    public String telefono;
    public int salario;
    public String puesto;
    public String estado;
    public clase_empleados(int id_empleado, String nombre, String apellidoP, String apellidoM, String telefono, int salario, String puesto, String estado) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.telefono = telefono;
        this.salario = salario;
        this.puesto = puesto;
        this.estado = estado;
    }

    protected clase_empleados(Parcel in) {
        id_empleado = in.readInt();
        nombre = in.readString();
        apellidoP = in.readString();
        apellidoM = in.readString();
        telefono = in.readString();
        salario = in.readInt();
        puesto = in.readString();
        estado = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_empleado);
        dest.writeString(nombre);
        dest.writeString(apellidoP);
        dest.writeString(apellidoM);
        dest.writeString(telefono);
        dest.writeInt(salario);
        dest.writeString(puesto);
        dest.writeString(estado);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<clase_empleados> CREATOR = new Creator<clase_empleados>() {
        @Override
        public clase_empleados createFromParcel(Parcel in) {
            return new clase_empleados(in);
        }

        @Override
        public clase_empleados[] newArray(int size) {
            return new clase_empleados[size];
        }
    };

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
