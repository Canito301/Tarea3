package org.Tarea3;

/**
 * Subclase que representa un tipo de bebida específica (Sprite).
 * <p>
 * Esta clase extiende la clase abstracta {@link Bebida} y otorga una implementación
 * del método {@code beber()}, devolviendo sprite.
 * </p>
 * @author Francisco Fuentealba
 */

public class Sprite extends Bebida{

    /**
     * Constructor que inicializa una instancia de Sprite con el número de serie dado.
     * @param n es el número de serie de la bebida Sprite.
     */

    public Sprite(int n){
        super(n);
    }

    /**
     * Devuelve el nombre del tipo de bebida al ser bebida.
     *
     * @return un String con el valor "sprite".
     */
    @Override
    public String beber(){
        return "sprite";
    }
}