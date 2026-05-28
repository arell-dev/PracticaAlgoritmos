## **Ejercicios de Listas Enlazadas Dobles y circulares** 

1) Escriba un programa que permita recorrer una lista doble en ambos sentidos. 

2) Se tiene 2 listas doblemente enlazadas encontrar la unión, intervención y diferencia entre ellas. 

3) Ingresar 2 listas doblemente enlazadas y reportar: 
   - a. Si las listas son iguales en tamaño y contenido 
   - b. Si las listas son iguales en tamaño, pero no en contenido 
   - c. No tienen el mismo tamaño ni contenido. 

4) Escribir un programa que permita invertir los datos almacenados en una lista doblemente enlazada, es decir que el primer elemento pase a ser el último y el último pase a ser el primero, que el segundo sea el penúltimo y el penúltimo pase a ser el segundo y así sucesivamente. 

5) Escribir un programa que retorne el número de veces que se encuentra el dato dentro de la lista doble. En caso de no encontrarse, se debe mostrar un mensaje indicando que el dato no fue encontrado. Se debe ingresar el valor que se desea buscar. 

6) Crear una lista doblemente enlazada con 50 números enteros, del 1 al 999 generados aleatoriamente. Una vez creada la lista, se deben eliminar los nodos que estén fuera de un rango de valores leídos desde el teclado. 

7) Crear un programa que maneje el registro de los estudiantes, utilizando listas doblemente enlazadas. Los estudiantes aprobados deben insertarse al inicio y los reprobados por el final de la lista. Los datos requeridos por cada estudiante son los siguientes: código, nombre, apellidos, correo y nota. El programa debe permitir realizar las operaciones de: 

   - a. Agregar un estudiante. 
   - b. Buscar un estudiante por código. 
   - c. Eliminar un estudiante 
   - d. Total, de estudiantes aprobados 
   - e. Total, de Estudiante desaprobados. 

8) Crear el siguiente menú para una lista simplemente circular 

   - a. Ingresar un elemento 
   - b. Dado un elemento buscarlo si se encuentra eliminarlo 
   - c. Mostrar el número de elementos de la lista 
   - d. Mostrar todos los elementos de la lista. 

9) Consideremos un problema que puede ser resuelto en una forma directa mediante listas circulares. El problema se conoce con el nombre de José, y consiste en un grupo de soldados rodeados por una gran fuerza enemiga. No hay esperanza de victoria si no llegan refuerzos, y existe un solo caballo disponible para el escape. Los soldados se ponen de acuerdo en un pacto para determinar cuál de ellos debe escapar y solicita ayuda. Forman un círculo y se escoge un número n al azar de un sombrero, igualmente se escoge el nombre de un soldado. Comenzando con el soldado cuyo nombre se ha seleccionado, comienzan a contar en la dirección del reloj al rededor del círculo. Cuando la cuenta alcanza n, este soldado es retirado del círculo y la cuenta empieza de nuevo, con el siguiente hombre. El proceso continúa de tal manera que cada vez que la cuenta alcanza n, se remueve un hombre del círculo. Un soldado que es removido del círculo por supuesto no se vuelve a contar. El último soldado que queda es el que debe tomar el caballo y escapar. El problema es: dado un número n, el ordenamiento de los hombres en el círculo y el hombre a partir del que se comienza a contar, determinar el orden en el cual los hombres son eliminados del círculo y cual debe escapar. La entrada al programa es el número n y una lista de nombres que es el ordenamiento en el sentido de las manecillas del reloj en el círculo, comenzando con el hombre a partir del cual se debe comenzar a contar. La última línea de entrada contiene "FIN", indicando el final de la entrada. El programa debe mostrar los nombres de los soldados en el orden que han sido eliminados y el nombre de la persona que escapa. 

Por ejemplo, supongamos que n es 3 y que hay 5 hombres denominados A, B, C, D y E. Contamos tres hombres partiendo de A, de tal manera que C es eliminado primero. Luego empezamos en D y contamos D, E y regresamos a A, de tal manera que A es eliminado. Después contamos B, D y E (C ya ha sido eliminado), se elimina E. Finalmente contamos B, D y B, se elimina B y D es el hombre que escapa. 

