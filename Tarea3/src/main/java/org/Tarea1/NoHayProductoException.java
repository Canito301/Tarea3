package org.Tarea1;

/**
 * Excepción personalizada que indica que no hay producto disponible.
 * <p>
 * Esta excepción se lanzará cuando se intente acceder o
 * manipular un producto que no está disponible en inventario.
 * </p>
 * @author Leonardo Guerrero
 */

public class NoHayProductoException extends Exception{

    /**
     * Crea una nueva instancia de {@code NoHayProductoException} con un mensaje específico.
     */
    public NoHayProductoException(){
        super("No hay producto");
    }
}
