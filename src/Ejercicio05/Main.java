package Ejercicio05;

import java.util.Scanner;

class Nodo {
    int dato;
    Nodo anterior;
    Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
    }
}

class ListaDoble {
    private Nodo cabeza;
    private Nodo cola;

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    public int contarVeces(int datoBuscado) {
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.dato == datoBuscado) {
                contador++;
            }
            actual = actual.siguiente;
        }

        return contador;
    }

    public void mostrar() {
        Nodo actual = cabeza;

        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDoble lista = new ListaDoble();

        lista.insertarAlFinal(5);
        lista.insertarAlFinal(8);
        lista.insertarAlFinal(5);
        lista.insertarAlFinal(12);
        lista.insertarAlFinal(5);
        lista.insertarAlFinal(20);

        System.out.print("Lista: ");
        lista.mostrar();

        System.out.print("Ingrese el dato que desea buscar: ");
        int buscado = scanner.nextInt();

        int veces = lista.contarVeces(buscado);

        if (veces == 0) {
            System.out.println("El dato no fue encontrado.");
        } else {
            System.out.println("El dato " + buscado + " aparece " + veces + " vez/veces.");
        }
    }
}

