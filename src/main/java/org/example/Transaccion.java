package org.example;

public class Transaccion {
    private String hashTransaccion;
    private String remitente;
    private String destinatario;
    private float cantidad;
    private float comision;

    public Transaccion(){

    }
    public Transaccion( String remitente, String destinatario, float cantidad, float comision) {
        this.hashTransaccion = remitente + destinatario;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.cantidad = cantidad;
        this.comision = comision;
    }

    public void calcularHash(){
        this.hashTransaccion = sha256.calcularSHA256(
    this.remitente +
            this.destinatario +
            this.cantidad +
            this.comision
        );
    }






    //GETTERS AND SETTERS ------------------------------------------------------------

    public String getHashTransaccion() {
        return hashTransaccion;
    }

    public void setHashTransaccion(String hashTransaccion) {
        this.hashTransaccion = hashTransaccion;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }
}
