package org.iesalandalus.programacion.damas;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.damas.modelo.Color;
import org.iesalandalus.programacion.damas.modelo.Direccion;
import org.iesalandalus.programacion.damas.modelo.Dama;

public class MainApp {
    private static Dama dama;
    public static void main(String[] args) {
        int opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcionMenu();
            ejecutarOpcion(opcion);
        } while (opcion != 4);
    }
    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> crearDamaDefecto();
            case 2 -> crearDamaColor();
            case 3 -> mover();
            case 4 -> Consola.despedirse();
            default -> System.out.println("La opcion no es valida");
        }
    }
    private static void crearDamaDefecto() {
        dama = new Dama();
        System.out.println("Dama creada por defecto: " + dama);
    }
    private static void crearDamaColor() {

        Color color = Consola.elegirColor();
        dama = new Dama(color);
        System.out.println("Dama creada con el color elegido: " + dama);
    }
    private static void mover() {
        if (dama == null) {
            System.out.println("Debes crear una dama primero");
        }

        Consola.mostrarMenuDirecciones();
        Direccion direccion = Consola.elegirDireccion();
        int pasos = 1;

        try {
            dama.mover(direccion, pasos);
            System.out.println("Dama movida a la nueva posicion: " + dama);
        } catch (OperationNotSupportedException o) {
            System.out.println("ERROR: Operacion no soportada" + o.getMessage());
        } catch (Exception e) {
            System.out.println("Error al mover la dama: " + e.getMessage());
        }
    }
    private static void mostrarDama() {
        if (dama == null) {
            System.out.println("No se ha creado ninguna dama");
        } else {
            System.out.println("La dama es " + dama);
        }
    }
}
