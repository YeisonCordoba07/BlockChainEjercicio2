package org.example;

import java.sql.SQLOutput;

import static java.lang.Integer.parseInt;

public class Bloque {
    private String bloque;
    private String nonce;
    private String datos;
    private String hashAnterior;
    private String hash;
    private Transaccion transacciones[];
    private boolean estaConfirmado;


    public Bloque() {
    }

    public Bloque(String bloque, String nonce, String datos, String hashAnterior) {
        this.bloque = bloque;
        this.nonce = nonce;
        this.datos = datos;
        this.hashAnterior = hashAnterior;
        this.hash = "x" + this.bloque;
        this.transacciones = new Transaccion[BlockChain.numeroDeTransaccionesPorBloque];
        this.estaConfirmado = false;
    }




    //CALCULAR HASH ------------------------------------------------------------
    public void calcularHash() {
        this.hash = sha256.calcularSHA256(this.bloque
                + this.nonce
                + this.datos
                + this.hashAnterior
                + this.transacciones
        );

        Bloque siguienteBloque;

        if (Integer.parseInt(this.bloque) != BlockChain.vectorBC.length - 1 && estaConfirmado == true) {
            siguienteBloque = BlockChain.getBloqueBlockChain(parseInt(this.bloque) + 1);
            siguienteBloque.setHashAnterior(this.hash);
            //System.out.println("SIGUIENTE: "+siguienteBloque.getBloque());
        }
    }




    //AGREGAR TRANSACCIONES ------------------------------------------------------------
    /*Agrega un numero de transacciones a este bloque
    y después las elimina de las transacciones sin confirmar*/
    public void agregarTransacciones() {
        for (int i = 0; i < this.transacciones.length; i++) {
            if (ListaTransacciones.nuevasTransacciones != null) {
                this.transacciones[i] = ListaTransacciones.nuevasTransacciones[i];
            }
        }
        System.out.println("Se agregaron " + BlockChain.numeroDeTransaccionesPorBloque + " [5] transacciones al bloque #" + this.bloque);
        //Quita las trasacciones que se agregaron a este bloque
        ListaTransacciones.actualizarNuevasTransacciones();
    }




    //GETTERS AND SETTERS ------------------------------------------------------------

    public String getBloque() {
        return bloque;
    }
    //-------------------------------
    public void setBloque(String bloque) {
        this.bloque = bloque;
        this.calcularHash();
    }


    //-------------------------------
    public String getNonce() {
        return nonce;
    }
    //-------------------------------
    public void setNonce(String nonce) {
        this.nonce = nonce;
        this.calcularHash();
    }


    //-------------------------------
    public String getDatos() {
        return datos;
    }
    //-------------------------------
    public void setDatos(String datos) {
        this.datos = datos;
        this.calcularHash();
    }


    //-------------------------------
    public String getHashAnterior() {
        return hashAnterior;
    }
    //-------------------------------
    public void setHashAnterior(String hashAnterior) {
        this.hashAnterior = hashAnterior;
        this.calcularHash();
    }


    //-------------------------------
    public String getHash() {
        return hash;
    }
    //-------------------------------
    public void setHash(String hash) {
        this.hash = hash;
        this.calcularHash();
    }


    //-------------------------------
    public Transaccion[] getTransacciones() {
        return transacciones;
    }


    public boolean getEstaConfirmado() {
        return estaConfirmado;
    }

    public void setEstaConfirmado(boolean estaConfirmado) {
        this.estaConfirmado = estaConfirmado;
    }
}




