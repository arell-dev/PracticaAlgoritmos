package Ejercicio11;

class Persona {
    String nombre;
    int edad;

    Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}

class NodoPersona {
    Persona persona;
    NodoPersona siguiente;

    NodoPersona(Persona persona) {
        this.persona = persona;
    }
}

class ListaPersonas {
    private NodoPersona cabeza;
    private NodoPersona cola;

    public void agregar(Persona persona) {
        NodoPersona nuevo = new NodoPersona(persona);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public int contar() {
        int total = 0;
        NodoPersona actual = cabeza;

        while (actual != null) {
            total++;
            actual = actual.siguiente;
        }

        return total;
    }

    public void mostrarMayoresDeEdad(String lugar) {
        NodoPersona actual = cabeza;

        while (actual != null) {
            if (actual.persona.edad >= 18) {
                System.out.println("- " + actual.persona.nombre + " (" + actual.persona.edad + " anios) - " + lugar);
            }
            actual = actual.siguiente;
        }
    }
}

class Pueblo {
    String nombre;
    ListaPersonas personas = new ListaPersonas();

    Pueblo(String nombre) {
        this.nombre = nombre;
    }

    public void agregarPersona(Persona persona) {
        personas.agregar(persona);
    }

    public int contarHabitantes() {
        return personas.contar();
    }

    public void mostrarMayoresDeEdad() {
        personas.mostrarMayoresDeEdad(nombre);
    }
}

class NodoPueblo {
    Pueblo pueblo;
    NodoPueblo siguiente;

    NodoPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }
}

class ListaPueblos {
    private NodoPueblo cabeza;
    private NodoPueblo cola;

