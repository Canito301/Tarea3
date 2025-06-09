package org.Tarea3.Logica;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Clase abstracta que representa una moneda con un valor y número de serie único.
 * <p>
 * Cada moneda tiene un número de serie aleatorio y un valor definido por sus subclases.
 * Implementa {@link Comparable} para ordenar monedas por valor.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public abstract class Moneda implements Comparable<Moneda> {

    /** Número de serie único de la moneda. */
    private String serie;

    /**
     * Constructor que genera un número de serie aleatorio para la moneda.
     */
    public Moneda() {
        Random rand = new Random();
        serie = String.valueOf(rand.nextInt(1000000000));
    }

    /**
     * Obtiene el número de serie y el valor de la moneda.
     *
     * @return una cadena con el número de serie y el valor (e.g., "123456789 100pesos chilenos")
     */
    public String getSerie() {
        return serie + " " + getValor() + "pesos chilenos";
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor en pesos
     */
    public abstract int getValor();

    /**
     * Compara esta moneda con otra según su valor.
     *
     * @param otra la moneda a comparar
     * @return -1 si esta moneda tiene menor valor, 0 si son iguales, 1 si tiene mayor valor
     */
    @Override
    public int compareTo(Moneda otra) {
        return Integer.compare(this.getValor(), otra.getValor());
    }
}