package org.example;

public class Transaccion {
    private String hashTransaccion;
    private String remitente;
    private String destinatario;
    private float cantidad;
    private float comision;
    private int numeroTransaccion;

    public Transaccion(){}

    public Transaccion( String remitente, String destinatario, float cantidad, float comision, int numeroTransaccion) {
        this.hashTransaccion = remitente + destinatario;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.cantidad = cantidad;
        this.comision = comision;
        this.numeroTransaccion = numeroTransaccion;
    }




    //CALCULAR HASH------------------------------------------------------------
    public void calcularHash(){
        this.hashTransaccion = sha256.calcularSHA256(
            this.remitente +
            this.destinatario +
            this.cantidad +
            this.comision
        );

//        Transaccion siguienteTransaccion;
//        if(this.numeroTransaccion != ListaTransacciones.vectorTX.length -1){
//            siguienteTransaccion = ListaTransacciones.getTransaccion(this.numeroTransaccion + 1);
//        }
    }




    //GETTERS AND SETTERS ------------------------------------------------------------

    public String getHashTransaccion() {
        return hashTransaccion;
    }


    //-------------------------------
    public String getRemitente() {
        return remitente;
    }
    //-------------------------------
    public void setRemitente(String remitente) {
        this.remitente = remitente;
        calcularHash();
    }


    //-------------------------------
    public String getDestinatario() {
        return destinatario;
    }
    //-------------------------------
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
        calcularHash();
    }


    //-------------------------------
    public float getCantidad() {
        return cantidad;
    }
    //-------------------------------
    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
        calcularHash();
    }


    //-------------------------------
    public float getComision() {
        return comision;
    }
    //-------------------------------
    public void setComision(float comision) {
        this.comision = comision;
        calcularHash();
    }


    //-------------------------------
    public int getNumeroTransaccion(){
        return numeroTransaccion;
    }
    //-------------------------------
    public void setNumeroTransaccion(int numeroTransaccion){
        this.numeroTransaccion = numeroTransaccion;
    }
}
