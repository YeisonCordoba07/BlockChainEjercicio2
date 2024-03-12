package org.example;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockChain {

    public static int dificultad = 1;
    public static int numeroDeTransaccionesPorBloque = 5;



    //Creacion de Bloques iniciales -----------------------------------------------------------------------------------
    static Bloque bloque0 = new Bloque("0", "0", "0000000000000000000000000000000000000000000000000000000000000000");
    static Bloque bloque1 = new Bloque("1", "0",  "hash anterior");
    static Bloque bloque2 = new Bloque("2", "0",  "hash anterior");
    static Bloque bloque3 = new Bloque("3", "0",  "hash anterior");
    static Bloque bloque4 = new Bloque("4", "0",  "hash anterior");
    static Bloque bloque5 = new Bloque("5", "0",  "hash anterior");




    //Creacion de BlockChain -------------------------------------------------------------------------------------------
    public static Bloque[] vectorBC = {bloque0, bloque1, bloque2, bloque3, bloque4, bloque5};




    //FUNCIONES --------------------------------------------------------------------------------------------------------

    public static void imprimirBlockChain() {

        System.out.println("\n\n\033[0;34m═════════════════════════════════════════════════════════| BLOCKCHAIN |════════════════════════════════════════════════════════════════════════════════════\033[0m\n");

        for (Bloque bloque : vectorBC) {
            System.out.println("#: " + bloque.getBloque() + " | Nonce: " + bloque.getNonce() + " | H: " + bloque.getHash() + " | HA: " + bloque.getHashAnterior());
        }
        System.out.println("\n\033[0;34m══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\033[0m\n\n");
    }




    //OBTENER UN BLOQUE DE LA BLOCKCHAIN -------------------------------------------------------------------------------

    public static Bloque getBloqueBlockChain(int posicion) {
        return vectorBC[posicion];
    }




    //VERIFICAR CANTIDAD DE CEROS DEL HASH------------------------------------------------------------------------------

    public static boolean tieneCantidadDeCeros(String cadena, int cantidadCeros) {
        Pattern pattern = Pattern.compile("^0{" + cantidadCeros + "}");
        Matcher matcher = pattern.matcher(cadena);

        // Retorna true si la cantidad de ceros es igual a cantidadCeros
        return matcher.find();
    }




    //CALCULA LA PRUEBA DE TRABAJO PARA TODOS LOS BLOQUES DE LA BLOCKCHAIN----------------------------------------------

    public static void pruebaDeTrabajoEspecial() {
        int nuevoNonce = 0;
        int posicion = 0;

        while (posicion <= vectorBC.length - 1) {
            if (tieneCantidadDeCeros(vectorBC[posicion].getHash(), dificultad)) {
                posicion = posicion + 1;
                nuevoNonce = 0;
            } else {
                vectorBC[posicion].setNonce(String.valueOf(nuevoNonce));
                //vectorBC[posicion].calcularHash();
                nuevoNonce = nuevoNonce + 1;
            }
        }
    }




    //CALCULA LA PRUEBA DE TRABAJO PARA UN BLOQUE ----------------------------------------------------------------------

    public static void pruebaDeTrabajo(Bloque bloque) {
        int nuevoNonce = 0;

        //Hallar el numero de ceros
        while (!tieneCantidadDeCeros(bloque.getHash(), BlockChain.dificultad)) {

            bloque.setNonce(String.valueOf(nuevoNonce));
            nuevoNonce = nuevoNonce + 1;
        }
    }




    //AGREGAR BLOQUE A LA BLOCKCHAIN -----------------------------------------------------------------------------------
    public static void agregarBloque(Bloque nuevoBloque){
        if(!nuevoBloque.getEstaConfirmado()) {
            Bloque[] nuevoVectorBC = new Bloque[vectorBC.length + 1];

            //Copia el array vectorBC en nuevoVectorBC
            System.arraycopy(vectorBC, 0, nuevoVectorBC, 0, vectorBC.length);
            nuevoVectorBC[vectorBC.length] = nuevoBloque;
            vectorBC = nuevoVectorBC;
            nuevoBloque.setEstaConfirmado(true);
        }
    }




    //MINAR ------------------------------------------------------------------------------------------------------------

    public static void minar(Bloque bloque){

        //Imprimir en la consola
        System.out.println("\n\n\033[0;35m═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\033[0m");
        System.out.println("\033[0;35m══════════════════════════════════════════════════════════| BLOQUE "+bloque.getBloque()+" |═══════════════════════════════════════════════════════════════════════\033[0m");
        System.out.println("\033[0;35m═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\033[0m\n");


        //Agregar transacciones a bloque
        bloque.agregarTransacciones();

        //Agrega el hash del ultimo bloque al bloque actual
        if(Integer.parseInt( bloque.getBloque() ) > 0){

            //Obtiene el hash del bloque anterior y lo agrega al atributo hashAnterior
            bloque.setHashAnterior(String.valueOf( BlockChain.getBloqueBlockChain(  Integer.parseInt(bloque.getBloque()) -1 ).getHash() ));

        }else{

            //Agrega al primer bloque el siguente hash
            bloque.setHashAnterior("0000000000");

        }

        //Calcular hash con numero de ceros
        pruebaDeTrabajo(bloque);

        //Agrega bloque a la BlockChain
        agregarBloque(bloque);



        /*
        System.out.println("\033[0;35m═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\033[0m");
        System.out.println("\033[0;35m═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\033[0m");
        System.out.println("\033[0;35m═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\033[0m\n");
        */
    }




    //IMPRIMIR TRANSACCIONES ------------------------------------------------------------
    public static void imprimirTransacciones(ArrayList<Transaccion> transacciones){

        System.out.println("\n\033[0;33m════════════════════════════════════| TRANSACCIONES B"+transacciones.get(0).getNumeroBloque()+" |═════════════════════════════════════\033[0m\n");
        for (Transaccion transaccione : transacciones) {
            if (transaccione != null) {
                System.out.println("TX" + transaccione.getNumeroTransaccion() + ": " + transaccione.getHashTransaccion());
            } else {
                System.out.println("TX NULLO");
            }
        }
        System.out.println("\n\033[0;33m═════════════════════════════════════════════════════════════════════════════════════════════\033[0m\n");
    }
}


