package Ejercicio01;

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

    public void recorrerAdelante() {
        Nodo actual = cabeza;

        System.out.print("Recorrido hacia adelante: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public void recorrerAtras() {
        Nodo actual = cola;

        System.out.print("Recorrido hacia atras: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.anterior;
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        ListaDoble lista = new ListaDoble();

        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        lista.recorrerAdelante();
        lista.recorrerAtras();
    }
}

