package org.iesalandalus.programacion.damas;

import org.iesalandalus.programacion.damas.modelo.Color;
import org.iesalandalus.programacion.damas.modelo.Direccion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
    private Consola() {

    }
    public static void mostrarMenu() {
        System.out.println("Menú de opciones");
        System.out.println("1.- Crear dama por defecto");
        System.out.println("2.- Crear dama eligiendo el color");
        System.out.println("3.- Mover");
        System.out.println("4.- Salir");
    }
    public static int elegirOpcionMenu() {

        int opcionMenu;

        do {
            System.out.println("Elige una opcion del menu (1-4)");
            opcionMenu=Entrada.entero();
            if (opcionMenu < 1 || opcionMenu > 4) {
                System.out.println("La opcion elegida no es valida. Tiene que estar comprendida entre el rango 1-4");
            }
        } while(opcionMenu < 1 || opcionMenu > 4);

        return opcionMenu;
    }
    public static Color elegirColor() {

        int opcionColor;
        do {
            System.out.println("Elige un color (Blanco o Negro");
            System.out.println("1.- Blanco");
            System.out.println("2.- Negro");
            opcionColor=Entrada.entero();
            if (opcionColor < 1 || opcionColor > 2) {
                System.out.println("La opcion elegida no es valida. Tiene que estar comprendida entre el rango 1-2");
            }
        } while (opcionColor < 1 || opcionColor > 2);

        if (opcionColor == 1) {
            return Color.BLANCO;
        } else {
            return Color.NEGRO;
        }
    }
    public static void mostrarMenuDirecciones() {
        System.out.println("Direcciones");
        System.out.println("1.- Noreste");
        System.out.println("2.- Sureste");
        System.out.println("3.- Suroeste");
        System.out.println("4.- Noroeste");
    }
    public static int elegirPasos() {
        int pasos;

        do {
            System.out.println("Introduce el numero de casillas mayor o igual a 1");
            pasos=Entrada.entero();
        } while (pasos < 1);

        return pasos;
    }
    public static Direccion elegirDireccion() {

        int opcionDireccion;

        do {
            System.out.println("Elige una direccion (1-4)");
            opcionDireccion = Entrada.entero();

        } while (opcionDireccion < 1 || opcionDireccion > 4);

        return switch (opcionDireccion) {
            case 1 -> Direccion.NORESTE;
            case 2 -> Direccion.SURESTE;
            case 3 -> Direccion.SUROESTE;
            case 4 -> Direccion.NOROESTE;
            default -> throw new IllegalArgumentException("Direccion no valida");
        };
    }
    public static void despedirse() {
        System.out.println("Gracias por usar la aplicacion. ¡Hasta luego!");
    }
}
