package org.Tarea3.Logica;

/**
 * Clase abstracta que representa una bebida como tipo de producto.
 * <p>
 * Hereda de {@link Producto} y define una interfaz común para todas las bebidas,
 * incluyendo el método abstracto {@code beber()}.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public abstract class Bebida extends Producto {

    /**
     * Constructor que inicializa una bebida con un número de serie.
     *
     * @param serie el número de serie de la bebida
     */
    public Bebida(int serie) {
        super(serie);
    }

    /**
     * Obtiene el número de serie de la bebida.
     *
     * @return el número de serie
     */
    @Override
    public int getSerie() {
        return super.getSerie();
    }

    /**
     * Método abstracto que define el comportamiento al consumir la bebida.
     *
     * @return el nombre de la bebida consumida
     */
    public abstract String beber();
}