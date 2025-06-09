package org.Tarea3.Logica;

/**
 * Clase que representa una bebida específica de tipo Sprite.
 * <p>
 * Extiende la clase abstracta {@link Bebida} e implementa el método {@code beber()}
 * para devolver el nombre "sprite".
 * </p>
 *
 * @author Francisco Fuentealba
 */
public class Sprite extends Bebida {

    /**
     * Constructor que inicializa una instancia de Sprite con un número de serie.
     *
     * @param n el número de serie de la bebida
     */
    public Sprite(int n) {
        super(n);
    }

    /**
     * Devuelve el nombre de la bebida al ser consumida.
     *
     * @return la cadena "sprite"
     */
    @Override
    public String beber() {
        return "sprite";
    }
}