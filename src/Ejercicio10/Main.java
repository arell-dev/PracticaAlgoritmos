package Ejercicio10;

import java.util.Scanner;

class Postre {
    String nombre;
    ListaIngredientes ingredientes;

    Postre(String nombre) {
        this.nombre = nombre;
        this.ingredientes = new ListaIngredientes();
    }
}

class SistemaPostres {
    private final Postre[] postres = new Postre[20];
    private int cantidad;

    public boolean agregarPostre(Postre postre) {
        if (cantidad == postres.length || buscarPostre(postre.nombre) != null) {
            return false;
        }

        postres[cantidad] = postre;
        cantidad++;
        return true;
    }

    public Postre buscarPostre(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (postres[i].nombre.equalsIgnoreCase(nombre)) {
                return postres[i];
            }
        }

        return null;
    }

    public boolean eliminarPostre(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (postres[i].nombre.equalsIgnoreCase(nombre)) {
                for (int j = i; j < cantidad - 1; j++) {
                    postres[j] = postres[j + 1];
                }

                postres[cantidad - 1] = null;
                cantidad--;
                return true;
            }
        }

        return false;
    }

    public void mostrarPostres() {
        if (cantidad == 0) {
            System.out.println("No hay postres registrados.");
            return;
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println("- " + postres[i].nombre);
        }
    }
}

class NodoIngrediente {
    String nombre;
    NodoIngrediente siguiente;

    NodoIngrediente(String nombre) {
        this.nombre = nombre;
    }
}

class ListaIngredientes {
    private NodoIngrediente cabeza;
    private NodoIngrediente cola;

    public void agregarAlFinal(String nombre) {
        NodoIngrediente nuevo = new NodoIngrediente(nombre);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public boolean eliminar(String nombre) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.nombre.equalsIgnoreCase(nombre)) {
            cabeza = cabeza.siguiente;

            if (cabeza == null) {
                cola = null;
            }

            return true;
        }

        NodoIngrediente anterior = cabeza;
        NodoIngrediente actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.nombre.equalsIgnoreCase(nombre)) {
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

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("No tiene ingredientes.");
            return;
        }

        NodoIngrediente actual = cabeza;

        while (actual != null) {
            System.out.println("- " + actual.nombre);
            actual = actual.siguiente;
        }
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaPostres sistema = new SistemaPostres();
        int opcion;

        do {
            System.out.println("\n--- Sistema de postres ---");
            System.out.println("1. Ingresar un postre con ingredientes");
            System.out.println("2. Mostrar ingredientes de un postre");
            System.out.println("3. Insertar ingredientes a un postre");
            System.out.println("4. Eliminar ingrediente de un postre");
            System.out.println("5. Eliminar postre");
            System.out.println("6. Mostrar postres");
            System.out.println("0. Salir");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> ingresarPostre(sistema);
                case 2 -> mostrarIngredientes(sistema);
                case 3 -> insertarIngredientes(sistema);
                case 4 -> eliminarIngrediente(sistema);
                case 5 -> eliminarPostre(sistema);
                case 6 -> sistema.mostrarPostres();
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private static void ingresarPostre(SistemaPostres sistema) {
        String nombre = leerTexto("Nombre del postre: ");
        Postre postre = new Postre(nombre);
        leerIngredientes(postre.ingredientes);

        if (sistema.agregarPostre(postre)) {
            System.out.println("Postre agregado.");
        } else {
            System.out.println("No se pudo agregar el postre.");
        }
    }

    private static void mostrarIngredientes(SistemaPostres sistema) {
        Postre postre = sistema.buscarPostre(leerTexto("Nombre del postre: "));

        if (postre == null) {
            System.out.println("Postre no encontrado.");
        } else {
            postre.ingredientes.mostrar();
        }
    }

    private static void insertarIngredientes(SistemaPostres sistema) {
        Postre postre = sistema.buscarPostre(leerTexto("Nombre del postre: "));

        if (postre == null) {
            System.out.println("Postre no encontrado.");
            return;
        }

        leerIngredientes(postre.ingredientes);
    }

    private static void eliminarIngrediente(SistemaPostres sistema) {
        Postre postre = sistema.buscarPostre(leerTexto("Nombre del postre: "));

        if (postre == null) {
            System.out.println("Postre no encontrado.");
            return;
        }

        String ingrediente = leerTexto("Ingrediente a eliminar: ");
        if (postre.ingredientes.eliminar(ingrediente)) {
            System.out.println("Ingrediente eliminado.");
        } else {
            System.out.println("Ingrediente no encontrado.");
        }
    }

    private static void eliminarPostre(SistemaPostres sistema) {
        String nombre = leerTexto("Nombre del postre a eliminar: ");

        if (sistema.eliminarPostre(nombre)) {
            System.out.println("Postre eliminado con sus ingredientes.");
        } else {
            System.out.println("Postre no encontrado.");
        }
    }

    private static void leerIngredientes(ListaIngredientes ingredientes) {
        System.out.println("Ingrese ingredientes. Escriba FIN para terminar:");

        while (true) {
            String ingrediente = scanner.nextLine();

            if (ingrediente.equalsIgnoreCase("FIN")) {
                break;
            }

            ingredientes.agregarAlFinal(ingrediente);
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

