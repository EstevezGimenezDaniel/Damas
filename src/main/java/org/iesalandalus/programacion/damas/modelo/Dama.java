package org.iesalandalus.programacion.damas.modelo;

import javax.naming.OperationNotSupportedException;

public class Dama {

    private Color color;
    private Posicion posicion;
    private boolean esDamaEspecial;

    public Dama() {
        this.color = Color.BLANCO;
        this.posicion = crearPosicionInicial(color);
        this.esDamaEspecial = false;
    }

    public Dama(Color color) {
        if (color == null) {
            throw new NullPointerException("ERROR: El color no puede ser nulo.");
        }
        this.color = color;
        this.posicion = crearPosicionInicial(color);
        this.esDamaEspecial = false;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new NullPointerException("ERROR: El color no puede ser nulo.");
        }
        this.color = color;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        if (posicion == null) {
            throw new NullPointerException("ERROR: La posicion no puede ser nula.");
        }
        this.posicion = posicion;
    }

    public boolean esDamaEspecial() {
        if (!this.esDamaEspecial) {
            if ((this.color == Color.BLANCO && this.posicion.getFila() == 8) ||
                    (this.color == Color.NEGRO && this.posicion.getFila() == 1)) {
                this.esDamaEspecial = true;
            }
        }
        return this.esDamaEspecial;
    }


    public void mover(Direccion direccion, int pasos) throws OperationNotSupportedException {
        if (pasos < 1) {
            throw new IllegalArgumentException("ERROR: El numero de pasos no puede ser menor que 1.");
        }
        if (direccion == null) {
            throw new NullPointerException("ERROR: La direccion no puede ser nula.");
        }
        if (getColor() == null) {
            throw new NullPointerException("ERROR: El color no puede ser nulo.");
        }
        if (!esDamaEspecial && pasos > 1){
            if ((color == Color.BLANCO && (direccion == Direccion.NORESTE || direccion == Direccion.NOROESTE)) ||
                    (color == Color.NEGRO && (direccion == Direccion.SURESTE || direccion == Direccion.SUROESTE))) {
                throw new OperationNotSupportedException("ERROR: Las damas normales solo se pueden mover 1 casilla.");
            }
        }

        if (esDamaEspecial) {
            if ((direccion == Direccion.SURESTE || direccion == Direccion.SUROESTE) && color == Color.BLANCO) {
                throw new OperationNotSupportedException("ERROR: Movimiento no válido para una dama especial.");
            }
            if ((direccion == Direccion.NORESTE || direccion == Direccion.NOROESTE) && color == Color.NEGRO) {
                throw new OperationNotSupportedException("ERROR: Movimiento no válido para una dama especial.");
            }
        }

        int nuevaFila = this.posicion.getFila();
        char nuevaColumna = this.posicion.getColumna();


        switch (direccion) {
            case NORESTE:
                nuevaFila += pasos;
                nuevaColumna += (char) pasos;
                break;
            case SURESTE:
                nuevaFila -= pasos;
                nuevaColumna += (char) pasos;
                break;
            case SUROESTE:
                nuevaFila -= pasos;
                nuevaColumna -= (char) pasos;
                break;
            case NOROESTE:
                nuevaFila += pasos;
                nuevaColumna -= (char) pasos;
                break;
        }

        try
        {
            posicion = new Posicion(nuevaFila, nuevaColumna);
        }
        catch(IllegalArgumentException e)
        {
            throw new OperationNotSupportedException("ERROR: Movimiento no permitido.");
        }
    }

    private Posicion crearPosicionInicial (Color color){
        int fila;
        char columna;

        if (color == Color.BLANCO) {
            fila = (int) (Math.random() * 3) + 1;
        } else {
            fila = (int) (Math.random() * 3) + 6;
        }

        do {
            columna = (char) ('a' + (int) (Math.random() * 8));
        } while ((fila + (columna - 'a')) % 2 == 0);

        return new Posicion(fila, columna);
    }

    @Override
    public String toString() {
        return "color=" + color + ", posicion=(" + posicion + ")";
    }
}
