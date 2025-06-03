package org.Tarea1;

/**
 * Clase abstracta que representa el tipo de producto bebida.
 * <p>
 * Hereda de {@link Producto} y define una interfaz común para todas
 * las bebidas, incluyendo el método abstracto {@code beber()}
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */

public abstract class Bebida extends Producto{

    /**
     * Constructor que inicializa el dulce con un número de serie.
     * @param serie es el número de serie del dulce.
     */

    public Bebida(int serie){
        super(serie);
    }

    /**
     * Obtiene el número de serie de la bebida.
     * @return el número de serie.
     */
    @Override
    public int getSerie() {
        return super.getSerie();
    }

    /**
     * Método abstracto que debe implementarse para definir el comportamiento al beber esta bebida.
     *
     * @return retorna un string con el nombre de bebida que consumió.
     */
    public abstract String beber();
}

