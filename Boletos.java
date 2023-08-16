package Boletos;
import java.util.ArrayList;
import java.util.Scanner;

class Localidad {
    String nombre;
    int boletos_disponibles;
    int boletos_vendidos;

    public Localidad(String nombre, int boletos_disponibles, int boletos_vendidos) {
        this.nombre = nombre;
        this.boletos_disponibles = boletos_disponibles;
        this.boletos_vendidos = boletos_vendidos;
    }
}

class Comprador {
    String nombre;

    public Comprador(String nombre) {
        this.nombre = nombre;
    }
}

public class Boletos {
    ArrayList<Localidad> localidades;
    Comprador comprador_actual;

    public Boletos() {
        localidades = new ArrayList<>();
        localidades.add(new Localidad("A", 100, 0));
        localidades.add(new Localidad("B", 200, 0));
        localidades.add(new Localidad("C", 150, 0));
        comprador_actual = null;
    }

    public void mostrar_menu() {
        System.out.println("Bienvenido al Sistema de Boletos");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Nuevo comprador");
        System.out.println("2. Nueva solicitud de boletos");
        System.out.println("3. Consultar disponibilidad total");
        System.out.println("4. Consultar disponibilidad individual");
        System.out.println("5. Reporte de caja");
        System.out.println("6. Salir");
    }

    public void nuevo_comprador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del comprador, A, B, C: ");
        String nombre = scanner.nextLine();
        comprador_actual = new Comprador(nombre);
        System.out.println("Comprador creado exitosamente.");
    }

    public void nueva_solicitud_boletos() {
        if (comprador_actual == null) {
            System.out.println("No hay comprador actual. Por favor cree un comprador primero.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la localidad: ");
        String localidad = scanner.nextLine();
        System.out.print("Ingrese la cantidad de boletos a solicitar: ");
        int cantidad = scanner.nextInt();

        Localidad localidad_encontrada = null;
        for (Localidad l : localidades) {
            if (l.nombre.equals(localidad)) {
                localidad_encontrada = l;
                break;
            }
        }

        if (localidad_encontrada == null) {
            System.out.println("La localidad ingresada no existe.");
            return;
        }

        if (localidad_encontrada.boletos_disponibles < cantidad) {
            System.out.println("No hay suficientes boletos disponibles.");
            return;
        }

        localidad_encontrada.boletos_disponibles -= cantidad;
        localidad_encontrada.boletos_vendidos += cantidad;

        System.out.println(cantidad + " boletos vendidos exitosamente.");
    }

    public void consultar_disponibilidad_total() {
        System.out.println("Disponibilidad total:");
        for (Localidad localidad : localidades) {
            System.out.println("Localidad " + localidad.nombre + ":");
            System.out.println(" - Boletos vendidos: " + localidad.boletos_vendidos);
            System.out.println(" - Boletos disponibles: " + localidad.boletos_disponibles);
        }
    }

    public void consultar_disponibilidad_individual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la localidad: ");
        String localidad_nombre = scanner.nextLine();

        Localidad localidad_encontrada = null;
        for (Localidad l : localidades) {
            if (l.nombre.equals(localidad_nombre)) {
                localidad_encontrada = l;
                break;
            }
        }

        if (localidad_encontrada == null) {
            System.out.println("La localidad ingresada no existe.");
            return;
        }

        System.out.println("Disponibilidad de boletos para la localidad " + localidad_encontrada.nombre + ":");
        System.out.println(" - Boletos vendidos: " + localidad_encontrada.boletos_vendidos);
        System.out.println(" - Boletos disponibles: " + localidad_encontrada.boletos_disponibles);
    }

    public void reporte_caja() {
        int total_recaudado = 0;
        for (Localidad localidad : localidades) {
            total_recaudado += localidad.boletos_vendidos;
        }

        System.out.println("Total recaudado: " + total_recaudado + " unidades monetarias.");
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrar_menu();
            System.out.print("Ingrese su opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    nuevo_comprador();
                    break;
                case "2":
                    nueva_solicitud_boletos();
                    break;
                case "3":
                    consultar_disponibilidad_total();
                    break;
                case "4":
                    consultar_disponibilidad_individual();
                    break;
                case "5":
                    reporte_caja();
                    break;
                case "6":
                    System.out.println("Gracias por utilizar el Sistema de Boletos.");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor ingrese un número del 1 al 6.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Boletos sistema = new Boletos();
        sistema.ejecutar();
    }
}