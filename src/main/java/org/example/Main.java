package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        System.out.println(BlockChain.vectorBC[2]);
        Bloque a = new Bloque();

        a = BlockChain.getBloqueBlockChain(1);
        System.out.println("A: "+a.getBloque());

        System.out.println(sha256.calcularSHA256("hola"));
        BlockChain.imprimirBlockChain();

        BlockChain.bloque0.setNonce("1");
        BlockChain.bloque3.setNonce("4");
        BlockChain.bloque5.setNonce("9");
        BlockChain.imprimirBlockChain();

        BlockChain.aumentarNonce();
        BlockChain.imprimirBlockChain();


    }
}