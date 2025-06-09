package org.Tarea3.Logica;

/**
 * Clase que representa una bebida específica de tipo Fanta.
 * <p>
 * Extiende la clase abstracta {@link Bebida} e implementa el método {@code beber()}
 * para devolver el nombre "fanta".
 * </p>
 *
 * @author Francisco Fuentealba
 */
public class Fanta extends Bebida {

    /**
     * Constructor que inicializa una instancia de Fanta con un número de serie.
     *
     * @param n el número de serie de la bebida
     */
    public Fanta(int n) {
        super(n);
    }

    /**
     * Devuelve el nombre de la bebida al ser consumida.
     *
     * @return la cadena "fanta"
     */
    @Override
    public String beber() {
        return "fanta";
    }
}