package Ejercicio04;

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

    public void invertirDatos() {
        Nodo izquierda = cabeza;
        Nodo derecha = cola;

        while (izquierda != null && derecha != null
                && izquierda != derecha
                && izquierda.anterior != derecha) {
            int temporal = izquierda.dato;
            izquierda.dato = derecha.dato;
            derecha.dato = temporal;

            izquierda = izquierda.siguiente;
            derecha = derecha.anterior;
        }
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
        ListaDoble lista = new ListaDoble();

        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        System.out.print("Lista original: ");
        lista.mostrar();

        lista.invertirDatos();

        System.out.print("Lista invertida: ");
        lista.mostrar();
    }
}

