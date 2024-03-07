package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Bloque {
    private String bloque;
    private String nonce;
    private String hashAnterior;
    private String hash;
    private Transaccion[] transacciones;
    private boolean estaConfirmado;
    private String rootHash;


    public Bloque() {
    }

    public Bloque(String bloque, String nonce, String hashAnterior) {
        this.bloque = bloque;
        this.nonce = nonce;
        this.hashAnterior = hashAnterior;
        this.hash = "x" + this.bloque;
        this.transacciones = new Transaccion[BlockChain.numeroDeTransaccionesPorBloque];
        this.estaConfirmado = false;
    }




    //CALCULAR HASH ------------------------------------------------------------
    public void calcularHash() {
        this.hash = sha256.calcularSHA256(this.bloque
                + this.nonce
                + this.hashAnterior
                + this.rootHash
                + Arrays.toString(this.transacciones)
        );

        Bloque siguienteBloque;

        if (Integer.parseInt(this.bloque) != BlockChain.vectorBC.length - 1 && estaConfirmado) {
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
                /*if(esTransaccionValida(ListaTransacciones.nuevasTransacciones[i])){
                    this.transacciones[i] = ListaTransacciones.nuevasTransacciones[i];
                    this.transacciones[i].setNumeroBloque(Integer.parseInt(this.bloque));
                }*/

                this.transacciones[i] = ListaTransacciones.nuevasTransacciones[i];
                this.transacciones[i].setNumeroBloque(Integer.parseInt(this.bloque));


            }
        }
        System.out.println("Se agregaron " + BlockChain.numeroDeTransaccionesPorBloque + " [5] transacciones al bloque #" + this.bloque);


        calcularRootHash();
        System.out.println("Se calculó el Root Hash: \033[0;31m" + this.rootHash+"\033[0m");
        //Quita las trasacciones que se agregaron a este bloque
        ListaTransacciones.actualizarNuevasTransacciones();
    }

    public boolean esTransaccionValida(Transaccion tx){
        float saldo = 0;
        String remitente = tx.getRemitente();
        Transaccion[] nuevaTx;
        for(int i = 0; i < BlockChain.vectorBC.length; i++){

            nuevaTx = BlockChain.vectorBC[i].getTransacciones();

            for (Transaccion transaccion : nuevaTx) {
                if(transaccion != null){
                    if (remitente.equalsIgnoreCase(transaccion.getRemitente())) {
                        saldo = saldo - transaccion.getCantidad();
                    }
                    if (remitente.equalsIgnoreCase(transaccion.getDestinatario())) {
                        saldo = saldo + transaccion.getCantidad();
                    }
                }


            }

        }
        System.out.println("LA TRANSACCION ES: saldo: "+saldo);
        return saldo >= tx.getCantidad();
    }



    //CALCULAR ROOT HASH ------------------------------------------------------------
    public void calcularRootHash(){
        ArrayList<String> listaTransacciones = new ArrayList<>();

        for(int i = 0; i <= this.transacciones.length - 1; i++){
            listaTransacciones.add(this.transacciones[i].getHashTransaccion());
        }

        String hashAuxiliar;

        System.out.println("\n-------------HASHES DE TODAS LAS TRANSACCIONES--------------\n");
        for(int j = 0; j <= listaTransacciones.size() -1; j++){
            System.out.println(listaTransacciones.get(j));
        }
        System.out.println();
        for(int i = 0; listaTransacciones.size() > 1; i++){
            if(i + 1 >= listaTransacciones.size()){

                if(!(i >= listaTransacciones.size())){
                    System.out.println("\033[0;35m"+listaTransacciones.get(i)+" --> "+listaTransacciones.get(i)+"\033[0m");
                }
                System.out.println("\n-------------------------------------RESULTADO HASHES--------------------------------------------\n");

                for(int j = 0; j <= listaTransacciones.size() -1; j++){
                    System.out.println(listaTransacciones.get(j));
                }
                System.out.println();
                i = -1;
            }else{
                hashAuxiliar = sha256.calcularSHA256(listaTransacciones.get(i) + listaTransacciones.get(i+1));
                System.out.println("\033[0;36m"+listaTransacciones.get(i)+" + "+listaTransacciones.get(i+1) +" --> "+hashAuxiliar+"\033[0m");
                listaTransacciones.set(i, hashAuxiliar);
                listaTransacciones.remove(i+1);
            }

            //System.out.println("ACABA UNA RONDA I CON VALOR: "+i);

        }
        this.rootHash = listaTransacciones.get(0);
        calcularHash();
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

    public String getRootHash() {
        return rootHash;
    }

    public void setRootHash(String rootHash) {
        this.rootHash = rootHash;
        calcularHash();
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




