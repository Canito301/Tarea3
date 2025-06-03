package org.Tarea1;

/**
 * Subclase que representa un tipo de bebida específica (Fanta).
 * <p>
 * Esta clase extiende la clase abstracta {@link Bebida} y otorga una implementación
 * del método {@code beber()}, devolviendo fanta.
 * </p>
 * @author Francisco Fuentealba
 */
public class Fanta extends Bebida{

    /**
     * Constructor que inicializa una instancia de Fanta con el número de serie dado.
     * @param n es el número de serie de la bebida Fanta.
     */
    public Fanta(int n){
        super(n);
    }

    /**
     * Devuelve el nombre del tipo de bebida al ser bebida.
     *
     * @return un String con el valor "fanta".
     */
    @Override
    public String beber(){
        return "fanta";
    }
}