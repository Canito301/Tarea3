package org.Tarea3.Logica;

/**
 * Clase abstracta que representa un dulce como tipo de producto.
 * <p>
 * Hereda de {@link Producto} y define una interfaz común para todos los dulces,
 * incluyendo el método abstracto {@code comer()}. Cada dulce tiene un número de serie único.
 * </p>
 *
 * @author Leonardo Guerrero
 */
public abstract class Dulce extends Producto {

    /**
     * Constructor que inicializa un dulce con un número de serie.
     *
     * @param serie_dulce el número de serie del dulce
     */
    public Dulce(int serie_dulce) {
        super(serie_dulce);
    }

    /**
     * Obtiene el número de serie del dulce.
     *
     * @return el número de serie
     */
    public int getSerie() {
        return super.getSerie();
    }

    /**
     * Método abstracto que define el comportamiento al consumir el dulce.
     *
     * @return el nombre del dulce consumido
     */
    public abstract String comer();
}