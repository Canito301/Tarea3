package org.Tarea1;

/**
 * Excepción personalizada que indica que el pago no alcanzó para comprar el producto.
 * <p>
 * Esta excepción se lanzará cuando se intente acceder o
 * manipular un producto cuando una moneda válida no alcanza para comprarlo.
 * </p>
 * @author Francisco Fuentealba, Leonardo Guerrero.
 */

public class PagoInsuficienteException extends Exception{

    /**
     * Crea una nueva instancia de {@code PagoInsuficienteException} con un mensaje específico.
     */
    public PagoInsuficienteException(){
        super("Pago insuficiente");
    }
}
