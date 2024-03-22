# Información del Proyecto
Este proyecto es una implementación básica de una blockchain en Java, diseñada como parte del curso de Blockchain.

------------

# Clase Main
En la clase main se hace lo siguiente:
### Creación y asignación de transacciones
El método main comienza creando un conjunto predefinido de transacciones mediante la asignación de instancias individuales de la clase Transaccion a elementos de la lista nuevasTransacciones de la clase ListaTransacciones. Esto se hace para tener un conjunto inicial de transacciones que se usaran en el programa.


#### Confirmación y minado de bloques iniciales

Se establece que los seis primeros bloques (*`bloque0`* a *`bloque5`*) están confirmados, lo que indica que ya están en la cadena de bloques. Luego, estos bloques se "minan" mediante la llamada al método *`minar`* de la clase *`BlockChain`*, que realiza el proceso de minado para cada bloque, incluyendo la adición de transacciones, el cálculo de *`roothash`* y la realización de la prueba de trabajo.


#### Creación y minado de bloques adicionales

Se crean dos bloques adicionales (*`bloque10`* y *`bloque7`*) y se realiza el proceso de minado para cada uno de ellos mediante la llamada al método `minar` de la clase `BlockChain`.


#### Impresión de la cadena de bloques y transacciones
Finalmente, se imprime la cadena de bloques completa llamando al método *`imprimirBlockChain`* de la clase *`BlockChain`*. También se imprimen las transacciones individuales de cada bloque llamando al método *`imprimirTransacciones`* de la clase *`BlockChain`* para cada bloque, incluidos los bloques adicionales creados.

------------


# Clase Transaccion
La clase `Transaccion` representa una transacción en BlockChain.

### Atributos de clase:
- **`hashTransaccion`**: Es el hash de la transacción.
- **`remitente`**: Persona que envia el dinero.
- **`destinatario`**: Persona que recibe el dinero.
- **`cantidad`**: Cantidad de dinero enviada
- **`comision`**: Lo que recibe el minero por incluir la transacción en el bloque **(en este codigo siempre es 0)**.
- **`numeroTransaccion`**: Hay 101 transacciones en total, empezando desde el 0. Representa el orden de la transacción.
- **`numeroBloque`**: Es el número del bloque donde se agregó o se agregará. **Inicialmente es -1**.

### Métodos:
- **`calcularHash()`**: Calcula el hash de la transacción utilizando el algoritmo SHA-256. El hash se genera usando la concatenación del *`remitente`*, *`destinatario`*, *`cantidad`*, *`comision`* y *`numeroTransaccion`*.

- **`Getters y Setters`**: Se usan para modificar los atributos. Los metodos setters, después de cambiar un atributo **invocan el metodo** *`calcularHash()`* para recalcular el hash de la transacción.


------------
# Clase ListaTransacciones
En la clase `ListaTransacciones` se crean todas las transacciones que se usarán en el proyecto. Estas transacciones son estáticas y son instancias de la clase *`Transacciones`*.

### Atributos de clase:
- **`nuevasTransacciones[]`**: Este es un array que guarda todas las transacciones que se crearon en esta clase. Las transacciones se guardan en este array en la clase *`Main`* así:

```java
ListaTransacciones.nuevasTransacciones[0] = ListaTransacciones.transaccion00;
ListaTransacciones.nuevasTransacciones[1] = ListaTransacciones.transaccion01;

```

-  **`transaccionXX`** (donde XX es un número): Instancias de la clase Transaccion.  Se crean  101 instancias de esta clase.
	- Las primeras 10 transacciones se usan para "recargar" el saldo de las cuentas que se usarán:

```java
    static Transaccion transaccion00 = new Transaccion("", "", 100, 0, 0);
    static Transaccion transaccion01 = new Transaccion("BTC", "Alejo", 100, 0, 1);
    static Transaccion transaccion02 = new Transaccion("BTC", "Camila", 100, 0, 2);
    static Transaccion transaccion03 = new Transaccion("BTC", "Diego", 100, 0, 3);
    static Transaccion transaccion04 = new Transaccion("BTC", "Nekko", 100, 0, 4);
    static Transaccion transaccion05 = new Transaccion("BTC", "Vi", 100, 0, 5);
    static Transaccion transaccion06 = new Transaccion("BTC", "Victoria", 100, 0, 6);
    static Transaccion transaccion07 = new Transaccion("BTC", "Sara", 100, 0, 7);
    static Transaccion transaccion08 = new Transaccion("BTC", "Yeison", 100, 0, 8);
```

