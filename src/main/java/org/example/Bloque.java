package org.example;


import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Bloque {
    private String bloque;
    private String nonce;
    private String hashAnterior;
    private String hash;
    private ArrayList<Transaccion> transacciones;
    private boolean estaConfirmado;
    private String rootHash;


    public Bloque() {
    }

    public Bloque(String bloque, String nonce, String hashAnterior) {
        this.bloque = bloque;
        this.nonce = nonce;
        this.hashAnterior = hashAnterior;
        this.hash = "x" + this.bloque;
        this.transacciones =  new ArrayList<>();
        this.estaConfirmado = false;
    }




    //CALCULAR HASH ----------------------------------------------------------------------------------------------------

    public void calcularHash() {

        //Calcula el hash
        this.hash = sha256.calcularSHA256(this.bloque
                + this.nonce
                + this.hashAnterior
                + this.rootHash
                + this.transacciones
        );

        Bloque siguienteBloque;

        //Le pasa al siguiente bloque en la BlockChain el hash calculado
        if (Integer.parseInt(this.bloque) != BlockChain.vectorBC.length - 1 && estaConfirmado) {
            siguienteBloque = BlockChain.getBloqueBlockChain(parseInt(this.bloque) + 1);
            siguienteBloque.setHashAnterior(this.hash);
        }
    }




    //AGREGAR TRANSACCIONES --------------------------------------------------------------------------------------------

    /*Agrega un numero de transacciones a este bloque
    y después las elimina de del Array nuevasTransacciones*/
    public void agregarTransacciones() {

        //Numero de transacciones que se agregarán
        int contadorTransacciones = BlockChain.numeroDeTransaccionesPorBloque;

        //Agrega transacciones a la lista de transacciones del bloque
        for (int i = 0; i < contadorTransacciones; i++) {
            if (ListaTransacciones.nuevasTransacciones != null) {

                //Le agrega a a transaccion actual el numero del bloque al que será agregado
                ListaTransacciones.nuevasTransacciones[i].setNumeroBloque(Integer.parseInt(this.bloque));

                //Si la transaccion es valida, se agrega al bloque, sino no se agrega y pasa a la siguiente
                if(esTransaccionValida(ListaTransacciones.nuevasTransacciones[i])){

                    this.transacciones.add(ListaTransacciones.nuevasTransacciones[i]);
                }else{
                    ListaTransacciones.nuevasTransacciones[i].setNumeroBloque(-1);
                    //Aumenta el numero de transacciones para que siga agregando 5
                    contadorTransacciones++;
                }

            }

        }
        System.out.println("\nSe agregaron " + contadorTransacciones+" transacciones al bloque #" + this.bloque);

        calcularRootHash();
        System.out.println("Se calculó el Root Hash: \033[0;31m" + this.rootHash+"\033[0m");

        //Quita las trasacciones que se agregaron a este bloque
        ListaTransacciones.actualizarNuevasTransacciones(contadorTransacciones);
    }




    //VERIFICAR TRANSACCIONES ------------------------------------------------------------------------------------------

    //Busca todas las transacciones donde haya participado el remitente
    // para verificar que si tenga el saldo que va a enviar
    public boolean esTransaccionValida(Transaccion tx) {

        //Se asume que las transacciones en los primeros 5 bloques están correctas
        if (tx.getNumeroBloque() <= 5) {
            System.out.println("TRANSACCION VALIDA");
            return true;

        } else {
            //A saldo se le aplicarán las transacciones domde haya participado el remitente
            float saldo = 0;

            //Guarda el nombre del remitente
            String remitente = tx.getRemitente();

            //Lista para almacenar las transacciones de cada bloque de la blockchain
            ArrayList<Transaccion> nuevaTx;

            //Itera entre todos los bloques de la blockchain para extraer
            for (int i = 0; i < BlockChain.vectorBC.length; i++) {

                //Guarda todas las transacciones del bloque actual
                nuevaTx = BlockChain.vectorBC[i].getTransacciones();

                //Itera sobre todas las transacciones del bloque actual
                for (Transaccion transaccion : nuevaTx) {
                    if (transaccion != null) {

                        // Si el remitente de la transacción coincide con el remitente de
                        // la transacción actual, resta la cantidad de la transacción al saldo
                        if (remitente.equalsIgnoreCase(transaccion.getRemitente())) {
                            saldo = saldo - transaccion.getCantidad();
                        }

                        // Si el remitente de la transacción coincide con el destinatario de
                        // la transacción actual, suma la cantidad de la transacción al saldo
                        if (remitente.equalsIgnoreCase(transaccion.getDestinatario())) {
                            saldo = saldo + transaccion.getCantidad();
                        }
                    }

                }

            }

            //Si el saldo calculado es mayor o igual a la cantidad que se queire enviar
            // entonces la transacción es valida, sino no
            if(saldo >= tx.getCantidad()){
                System.out.printf("\u001B[32mTRANSACCION VALIDA: %s tiene %.2f y manda a %s %.2f%n\u001B[0m",
                        tx.getRemitente(),
                        saldo,
                        tx.getDestinatario(),
                        tx.getCantidad());
                return true;
            }else{
                System.out.printf("\u001B[31mTRANSACCION INVALIDA: %s tiene %.2f y manda a %s %.2f%n\u001B[0m",
                        tx.getRemitente(),
                        saldo,
                        tx.getDestinatario(),
                        tx.getCantidad());
                return false;
            }
        }

    }




    //CALCULAR ROOT HASH -----------------------------------------------------------------------------------------------

    public void calcularRootHash(){
        ArrayList<String> listaTransacciones = new ArrayList<>();
        if(this.transacciones.size() > 0) {

            //Guarda en listaTransacciones los hashes de todas las transacciones del bloque
            for (int i = 0; i <= this.transacciones.size() - 1; i++) {
                listaTransacciones.add(this.transacciones.get(i).getHashTransaccion());
            }

            String hashAuxiliar;

            System.out.println("\n-----------------------CALCULANDO EL ROOT HASH------------------------\n");

            //Imprime los hashes de las transacciones
            for (int j = 0; j <= listaTransacciones.size() - 1; j++) {
                System.out.println(listaTransacciones.get(j));
            }
            System.out.println();

            //Itera sobre listaTransacciones hasta que solo quede un hash en la lista (el hash raíz)
            for (int i = 0; listaTransacciones.size() > 1; i++) {

                if (i + 1 >= listaTransacciones.size()) {
                    if (!(i >= listaTransacciones.size())) {
                        System.out.println("\033[0;35m" + listaTransacciones.get(i) + " --> " + listaTransacciones.get(i) + "\033[0m");
                    }
                    System.out.println("\n-------------------------------------SIGUIENTE NIVEL--------------------------------------------\n");

                    //Imprime los hashes de las transacciones hasta el momento
                    for (int j = 0; j <= listaTransacciones.size() - 1; j++) {
                        System.out.println(listaTransacciones.get(j));
                    }
                    System.out.println();
                    //Reinicia el contador para recorrer otra vez la lista
                    i = -1;
                } else {
                    //Calcula el hash combinado las dos transacciones consecutivas (i + (i+1))
                    hashAuxiliar = sha256.calcularSHA256(listaTransacciones.get(i) + listaTransacciones.get(i + 1));
                    System.out.println("\033[0;36m" + listaTransacciones.get(i) + " + " + listaTransacciones.get(i + 1) + " --> " + hashAuxiliar + "\033[0m");
                    //Actualiza el hash en la lista por el calculado (i)
                    listaTransacciones.set(i, hashAuxiliar);
                    //Elimina el siguiente hash (i+1)
                    listaTransacciones.remove(i + 1);
                }
            }
            // Asigna el hash raíz calculado (root hash) al bloque
            this.rootHash = listaTransacciones.get(0);

        }else{
            // Si no hay transacciones en el bloque, asigna un valor predeterminado al root hash
            this.rootHash = "X";
        }

        //Como el root hash se actualizó, debe calcularse nuevamente el hash del bloque
        calcularHash();
    }




    //GETTERS AND SETTERS ----------------------------------------------------------------------------------------------

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
    public ArrayList<Transaccion> getTransacciones() {
        return this.transacciones;
    }


    public boolean getEstaConfirmado() {
        return estaConfirmado;
    }

    public void setEstaConfirmado(boolean estaConfirmado) {
        this.estaConfirmado = estaConfirmado;
    }
}




