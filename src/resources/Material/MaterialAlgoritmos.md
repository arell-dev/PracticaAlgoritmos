# Listas enlazadas: simples, doblemente enlazadas y circulares

**Fuente base:** MaterialAlgoritmos.pdf y archivo Markdown anterior de explicación de listas enlazadas.

> Este archivo conserva la explicación general del material de clases y añade métodos complementarios para cada tipo de lista: insertar, eliminar, buscar, limpiar, contar elementos, verificar si está vacía y mostrar la estructura.  
> Los códigos están pensados para practicar en Java. Se recomienda probar cada ejemplo por separado, porque varias clases tienen nombres similares.

---

# 1. ¿Qué es una lista enlazada?

Una **lista enlazada** es una estructura de datos formada por una secuencia de **nodos**. Cada nodo almacena información y guarda una referencia hacia otro nodo.

A diferencia de un arreglo, una lista enlazada puede crecer o reducirse durante la ejecución del programa, sin necesidad de conocer desde el inicio cuántos elementos se van a guardar.

Representación general:

```text
[Dato | enlace] -> [Dato | enlace] -> [Dato | enlace] -> null
```

---

# 2. ¿Qué es un nodo?

El **nodo** es la unidad básica de una lista enlazada.

En una lista simple, un nodo contiene:

1. **Dato:** valor almacenado.
2. **Siguiente:** referencia al próximo nodo.

Ejemplo:

```java
class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
```

---

# 3. Tipos principales de listas enlazadas

El material de clases trabaja principalmente con tres tipos:

| Tipo de lista | Característica principal |
|---|---|
| Lista enlazada simple | Cada nodo apunta solo al siguiente. |
| Lista doblemente enlazada | Cada nodo apunta al anterior y al siguiente. |
| Lista circular | El último nodo vuelve a apuntar al primero. |

---

# 4. Lista enlazada simple

## 4.1. Definición

Una **lista enlazada simple** es una estructura donde cada nodo tiene un dato y una referencia al siguiente nodo.

Solo se puede recorrer en una dirección: desde la **cabeza** hasta el último nodo.

Representación:

```text
HEAD
 ↓
[10 | sig] -> [20 | sig] -> [30 | sig] -> null
```

- `cabeza`: primer nodo de la lista.
- `siguiente`: referencia al próximo nodo.
- `null`: indica el final de la lista.

---

## 4.2. Ventajas

- Es dinámica.
- Puede crecer o reducirse durante la ejecución.
- No necesita conocer el tamaño inicial.
- Permite insertar y eliminar elementos sin mover todos los datos, como ocurre en un arreglo.
- Es útil para implementar pilas, colas y otras estructuras.

---

## 4.3. Desventajas

- Consume más memoria que un arreglo porque cada nodo guarda una referencia adicional.
- Para buscar un elemento, normalmente se debe recorrer desde la cabeza.
- No permite acceso directo por índice como los arreglos.

---

## 4.4. Métodos principales de una lista simple

| Método | ¿Qué hace? |
|---|---|
| `insertarAlInicio(dato)` | Agrega un nodo al comienzo. |
| `insertarAlFinal(dato)` | Agrega un nodo al final. |
| `insertarEnIndice(indice, dato)` | Agrega un nodo en una posición específica. |
| `eliminarAlInicio()` | Elimina el primer nodo. |
| `eliminarAlFinal()` | Elimina el último nodo. |
| `eliminarPorValor(dato)` | Elimina el primer nodo que tenga ese dato. |
| `eliminarEnIndice(indice)` | Elimina el nodo de una posición específica. |
| `buscar(dato)` | Devuelve `true` si el dato existe. |
| `indiceDe(dato)` | Devuelve la posición del dato o `-1` si no existe. |
| `obtenerEnIndice(indice)` | Devuelve el dato ubicado en un índice. |
| `limpiar()` | Borra toda la lista. |
| `estaVacia()` | Verifica si la lista no tiene nodos. |
| `tamanio()` | Devuelve la cantidad de nodos. |
| `mostrarLista()` | Imprime todos los elementos. |

---

## 4.5. Explicación de métodos importantes

### Insertar al inicio

Para insertar al inicio:

