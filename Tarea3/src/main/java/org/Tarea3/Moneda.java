package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Clase abstracta que representa una moneda con valor y número de serie único.
 * <p>
 * Se dibuja como un círculo con color según su valor (100: amarillo, 500: gris, 1000: naranja)
 * y muestra parte de su número de serie.
 * </p>
 */
public abstract class Moneda extends JPanel implements Comparable<Moneda> {
    /** Número de serie único de la moneda. */
    private String serie;

    /** Coordenadas x e y para posicionamiento gráfico. */
    private int x, y;

    /**
     * Constructor que genera un número de serie aleatorio.
     */
    public Moneda() {
        Random rand = new Random();
        serie = String.valueOf(rand.nextInt(1000000000));
    }

    /**
     * Devuelve el número de serie y valor de la moneda.
     *
     * @return Cadena con el número de serie y valor (e.g., "123456789 100pesos chilenos").
     */
    public String getSerie() {
        return serie + " " + getValor() + "pesos chilenos";
    }

    /**
     * Devuelve el valor de la moneda.
     *
     * @return Valor en pesos.
     */
    public abstract int getValor();

    /**
     * Compara monedas por su valor.
     *
     * @param otra Moneda a comparar.
     * @return -1, 0 o 1 si esta moneda es menor, igual o mayor que la otra.
     */
    @Override
    public int compareTo(Moneda otra) {
        return Integer.compare(this.getValor(), otra.getValor());
    }

    /**
     * Establece la posición gráfica de la moneda.
     *
     * @param x Coordenada x.
     * @param y Coordenada y.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


