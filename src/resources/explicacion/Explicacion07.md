# Explicacion 07 - Ejercicio 07

## En que consiste

El programa maneja un registro de estudiantes usando una lista doblemente enlazada. Los aprobados se insertan al inicio y los desaprobados al final.

## Estructuras usadas

- `Estudiante`: guarda codigo, nombre, apellidos, correo y nota.
- `NodoEstudiante`: contiene un estudiante y enlaces `anterior` y `siguiente`.
- `ListaEstudiantes`: permite agregar, buscar, eliminar, contar aprobados, contar desaprobados y mostrar.
- `Scanner`: permite manejar el menu.

## Idea clave

La nota decide la posicion de insercion: si el estudiante aprueba, entra por la cabeza; si desaprueba, entra por la cola.