1. Crear un nuevo nodo.
2. Hacer que el nuevo nodo apunte a la cabeza actual.
3. Actualizar la cabeza para que sea el nuevo nodo.

```text
Antes:
10 -> 20 -> 30 -> null

Insertar 5 al inicio:
5 -> 10 -> 20 -> 30 -> null
```

---

### Insertar al final

Para insertar al final:

1. Crear un nuevo nodo.
2. Si la lista está vacía, el nuevo nodo será la cabeza.
3. Si no está vacía, recorrer hasta el último nodo.
4. Hacer que el último nodo apunte al nuevo nodo.

```text
Antes:
10 -> 20 -> 30 -> null

Insertar 40 al final:
10 -> 20 -> 30 -> 40 -> null
```

---

### Insertar en un índice

Para insertar en una posición:

1. Validar que el índice exista.
2. Si el índice es `0`, insertar al inicio.
3. Si el índice es igual al tamaño, insertar al final.
4. Si está en medio, avanzar hasta el nodo anterior.
5. Conectar el nuevo nodo entre el nodo anterior y el siguiente.

```text
Antes:
10 -> 20 -> 30 -> null

Insertar 15 en índice 1:
10 -> 15 -> 20 -> 30 -> null
```

---

### Eliminar por valor

Para eliminar un valor:

1. Revisar si la lista está vacía.
2. Si el valor está en la cabeza, mover la cabeza al siguiente nodo.
3. Si está en medio o al final, recorrer hasta encontrarlo.
4. Hacer que el nodo anterior apunte al nodo siguiente del eliminado.

```text
Antes:
10 -> 20 -> 30 -> null

Eliminar 20:
10 -> 30 -> null
```

---

### Buscar

Para buscar:

1. Empezar desde la cabeza.
2. Comparar el dato del nodo actual con el dato buscado.
3. Avanzar nodo por nodo.
4. Si se encuentra, devolver `true`.
5. Si se llega a `null`, el dato no existe.

---

### Limpiar

Para borrar toda la lista simple, basta con hacer:

```java
cabeza = null;
```

Además, si se maneja una variable de tamaño, se debe reiniciar:

```java
tamanio = 0;
```

---

## 4.6. Código completo de lista enlazada simple con métodos complementarios

```java
class NodoSimple {
    int dato;
    NodoSimple siguiente;

    public NodoSimple(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaEnlazadaSimple {
    private NodoSimple cabeza;
    private int tamanio;

    public ListaEnlazadaSimple() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public void insertarAlInicio(int dato) {
        NodoSimple nuevo = new NodoSimple(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamanio++;
    }

    public void insertarAlFinal(int dato) {
        NodoSimple nuevo = new NodoSimple(dato);

        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            NodoSimple actual = cabeza;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }

        tamanio++;
    }

    public boolean insertarEnIndice(int indice, int dato) {
        if (indice < 0 || indice > tamanio) {
            return false;
        }

        if (indice == 0) {
            insertarAlInicio(dato);
            return true;
        }

        if (indice == tamanio) {
            insertarAlFinal(dato);
            return true;
        }

        NodoSimple nuevo = new NodoSimple(dato);
        NodoSimple actual = cabeza;

        for (int i = 0; i < indice - 1; i++) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        tamanio++;

        return true;
    }

    public boolean buscar(int dato) {
        return indiceDe(dato) != -1;
    }

    public int indiceDe(int dato) {
        NodoSimple actual = cabeza;
        int indice = 0;

        while (actual != null) {
            if (actual.dato == dato) {
                return indice;
            }

            actual = actual.siguiente;
            indice++;
        }

        return -1;
    }

    public Integer obtenerEnIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }

        NodoSimple actual = cabeza;

        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    public boolean eliminarAlInicio() {
        if (estaVacia()) {
            return false;
        }

        cabeza = cabeza.siguiente;
        tamanio--;

        return true;
    }

    public boolean eliminarAlFinal() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza.siguiente == null) {
            cabeza = null;
            tamanio--;
            return true;
        }

        NodoSimple actual = cabeza;

        while (actual.siguiente.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = null;
        tamanio--;

        return true;
    }

    public boolean eliminarPorValor(int dato) {
        if (estaVacia()) {
            return false;
        }

        if (cabeza.dato == dato) {
            return eliminarAlInicio();
        }

        NodoSimple actual = cabeza;

        while (actual.siguiente != null && actual.siguiente.dato != dato) {
            actual = actual.siguiente;
        }

        if (actual.siguiente == null) {
            return false;
        }

        actual.siguiente = actual.siguiente.siguiente;
        tamanio--;

        return true;
    }

    public boolean eliminarEnIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return false;
        }

        if (indice == 0) {
            return eliminarAlInicio();
        }

        NodoSimple actual = cabeza;

        for (int i = 0; i < indice - 1; i++) {
            actual = actual.siguiente;
        }

        actual.siguiente = actual.siguiente.siguiente;
        tamanio--;

        return true;
    }

    public void limpiar() {
        cabeza = null;
        tamanio = 0;
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoSimple actual = cabeza;

        System.out.print("Lista simple: ");

        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }

        System.out.println("null");
    }
}
```

