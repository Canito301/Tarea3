package org.Tarea3;

/**
 * Clase abstracta que representa un producto identificado por un número de serie.
 *
 * @author Francisco Fuentealba
 */
public abstract class Producto {

    /**
     * Número de serie del producto.
     */
    private int serie_producto;

    /**
     * Constructor que inicializa el producto con su número de serie.
     * @param numero_producto el número de serie del producto.
     */
    public Producto(int numero_producto){
        this.serie_producto = numero_producto;
    }

    /**
     * Devuelve el número de serie del producto.
     *
     * @return el número de serie.
     */
    public int getSerie(){
        return serie_producto;
    }

}
