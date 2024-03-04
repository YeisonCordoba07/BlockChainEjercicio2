package org.example;

public class ListaTransacciones {

    static Transaccion transaccion01 = new Transaccion("BTC", "Alejo", 100, 0, 1);
    static Transaccion transaccion02 = new Transaccion("BTC", "Camila", 100, 0, 2);
    static Transaccion transaccion03 = new Transaccion("BTC", "Diego", 100, 0, 3);
    static Transaccion transaccion04 = new Transaccion("BTC", "Nekko", 100, 0, 4);
    static Transaccion transaccion05 = new Transaccion("BTC", "Vi", 100, 0, 5);
    static Transaccion transaccion06 = new Transaccion("BTC", "Victoria", 100, 0, 6);
    static Transaccion transaccion07 = new Transaccion("BTC", "Sara", 100, 0, 7);
    static Transaccion transaccion08 = new Transaccion("BTC", "Yeison", 100, 0, 8);



    static Transaccion transaccion0 = new Transaccion("Yeison", "Vi", 5, 0, 9);
    static Transaccion transaccion1 = new Transaccion("Alejo", "Sara", 15, 3, 10);
    static Transaccion transaccion2 = new Transaccion("Nekko", "Camila", 7, 6, 11);
    static Transaccion transaccion3 = new Transaccion("Yeison", "Victoria", 6, 1, 12);
    static Transaccion transaccion4 = new Transaccion("Yeison", "Vicky", 50, 15, 13);
    static Transaccion transaccion5 = new Transaccion("Camila", "Yeison", 35, 20, 14);
    static Transaccion transaccion6 = new Transaccion("Yeison", "Nekko", 25, 10, 15);


    public static Transaccion vectorTX[] = {
            transaccion01, transaccion02, transaccion03, transaccion04,
            transaccion05, transaccion06, transaccion07, transaccion08,
            transaccion0, transaccion1, transaccion2, transaccion3,
            transaccion4, transaccion5, transaccion6
    };

    public static Transaccion[] nuevasTransacciones = new Transaccion[50];

    public static Transaccion getTransaccion(int posicion){
        return vectorTX[posicion];
    }


    public static void actualizarNuevasTransacciones(){
        if (ListaTransacciones.nuevasTransacciones.length >= BlockChain.numeroDeTransaccionesPorBloque) {
            // Crea un nuevo array con menor tamaño
            Transaccion[] transaccionesReducidas =
                    new Transaccion[
                            ListaTransacciones.nuevasTransacciones.length
                                    - BlockChain.numeroDeTransaccionesPorBloque
                            ];

            // Copia las transacciones restantes al nuevo array
            //System.arraycopy(
            //TIENE CINCO PARAMETROS
            // P1: VectorOriginal de donde vamos a copiar los datos [nuevasTransacciones],
            // P2: Posicion desde donde se empieza a copiar en el VectorOriginal [5],
            // P3: Vector donde se copiarán los datos [transaccionesReducidas],
            // P4: Posicion desde donde se copiará en el NuevoVector [0]
            // P5: Numero de elementos que se deben copiar [tamaño del vectorOriginal -5])
            System.arraycopy(ListaTransacciones.nuevasTransacciones, BlockChain.numeroDeTransaccionesPorBloque, transaccionesReducidas, 0, transaccionesReducidas.length);

            // Asigna el nuevo vector al vector original
            ListaTransacciones.nuevasTransacciones = transaccionesReducidas;
            System.out.println("Transacciones eliminadas exitosamente");
            System.out.println("Se imprime nuevas transaciones:"+nuevasTransacciones);
        } else {
            System.out.println("No hay suficientes transacciones para eliminar.");
        }
    }
}
