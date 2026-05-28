package Ejercicio02;

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

    public boolean contiene(int dato) {
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    public ListaDoble union(ListaDoble otra) {
        ListaDoble resultado = new ListaDoble();
        Nodo actual = cabeza;

        while (actual != null) {
            if (!resultado.contiene(actual.dato)) {
                resultado.insertarAlFinal(actual.dato);
            }
            actual = actual.siguiente;
        }

        actual = otra.cabeza;
        while (actual != null) {
            if (!resultado.contiene(actual.dato)) {
                resultado.insertarAlFinal(actual.dato);
            }
            actual = actual.siguiente;
        }

        return resultado;
    }

    public ListaDoble interseccion(ListaDoble otra) {
        ListaDoble resultado = new ListaDoble();
        Nodo actual = cabeza;

        while (actual != null) {
            if (otra.contiene(actual.dato) && !resultado.contiene(actual.dato)) {
                resultado.insertarAlFinal(actual.dato);
            }
            actual = actual.siguiente;
        }

        return resultado;
    }

    public ListaDoble diferencia(ListaDoble otra) {
        ListaDoble resultado = new ListaDoble();
        Nodo actual = cabeza;

        while (actual != null) {
            if (!otra.contiene(actual.dato) && !resultado.contiene(actual.dato)) {
                resultado.insertarAlFinal(actual.dato);
            }
            actual = actual.siguiente;
        }

        return resultado;
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

        listaA.insertarAlFinal(1);
        listaA.insertarAlFinal(2);
        listaA.insertarAlFinal(3);
        listaA.insertarAlFinal(4);

        listaB.insertarAlFinal(3);
        listaB.insertarAlFinal(4);
        listaB.insertarAlFinal(5);
        listaB.insertarAlFinal(6);

        System.out.print("Lista A: ");
        listaA.mostrar();

        System.out.print("Lista B: ");
        listaB.mostrar();

        System.out.print("Union: ");
        listaA.union(listaB).mostrar();

        System.out.print("Interseccion: ");
        listaA.interseccion(listaB).mostrar();

        System.out.print("Diferencia A - B: ");
        listaA.diferencia(listaB).mostrar();

        System.out.print("Diferencia B - A: ");
        listaB.diferencia(listaA).mostrar();
    }
}

