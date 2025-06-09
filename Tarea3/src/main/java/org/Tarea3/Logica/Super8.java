package org.Tarea3.Logica;

/**
 * Clase que representa un dulce específico de tipo Super8.
 * <p>
 * Extiende la clase abstracta {@link Dulce} e implementa el método {@code comer()}
 * para devolver el nombre "super8".
 * </p>
 *
 * @author Leonardo Guerrero
 */
public class Super8 extends Dulce {

    /**
     * Constructor que inicializa una instancia de Super8 con un número de serie.
     *
     * @param n el número de serie del dulce
     */
    public Super8(int n) {
        super(n);
    }

    /**
     * Devuelve el nombre del dulce al ser consumido.
     *
     * @return la cadena "super8"
     */
    @Override
    public String comer() {
        return "super8";
    }
}