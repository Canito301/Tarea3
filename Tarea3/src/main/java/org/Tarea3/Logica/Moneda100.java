package org.Tarea3.Logica;

/**
 * Clase que representa una moneda chilena de 100 pesos.
 * <p>
 * Extiende la clase abstracta {@link Moneda} y define el valor específico de 100 pesos.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Moneda100 extends Moneda {

    /**
     * Constructor que inicializa una moneda de 100 pesos con un número de serie aleatorio.
     */
    public Moneda100() {
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor 100 (pesos chilenos)
     */
    @Override
    public int getValor() {
        return 100;
    }
}