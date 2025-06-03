package org.Tarea1;

/**
 * Subclase que representa un tipo de bebida específica (CocaCola).
 * <p>
 * Esta clase extiende la clase abstracta {@link Bebida} y otorga una implementación
 * del método {@code beber()}, devolviendo cocacola.
 * </p>
 * @author Francisco Fuentealba
 */

public class CocaCola extends Bebida{

    /**
     * Constructor que inicializa una instancia de CocaCola con el número de serie dado.
     * @param n es el número de serie de la bebida CocaCola.
     */
    public CocaCola(int n){
        super(n);
    }

    /**
     * Devuelve el nombre del tipo de bebida al ser bebida.
     *
     * @return un String con el valor "cocacola".
     */
    @Override
    public String beber(){
        return "cocacola";
    }
}