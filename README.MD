# Información del Proyecto
Este proyecto es una implementación básica de una blockchain en Java, diseñada como parte del curso de Blockchain.


------------

# Clase Transaccion

La clase `Transaccion` en este proyecto representa una transacción dentro de un sistema de blockchain. Aquí hay una explicación más detallada de sus componentes y funcionalidades:

### Componentes de la clase `Transaccion`:

1. **Atributos privados:**
   - `hashTransaccion`: Almacena el hash único de la transacción, calculado a partir de otros atributos de la transacción.
   - `remitente`: Representa el remitente de la transacción.
   - `destinatario`: Representa el destinatario de la transacción.
   - `cantidad`: Indica la cantidad de la criptomoneda (o cualquier otro activo) que se está transfiriendo en la transacción.
   - `comision`: Almacena la comisión asociada a la transacción.
   - `numeroTransaccion`: Identificador único de la transacción.
   - `numeroBloque`: Número del bloque al que pertenece la transacción.

2. **Constructor:**
   - Se proporciona un constructor que inicializa los atributos de la transacción, como el remitente, destinatario, cantidad, comisión y número de transacción. Además, calcula automáticamente el hash de la transacción.

3. **Métodos:**

   - `calcularHash()`: Este método calcula el hash de la transacción utilizando el algoritmo SHA-256. El hash se genera a partir de la concatenación de los datos relevantes de la transacción, como el remitente, destinatario, cantidad, comisión y número de transacción.

   - `Getters y Setters`: Se proporcionan métodos para acceder y modificar los atributos de la transacción de manera segura.

### Funcionalidades de la clase `Transaccion`:

- **Generación de hash único**: Cada instancia de la clase `Transaccion` genera un hash único que representa la transacción. Esto garantiza la integridad de la transacción y su identificación única en la blockchain.

- **Mantenimiento de datos de transacción**: La clase almacena todos los detalles relevantes de una transacción, incluidos remitente, destinatario, cantidad, comisión, número de transacción y número de bloque.



- **Cálculo automático del hash**: Cada vez que se modifican los atributos de la transacción mediante los métodos setters, se recalcula automáticamente el hash de la transacción. Esto asegura que el hash esté siempre actualizado y refleje con precisión el estado actual de la transacción.

- **Identificación única**: Cada transacción se identifica de forma única mediante su número de transacción, lo que facilita su seguimiento y referencia en la blockchain.




La clase `ListaTransacciones` parece ser una estructura que almacena una lista estática de transacciones. A continuación, proporcionaré una descripción más detallada de la clase y cómo se utiliza en el proyecto.

------------


#  Clase ListaTransacciones

La clase `ListaTransacciones` es una clase que contiene una serie de transacciones predefinidas que se utilizan en la simulación de una cadena de bloques. La lista de transacciones se organiza en un array, donde cada elemento del array es una instancia de la clase `Transaccion`.

#### Atributos

- **`nuevasTransacciones: Transaccion[]`**: Este atributo es un array que almacena las transacciones.

- **`transaccionXX: Transaccion`** (donde "XX" es un número): Estos atributos representan instancias individuales de la clase `Transaccion`. Cada transacción se crea y se asigna a un atributo específico en la clase `ListaTransacciones`.

#### Métodos

- **Métodos de asignación**: En el código, en el método `main` de la clase `Main`, se asignan instancias de `Transaccion` a los elementos del array `nuevasTransacciones` utilizando una notación de índice. Por ejemplo:
  
  ```java
  ListaTransacciones.nuevasTransacciones[0] = ListaTransacciones.transaccion00;
  ```

  Esto asigna la transacción `transaccion00` al primer elemento del array `nuevasTransacciones`.

- **Uso en el proyecto**: Esta clase se utiliza para predefinir un conjunto de transacciones que se agregarán a los bloques de la Blockchain durante la ejecución del proyecto. Estas transacciones se utilizan para simular datos en la Blockchain.





------------


# Clase Bloque

#### Atributos

- `numeroBloque`: Un identificador único que indica la posición del bloque en la cadena de bloques.
- `nonce`: El número que se cambiará para hallar el hash.
- `hashAnterior`: Es el hash del bloque anterior en la cadena.
- `roothash`: El hash de la raíz del árbol Merkle, que representa todas las transacciones incluidas en el bloque.
- `estaConfirmado`: Un indicador booleano que indica si el bloque ha sido confirmado en la cadena de bloques.

#### Transacciones

- `transacciones`: Una lista que contiene las transacciones incluidas en el bloque.

#### Constructor

- `Bloque(String numeroBloque, String nonce, String hashAnterior)`: Un constructor que inicializa un bloque con el número de bloque, el nonce y el hash del bloque anterior.

#### Métodos

