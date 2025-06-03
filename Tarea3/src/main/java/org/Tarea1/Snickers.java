package org.Tarea1;

/**
 * Subclase que representa un tipo de dulce específico (Snickers).
 * <p>
 * Esta clase extiende la clase abstracta {@link Dulce} y otorga una implementación
 * del método {@code comer()}, devolviendo snickers.
 * </p>
 * @author Leonardo Guerrero
 */

public class Snickers extends Dulce{

    /**
     * Constructor que inicializa una instancia de Snickers con el número de serie dado.
     * @param n es el número de serie del dulce Snickers.
     */

    public Snickers(int n){
        super(n);
    }

    /**
     * Devuelve el nombre del tipo de dulce al ser dulce.
     *
     * @return un String con el valor "snickers".
     */
    @Override
    public String comer(){
        return "snickers";
    }
}

