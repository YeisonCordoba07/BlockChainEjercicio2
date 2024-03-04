package org.example;

import static java.lang.Integer.parseInt;

public class Bloque {
    private String bloque;
    private String nonce;
    private String datos;
    private String hashAnterior;
    private String hash;


    public Bloque(){}

    public Bloque(String bloque, String nonce, String datos, String hashAnterior) {
        this.bloque = bloque;
        this.nonce = nonce;
        this.datos = datos;
        this.hashAnterior = hashAnterior;
        this.hash = "x" + this.bloque;
    }


    //CALCULAR HASH ------------------------------------------------------------
    public void calcularHash(){
        this.hash = sha256.calcularSHA256(this.bloque + this.nonce + this.datos + this.hashAnterior);

        Bloque siguienteBloque;

        if(Integer.parseInt(this.bloque) != BlockChain.vectorBC.length-1){
            siguienteBloque = BlockChain.getBloqueBlockChain(parseInt(this.bloque) +1);
            siguienteBloque.setHashAnterior(this.hash);
            System.out.println("SIGUIENTE: "+siguienteBloque.getBloque());
        }
    }


    //GETTERS AND SETTERS ------------------------------------------------------------

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
        this.calcularHash();
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
        this.calcularHash();
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
        this.calcularHash();
    }

    public String getHashAnterior() {
        return hashAnterior;
    }

    public void setHashAnterior(String hashAnterior) {
        this.hashAnterior = hashAnterior;
        this.calcularHash();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
        this.calcularHash();
    }
}




