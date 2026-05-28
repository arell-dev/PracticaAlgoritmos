package Ejercicio12;

class FeriaLibro {
    private ListaCasetas casetas = new ListaCasetas();
    private ListaFirmas firmas = new ListaFirmas();

    public void agregarCaseta(Caseta caseta) {
        casetas.agregar(caseta);
    }

    public void agregarFirma(Firma firma) {
        firmas.agregar(firma);
    }

    public void mostrarLibrosDeCaseta(String nombreCaseta) {
        Caseta caseta = casetas.buscar(nombreCaseta);

        if (caseta == null) {
            System.out.println("No se encontro la caseta.");
        } else {
            caseta.libros.mostrar();
        }
    }

    public void mostrarCasetasConLibro(String titulo) {
        casetas.mostrarCasetasConLibro(titulo);
    }

    public void mostrarFirmaAutor(String autor) {
        firmas.mostrarFirmaAutor(autor);
    }

    public void mostrarAutoresFirmantes() {
        firmas.mostrarAutores();
    }

    public void mostrarTop10Vendidos() {
        LibroVendido[] libros = new LibroVendido[100];
        int cantidad = casetas.llenarLibrosVendidos(libros, 0);

        ordenarPorVentas(libros, cantidad);

        int limite = cantidad < 10 ? cantidad : 10;
        for (int i = 0; i < limite; i++) {
            System.out.println((i + 1) + ". " + libros[i].titulo + " - " + libros[i].vendidos
                    + " vendidos (" + libros[i].caseta + ")");
        }
    }

    private void ordenarPorVentas(LibroVendido[] libros, int cantidad) {
        for (int i = 0; i < cantidad - 1; i++) {
            int mayor = i;

            for (int j = i + 1; j < cantidad; j++) {
                if (libros[j].vendidos > libros[mayor].vendidos) {
                    mayor = j;
                }
            }

            LibroVendido temporal = libros[i];
            libros[i] = libros[mayor];
            libros[mayor] = temporal;
        }
    }
}

class Caseta {
    String nombre;
    ListaLibros libros = new ListaLibros();

    Caseta(String nombre) {
        this.nombre = nombre;
    }

    public void agregarLibro(Libro libro) {
        libros.agregar(libro);
    }
}

class NodoCaseta {
    Caseta caseta;
    NodoCaseta siguiente;

    NodoCaseta(Caseta caseta) {
        this.caseta = caseta;
    }
}

class ListaCasetas {
    private NodoCaseta cabeza;
    private NodoCaseta cola;