### Ejemplo de uso

```java
public class Main {
    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();

        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);
        lista.insertarEnIndice(1, 15);

        lista.mostrarLista(); // 10 -> 15 -> 20 -> 30 -> null

        System.out.println("¿Existe 20? " + lista.buscar(20));
        System.out.println("Índice de 30: " + lista.indiceDe(30));
        System.out.println("Dato en índice 2: " + lista.obtenerEnIndice(2));

        lista.eliminarPorValor(15);
        lista.mostrarLista(); // 10 -> 20 -> 30 -> null

        lista.eliminarAlInicio();
        lista.mostrarLista(); // 20 -> 30 -> null

        lista.eliminarAlFinal();
        lista.mostrarLista(); // 20 -> null

        lista.limpiar();
        lista.mostrarLista(); // La lista está vacía.
    }
}
```

---

# 5. Lista doblemente enlazada

## 5.1. Definición

Una **lista doblemente enlazada** es una estructura donde cada nodo guarda dos referencias:

1. Una referencia al nodo anterior.
2. Una referencia al nodo siguiente.

Esto permite recorrer la lista en ambos sentidos.

Representación:

```text
null <- [10] <-> [20] <-> [30] -> null
```

---

## 5.2. Estructura de un nodo doble

Cada nodo contiene:

1. **Dato**
2. **Anterior**
3. **Siguiente**

```java
class NodoDoble {
    int dato;
    NodoDoble anterior;
    NodoDoble siguiente;

    public NodoDoble(int dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}
```

---

## 5.3. Ventajas de una lista doble

- Permite avanzar y retroceder.
- Eliminar un nodo puede ser más directo si ya se tiene la referencia al nodo.
- Es útil para historiales, reproductores, navegación hacia adelante y atrás.

---

## 5.4. Desventajas de una lista doble

- Usa más memoria que una lista simple, porque cada nodo tiene dos referencias.
- Requiere más cuidado al insertar o eliminar, porque se deben actualizar los enlaces `anterior` y `siguiente`.

---

## 5.5. Métodos principales de una lista doblemente enlazada

| Método | ¿Qué hace? |
|---|---|
| `insertarAlInicio(dato)` | Agrega un nodo al inicio. |
| `insertarAlFinal(dato)` | Agrega un nodo al final. |
| `insertarEnIndice(indice, dato)` | Inserta un dato en una posición específica. |
| `eliminarAlInicio()` | Elimina la cabeza. |
| `eliminarAlFinal()` | Elimina la cola. |
| `eliminarPorValor(dato)` | Elimina el primer nodo con ese dato. |
| `eliminarEnIndice(indice)` | Elimina el nodo de una posición. |
| `buscar(dato)` | Verifica si el dato existe. |
| `indiceDe(dato)` | Devuelve el índice del dato. |
| `obtenerEnIndice(indice)` | Obtiene el dato de una posición. |
| `limpiar()` | Borra todos los nodos. |
| `recorrerAdelante()` | Muestra desde la cabeza hasta la cola. |
| `recorrerAtras()` | Muestra desde la cola hasta la cabeza. |
| `estaVacia()` | Verifica si no hay nodos. |
| `tamanio()` | Devuelve cuántos nodos tiene la lista. |

---

## 5.6. Explicación de métodos importantes

### Insertar al inicio

```text
Antes:
null <- 10 <-> 20 <-> 30 -> null

Insertar 5:
null <- 5 <-> 10 <-> 20 <-> 30 -> null
```

