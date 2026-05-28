package Ejercicio03;

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
    private int tamanio;

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

        tamanio++;
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean mismoContenido(ListaDoble otra) {
        Nodo actualA = cabeza;
        Nodo actualB = otra.cabeza;

        while (actualA != null && actualB != null) {
            if (actualA.dato != actualB.dato) {
                return false;
            }

            actualA = actualA.siguiente;
            actualB = actualB.siguiente;
        }

        return actualA == null && actualB == null;
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
        ListaDoble listaA = new ListaDoble();
        ListaDoble listaB = new ListaDoble();

        listaA.insertarAlFinal(8);
        listaA.insertarAlFinal(15);
        listaA.insertarAlFinal(20);

        listaB.insertarAlFinal(8);
        listaB.insertarAlFinal(15);
        listaB.insertarAlFinal(25);

        System.out.print("Lista A: ");
        listaA.mostrar();

        System.out.print("Lista B: ");
        listaB.mostrar();

        compararListas(listaA, listaB);
    }

    private static void compararListas(ListaDoble listaA, ListaDoble listaB) {
        if (listaA.tamanio() == listaB.tamanio() && listaA.mismoContenido(listaB)) {
            System.out.println("a. Las listas son iguales en tamanio y contenido.");
        } else if (listaA.tamanio() == listaB.tamanio()) {
            System.out.println("b. Las listas son iguales en tamanio, pero no en contenido.");
        } else {
            System.out.println("c. No tienen el mismo tamanio ni contenido.");
        }
    }
}

