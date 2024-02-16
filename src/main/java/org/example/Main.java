package org.example;

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
    }
}