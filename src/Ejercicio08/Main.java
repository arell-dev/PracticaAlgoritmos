package Ejercicio08;

import java.util.Scanner;

class Nodo {
    int dato;
    Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
    }
}

class ListaCircular {
    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public int tamanio() {
        return tamanio;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }

        tamanio++;
    }

    public boolean eliminarPorValor(int dato) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.dato == dato) {
            if (cabeza == cola) {
                cabeza = null;
                cola = null;
            } else {
                cabeza = cabeza.siguiente;
                cola.siguiente = cabeza;
            }
            tamanio--;
            return true;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;

        while (actual != cabeza) {
            if (actual.dato == dato) {
                anterior.siguiente = actual.siguiente;

                if (actual == cola) {
                    cola = anterior;
                }

                tamanio--;
                return true;
            }

            anterior = actual;
            actual = actual.siguiente;
        }

        return false;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        Nodo actual = cabeza;
        System.out.print("Lista circular: ");

        for (int i = 0; i < tamanio; i++) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }

        System.out.println();
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ListaCircular lista = new ListaCircular();
        int opcion;

        do {
            System.out.println("\n--- Lista simplemente circular ---");
            System.out.println("1. Ingresar un elemento");
            System.out.println("2. Buscar y eliminar un elemento");
            System.out.println("3. Mostrar numero de elementos");
            System.out.println("4. Mostrar todos los elementos");
            System.out.println("0. Salir");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> lista.insertarAlFinal(leerEntero("Dato: "));
                case 2 -> {
                    int dato = leerEntero("Dato a eliminar: ");
                    if (lista.eliminarPorValor(dato)) {
                        System.out.println("Dato eliminado.");
                    } else {
                        System.out.println("Dato no encontrado.");
                    }
                }
                case 3 -> System.out.println("Cantidad de elementos: " + lista.tamanio());
                case 4 -> lista.mostrar();
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero.");
            }
        }
    }
}

