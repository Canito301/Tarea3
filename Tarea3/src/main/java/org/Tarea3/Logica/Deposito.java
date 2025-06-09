package org.Tarea3.Logica;

import java.util.ArrayList;

/**
 * Clase genérica que representa un depósito de productos.
 * <p>
 * Almacena objetos de tipo genérico {@code T} en una lista interna, siguiendo un comportamiento
 * FIFO (primero en entrar, primero en salir).
 * </p>
 *
 * @param <T> el tipo de objeto que almacena el depósito
 * @author Leonardo Guerrero
 */
public class Deposito<T> {

    /** Lista que almacena los productos en el depósito. */
    private ArrayList<T> lista;

    /**
     * Constructor que inicializa un depósito vacío.
     */
    public Deposito() {
        this.lista = new ArrayList<>();
    }

    /**
     * Agrega un producto al depósito.
     *
     * @param producto el producto a agregar
     */
    public void addProducto(T producto) {
        lista.add(producto);
    }

    /**
     * Retira y devuelve el primer producto del depósito.
     *
     * @return el primer producto almacenado, o null si el depósito está vacío
     */
    public T getProducto() {
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.remove(0);
        }
    }
}