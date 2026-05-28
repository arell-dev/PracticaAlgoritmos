# Explicacion 06 - Ejercicio 06

## En que consiste

El programa genera una lista doblemente enlazada con 50 numeros aleatorios entre 1 y 999. Luego elimina los nodos que estan fuera de un rango ingresado por teclado.

## Estructuras usadas

- `Nodo`: almacena un entero y sus enlaces dobles.
- `ListaDoble`: inserta al final y elimina nodos internos, iniciales o finales.
- `Random`: genera los numeros aleatorios.
- `Scanner`: lee el rango permitido.

## Idea clave

Antes de eliminar un nodo se guarda su `siguiente`, porque al borrar el nodo sus enlaces cambian. Asi se puede continuar el recorrido sin perder la posicion.