- A excepción de 7 transacciones y aquellas que se modificaron para que fallaran, todas las otras cumplen con ser circulares, es decir, las dos personas involucradas en las transacciones terminan con el saldo inicial que tenian, así:

```java
    static Transaccion transaccion42 = new Transaccion("Sara", "Victoria", 25, 0, 51);
    static Transaccion transaccion43 = new Transaccion("Victoria", "Sara", 50, 0, 52);
    static Transaccion transaccion44 = new Transaccion("Victoria", "Sara", 25, 0, 53);
    static Transaccion transaccion45 = new Transaccion("Sara", "Victoria", 25, 0, 54);
    static Transaccion transaccion46 = new Transaccion("Victoria", "Sara", 25, 0, 55);
    static Transaccion transaccion47 = new Transaccion("Sara", "Victoria", 25, 0, 56);
    static Transaccion transaccion48 = new Transaccion("Victoria", "Sara", 25, 0, 57);
    static Transaccion transaccion49 = new Transaccion("Sara", "Victoria", 25, 0, 58);
    static Transaccion transaccion50 = new Transaccion("Victoria", "Sara", 25, 0, 59);
    static Transaccion transaccion51 = new Transaccion("Sara", "Victoria", 50, 0, 60);
```

------------


# Clase Bloque
La clase **`Bloque`** representa los bloques que tendrán las transacciones y que se agregarán a la cadena de bloques o BlockChain.

### Atributos de clase:
- **`bloque`**: Es el número del bloque en la BlockChain.
- **`nonce`**: El número que se cambiará para hallar el hash.
- **`hashAnterior`**: Es el hash del bloque anterior en BlockChain.
- **`hash`**: Es el hash del bloque.
- **`transacciones <>`**: Es una lista de transacciones que guardará las transacciones que incluirá el minero en el bloque.
- **`estaConfirmado`**: Booleano que indica si el bloque ha sido agreago en la BlockChain.
- **`rootHash`**: El hash de la raíz del árbol Merkle, que representa todas las transacciones incluidas en el bloque.

### Métodos:
- **`calcularHash()`**: Hace lo siguiente
	1.  Calcula el hash del bloque utilizando el algoritmo SHA-256. El hash se genera usando la concatenación del *`bloque`*, *`nonce`*, *`hashAnterior`*, *`rootHash`* y *`transacciones`*.
	
	2.  Envia al siguiente bloque de la BlockChain el hash del bloque actual, es decir, el calculado con este mismo metodo.
- **`agregarTransacciones()`**: Agrega un numero de transacciones al bloque actual. El numero de transacciones que agregará se determina con el atributo *`numeroDeTransaccionesPorBloque`* de la clase *`BlockChain`*. 
Para agregar la transacción se hace lo siguiente:

	1.  Se obtiene de la clase *`BlockChain`* el numero de transacciones que se van a agregar.
	2.  Se usa el metodo esTransaccionValida() para verificar que la transaccion sea valida.
	3. Se agrega la transacción al bloque y se cambia el atributo *`numeroBloque`* de la Transaccion por el numero del bloque donde se agregó *(si no es valida, se pone en -1)*.
	4.  Después de agregar las transacciones, se invoca el metodo *`calcularRootHash()`*, para calcular el root hash de las transacciones.
	5. Se invoca el metodo *`actualizarNuevasTransacciones(transaccionesAgregadas: Int)`* de la clase *`ListaTransacciones`* para quitar del Array *`nuevasTransacciones`* las transacciones agregadas al bloque.


- **`esTransaccionValida(Transaccion transaccion)`**: Recorre todos los Bloques de la BlockChain obteniendo sus transacciones. Crea la variable *`saldo`* donde almacenará el calculo de las transacciones. Obtiene todas las transacciones donde el remiente de la transacción pasada como parametro aparezca. Aquellas en las que envió dinero, le restan a saldo el dinero enviado y aquellas en las que recibió dinero, le suman a saldo el dinero recibido. Al final, si el saldo calculado es mayor o igual que el que quiere enviar, entonces la transacción en valida y retorna `true`, sino la transacción es invalida y retorna `false`.

