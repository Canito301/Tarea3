package org.Tarea3.Logica;

/**
 * Clase que representa un dulce específico de tipo Snickers.
 * <p>
 * Extiende la clase abstracta {@link Dulce} e implementa el método {@code comer()}
 * para devolver el nombre "snickers".
 * </p>
 *
 * @author Leonardo Guerrero
 */
public class Snickers extends Dulce {

    /**
     * Constructor que inicializa una instancia de Snickers con un número de serie.
     *
     * @param n el número de serie del dulce
     */
    public Snickers(int n) {
        super(n);
    }

    /**
     * Devuelve el nombre del dulce al ser consumido.
     *
     * @return la cadena "snickers"
     */
    @Override
    public String comer() {
        return "snickers";
    }
}