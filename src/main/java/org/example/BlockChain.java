package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockChain {



    //Creacion de Bloques -----------------------------------------------------------------------------------
    static Bloque bloque0 = new Bloque("0", "0", "datos", "0000000000000000000000000000000000000000000000000000000000000000");
    static Bloque bloque1 = new Bloque("1", "0", "datos", "hash anterior");
    static Bloque bloque2 = new Bloque("2", "0", "datos", "hash anterior");
    static Bloque bloque3 = new Bloque("3", "0", "datos", "hash anterior");
    static Bloque bloque4 = new Bloque("4", "0", "datos", "hash anterior");
    static Bloque bloque5 = new Bloque("5", "0", "datos", "hash anterior");




    //Creacion de BlockChain -----------------------------------------------------------------------------------
    public static Bloque vectorBC[] = {bloque0, bloque1, bloque2, bloque3, bloque4, bloque5};




    //FUNCIONES -----------------------------------------------------------------------------------

    public static void imprimirBlockChain() {

        System.out.println();
        for (int i = 0; i < vectorBC.length; i++) {
            System.out.println("#: " + vectorBC[i].getBloque() + " | Nonce: " + vectorBC[i].getNonce() + " | Datos:" + vectorBC[i].getDatos() + " | H: " + vectorBC[i].getHash() + " | HA: " + vectorBC[i].getHashAnterior());
        }
    }


    //-----------------------------------------------------------------------------------
    public static Bloque getBloqueBlockChain(int posicion) {
        return vectorBC[posicion];
    }


    //-----------------------------------------------------------------------------------
    public static boolean verificarCantidadCeros(String cadena, int cantidadCeros) {
        Pattern pattern = Pattern.compile("^0{" + cantidadCeros + "}");

        Matcher matcher = pattern.matcher(cadena);

        // Retorna true si la cantidad de ceros es igual a cantidadCeros
        return matcher.find();
    }


    //-----------------------------------------------------------------------------------
    public static void aumentarNonce() {
        int nuevoNonce = 0;
        int posicion = 0;

        while (posicion <= vectorBC.length - 1) {
            if (verificarCantidadCeros(vectorBC[posicion].getHash(), 2)) {
                posicion = posicion + 1;
                nuevoNonce = 0;
            } else {
                vectorBC[posicion].setNonce(String.valueOf(nuevoNonce));
                //vectorBC[posicion].calcularHash();
                nuevoNonce = nuevoNonce + 1;
            }
        }
    }

}
