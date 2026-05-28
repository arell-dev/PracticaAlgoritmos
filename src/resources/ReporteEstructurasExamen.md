# Reporte: estructuras que se deben entender para el examen

Este reporte resume las estructuras y operaciones que aparecen en los ejercicios de listas enlazadas dobles, circulares y sistemas con listas internas.

---

## 1. Clase Nodo

El nodo es la base de una lista enlazada. Siempre guarda un dato y al menos un enlace.

Ejemplo de nodo simple:

```java
class Nodo {
    int dato;
    Nodo siguiente;
}
```

Ejemplo de nodo doble:

```java
class Nodo {
    int dato;
    Nodo anterior;
    Nodo siguiente;
}
```

Lo que se debe entender:

- El dato puede ser `int`, `String` o una clase como `Estudiante`, `Libro` o `Persona`.
- `siguiente` conecta con el nodo que viene despues.
- `anterior` conecta con el nodo que viene antes, solo en listas dobles.

---

## 2. Lista enlazada simple

Una lista simple permite avanzar en una sola direccion.

Estructura tipica:

```java
class ListaSimple {
    Nodo cabeza;
    Nodo cola;
}
```

Operaciones importantes:

- Insertar al inicio.
- Insertar al final.
- Buscar un dato.
- Eliminar por valor.
- Mostrar todos los datos.
- Contar elementos.

Idea clave:

Para recorrer una lista simple se empieza en `cabeza` y se avanza con `actual = actual.siguiente` hasta llegar a `null`.

---

## 3. Lista doblemente enlazada

Una lista doble permite recorrer en ambos sentidos.

Estructura tipica:

```java
class ListaDoble {
    Nodo cabeza;
    Nodo cola;
}
```

Operaciones importantes:

- Insertar al final:
  - `cola.siguiente = nuevo`
  - `nuevo.anterior = cola`
  - `cola = nuevo`
- Recorrer hacia adelante desde `cabeza`.
- Recorrer hacia atras desde `cola`.
- Eliminar un nodo actualizando `anterior` y `siguiente`.
- Invertir datos usando dos referencias: una al inicio y otra al final.

Idea clave:

Cada cambio debe mantener bien conectados los dos lados del nodo. Si se elimina un nodo intermedio, el nodo anterior debe apuntar al siguiente y el siguiente debe apuntar al anterior.

---

## 4. Lista circular simple

Una lista circular no termina en `null`; la cola vuelve a apuntar a la cabeza.

Estructura tipica:

```java
class ListaCircular {
    Nodo cabeza;
    Nodo cola;
    int tamanio;
}
```

Operaciones importantes:

- Insertar al final:
  - si esta vacia, el nuevo nodo apunta a si mismo.
  - si no esta vacia, la cola apunta al nuevo nodo y el nuevo nodo apunta a la cabeza.
- Mostrar elementos usando un contador.
- Eliminar por valor sin romper el circulo.

Idea clave:

No se debe usar `while (actual != null)` para recorrer una circular, porque nunca llega a `null`. Se usa `tamanio` o se detiene cuando se vuelve a la cabeza.

---

## 5. Insercion al inicio y al final

Insertar al inicio se usa cuando el nuevo dato debe quedar como primer elemento.

Ejemplo conceptual:

```java
nuevo.siguiente = cabeza;
cabeza = nuevo;
```

Insertar al final se usa cuando el nuevo dato debe quedar despues de todos.

Ejemplo conceptual:

```java
cola.siguiente = nuevo;
cola = nuevo;
```

En lista doble tambien se actualiza `nuevo.anterior`.

---

## 6. Eliminacion de nodos

Para eliminar hay que considerar tres casos:

- El nodo esta al inicio.
- El nodo esta al final.
- El nodo esta en medio.

En lista simple se necesita normalmente una referencia al nodo anterior.

En lista doble se usan los enlaces:

```java
nodo.anterior.siguiente = nodo.siguiente;
nodo.siguiente.anterior = nodo.anterior;
```

