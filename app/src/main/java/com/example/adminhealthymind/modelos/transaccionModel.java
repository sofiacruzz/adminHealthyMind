package com.example.adminhealthymind.modelos;

public class transaccionModel {
    private String id_paciente;
    private String hr_cita;
    private String fecha_cita;
    private String rfc_especialista;
    public transaccionModel(String id_paciente, String hr_cita, String fecha_cita, String rfc_especialista) {
        this.id_paciente = id_paciente;
        this.hr_cita = hr_cita;
        this.fecha_cita = fecha_cita;
        this.rfc_especialista = rfc_especialista;
    }
    public transaccionModel(){

    }
    public String getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getHr_cita() {
        return hr_cita;
    }

    public void setHr_cita(String hr_cita) {
        this.hr_cita = hr_cita;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getRfc_especialista() {
        return rfc_especialista;
    }

    public void setRfc_especialista(String rfc_especialista) {
        this.rfc_especialista = rfc_especialista;
    }





}