Cambios importantes:

```java
nuevo.siguiente = cabeza;
cabeza.anterior = nuevo;
cabeza = nuevo;
```

---

### Insertar al final

```text
Antes:
null <- 10 <-> 20 <-> 30 -> null

Insertar 40:
null <- 10 <-> 20 <-> 30 <-> 40 -> null
```

Cambios importantes:

```java
cola.siguiente = nuevo;
nuevo.anterior = cola;
cola = nuevo;
```

---

### Eliminar un nodo intermedio

```text
Antes:
null <- 10 <-> 20 <-> 30 -> null

Eliminar 20:
null <- 10 <-> 30 -> null
```

Cambios importantes:

```java
actual.anterior.siguiente = actual.siguiente;
actual.siguiente.anterior = actual.anterior;
```

---

### Limpiar

En una lista doble se borran las referencias principales:

```java
cabeza = null;
cola = null;
tamanio = 0;
```

Con eso, los nodos dejan de ser accesibles desde la lista.

---

## 5.7. Código completo de lista doblemente enlazada con métodos complementarios

```java
class NodoDoble {
    int dato;
    NodoDoble anterior;
    NodoDoble siguiente;

    public NodoDoble(int dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}

class ListaDoblementeEnlazada {
    private NodoDoble cabeza;
    private NodoDoble cola;
    private int tamanio;

    public ListaDoblementeEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public void insertarAlInicio(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }

        tamanio++;
    }

    public void insertarAlFinal(int dato) {
        NodoDoble nuevo = new NodoDoble(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }

        tamanio++;
    }

    public boolean insertarEnIndice(int indice, int dato) {
        if (indice < 0 || indice > tamanio) {
            return false;
        }

        if (indice == 0) {
            insertarAlInicio(dato);
            return true;
        }

        if (indice == tamanio) {
            insertarAlFinal(dato);
            return true;
        }

        NodoDoble actual = obtenerNodo(indice);
        NodoDoble nuevo = new NodoDoble(dato);
        NodoDoble anterior = actual.anterior;

        anterior.siguiente = nuevo;
        nuevo.anterior = anterior;

        nuevo.siguiente = actual;
        actual.anterior = nuevo;

        tamanio++;

        return true;
    }

    public boolean buscar(int dato) {
        return indiceDe(dato) != -1;
    }

    public int indiceDe(int dato) {
        NodoDoble actual = cabeza;
        int indice = 0;

        while (actual != null) {
            if (actual.dato == dato) {
                return indice;
            }

            actual = actual.siguiente;
            indice++;
        }

        return -1;
    }

    public Integer obtenerEnIndice(int indice) {
        NodoDoble nodo = obtenerNodo(indice);

        if (nodo == null) {
            return null;
        }

        return nodo.dato;
    }

    private NodoDoble obtenerNodo(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }

        NodoDoble actual;

        // Si el índice está en la primera mitad, se avanza desde la cabeza.
        if (indice < tamanio / 2) {
            actual = cabeza;

            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
        }

        // Si el índice está en la segunda mitad, se retrocede desde la cola.
        else {
            actual = cola;

            for (int i = tamanio - 1; i > indice; i--) {
                actual = actual.anterior;
            }
        }

        return actual;
    }

    public boolean eliminarAlInicio() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }

        tamanio--;

        return true;
    }

    public boolean eliminarAlFinal() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }

        tamanio--;

        return true;
    }

    public boolean eliminarPorValor(int dato) {
        NodoDoble actual = cabeza;

        while (actual != null && actual.dato != dato) {
            actual = actual.siguiente;
        }

        if (actual == null) {
            return false;
        }

        eliminarNodo(actual);

        return true;
    }

    public boolean eliminarEnIndice(int indice) {
        NodoDoble nodo = obtenerNodo(indice);

        if (nodo == null) {
            return false;
        }

        eliminarNodo(nodo);

        return true;
    }

    private void eliminarNodo(NodoDoble nodo) {
        if (nodo == cabeza) {
            eliminarAlInicio();
        } else if (nodo == cola) {
            eliminarAlFinal();
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
            tamanio--;
        }
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void recorrerAdelante() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoDoble actual = cabeza;

        System.out.print("Recorrido hacia adelante: ");

        while (actual != null) {
            System.out.print(actual.dato + " <-> ");
            actual = actual.siguiente;
        }

        System.out.println("null");
    }

    public void recorrerAtras() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoDoble actual = cola;

        System.out.print("Recorrido hacia atrás: ");

        while (actual != null) {
            System.out.print(actual.dato + " <-> ");
            actual = actual.anterior;
        }

        System.out.println("null");
    }
}
```