10) Se ha definido la siguiente estructura de datos: 
En el arreglo "POSTRES" se almacenan nombres de postres. A su vez cada elemento del arreglo tiene una lista de todos los ingredientes que requiere dicho postre. Escribir un programa para: 
   - a. Ingresar un postre con todos sus ingredientes 
   - b. Dado el nombre de un postre, muestre la lista de todos sus ingredientes. 
   - c. Dado el nombre de un postre, pueda insertar nuevos ingredientes a su correspondiente lista. 
   - d. Dado el nombre de un postre, pueda eliminar alguno de sus ingredientes. 
   - e. Eliminar un postre con todos sus ingredientes 

11) Una comunidad tiene una serie de provincias, cada una de ellas compuestas por un conjunto de pueblos que poseen a su vez unas personas empadronadas en ellos. El sistema a desarrollar debe ser capaz de ofrecer la siguiente información: 

   - a. Mostrar todos los pueblos de más de x habitantes de una comunidad o provincia. 
   - b. Listar todos los habitantes mayores de 18 años de una comunidad, provincia o pueblo. 
   - c. Contar el número de personas de una comunidad, provincia o pueblo. 

12) Se desea realizar un programa de atención al usuario en la Feria del Libro. Como sabrá, la feria la integran una serie de casetas, cada una de las cuales ofrece la posibilidad de comprar una cierta cantidad de libros. Por otra parte, a cada visitante se le entrega un catálogo con los nombres de los autores que firmarán libros y a qué hora. El programa debe ser capaz de ofrecer al visitante la siguiente información: 

   - a. Qué libros hay en una determinada caseta. 
   - b. En qué casetas se puede comprar un libro determinado. 
   - c. A qué horas firma los ejemplares de su último libro publicado, un autor y en qué caseta. 
   - d. Qué autores firman libros. 
   - e. Los 10 libros más vendidos al final de la feria. 

13) Se desea crear un sistema software necesario para representar la información almacenada en la biblioteca de una facultad, correspondiente a los libros y a los usuarios de la misma. 

   - De cada libro, se desea tener acceso a su título, autor, editorial, año de publicación, número de ejemplares, signatura (única y diferente para cada ejemplar de cada libro) y si está disponible o no. En caso de que esté prestado, hay que saber la fecha de préstamo, la de devolución y los datos del usuario que lo tiene. Si está disponible, se mostrará la identificación de la estantería en la que se puede encontrar. 
   - Por otro lado, los usuarios de la biblioteca pueden ser alumnos y profesores de la facultad, de los que se almacenará su nombre, apellidos, dirección, DNI, el máximo número de libros que pueden tener prestados (distinto para alumnos que para profesores) y la lista de libros prestados. 
   - Diseñe e implemente un método para que, dada una persona, muestre los datos de los libros que tiene prestados. A partir de los requisitos previamente comentados, solucione los siguientes apartados. 
   - a. Realice un gráfico explicativo del diseño general de la solución propuesta. 
   - b. Diseñe e implemente las estructuras de datos necesarias para resolver este problema. 
   - c. Diseñe e implemente los algoritmos que proporcionen la funcionalidad necesaria para el sistema software planteado. 

14) Se desea crear un sistema software necesario para representar la información almacenada en la biblioteca de una facultad, correspondiente a los libros y a los usuarios de la misma. 
   - De cada libro, se desea tener acceso a su título, autor, editorial, año de publicación, número de ejemplares, signatura (única y diferente para cada ejemplar de cada libro) y si está disponible o no. En caso de que esté prestado, hay que saber la fecha de préstamo, la de devolución y los datos del usuario que lo tiene. Si está disponible, se mostrará la identificación de la estantería en la que se puede encontrar. 

   Por otro lado, los usuarios de la biblioteca pueden ser alumnos y profesores de la facultad, de los que se almacenará su nombre, apellidos, dirección, DNI, el máximo número de libros que pueden tener prestados (distinto para alumnos que para profesores) y la lista de libros prestados. 

   Diseñe e implemente un método para que, dada una persona, muestre los datos de los libros que tiene prestados. A partir de los requisitos previamente comentados, solucione los siguientes apartados. 

   - a. Realice un gráfico explicativo del diseño general de la solución propuesta. 
   - b. Diseñe e implemente las estructuras de datos necesarias para resolver este problema. 
   - c. Diseñe e implemente los algoritmos que proporcionen la funcionalidad necesaria para el sistema software planteado. 

