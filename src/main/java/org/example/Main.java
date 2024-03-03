package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        BlockChain.imprimirBlockChain();

        BlockChain.bloque0.setNonce("1");
        BlockChain.bloque3.setNonce("4");
        BlockChain.bloque5.setNonce("9");

        BlockChain.imprimirBlockChain();
        BlockChain.aumentarNonce();
        BlockChain.imprimirBlockChain();


    }
}