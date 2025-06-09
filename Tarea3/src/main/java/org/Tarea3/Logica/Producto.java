package org.Tarea3.Logica;

import javax.swing.*;
import java.awt.*;

/**
 * Clase abstracta que representa un producto con un número de serie único.
 * <p>
 * Sirve como clase base para las clases derivadas {@link Bebida} y {@link Dulce},
 * proporcionando un identificador único para cada producto.
 * </p>
 *
 * @author Francisco Fuentealba
 */
public abstract class Producto {

    /** Número de serie único del producto. */
    private int serie;

    /**
     * Constructor que inicializa un producto con un número de serie.
     *
     * @param serie el número de serie del producto
     */
    public Producto(int serie) {
        this.serie = serie;
    }

    /**
     * Obtiene el número de serie del producto.
     *
     * @return el número de serie del producto
     */
    public int getSerie() {
        return serie;
    }
}