package org.Tarea3.Logica;

/**
 * Excepción personalizada que indica que el pago no es suficiente para comprar el producto.
 * <p>
 * Se lanza cuando el valor total de las monedas proporcionadas es menor al precio del producto.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class PagoInsuficienteException extends Exception {

    /**
     * Constructor que inicializa la excepción con un mensaje predeterminado.
     */
    public PagoInsuficienteException() {
        super("Pago insuficiente");
    }
}