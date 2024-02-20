package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Bloque bloque0 = new Bloque("0", "0", "datos", "0000000000000000000000000000000000000000000000000000000000000000");
    static Bloque bloque1 = new Bloque("1", "0", "datos", "hash anterior");
    static Bloque bloque2 = new Bloque("2", "0", "datos", "hash anterior");
    static Bloque bloque3 = new Bloque("3", "0", "datos", "hash anterior");
    static Bloque bloque4 = new Bloque("4", "0", "datos", "hash anterior");
    static Bloque bloque5 = new Bloque("5", "0", "datos", "hash anterior");


    public static Bloque vectorBC[] = {bloque0, bloque1, bloque2, bloque3, bloque4, bloque5};
    public static void imprimirBlockChain(){

        System.out.println();
        for(int i = 0; i < vectorBC.length; i++){
            System.out.println("#: "+vectorBC[i].getBloque()+" | Nonce: "+vectorBC[i].getNonce()+" | Datos:"+vectorBC[i].getDatos()+" | H: "+vectorBC[i].getHash()+" | HA: "+vectorBC[i].getHashAnterior());
        }
    }





    public static boolean verificarCantidadCeros(String cadena, int cantidadCeros) {
        // Compila la expresión regular para ceros
        Pattern pattern = Pattern.compile("^0{" + cantidadCeros + "}");

        // Crea un matcher para la cadena
        Matcher matcher = pattern.matcher(cadena);

        // Retorna true si la cantidad de ceros coincide
        return matcher.find();
    }

    public static void aumentarNonce(){
        int nuevoNonce = 0;
        int posicion = 0;
        while(posicion <= vectorBC.length -1){
            if(verificarCantidadCeros(vectorBC[posicion].getHash(), 2)){
                posicion = posicion +1;
                nuevoNonce = 0;
            }else{
                vectorBC[posicion].setNonce(String.valueOf(nuevoNonce));
                vectorBC[posicion].calcularHash();
                nuevoNonce = nuevoNonce +1;
            }
        }

    }



    public static Bloque getBloqueBlockChain(int posicion){
        return vectorBC[posicion];
    }





    public static void main(String[] args) {

        System.out.println(vectorBC[2]);
        Bloque a = new Bloque();

        a = getBloqueBlockChain(1);
        System.out.println("A: "+a.getBloque());

        System.out.println(sha256.calcularSHA256("hola"));
        imprimirBlockChain();

        bloque0.setNonce("1");
        bloque3.setNonce("4");
        bloque5.setNonce("9");
        imprimirBlockChain();

        aumentarNonce();
        imprimirBlockChain();




    }
}