### Ejemplo de uso

```java
public class Main {
    public static void main(String[] args) {
        ListaDoblementeEnlazada lista = new ListaDoblementeEnlazada();

        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarEnIndice(2, 25);

        lista.recorrerAdelante(); // 10 <-> 20 <-> 25 <-> 30 <-> 40 <-> null
        lista.recorrerAtras();    // 40 <-> 30 <-> 25 <-> 20 <-> 10 <-> null

        System.out.println("¿Existe 30? " + lista.buscar(30));
        System.out.println("Índice de 25: " + lista.indiceDe(25));
        System.out.println("Dato en índice 3: " + lista.obtenerEnIndice(3));

        lista.eliminarPorValor(25);
        lista.recorrerAdelante(); // 10 <-> 20 <-> 30 <-> 40 <-> null

        lista.eliminarAlInicio();
        lista.recorrerAdelante(); // 20 <-> 30 <-> 40 <-> null

        lista.eliminarAlFinal();
        lista.recorrerAdelante(); // 20 <-> 30 <-> null

        lista.limpiar();
        lista.recorrerAdelante(); // La lista está vacía.
    }
}
```

---

# 6. Lista circular

## 6.1. Definición

Una **lista circular** es una lista enlazada donde el último nodo no apunta a `null`, sino que apunta otra vez al primer nodo.

Representación:

```text
       ┌──────────────────┐
       ↓                  │
[10] -> [20] -> [30] -----┘
```

En una lista circular, después del último nodo se vuelve a la cabeza.

---

## 6.2. Idea principal

En una lista simple, el recorrido termina cuando se llega a `null`.

En una lista circular, no existe ese `null` al final. Por eso, se debe controlar el recorrido para no entrar en un ciclo infinito.

Por ejemplo:

```text
A -> B -> C -> A -> B -> C -> ...
```

Esto sirve para simular turnos:

```text
Turno 1: A
Turno 2: B
Turno 3: C
Turno 4: A
Turno 5: B
Turno 6: C
```

---

## 6.3. Usos de una lista circular

- Sistemas de turnos.
- Juegos donde los participantes se repiten cíclicamente.
- Colas circulares.
- Buffers circulares.
- Procesos que deben repetirse después del último elemento.

---

## 6.4. Cuidado importante

En una lista circular, este tipo de recorrido puede ser incorrecto:

```java
while (actual != null) {
    actual = actual.siguiente;
}
```

¿Por qué? Porque en una lista circular `actual` no llega a `null`, ya que el último nodo vuelve a apuntar a la cabeza.

Formas correctas de detener el recorrido:

1. Usar un contador.
2. Detenerse cuando se regrese a la cabeza.
3. Recorrer exactamente `tamanio` nodos.

---

## 6.5. Métodos principales de una lista circular simple

| Método | ¿Qué hace? |
|---|---|
| `insertarAlInicio(dato)` | Inserta un nodo antes de la cabeza. |
| `insertarAlFinal(dato)` | Inserta un nodo después de la cola. |
| `insertarEnIndice(indice, dato)` | Inserta un nodo en una posición. |
| `eliminarAlInicio()` | Elimina la cabeza. |
| `eliminarAlFinal()` | Elimina la cola. |
| `eliminarPorValor(dato)` | Elimina el primer nodo que tenga ese dato. |
| `eliminarEnIndice(indice)` | Elimina un nodo por posición. |
| `buscar(dato)` | Verifica si un dato existe. |
| `indiceDe(dato)` | Devuelve la posición del dato. |
| `obtenerEnIndice(indice)` | Obtiene el dato de una posición. |
| `limpiar()` | Borra toda la lista. |
| `mostrarUnaVuelta()` | Muestra cada nodo una sola vez. |
| `simularTurnos(cantidadTurnos)` | Recorre la lista de forma repetida por una cantidad de turnos. |
| `estaVacia()` | Verifica si no tiene nodos. |
| `tamanio()` | Devuelve la cantidad de nodos. |

