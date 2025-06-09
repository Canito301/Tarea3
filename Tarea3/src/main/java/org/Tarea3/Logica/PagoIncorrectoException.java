package org.Tarea3.Logica;

/**
 * Excepción personalizada que indica que el pago contiene monedas nulas o inválidas.
 * <p>
 * Se lanza cuando se intenta realizar una compra con monedas que no son válidas
 * (nulas o de un tipo no permitido).
 * </p>
 *
 * @author Leonardo Guerrero
 */
public class PagoIncorrectoException extends Exception {

    /**
     * Constructor que inicializa la excepción con un mensaje predeterminado.
     */
    public PagoIncorrectoException() {
        super("Pago incorrecto (no se ingresó moneda o moneda no válida)");
    }
}
