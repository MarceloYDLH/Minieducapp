package com.example.minieducapp;


import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    private String rut;
    private String nombre;
    private String correo;
    private String contrasena;
    private String direccion;
    private int telefono;
    private int id_tip;
    private String nom_tip;

    public Usuario() {
    }

    public Usuario(String rut, String nombre, String correo, String contrasena, String direccion, int telefono, int id_tip, String nom_tip) {
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.id_tip = id_tip;
        this.nom_tip = nom_tip;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNom_tip() {
        return nom_tip;
    }

    public void setNom_tip(String nom_tip) {
        this.nom_tip = nom_tip;
    }

    public int getId_tip() {
        return id_tip;
    }

    public void setId_tip(int id_tip) {
        this.id_tip = id_tip;
    }

    @Override
    public String toString() {
        return "" + rut + "     " + nombre;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rut);
        dest.writeString(this.nombre);
        dest.writeString(this.correo);
        dest.writeString(this.contrasena);
        dest.writeString(this.direccion);
        dest.writeInt(this.telefono);
        dest.writeInt(this.id_tip);
        dest.writeString(this.nom_tip);
    }

    protected Usuario(Parcel in) {
        this.rut = in.readString();
        this.nombre = in.readString();
        this.correo = in.readString();
        this.contrasena = in.readString();
        this.direccion = in.readString();
        this.telefono = in.readInt();
        this.id_tip = in.readInt();
        this.nom_tip = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
