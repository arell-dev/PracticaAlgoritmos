# Explicacion 13 - Ejercicio 13

## En que consiste

El programa modela una biblioteca de facultad con libros, ejemplares y usuarios. Muestra un grafico textual del diseno y realiza prestamos de ejemplo.

## Estructuras usadas

- `Biblioteca`: contiene listas de libros y usuarios.
- `Libro`: guarda titulo, autor, editorial, anio y lista de ejemplares.
- `Ejemplar`: guarda signatura, estado, estanteria, fechas y usuario del prestamo.
- `Usuario`: guarda datos personales, maximo de prestamos y lista de prestamos.
- Listas enlazadas simples para libros, ejemplares, usuarios y prestamos.

## Idea clave

Un libro puede tener varios ejemplares. El prestamo se hace sobre un ejemplar disponible, no sobre el libro general.
