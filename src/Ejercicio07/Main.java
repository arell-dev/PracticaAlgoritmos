package Ejercicio07;

import java.util.Scanner;

class Estudiante {
    String codigo;
    String nombre;
    String apellidos;
    String correo;
    double nota;

    Estudiante(String codigo, String nombre, String apellidos, String correo, double nota) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.nota = nota;
    }

    boolean estaAprobado() {
        return nota >= 11;
    }

    void mostrar() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre + " " + apellidos);
        System.out.println("Correo: " + correo);
        System.out.println("Nota: " + nota);
        System.out.println("Estado: " + (estaAprobado() ? "Aprobado" : "Desaprobado"));
    }
}

class NodoEstudiante {
    Estudiante estudiante;
    NodoEstudiante anterior;
    NodoEstudiante siguiente;

    NodoEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}

class ListaEstudiantes {
    private NodoEstudiante cabeza;
    private NodoEstudiante cola;

    public void agregar(Estudiante estudiante) {
        if (estudiante.estaAprobado()) {
            insertarAlInicio(estudiante);
        } else {
            insertarAlFinal(estudiante);
        }
    }

    private void insertarAlInicio(Estudiante estudiante) {
        NodoEstudiante nuevo = new NodoEstudiante(estudiante);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cabeza = nuevo;
        }
    }

    private void insertarAlFinal(Estudiante estudiante) {
        NodoEstudiante nuevo = new NodoEstudiante(estudiante);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }

    public Estudiante buscarPorCodigo(String codigo) {
        NodoEstudiante actual = cabeza;

        while (actual != null) {
            if (actual.estudiante.codigo.equalsIgnoreCase(codigo)) {
                return actual.estudiante;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public boolean eliminarPorCodigo(String codigo) {
        NodoEstudiante actual = cabeza;

        while (actual != null) {
            if (actual.estudiante.codigo.equalsIgnoreCase(codigo)) {
                eliminarNodo(actual);
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    private void eliminarNodo(NodoEstudiante nodo) {
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

    public int contarAprobados() {
        int contador = 0;
        NodoEstudiante actual = cabeza;

        while (actual != null) {
            if (actual.estudiante.estaAprobado()) {
                contador++;
            }
            actual = actual.siguiente;
        }

        return contador;
    }

    public int contarDesaprobados() {
        int contador = 0;
        NodoEstudiante actual = cabeza;

        while (actual != null) {
            if (!actual.estudiante.estaAprobado()) {
                contador++;
            }
            actual = actual.siguiente;
        }

        return contador;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        NodoEstudiante actual = cabeza;

        while (actual != null) {
            System.out.println("--------------------");
            actual.estudiante.mostrar();
            actual = actual.siguiente;
        }
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ListaEstudiantes registro = new ListaEstudiantes();
        int opcion;

        do {
            System.out.println("\n--- Registro de estudiantes ---");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Buscar estudiante por codigo");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Total de estudiantes aprobados");
            System.out.println("5. Total de estudiantes desaprobados");
            System.out.println("6. Mostrar registro");
            System.out.println("0. Salir");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1 -> agregarEstudiante(registro);
                case 2 -> buscarEstudiante(registro);
                case 3 -> eliminarEstudiante(registro);
                case 4 -> System.out.println("Aprobados: " + registro.contarAprobados());
                case 5 -> System.out.println("Desaprobados: " + registro.contarDesaprobados());
                case 6 -> registro.mostrar();
                case 0 -> System.out.println("Fin del programa.");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private static void agregarEstudiante(ListaEstudiantes registro) {
        String codigo = leerTexto("Codigo: ");
        String nombre = leerTexto("Nombre: ");
        String apellidos = leerTexto("Apellidos: ");
        String correo = leerTexto("Correo: ");
        double nota = leerDouble("Nota: ");

        Estudiante estudiante = new Estudiante(codigo, nombre, apellidos, correo, nota);
        registro.agregar(estudiante);
        System.out.println("Estudiante agregado.");
    }

    private static void buscarEstudiante(ListaEstudiantes registro) {
        String codigo = leerTexto("Codigo a buscar: ");
        Estudiante estudiante = registro.buscarPorCodigo(codigo);

        if (estudiante == null) {
            System.out.println("No se encontro el estudiante.");
        } else {
            estudiante.mostrar();
        }
    }

    private static void eliminarEstudiante(ListaEstudiantes registro) {
        String codigo = leerTexto("Codigo a eliminar: ");

        if (registro.eliminarPorCodigo(codigo)) {
            System.out.println("Estudiante eliminado.");
        } else {
            System.out.println("No se encontro el estudiante.");
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

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una nota valida.");
            }
        }
    }
}

