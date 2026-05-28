package Ejercicio09;

import java.util.Scanner;

class NodoSoldado {
    String nombre;
    NodoSoldado siguiente;

    NodoSoldado(String nombre) {
        this.nombre = nombre;
    }
}

class ListaSoldados {
    private NodoSoldado cabeza;
    private NodoSoldado cola;
    private int tamanio;

    public void agregar(String nombre) {
        NodoSoldado nuevo = new NodoSoldado(nombre);

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

    public void resolverJose(int n) {
        if (tamanio == 0) {
            System.out.println("No se ingresaron soldados.");
            return;
        }

        if (n <= 0) {
            System.out.println("El numero n debe ser mayor que cero.");
            return;
        }

        NodoSoldado actual = cabeza;
        NodoSoldado anterior = cola;

        System.out.println("Orden de eliminacion:");

        while (tamanio > 1) {
            for (int i = 1; i < n; i++) {
                anterior = actual;
                actual = actual.siguiente;
            }

            System.out.println(actual.nombre);

            if (actual == cabeza) {
                cabeza = actual.siguiente;
            }

            if (actual == cola) {
                cola = anterior;
            }

            anterior.siguiente = actual.siguiente;
            actual = actual.siguiente;
            tamanio--;
        }

        System.out.println("Soldado que escapa: " + actual.nombre);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaSoldados soldados = new ListaSoldados();

        System.out.print("Ingrese el numero n para contar: ");
        int n = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese los nombres de los soldados en orden. Escriba FIN para terminar:");
        while (true) {
            String nombre = scanner.nextLine();

            if (nombre.equalsIgnoreCase("FIN")) {
                break;
            }

            soldados.agregar(nombre);
        }

        soldados.resolverJose(n);

        scanner.close();
    }
}

