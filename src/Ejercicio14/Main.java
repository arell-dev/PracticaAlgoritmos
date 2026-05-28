package Ejercicio14;

import java.util.Scanner;

class Biblioteca {
    private ListaLibros libros = new ListaLibros();
    private ListaUsuarios usuarios = new ListaUsuarios();

    public void agregarLibro(Libro libro) {
        libros.agregar(libro);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.agregar(usuario);
    }

    public void prestarLibro(String dni, String titulo, String fechaPrestamo, String fechaDevolucion) {
        Usuario usuario = usuarios.buscarPorDni(dni);
        Libro libro = libros.buscarPorTitulo(titulo);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        if (!usuario.puedePrestar()) {
            System.out.println("El usuario llego al maximo de prestamos.");
            return;
        }

        Ejemplar ejemplar = libro.buscarDisponible();

        if (ejemplar == null) {
            System.out.println("No hay ejemplares disponibles.");
            return;
        }

        ejemplar.prestar(usuario, fechaPrestamo, fechaDevolucion);
        usuario.agregarPrestamo(ejemplar);
        System.out.println("Prestamo registrado.");
    }

    public boolean devolverLibro(String signatura) {
        Ejemplar ejemplar = libros.buscarEjemplarPorSignatura(signatura);

        if (ejemplar == null || ejemplar.disponible || ejemplar.usuarioPrestamo == null) {
            return false;
        }

        Usuario usuario = ejemplar.usuarioPrestamo;
        usuario.quitarPrestamo(signatura);
        ejemplar.devolver();
        return true;
    }

    public void mostrarLibrosPrestados(String dni) {
        Usuario usuario = usuarios.buscarPorDni(dni);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            usuario.mostrarPrestamos();
        }
    }

    public void mostrarCatalogo() {
        libros.mostrar();
    }
}

class Libro {
    String titulo;
    String autor;
    String editorial;
    int anioPublicacion;
    ListaEjemplares ejemplares = new ListaEjemplares();

    Libro(String titulo, String autor, String editorial, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
    }

    public void agregarEjemplar(Ejemplar ejemplar) {
        ejemplar.libro = this;
        ejemplares.agregar(ejemplar);
    }

    public Ejemplar buscarDisponible() {
        return ejemplares.buscarDisponible();
    }

    public Ejemplar buscarPorSignatura(String signatura) {
        return ejemplares.buscarPorSignatura(signatura);
    }

    public void mostrar() {
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Editorial: " + editorial);
        System.out.println("Anio: " + anioPublicacion);
        System.out.println("Numero de ejemplares: " + ejemplares.contar());
        ejemplares.mostrar();
    }
}

class Ejemplar {
    Libro libro;
    String signatura;
    boolean disponible = true;
    String estanteria;
    String fechaPrestamo;
    String fechaDevolucion;
    Usuario usuarioPrestamo;

    Ejemplar(String signatura, String estanteria) {
        this.signatura = signatura;
        this.estanteria = estanteria;
    }

    public void prestar(Usuario usuario, String fechaPrestamo, String fechaDevolucion) {
        disponible = false;
        usuarioPrestamo = usuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public void devolver() {
        disponible = true;
        usuarioPrestamo = null;
        fechaPrestamo = null;
        fechaDevolucion = null;
    }

    public void mostrar() {
        System.out.println("  Signatura: " + signatura);

        if (disponible) {
            System.out.println("  Disponible en " + estanteria);
        } else {
            System.out.println("  Prestado a " + usuarioPrestamo.nombre + " " + usuarioPrestamo.apellidos);
            System.out.println("  Prestamo: " + fechaPrestamo + " / Devolucion: " + fechaDevolucion);
        }
    }
}

class Usuario {
    String nombre;
    String apellidos;
    String direccion;
    String dni;
    int maximoPrestamos;
    ListaPrestamos prestamos = new ListaPrestamos();

