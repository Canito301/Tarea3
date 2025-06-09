package org.Tarea3.Logica;

/**
 * Excepción personalizada que indica que no hay producto disponible en el inventario.
 * <p>
 * Se lanza cuando se intenta comprar un producto que no está disponible en el depósito.
 * </p>
 *
 * @author Leonardo Guerrero
 */
public class NoHayProductoException extends Exception {

    /**
     * Constructor que inicializa la excepción con un mensaje predeterminado.
     */
    public NoHayProductoException() {
        super("No hay producto");
    }
}