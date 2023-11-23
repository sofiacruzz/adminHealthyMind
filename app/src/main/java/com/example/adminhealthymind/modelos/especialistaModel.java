package com.example.adminhealthymind.modelos;

public class especialistaModel {
    private String email;
    private String apellido;
    private String especialidad;
    private String fechanac;
    private String foto;
    private String nombres;
    private String rfc;
    private String telefono;

    public especialistaModel(String email, String apellido, String especialidad, String fechanac, String foto, String nombres, String rfc, String telefono) {
        this.email = email;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.fechanac = fechanac;
        this.foto = foto;
        this.nombres = nombres;
        this.rfc = rfc;
        this.telefono = telefono;
    }
    public especialistaModel(){

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
