# Explicacion 01 - Ejercicio 01

## En que consiste

El programa crea una lista doblemente enlazada de numeros enteros y la recorre en ambos sentidos: desde la cabeza hasta la cola y desde la cola hasta la cabeza.

## Estructuras usadas

- `Nodo`: guarda un `int dato`, una referencia al nodo `anterior` y una referencia al nodo `siguiente`.
- `ListaDoble`: mantiene dos referencias principales: `cabeza` y `cola`.

## Idea clave

La lista doble permite avanzar usando `siguiente` y retroceder usando `anterior`. Por eso se puede imprimir la misma lista en los dos sentidos.
