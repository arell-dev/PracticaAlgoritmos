package Ejercicio06;

import java.util.Random;
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

    public void eliminarFueraDeRango(int minimo, int maximo) {
        Nodo actual = cabeza;

        while (actual != null) {
            Nodo siguiente = actual.siguiente;

            if (actual.dato < minimo || actual.dato > maximo) {
                eliminarNodo(actual);
            }

            actual = siguiente;
        }
    }

    private void eliminarNodo(Nodo nodo) {
        if (nodo == cabeza && nodo == cola) {
            cabeza = null;
            cola = null;
        } else if (nodo == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        } else if (nodo == cola) {
            cola = cola.anterior;
            cola.siguiente = null;
        } else {
            nodo.anterior.siguiente = nodo.siguiente;
            nodo.siguiente.anterior = nodo.anterior;
        }
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("La lista quedo vacia.");
            return;
        }

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
        Random random = new Random();
        ListaDoble lista = new ListaDoble();

        for (int i = 0; i < 50; i++) {
            lista.insertarAlFinal(random.nextInt(999) + 1);
        }

        System.out.println("Lista generada:");
        lista.mostrar();

        System.out.print("Ingrese el limite inferior del rango: ");
        int minimo = scanner.nextInt();

        System.out.print("Ingrese el limite superior del rango: ");
        int maximo = scanner.nextInt();

        if (minimo > maximo) {
            int temporal = minimo;
            minimo = maximo;
            maximo = temporal;
        }

        lista.eliminarFueraDeRango(minimo, maximo);

        System.out.println("Lista despues de eliminar datos fuera de [" + minimo + ", " + maximo + "]:");
        lista.mostrar();

        scanner.close();
    }
}

