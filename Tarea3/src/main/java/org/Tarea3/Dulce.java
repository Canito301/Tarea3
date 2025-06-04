package org.Tarea3;
/**
 * Clase abstracta que representa un dulce como tipo de producto.
 * <p>
 * Hereda de {@link Producto} y define una interfaz común para todos
 * los dulces, incluyendo el método abstracto {@code comer()}
 * <p>
 * Cada dulce tiene un número de serie único dado al constructor.
 * @author Leonardo Guerrero
 */
public abstract class Dulce extends Producto{
    /**
     * Constructor que inicializa el dulce con un número de serie.
     * @param serie_dulce es el número de serie del dulce.
     */
    public Dulce( int serie_dulce){
        super(serie_dulce);
    }
    /**
     * Retorna el número de serie del dulce.
     * @return el número de serie.
     */
    public int getSerie() {
        return super.getSerie();
    }
    /**
     * Metodo abstracto que representa la acción de comer el dulce.
     * @return una cadena que describe el dulce al ser comido.
     */
    public abstract String comer();
}



