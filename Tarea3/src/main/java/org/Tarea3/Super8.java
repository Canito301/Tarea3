package org.Tarea3;

/**
 * Subclase que representa un tipo de dulce específico (Super8).
 * <p>
 * Esta clase extiende la clase abstracta {@link Dulce} y otorga una implementación
 * del método {@code comer()}, devolviendo super8.
 * </p>
 * @author Leonardo Guerrero
 */
public class Super8 extends Dulce{

    /**
     * Constructor que inicializa una instancia de Super8 con el número de serie dado.
     * @param n es el número de serie del dulce Super8.
     */

    public Super8(int n){
        super(n);
    }

    /**
     * Devuelve el nombre del tipo de dulce al ser dulce.
     *
     * @return un String con el valor "super8".
     */
    @Override
    public String comer(){
        return "super8";
    }
}
