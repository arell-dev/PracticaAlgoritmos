# Explicacion 14 - Ejercicio 14

## En que consiste

El programa tambien modela una biblioteca de facultad, pero con menu para mostrar catalogo, prestar, devolver y consultar prestamos por persona.

## Estructuras usadas

- `Biblioteca`: administra libros y usuarios.
- `Libro`: contiene los datos bibliograficos y la lista de ejemplares.
- `Ejemplar`: indica si esta disponible o prestado.
- `Usuario`: tiene sus datos, limite de prestamos y lista de ejemplares prestados.
- Listas enlazadas simples para libros, ejemplares, usuarios y prestamos.
- `Scanner`: permite interactuar con el menu.

## Idea clave

La devolucion se realiza por signatura, porque la signatura identifica de forma unica a cada ejemplar.
