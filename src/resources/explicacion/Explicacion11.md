# Explicacion 11 - Ejercicio 11

## En que consiste

El programa representa una comunidad formada por provincias, pueblos y personas. Permite mostrar pueblos con mas de cierta cantidad de habitantes, listar mayores de edad y contar personas.

## Estructuras usadas

- `Comunidad`: contiene una lista de provincias.
- `Provincia`: contiene una lista de pueblos.
- `Pueblo`: contiene una lista de personas.
- `Persona`: guarda nombre y edad.
- Listas enlazadas simples para provincias, pueblos y personas.

## Idea clave

Se usa una estructura jerarquica: comunidad -> provincias -> pueblos -> personas. Para responder consultas se recorren las listas desde el nivel mas general hasta el nivel necesario.
