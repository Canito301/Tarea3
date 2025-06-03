package org.Tarea1;

/**
 * Excepción personalizada que indica que no hay moneda o que la moneda es inválida.
 * <p>
 * Esta excepción se lanzará cuando se intente comprar con
 * una moneda inexistente o que no sea de los tipos establecidos (100, 500, 1000).
 * </p>
 * @author Leonardo Guerrero
 */

public class PagoIncorrectoException extends Exception{

    /**
     * Crea una nueva instancia de {@code PagoIncorrectoException} con un mensaje específico.
     */
    public PagoIncorrectoException(){
        super("Pago incorrecto (no se ingresó moneda o moneda no válida)");
    }
}
