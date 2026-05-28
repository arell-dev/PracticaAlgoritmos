# Explicacion 09 - Ejercicio 09

## En que consiste

El programa resuelve el problema de Jose usando una lista circular de soldados. Se elimina un soldado cada vez que la cuenta llega a `n` y el ultimo que queda es quien escapa.

## Estructuras usadas

- `NodoSoldado`: guarda el nombre del soldado y el enlace al siguiente.
- `ListaSoldados`: mantiene el circulo de soldados con `cabeza`, `cola` y `tamanio`.
- `Scanner`: lee el numero `n` y los nombres hasta escribir `FIN`.

## Idea clave

La lista circular representa el grupo de soldados. Al eliminar un nodo, el anterior se conecta con el siguiente y el conteo continua desde el soldado siguiente.