- **`calcularRootHash()`**: Calcula el hash de la raíz del árbol Merkle a partir de las transacciones incluidas en el bloque. Para esto sigue los siguientes pasos:
	1.  Crea una lista llamada *`listaTransacciones`* en la que se guardarán todos los hashes de la transacción del bloque.
	2. Se inicia un bucle que continuará hasta que en *`listaTransacciones`* solo quede un elemento, que en este caso es un hash.
	3. Se toman dos elementos **A** y **B** de la lista (se empieza por los dos primeros y va en orden) y se concatenan para calcular un nuevo hash **AB**.
	4. Este nuevo hash **AB** se coloca en la primera posición de la lista, reemplazando a **A**.
	5. Se elimina el segundo elemento de la lista, el cual es **B**.
	6. Los demás elementos de la lista se mueven para llenar el espacio dejado por **B**.
	7. Luego se toman los siguientes dos hashes **C** y **D** de la lista y se repite el proceso anterior desde el** paso 3**. Los usamos para calcular el hash **CD**. En la posición de **C** ponemos a **CD** y el elemento **D** se elimina y los demas se corren para llenar el vacio.

	- **ACLARACIONES**: 
		- En caso de que la lista sea **par**, se sigue haciendo lo mismo hasta los dos últimos elementos de la lista.
		- En caso que la lista sea **impar**, el último elemento no se combina y se deja en su posición.
		- Una vez se ha llegado al final de la lista, se reinicia el ciclo y se repite el proceso con los nuevos hashes calculados. Es decir, con **AB**, **CD** y **EF** si la lista es par y con **AB**, **CD** y **E** si la lista es impar.
		- Este proceso se repite hasta que en la lista solo quede un unico elemento. Este último elemento será el **root hash**.
		- Una vez se ha terminado el proceso. Se invoca el metodo *`calcularHash()`* para calcular el nuevo hash que tendrá el bloque con el nuevo valor del rootHash.


- **`Getters y Setters`**: Se usan para modificar los atributos. Los metodos setters, después de cambiar un atributo **invocan el metodo** *`calcularHash()`* para recalcular el hash del Bloque.



------------



# Clase BlockChain.
La clase BlockChain representa la cadena de bloques.


### Atributos de clase:
- **`dificultad`**: Número que define la dificultad. Es el numero de ceros que se deben hallar en la prueba de trabajo.
- **`numeroDeTransaccionesPorBloque`**: Contiene el número de transacciones que se agregarán a los bloques, por defecto está en 5.
- **`bloqueX`** (donde X son numeros desde el 0 hasta el 5): Bloques iniciales de la Blockchain.
- **`cantidad`**: Cantidad de dinero enviada
- **`vectorBC[]`**:  Array que contendrá todos los bloques agregados a la Blockchain..

### Métodos:
- **`imprimirBlockChain()`**: Imprime en la consola la cadena de bloques completa, incluyendo la información de cada bloque y sus transacciones.
- **`imprimirTransacciones(Transaccion<> transacciones)`**: Imprime en la consola la cadena de bloques completa, incluyendo la información de cada bloque y sus transacciones.
- **`getBloqueBlockChain(posicion: int)`**: Obtiene el bloque de la posición *`posición`* del vector *`vectorBC`*.
- **` tieneCantidadDeCeros(cadena: String, cantidadCeros int)`**: Verifica que el hash pasado como parametro tenga la cantidad de ceros pedida.
- **`pruebaDeTrabajoEspecial()`**: Hace la prueba de trabajo con todos los bloques de la Blockchain.
- **`pruebaDeTrabajo(Bloque bloque)`**: Hace la prueba de trabajo para el bloque pasado como parametro
- **`agregarBloque(Bloque nuevoBloque)`**:  Agrega al Array *`vectorBC`* de la Blockchain el bloque pasado como parametro.
- **`minar(Bloque bloque)`**: Se encarga de minar un nuevo bloque. Para esto hace lo siguiente:
	-  *Invoca el metodo `agregarTransacciones()`*.
	-  Le asigna la bloque pasado como parametro el hash del bloque anterior, es decir, el último bloque agregado a al *`vectorBC`*. Al primer bloque le asigna `"0000000000"`.
	-  Invoca el método *`pruebaDetrabajo(bloque)`*.
	-  Invoca el método *`agregarBloque(bloque)`*.