---

## 6.6. Explicación de métodos importantes

### Insertar al inicio

Si la lista está vacía, el nuevo nodo apunta a sí mismo:

```text
[10] ─┐
 ↑    │
 └────┘
```

Si la lista ya tiene elementos:

```text
Antes:
10 -> 20 -> 30 -> vuelve a 10

Insertar 5 al inicio:
5 -> 10 -> 20 -> 30 -> vuelve a 5
```

Se debe actualizar:

```java
nuevo.siguiente = cabeza;
cabeza = nuevo;
cola.siguiente = cabeza;
```

---

### Insertar al final

```text
Antes:
10 -> 20 -> 30 -> vuelve a 10

Insertar 40:
10 -> 20 -> 30 -> 40 -> vuelve a 10
```

Se debe actualizar:

```java
cola.siguiente = nuevo;
cola = nuevo;
cola.siguiente = cabeza;
```

---

### Eliminar al inicio

```text
Antes:
10 -> 20 -> 30 -> vuelve a 10

Eliminar 10:
20 -> 30 -> vuelve a 20
```

Se debe actualizar:

```java
cabeza = cabeza.siguiente;
cola.siguiente = cabeza;
```

---

### Eliminar por valor

Para eliminar un nodo por valor:

1. Si la lista está vacía, no se elimina nada.
2. Si el valor está en la cabeza, se llama a `eliminarAlInicio()`.
3. Si está en otro nodo, se busca usando dos referencias:
   - `anterior`
   - `actual`
4. El nodo anterior salta al nodo actual.

```text
Antes:
10 -> 20 -> 30 -> vuelve a 10

Eliminar 20:
10 -> 30 -> vuelve a 10
```

---

### Limpiar

En una lista circular, limpiar significa romper el acceso al ciclo:

```java
cabeza = null;
cola = null;
tamanio = 0;
```

---

## 6.7. Código completo de lista circular simple con métodos complementarios

```java
class NodoCircular {
    int dato;
    NodoCircular siguiente;

    public NodoCircular(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaCircular {
    private NodoCircular cabeza;
    private NodoCircular cola;
    private int tamanio;

    public ListaCircular() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public void insertarAlInicio(int dato) {
        NodoCircular nuevo = new NodoCircular(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza;
        }

        tamanio++;
    }

    public void insertarAlFinal(int dato) {
        NodoCircular nuevo = new NodoCircular(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }

        tamanio++;
    }

    public boolean insertarEnIndice(int indice, int dato) {
        if (indice < 0 || indice > tamanio) {
            return false;
        }

        if (indice == 0) {
            insertarAlInicio(dato);
            return true;
        }

        if (indice == tamanio) {
            insertarAlFinal(dato);
            return true;
        }

        NodoCircular nuevo = new NodoCircular(dato);
        NodoCircular actual = cabeza;

        for (int i = 0; i < indice - 1; i++) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        tamanio++;

        return true;
    }

    public boolean buscar(int dato) {
        return indiceDe(dato) != -1;
    }

    public int indiceDe(int dato) {
        if (estaVacia()) {
            return -1;
        }

        NodoCircular actual = cabeza;

        for (int i = 0; i < tamanio; i++) {
            if (actual.dato == dato) {
                return i;
            }

            actual = actual.siguiente;
        }

        return -1;
    }

    public Integer obtenerEnIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return null;
        }

        NodoCircular actual = cabeza;

        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }

        return actual.dato;
    }

    public boolean eliminarAlInicio() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        }

        tamanio--;

        return true;
    }

    public boolean eliminarAlFinal() {
        if (estaVacia()) {
            return false;
        }

        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            NodoCircular actual = cabeza;

            while (actual.siguiente != cola) {
                actual = actual.siguiente;
            }

            actual.siguiente = cabeza;
            cola = actual;
        }

        tamanio--;

        return true;
    }

    public boolean eliminarPorValor(int dato) {
        if (estaVacia()) {
            return false;
        }

        if (cabeza.dato == dato) {
            return eliminarAlInicio();
        }

        NodoCircular anterior = cabeza;
        NodoCircular actual = cabeza.siguiente;

        while (actual != cabeza) {
            if (actual.dato == dato) {
                anterior.siguiente = actual.siguiente;

                if (actual == cola) {
                    cola = anterior;
                }

                tamanio--;
                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    public boolean eliminarEnIndice(int indice) {
        if (indice < 0 || indice >= tamanio) {
            return false;
        }

        if (indice == 0) {
            return eliminarAlInicio();
        }

        NodoCircular anterior = cabeza;

        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.siguiente;
        }

        NodoCircular nodoEliminar = anterior.siguiente;
        anterior.siguiente = nodoEliminar.siguiente;

        if (nodoEliminar == cola) {
            cola = anterior;
        }

        tamanio--;

        return true;
    }

    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public void mostrarUnaVuelta() {
        if (estaVacia()) {
            System.out.println("La lista circular está vacía.");
            return;
        }

        NodoCircular actual = cabeza;

        System.out.print("Lista circular: ");

        for (int i = 0; i < tamanio; i++) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }

        System.out.println("(vuelve a " + cabeza.dato + ")");
    }

    public void simularTurnos(int cantidadTurnos) {
        if (estaVacia()) {
            System.out.println("No hay elementos para simular turnos.");
            return;
        }

        NodoCircular actual = cabeza;

        for (int turno = 1; turno <= cantidadTurnos; turno++) {
            System.out.println("Turno " + turno + ": " + actual.dato);
            actual = actual.siguiente;
        }
    }
}
```

