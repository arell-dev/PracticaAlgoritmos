# Explicacion 08 - Ejercicio 08

## En que consiste

El programa implementa un menu para una lista simplemente circular de numeros enteros.

## Estructuras usadas

- `Nodo`: guarda un entero y una referencia `siguiente`.
- `ListaCircular`: mantiene `cabeza`, `cola` y `tamanio`.
- `Scanner`: permite leer las opciones del menu.

## Idea clave

En una lista circular, la cola apunta nuevamente a la cabeza. Por eso, para mostrar los datos se usa un contador y se recorren exactamente `tamanio` nodos.