- `agregarTransaccion(Transaccion transaccion)`: Agrega una transacción al bloque.
- `esTransaccionValida(Transaccion transaccion)`: Verifica que el remitente tenga la cantidad de dinero que desea enviar.
- `calcularRootHash()`: Calcula el hash de la raíz del árbol Merkle a partir de las transacciones incluidas en el bloque.
- `getters` y `setters`: Métodos para acceder y modificar los atributos del bloque.

#### Funcionalidad principal

- **Agregación de transacciones**: La clase `Bloque` permite la adición de transacciones mediante el método `agregarTransaccion`, lo que permite construir el contenido del bloque.
  
- **Cálculo del roothash**: El método `calcularRootHash` se encarga de calcular el hash de la raíz del árbol Merkle a partir de las transacciones incluidas en el bloque. Este hash se utiliza para garantizar la integridad de las transacciones contenidas en el bloque.

- **Interacción con la cadena de bloques**: La clase `Bloque` es esencial para la estructura y la funcionalidad de la cadena de bloques en su conjunto. Los bloques contienen transacciones, se enlazan mediante hashes y se utilizan en el proceso de minado para garantizar la seguridad y la integridad de la cadena de bloques.




------------


# Clase Blockchain

La clase `Blockchain` es el núcleo del proyecto, ya que se encarga de gestionar la cadena de bloques, que es la base de una red blockchain. Aquí se implementan varias funcionalidades esenciales, como la minería de bloques, la verificación de la integridad de la cadena y la gestión de las transacciones.

### Atributos

- **`dificultad`**: Define la dificultad del algoritmo de prueba de trabajo utilizado para la minería de nuevos bloques.
- **`numeroDeTransaccionesPorBloque`**: Contiene el número de transacciones que se agregarán a los bloques, por defecto está en 5.
- **`bloqueX`**: Bloques iniciales de la Blockchain.
- **`vectorBC[]`**: Array que contendrá todos los bloques agregados a la Blockchain.

### Métodos

- **`minar(Bloque bloque)`**: Este método se encarga de minar un nuevo bloque. Toma como argumento un objeto de tipo `Bloque` y realiza los siguientes pasos:

  - Agrega al bloque las primeras 5 transacciones del Array `nuevasTransacciones` y luego de agregarlas, las elimina del Array.
  - Agrega el hash del bloque anterior y lo utiliza para calcular el hash del nuevo bloque.
  - Realiza la prueba de trabajo para encontrar un hash que cumpla con la dificultad de minado establecida.
  - Una vez encontrado el hash válido, agrega el nuevo bloque a la cadena.



- **`imprimirBlockchain()`**: Imprime en la consola la cadena de bloques completa, incluyendo la información de cada bloque y sus transacciones.

- **`imprimirTransacciones(Transaccion[] transacciones)`**: Método auxiliar para imprimir las transacciones de un bloque específico.

- **`pruebaDeTrabajoEspecial()`**: Hace la prueba de trabajo con todos los bloques de la Blockchain.

- **`pruebaDeTrabajo(Bloque bloque)`**: Hace la prueba de trabajo para el bloque pasado como parametro.

- **`agregarBloque(Bloque nuevoBloque)`**: Agrega al Array que contiene los bloques de la Blockchain el bloque nuevoBloque.

- **`tieneCantidadDeCeros(String cadena, int cantidadCeros`**: Verifica que el hash pasado como parametro tenga la cantidad de ceros pedida.



------------



# Clase Main

#### Punto de entrada del programa

El método `main` es el punto de entrada del programa.

#### Creación y asignación de transacciones

El método `main` comienza creando un conjunto predefinido de transacciones mediante la asignación de instancias individuales de la clase `Transaccion` a elementos de la lista `nuevasTransacciones` de la clase `ListaTransacciones`. Esto se hace para tener un conjunto inicial de transacciones que se utilizarán en la simulación.

#### Confirmación y minado de bloques iniciales

Se establece que los seis primeros bloques (`bloque0` a `bloque5`) están confirmados, lo que indica que ya están en la cadena de bloques. Luego, estos bloques se "minan" mediante la llamada al método `minar` de la clase `BlockChain`, que realiza el proceso de minado para cada bloque, incluyendo la adición de transacciones, el cálculo de `roothash` y la realización de la prueba de trabajo.

#### Creación y minado de bloques adicionales

Se crean dos bloques adicionales (`bloque10` y `bloque7`) y se realiza el proceso de minado para cada uno de ellos mediante la llamada al método `minar` de la clase `BlockChain`.

#### Impresión de la cadena de bloques y transacciones

Finalmente, se imprime la cadena de bloques completa llamando al método `imprimirBlockChain` de la clase `BlockChain`. También se imprimen las transacciones individuales de cada bloque llamando al método `imprimirTransacciones` de la clase `BlockChain` para cada bloque, incluidos los bloques adicionales creados.




