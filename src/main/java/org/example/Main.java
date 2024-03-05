package org.example;

public class Main {

    public static void main(String[] args) {

        BlockChain.imprimirBlockChain();
        BlockChain.bloque0.setEstaConfirmado(true);
        BlockChain.bloque1.setEstaConfirmado(true);
        BlockChain.bloque2.setEstaConfirmado(true);
        BlockChain.bloque3.setEstaConfirmado(true);
        BlockChain.bloque4.setEstaConfirmado(true);
        BlockChain.bloque5.setEstaConfirmado(true);



        Bloque bloque10 = new Bloque("6", "0", "datos", "hash anterior");
        BlockChain.imprimirBlockChain();
        //BlockChain.bloque2.imprimirTransacciones();


        //BlockChain.pruebaDeTrabajo(BlockChain.vectorBC[5]);
        BlockChain.pruebaDeTrabajoEspecial();
        BlockChain.minar(bloque10);
        //bloque10.imprimirTransacciones();
        BlockChain.imprimirBlockChain();


    }
}