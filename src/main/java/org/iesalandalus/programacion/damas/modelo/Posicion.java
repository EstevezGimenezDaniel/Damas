package org.iesalandalus.programacion.damas.modelo;

import java.util.Objects;

public class Posicion {
    private int fila;
    private char columna;

    public Posicion(int fila, char columna) {
        setFila(fila);
        setColumna(columna);
    }

    public Posicion(Posicion posicion) {
        if (posicion == null) {
            throw new NullPointerException("ERROR: No es posible copiar una posicion nula.");
        }
        this.fila = posicion.fila;
        this.columna = posicion.columna;
    }

    public int getFila() {
        return fila;
    }
    public void setFila(int fila) {
        if (fila < 1 || fila > 8) {
            throw new IllegalArgumentException("ERROR: Fila no valida.");
        }
        this.fila = fila;
    }
    public char getColumna() {
        return columna;
    }
    public void setColumna(char columna) {
        if (columna < 'a' || columna > 'h') {
            throw new IllegalArgumentException("ERROR: Columna no valida.");
        }
        this.columna = columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posicion posicion = (Posicion) obj;
        return fila == posicion.fila && columna == posicion.columna;
    }

    @Override
    public String toString() {
        return "fila=" + fila + ", columna=" + columna;
    }
}