    Usuario(String nombre, String apellidos, String direccion, String dni, int maximoPrestamos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.maximoPrestamos = maximoPrestamos;
    }

    public boolean puedePrestar() {
        return prestamos.contar() < maximoPrestamos;
    }

    public void agregarPrestamo(Ejemplar ejemplar) {
        prestamos.agregar(ejemplar);
    }

    public void quitarPrestamo(String signatura) {
        prestamos.eliminarPorSignatura(signatura);
    }

    public void mostrarPrestamos() {
        System.out.println("Usuario: " + nombre + " " + apellidos);
        System.out.println("DNI: " + dni);
        System.out.println("Direccion: " + direccion);
        prestamos.mostrar();
    }
}

class NodoLibro {
    Libro libro;
    NodoLibro siguiente;

    NodoLibro(Libro libro) {
        this.libro = libro;
    }
}

class ListaLibros {
    private NodoLibro cabeza;
    private NodoLibro cola;

    public void agregar(Libro libro) {
        NodoLibro nuevo = new NodoLibro(libro);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Libro buscarPorTitulo(String titulo) {
        NodoLibro actual = cabeza;

        while (actual != null) {
            if (actual.libro.titulo.equalsIgnoreCase(titulo)) {
                return actual.libro;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public Ejemplar buscarEjemplarPorSignatura(String signatura) {
        NodoLibro actual = cabeza;

        while (actual != null) {
            Ejemplar encontrado = actual.libro.buscarPorSignatura(signatura);

            if (encontrado != null) {
                return encontrado;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public void mostrar() {
        NodoLibro actual = cabeza;

        while (actual != null) {
            System.out.println("--------------------");
            actual.libro.mostrar();
            actual = actual.siguiente;
        }
    }
}

class NodoEjemplar {
    Ejemplar ejemplar;
    NodoEjemplar siguiente;

    NodoEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
}

class ListaEjemplares {
    private NodoEjemplar cabeza;
    private NodoEjemplar cola;

    public void agregar(Ejemplar ejemplar) {
        NodoEjemplar nuevo = new NodoEjemplar(ejemplar);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Ejemplar buscarDisponible() {
        NodoEjemplar actual = cabeza;

        while (actual != null) {
            if (actual.ejemplar.disponible) {
                return actual.ejemplar;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public Ejemplar buscarPorSignatura(String signatura) {
        NodoEjemplar actual = cabeza;

        while (actual != null) {
            if (actual.ejemplar.signatura.equalsIgnoreCase(signatura)) {
                return actual.ejemplar;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public int contar() {
        int total = 0;
        NodoEjemplar actual = cabeza;

        while (actual != null) {
            total++;
            actual = actual.siguiente;
        }

        return total;
    }

    public void mostrar() {
        NodoEjemplar actual = cabeza;

        while (actual != null) {
            actual.ejemplar.mostrar();
            actual = actual.siguiente;
        }
    }
}

class NodoUsuario {
    Usuario usuario;
    NodoUsuario siguiente;

    NodoUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

class ListaUsuarios {
    private NodoUsuario cabeza;
    private NodoUsuario cola;

    public void agregar(Usuario usuario) {
        NodoUsuario nuevo = new NodoUsuario(usuario);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Usuario buscarPorDni(String dni) {
        NodoUsuario actual = cabeza;

        while (actual != null) {
            if (actual.usuario.dni.equalsIgnoreCase(dni)) {
                return actual.usuario;
            }
            actual = actual.siguiente;
        }

        return null;
    }
}

class NodoPrestamo {
    Ejemplar ejemplar;
    NodoPrestamo siguiente;

    NodoPrestamo(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
}

class ListaPrestamos {
    private NodoPrestamo cabeza;
    private NodoPrestamo cola;

    public void agregar(Ejemplar ejemplar) {
        NodoPrestamo nuevo = new NodoPrestamo(ejemplar);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public boolean eliminarPorSignatura(String signatura) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.ejemplar.signatura.equalsIgnoreCase(signatura)) {
            cabeza = cabeza.siguiente;

            if (cabeza == null) {
                cola = null;
            }

            return true;
        }

        NodoPrestamo anterior = cabeza;
        NodoPrestamo actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.ejemplar.signatura.equalsIgnoreCase(signatura)) {
                anterior.siguiente = actual.siguiente;

                if (actual == cola) {
                    cola = anterior;
                }

                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    public int contar() {
        int total = 0;
        NodoPrestamo actual = cabeza;

        while (actual != null) {
            total++;
            actual = actual.siguiente;
        }

        return total;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("No tiene libros prestados.");
            return;
        }

        NodoPrestamo actual = cabeza;

        while (actual != null) {
            Ejemplar ejemplar = actual.ejemplar;
            System.out.println("- " + ejemplar.libro.titulo + " | Signatura: " + ejemplar.signatura
                    + " | Prestamo: " + ejemplar.fechaPrestamo
                    + " | Devolucion: " + ejemplar.fechaDevolucion);
            actual = actual.siguiente;
        }
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Biblioteca biblioteca = crearBibliotecaDeEjemplo();
        int opcion;

        do {
            System.out.println("\n--- Biblioteca de facultad ---");
            System.out.println("1. Mostrar grafico del diseno");
            System.out.println("2. Mostrar catalogo");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro por signatura");
            System.out.println("5. Mostrar prestamos de una persona");
            System.out.println("0. Salir");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> mostrarGrafico();
                case 2 -> biblioteca.mostrarCatalogo();
                case 3 -> prestar(biblioteca);
                case 4 -> devolver(biblioteca);
                case 5 -> biblioteca.mostrarLibrosPrestados(leerTexto("DNI: "));
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private static void mostrarGrafico() {
        System.out.println("Biblioteca -> Libros -> Ejemplares");
        System.out.println("Biblioteca -> Usuarios -> Prestamos");
        System.out.println("Ejemplar disponible -> estanteria");
        System.out.println("Ejemplar prestado -> fechas y usuario");
    }

    private static Biblioteca crearBibliotecaDeEjemplo() {
        Biblioteca biblioteca = new Biblioteca();

        Usuario alumno = new Usuario("Pedro", "Diaz", "Av. Peru 111", "11111111", 3);
        Usuario profesora = new Usuario("Diana", "Lopez", "Jr. Sol 222", "22222222", 5);

        Libro algoritmos = new Libro("Algoritmos", "Nora Paz", "Editorial Alfa", 2023);
        algoritmos.agregarEjemplar(new Ejemplar("ALG-001", "Estanteria C1"));
        algoritmos.agregarEjemplar(new Ejemplar("ALG-002", "Estanteria C1"));

        Libro bases = new Libro("Bases de Datos", "Mario Ruiz", "Editorial Beta", 2021);
        bases.agregarEjemplar(new Ejemplar("BD-001", "Estanteria D3"));

        biblioteca.agregarUsuario(alumno);
        biblioteca.agregarUsuario(profesora);
        biblioteca.agregarLibro(algoritmos);
        biblioteca.agregarLibro(bases);

        return biblioteca;
    }

    private static void prestar(Biblioteca biblioteca) {
        String dni = leerTexto("DNI del usuario: ");
        String titulo = leerTexto("Titulo del libro: ");
        String fechaPrestamo = leerTexto("Fecha de prestamo: ");
        String fechaDevolucion = leerTexto("Fecha de devolucion: ");

        biblioteca.prestarLibro(dni, titulo, fechaPrestamo, fechaDevolucion);
    }

    private static void devolver(Biblioteca biblioteca) {
        String signatura = leerTexto("Signatura del ejemplar: ");

        if (biblioteca.devolverLibro(signatura)) {
            System.out.println("Libro devuelto.");
        } else {
            System.out.println("No se encontro un prestamo con esa signatura.");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero.");
            }
        }
    }
}

