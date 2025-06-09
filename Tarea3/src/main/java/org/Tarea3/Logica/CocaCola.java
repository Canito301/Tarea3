package org.Tarea3.Logica;

/**
 * Clase que representa una bebida específica de tipo CocaCola.
 * <p>
 * Extiende la clase abstracta {@link Bebida} e implementa el método {@code beber()}
 * para devolver el nombre "cocacola".
 * </p>
 *
 * @author Francisco Fuentealba
 */
public class CocaCola extends Bebida {

    /**
     * Constructor que inicializa una instancia de CocaCola con un número de serie.
     *
     * @param n el número de serie de la bebida
     */
    public CocaCola(int n) {
        super(n);
    }

    /**
     * Devuelve el nombre de la bebida al ser consumida.
     *
     * @return la cadena "cocacola"
     */
    @Override
    public String beber() {
        return "cocacola";
    }
}