### Ejemplo de uso

```java
public class Main {
    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();

        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlInicio(5);
        lista.insertarEnIndice(2, 15);

        lista.mostrarUnaVuelta(); // 5 -> 10 -> 15 -> 20 -> 30 -> vuelve a 5

        System.out.println("¿Existe 20? " + lista.buscar(20));
        System.out.println("Índice de 30: " + lista.indiceDe(30));
        System.out.println("Dato en índice 2: " + lista.obtenerEnIndice(2));

        lista.eliminarPorValor(15);
        lista.mostrarUnaVuelta(); // 5 -> 10 -> 20 -> 30 -> vuelve a 5

        lista.eliminarAlInicio();
        lista.mostrarUnaVuelta(); // 10 -> 20 -> 30 -> vuelve a 10

        lista.eliminarAlFinal();
        lista.mostrarUnaVuelta(); // 10 -> 20 -> vuelve a 10

        System.out.println("\nSimulación de turnos:");
        lista.simularTurnos(6);

        lista.limpiar();
        lista.mostrarUnaVuelta(); // La lista circular está vacía.
    }
}
```

---

# 7. Comparación de métodos por tipo de lista

| Operación | Lista simple | Lista doble | Lista circular |
|---|---|---|---|
| Insertar al inicio | Fácil | Fácil | Fácil, pero se actualiza `cola.siguiente`. |
| Insertar al final | Requiere recorrer si no hay cola | Fácil si se tiene cola | Fácil si se tiene cola. |
| Eliminar al inicio | Fácil | Fácil | Fácil, pero se actualiza `cola.siguiente`. |
| Eliminar al final | Requiere encontrar el anterior | Fácil por `cola.anterior` | Requiere encontrar el anterior a la cola. |
| Buscar | Recorre desde cabeza | Recorre desde cabeza o cola | Recorre con contador para evitar ciclo infinito. |
| Limpiar | `cabeza = null` | `cabeza = null`, `cola = null` | `cabeza = null`, `cola = null` |
| Recorrido | Hacia adelante | Adelante y atrás | Cíclico |

---

# 8. Resumen final

- Una **lista simple** conecta cada nodo con el siguiente.
- Una **lista doblemente enlazada** conecta cada nodo con el anterior y el siguiente.
- Una **lista circular** conecta el último nodo con el primero.
- Para eliminar nodos, siempre se deben reorganizar las referencias.
- Para buscar, normalmente se recorre desde la cabeza.
- Para limpiar una lista, se eliminan las referencias principales (`cabeza`, y también `cola` si existe).
- En una lista circular se debe tener cuidado con los recorridos infinitos, porque no hay un `null` al final.
- Los métodos complementarios más útiles son: buscar, eliminar, limpiar, contar elementos, obtener por índice y mostrar.