En lista circular tambien se debe revisar si se elimina la cabeza o la cola para que el circulo siga conectado.

---

## 7. Busqueda y conteo

La busqueda consiste en recorrer nodo por nodo comparando el dato.

Ejemplo:

```java
while (actual != null) {
    if (actual.dato == buscado) {
        return true;
    }
    actual = actual.siguiente;
}
```

Para contar apariciones, se recorre toda la lista y se aumenta un contador cada vez que hay coincidencia.

---

## 8. Comparacion entre listas

Para comparar listas se debe revisar:

- Si tienen el mismo tamanio.
- Si los datos coinciden en la misma posicion.

La forma mas clara es recorrer ambas listas al mismo tiempo:

```java
actualA = cabezaA;
actualB = cabezaB;
```

Si algun dato es diferente, las listas no tienen el mismo contenido.

---

## 9. Union, interseccion y diferencia

Estas operaciones se parecen a conjuntos matematicos.

- Union: todos los elementos de ambas listas sin repetir.
- Interseccion: solo los elementos que aparecen en ambas listas.
- Diferencia: elementos que estan en la primera lista pero no en la segunda.

Estructuras necesarias:

- Una lista origen.
- Otra lista para comparar.
- Una lista resultado.
- Un metodo `contiene`.

---

## 10. Estructuras combinadas

Varios ejercicios usan una estructura dentro de otra.

Ejemplos:

- Arreglo de postres, donde cada postre tiene una lista de ingredientes.
- Comunidad con lista de provincias, cada provincia con lista de pueblos y cada pueblo con lista de personas.
- Biblioteca con lista de libros, cada libro con lista de ejemplares.

Idea clave:

Primero se busca el objeto principal y luego se trabaja con su lista interna.

Ejemplo:

```java
Postre postre = buscarPostre(nombre);
postre.ingredientes.agregarAlFinal(ingrediente);
```

---

## 11. Menus con switch moderno

En Java moderno se puede usar `switch` con flechas:

```java
switch (opcion) {
    case 1 -> agregar();
    case 2 -> buscar();
    case 0 -> System.out.println("Fin");
    default -> System.out.println("Opcion no valida");
}
```

Si una opcion tiene varias instrucciones, se usan llaves:

```java
case 2 -> {
    int dato = leerEntero("Dato: ");
    eliminar(dato);
}
```

Esto evita escribir `break` en cada opcion.

---

## 12. Problema de Jose

Este problema se entiende mejor con una lista circular.

Pasos:

1. Los soldados se guardan en una lista circular.
2. Se avanza contando hasta `n`.
3. El soldado donde cae la cuenta se elimina.
4. El conteo continua desde el siguiente.
5. El ultimo nodo restante es el soldado que escapa.

Estructuras necesarias:

- Nodo con `String nombre`.
- Lista circular.
- Referencia `actual`.
- Referencia `anterior`.
- Variable `tamanio`.

---

## 13. Biblioteca y prestamos

La biblioteca requiere entender relaciones entre clases.

Clases principales:

- `Biblioteca`: administra libros y usuarios.
- `Libro`: datos generales del libro.
- `Ejemplar`: copia fisica del libro, identificada por signatura.
- `Usuario`: alumno o profesor con limite de prestamos.
- `ListaPrestamos`: lista de ejemplares prestados al usuario.

Idea clave:

El prestamo no se hace al libro general, sino a un ejemplar especifico disponible.

---

## 14. Recomendacion para estudiar

Para el examen conviene practicar en este orden:

1. Crear una clase `Nodo`.
2. Insertar al final en una lista.
3. Mostrar una lista.
4. Buscar un dato.
5. Eliminar un dato.
6. Hacer una lista doble y recorrer en ambos sentidos.
7. Hacer una lista circular y mostrar una vuelta.
8. Resolver consultas recorriendo listas internas.
9. Usar `switch` moderno para menus.

Si dominas esos puntos, puedes resolver la mayoria de ejercicios del material.