    public void agregar(Caseta caseta) {
        NodoCaseta nuevo = new NodoCaseta(caseta);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Caseta buscar(String nombre) {
        NodoCaseta actual = cabeza;

        while (actual != null) {
            if (actual.caseta.nombre.equalsIgnoreCase(nombre)) {
                return actual.caseta;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public void mostrarCasetasConLibro(String titulo) {
        NodoCaseta actual = cabeza;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.caseta.libros.contiene(titulo)) {
                System.out.println("- " + actual.caseta.nombre);
                encontrado = true;
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("No se encontro el libro.");
        }
    }

    public int llenarLibrosVendidos(LibroVendido[] arreglo, int posicion) {
        NodoCaseta actual = cabeza;
        int indice = posicion;

        while (actual != null) {
            indice = actual.caseta.libros.llenarVendidos(arreglo, indice, actual.caseta.nombre);
            actual = actual.siguiente;
        }

        return indice;
    }
}

class Libro {
    String titulo;
    int vendidos;

    Libro(String titulo, int vendidos) {
        this.titulo = titulo;
        this.vendidos = vendidos;
    }
}

class LibroVendido {
    String titulo;
    String caseta;
    int vendidos;

    LibroVendido(String titulo, String caseta, int vendidos) {
        this.titulo = titulo;
        this.caseta = caseta;
        this.vendidos = vendidos;
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

    public boolean contiene(String titulo) {
        NodoLibro actual = cabeza;

        while (actual != null) {
            if (actual.libro.titulo.equalsIgnoreCase(titulo)) {
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    public void mostrar() {
        NodoLibro actual = cabeza;

        while (actual != null) {
            System.out.println("- " + actual.libro.titulo + " (" + actual.libro.vendidos + " vendidos)");
            actual = actual.siguiente;
        }
    }

    public int llenarVendidos(LibroVendido[] arreglo, int posicion, String nombreCaseta) {
        NodoLibro actual = cabeza;
        int indice = posicion;

        while (actual != null && indice < arreglo.length) {
            int posicionExistente = buscarVendido(arreglo, indice, actual.libro.titulo);

            if (posicionExistente == -1) {
                arreglo[indice] = new LibroVendido(actual.libro.titulo, nombreCaseta, actual.libro.vendidos);
                indice++;
            } else {
                arreglo[posicionExistente].vendidos += actual.libro.vendidos;
                arreglo[posicionExistente].caseta += ", " + nombreCaseta;
            }

            actual = actual.siguiente;
        }

        return indice;
    }

    private int buscarVendido(LibroVendido[] arreglo, int cantidad, String titulo) {
        for (int i = 0; i < cantidad; i++) {
            if (arreglo[i].titulo.equalsIgnoreCase(titulo)) {
                return i;
            }
        }

        return -1;
    }
}

class Firma {
    String autor;
    String tituloLibro;
    String hora;
    String caseta;

    Firma(String autor, String tituloLibro, String hora, String caseta) {
        this.autor = autor;
        this.tituloLibro = tituloLibro;
        this.hora = hora;
        this.caseta = caseta;
    }
}

class NodoFirma {
    Firma firma;
    NodoFirma siguiente;

    NodoFirma(Firma firma) {
        this.firma = firma;
    }
}

class ListaFirmas {
    private NodoFirma cabeza;
    private NodoFirma cola;

    public void agregar(Firma firma) {
        NodoFirma nuevo = new NodoFirma(firma);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public void mostrarFirmaAutor(String autor) {
        NodoFirma actual = cabeza;
        boolean encontrado = false;

        while (actual != null) {
            if (actual.firma.autor.equalsIgnoreCase(autor)) {
                System.out.println("- " + actual.firma.autor + " firma " + actual.firma.tituloLibro
                        + " a las " + actual.firma.hora + " en " + actual.firma.caseta);
                encontrado = true;
            }
            actual = actual.siguiente;
        }

        if (!encontrado) {
            System.out.println("No se encontraron firmas para ese autor.");
        }
    }

    public void mostrarAutores() {
        String[] autoresMostrados = new String[50];
        int cantidad = 0;
        NodoFirma actual = cabeza;

        while (actual != null) {
            if (!yaFueMostrado(autoresMostrados, cantidad, actual.firma.autor)) {
                autoresMostrados[cantidad] = actual.firma.autor;
                cantidad++;
                System.out.println("- " + actual.firma.autor);
            }
            actual = actual.siguiente;
        }
    }

    private boolean yaFueMostrado(String[] autores, int cantidad, String autor) {
        for (int i = 0; i < cantidad; i++) {
            if (autores[i].equalsIgnoreCase(autor)) {
                return true;
            }
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        FeriaLibro feria = crearFeriaDeEjemplo();

        System.out.println("a. Libros en la Caseta Norte:");
        feria.mostrarLibrosDeCaseta("Caseta Norte");

        System.out.println("\nb. Casetas donde se compra 'Algoritmos Basicos':");
        feria.mostrarCasetasConLibro("Algoritmos Basicos");

        System.out.println("\nc. Horarios de firma de Ana Torres:");
        feria.mostrarFirmaAutor("Ana Torres");

        System.out.println("\nd. Autores que firman libros:");
        feria.mostrarAutoresFirmantes();

        System.out.println("\ne. Los 10 libros mas vendidos:");
        feria.mostrarTop10Vendidos();
    }

    private static FeriaLibro crearFeriaDeEjemplo() {
        FeriaLibro feria = new FeriaLibro();

        Caseta norte = new Caseta("Caseta Norte");
        norte.agregarLibro(new Libro("Estructuras en Java", 35));
        norte.agregarLibro(new Libro("Algoritmos Basicos", 48));
        norte.agregarLibro(new Libro("Programacion Clara", 21));

        Caseta sur = new Caseta("Caseta Sur");
        sur.agregarLibro(new Libro("Poesia de Lima", 17));
        sur.agregarLibro(new Libro("Algoritmos Basicos", 28));
        sur.agregarLibro(new Libro("Base de Datos Inicial", 33));

        Caseta central = new Caseta("Caseta Central");
        central.agregarLibro(new Libro("Redes para Todos", 40));
        central.agregarLibro(new Libro("Cuentos Cortos", 11));

        feria.agregarCaseta(norte);
        feria.agregarCaseta(sur);
        feria.agregarCaseta(central);

        feria.agregarFirma(new Firma("Ana Torres", "Estructuras en Java", "10:00", "Caseta Norte"));
        feria.agregarFirma(new Firma("Luis Ramos", "Algoritmos Basicos", "12:30", "Caseta Sur"));
        feria.agregarFirma(new Firma("Ana Torres", "Programacion Clara", "16:00", "Caseta Norte"));
        feria.agregarFirma(new Firma("Marta Ruiz", "Redes para Todos", "18:00", "Caseta Central"));

        return feria;
    }
}

