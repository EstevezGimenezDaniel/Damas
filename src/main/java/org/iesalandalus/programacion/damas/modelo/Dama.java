package org.iesalandalus.programacion.damas.modelo;

import javax.naming.OperationNotSupportedException;
import java.util.Random;

public class Dama {
    private Color color;
    private Posicion posicion;
    private Boolean esDamaEspecial;
    public Dama() {
        this.color = Color.BLANCO;
        this.posicion = crearPosicionInicial(Color.BLANCO);
        this.esDamaEspecial = false;
    }
    public Dama(Color color) {
        setColor(color);
        this.posicion = crearPosicionInicial(color);
        this.esDamaEspecial = false;
    }
    public Color getColor() {
        return color;
    }
    private void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser nulo");
        }
        this.color = color;
    }
    public Posicion getPosicion() {
        return posicion;
    }
    public void setPosicion(Posicion posicion) {
        if (posicion == null) {
            throw new IllegalArgumentException("La posicion no puede ser nula");
        }
        this.posicion = posicion;
    }

    public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
        if (direccion == null) {
            throw new IllegalArgumentException("La direccion no puede ser nula");
        }
        if (pasos < 1) {
            throw new IllegalArgumentException("El número de pasos no puede ser menor a 1");
        }



        int nuevaFila = posicion.getFila();
        char nuevaColumna = posicion.getColumna();

        switch (direccion) {
            case NORESTE:
                nuevaFila += pasos;
                nuevaColumna += pasos;
                break;
            case NOROESTE:
                nuevaFila += pasos;
                nuevaColumna -= pasos;
                break;
            case SURESTE:
                nuevaFila -= pasos;
                nuevaColumna += pasos;
                break;
            case SUROESTE:
                nuevaFila -= pasos;
                nuevaColumna -= pasos;
                break;
        }

        if (nuevaFila < 1 || nuevaFila > 8 || nuevaColumna < 'a' || nuevaColumna > 'h') {
            throw new OperationNotSupportedException("Movimiento fuera de los limites del tablero");
        }

        if ((color == Color.BLANCO && nuevaFila == 8) || (color == Color.NEGRO && nuevaFila == 1)) {
            esDamaEspecial = true;
        }

        posicion = new Posicion(nuevaFila, nuevaColumna);
    }
    private Posicion crearPosicionInicial(Color color) {
        Random random = new Random();
        int fila;
        char columna;

        do {
            fila = (color == Color.BLANCO) ? random.nextInt(3) + 1 : random.nextInt(3) + 6;
            columna = (char) ('a' + random.nextInt(8));
        } while ((fila+columna) % 2 == 0);
        return new Posicion(fila, columna);
    }

    @Override
    public String toString() {
        return "Dama de color " + color +", en la posicion " + posicion + ", esDamaEspecial=" + esDamaEspecial;
    }
}
