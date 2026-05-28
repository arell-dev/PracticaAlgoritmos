package Ejercicio13;

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
            System.out.println("No existe el usuario con DNI " + dni);
            return;
        }

        if (libro == null) {
            System.out.println("No existe el libro " + titulo);
            return;
        }

        if (!usuario.puedePrestar()) {
            System.out.println("El usuario alcanzo su maximo de prestamos.");
            return;
        }

        Ejemplar ejemplar = libro.buscarDisponible();

        if (ejemplar == null) {
            System.out.println("No hay ejemplares disponibles de " + titulo);
            return;
        }

        ejemplar.prestar(usuario, fechaPrestamo, fechaDevolucion);
        usuario.agregarPrestamo(ejemplar);
        System.out.println("Prestamo realizado: " + titulo + " para " + usuario.nombre);
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

    public void mostrar() {
        System.out.println("  Signatura: " + signatura);

        if (disponible) {
            System.out.println("  Estado: disponible en " + estanteria);
        } else {
            System.out.println("  Estado: prestado a " + usuarioPrestamo.nombre + " " + usuarioPrestamo.apellidos);
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
    public static void main(String[] args) {
        mostrarGrafico();

        Biblioteca biblioteca = crearBibliotecaDeEjemplo();

        biblioteca.prestarLibro("87654321", "Programacion Java", "28/05/2026", "04/06/2026");
        biblioteca.prestarLibro("12345678", "Estructuras de Datos", "28/05/2026", "11/06/2026");

        System.out.println("\nCatalogo de libros:");
        biblioteca.mostrarCatalogo();

        System.out.println("\nLibros prestados a la alumna con DNI 87654321:");
        biblioteca.mostrarLibrosPrestados("87654321");

        System.out.println("\nLibros prestados al profesor con DNI 12345678:");
        biblioteca.mostrarLibrosPrestados("12345678");
    }

    private static void mostrarGrafico() {
        System.out.println("Grafico general del sistema:");
        System.out.println("Biblioteca");
        System.out.println("  -> Lista de libros");
        System.out.println("       -> Libro");
        System.out.println("            -> Lista de ejemplares");
        System.out.println("                 -> Ejemplar disponible: estanteria");
        System.out.println("                 -> Ejemplar prestado: fechas y usuario");
        System.out.println("  -> Lista de usuarios");
        System.out.println("       -> Alumno o Profesor");
        System.out.println("            -> Lista de libros prestados");
    }

    private static Biblioteca crearBibliotecaDeEjemplo() {
        Biblioteca biblioteca = new Biblioteca();

        Usuario alumna = new Usuario("Lucia", "Perez", "Av. Los Olivos 123", "87654321", 3);
        Usuario profesor = new Usuario("Marco", "Sanchez", "Jr. Central 456", "12345678", 5);

        Libro java = new Libro("Programacion Java", "Ana Torres", "Editorial Norte", 2024);
        java.agregarEjemplar(new Ejemplar("JAVA-001", "Estanteria A1"));
        java.agregarEjemplar(new Ejemplar("JAVA-002", "Estanteria A1"));

        Libro estructuras = new Libro("Estructuras de Datos", "Luis Ramos", "Editorial Sur", 2022);
        estructuras.agregarEjemplar(new Ejemplar("ED-001", "Estanteria B2"));
        estructuras.agregarEjemplar(new Ejemplar("ED-002", "Estanteria B2"));

        biblioteca.agregarUsuario(alumna);
        biblioteca.agregarUsuario(profesor);
        biblioteca.agregarLibro(java);
        biblioteca.agregarLibro(estructuras);

        return biblioteca;
    }
}

