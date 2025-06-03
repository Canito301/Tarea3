package org.Tarea1;
import java.util.ArrayList;

/**
 * Clase genérica que representa un depósito de productos.
 * <p>
 * Utiliza una lista interna para almacenar objetos del tipo genérico {@code T}.
 * <p>
 * Permite agregar productos y retirar el primero en orden de llegada (FIFO).
 * @param <T> el tipo de objeto que almacenará el depósito.
 *
 * @author Leonardo Guerrero
 */

public class Deposito<T> {

    /**
     * Lista que almacena los productos en el depósito.
     */
    private ArrayList<T> lista;

    /**
     * Constructor que inicializa el depósito vacío.
     */
    public Deposito() {
        this.lista = new ArrayList<>();
    }

    /**
     * Agrega un producto al depósito.
     *
     * @param producto el producto a agregar.
     */
    public void addProducto(T producto) {
        lista.add(producto);
    }

    /**
     * Retorna y elimina el primer producto del depósito.
     * <p>
     * Si el depósito está vacío, retorna {@code null}.
     * </p>
     * @return el primer producto almacenado, o {@code null} si está vacío.
     */
    public T getProducto() {
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.remove(0);
        }
    }
}