    public void agregar(Pueblo pueblo) {
        NodoPueblo nuevo = new NodoPueblo(pueblo);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Pueblo buscar(String nombre) {
        NodoPueblo actual = cabeza;

        while (actual != null) {
            if (actual.pueblo.nombre.equalsIgnoreCase(nombre)) {
                return actual.pueblo;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public void mostrarPueblosConMasDe(int habitantes) {
        NodoPueblo actual = cabeza;

        while (actual != null) {
            if (actual.pueblo.contarHabitantes() > habitantes) {
                System.out.println("- " + actual.pueblo.nombre + ": " + actual.pueblo.contarHabitantes() + " habitantes");
            }
            actual = actual.siguiente;
        }
    }

    public void mostrarMayoresDeEdad() {
        NodoPueblo actual = cabeza;

        while (actual != null) {
            actual.pueblo.mostrarMayoresDeEdad();
            actual = actual.siguiente;
        }
    }

    public int contarPersonas() {
        int total = 0;
        NodoPueblo actual = cabeza;

        while (actual != null) {
            total += actual.pueblo.contarHabitantes();
            actual = actual.siguiente;
        }

        return total;
    }
}

class Provincia {
    String nombre;
    ListaPueblos pueblos = new ListaPueblos();

    Provincia(String nombre) {
        this.nombre = nombre;
    }

    public void agregarPueblo(Pueblo pueblo) {
        pueblos.agregar(pueblo);
    }

    public Pueblo buscarPueblo(String nombrePueblo) {
        return pueblos.buscar(nombrePueblo);
    }

    public void mostrarPueblosConMasDe(int habitantes) {
        pueblos.mostrarPueblosConMasDe(habitantes);
    }

    public void mostrarMayoresDeEdad() {
        pueblos.mostrarMayoresDeEdad();
    }

    public int contarPersonas() {
        return pueblos.contarPersonas();
    }
}

class NodoProvincia {
    Provincia provincia;
    NodoProvincia siguiente;

    NodoProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}

class ListaProvincias {
    private NodoProvincia cabeza;
    private NodoProvincia cola;

    public void agregar(Provincia provincia) {
        NodoProvincia nuevo = new NodoProvincia(provincia);

        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
        }
    }

    public Provincia buscar(String nombre) {
        NodoProvincia actual = cabeza;

        while (actual != null) {
            if (actual.provincia.nombre.equalsIgnoreCase(nombre)) {
                return actual.provincia;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    public Pueblo buscarPueblo(String nombrePueblo) {
        NodoProvincia actual = cabeza;

        while (actual != null) {
            Pueblo encontrado = actual.provincia.buscarPueblo(nombrePueblo);

            if (encontrado != null) {
                return encontrado;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public void mostrarPueblosConMasDe(int habitantes) {
        NodoProvincia actual = cabeza;

        while (actual != null) {
            System.out.println("Provincia " + actual.provincia.nombre + ":");
            actual.provincia.mostrarPueblosConMasDe(habitantes);
            actual = actual.siguiente;
        }
    }

    public void mostrarMayoresDeEdad() {
        NodoProvincia actual = cabeza;

        while (actual != null) {
            actual.provincia.mostrarMayoresDeEdad();
            actual = actual.siguiente;
        }
    }

    public int contarPersonas() {
        int total = 0;
        NodoProvincia actual = cabeza;

        while (actual != null) {
            total += actual.provincia.contarPersonas();
            actual = actual.siguiente;
        }

        return total;
    }
}

class Comunidad {
    String nombre;
    ListaProvincias provincias = new ListaProvincias();

    Comunidad(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProvincia(Provincia provincia) {
        provincias.agregar(provincia);
    }

    public Provincia buscarProvincia(String nombreProvincia) {
        return provincias.buscar(nombreProvincia);
    }

    public Pueblo buscarPueblo(String nombrePueblo) {
        return provincias.buscarPueblo(nombrePueblo);
    }

    public void mostrarPueblosConMasDe(int habitantes) {
        provincias.mostrarPueblosConMasDe(habitantes);
    }

    public void mostrarMayoresDeEdad() {
        provincias.mostrarMayoresDeEdad();
    }

    public int contarPersonas() {
        return provincias.contarPersonas();
    }
}

public class Main {
    public static void main(String[] args) {
        Comunidad comunidad = crearDatosDeEjemplo();

        System.out.println("a. Pueblos con mas de 2 habitantes en la comunidad:");
        comunidad.mostrarPueblosConMasDe(2);

        Provincia lima = comunidad.buscarProvincia("Lima");
        System.out.println("\na. Pueblos con mas de 1 habitante en la provincia Lima:");
        if (lima != null) {
            lima.mostrarPueblosConMasDe(1);
        }

        System.out.println("\nb. Habitantes mayores de 18 anios en la comunidad:");
        comunidad.mostrarMayoresDeEdad();

        System.out.println("\nb. Habitantes mayores de 18 anios en el pueblo Barranco:");
        Pueblo barranco = comunidad.buscarPueblo("Barranco");
        if (barranco != null) {
            barranco.mostrarMayoresDeEdad();
        }

        System.out.println("\nc. Numero de personas en la comunidad: " + comunidad.contarPersonas());
        System.out.println("c. Numero de personas en Lima: " + (lima != null ? lima.contarPersonas() : 0));
        System.out.println("c. Numero de personas en Barranco: " + (barranco != null ? barranco.contarHabitantes() : 0));
    }

    private static Comunidad crearDatosDeEjemplo() {
        Comunidad comunidad = new Comunidad("Comunidad Principal");

        Provincia lima = new Provincia("Lima");
        Provincia callao = new Provincia("Callao");

        Pueblo barranco = new Pueblo("Barranco");
        barranco.agregarPersona(new Persona("Luis", 22));
        barranco.agregarPersona(new Persona("Ana", 17));
        barranco.agregarPersona(new Persona("Rosa", 35));

        Pueblo miraflores = new Pueblo("Miraflores");
        miraflores.agregarPersona(new Persona("Carlos", 41));
        miraflores.agregarPersona(new Persona("Mateo", 12));

        Pueblo laPunta = new Pueblo("La Punta");
        laPunta.agregarPersona(new Persona("Elena", 28));
        laPunta.agregarPersona(new Persona("Sofia", 19));
        laPunta.agregarPersona(new Persona("Diego", 15));
        laPunta.agregarPersona(new Persona("Miguel", 62));

        lima.agregarPueblo(barranco);
        lima.agregarPueblo(miraflores);
        callao.agregarPueblo(laPunta);

        comunidad.agregarProvincia(lima);
        comunidad.agregarProvincia(callao);

        return comunidad;
    }
}

