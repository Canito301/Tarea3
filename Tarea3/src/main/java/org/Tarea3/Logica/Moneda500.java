package org.Tarea3.Logica;

/**
 * Clase que representa una moneda chilena de 500 pesos.
 * <p>
 * Extiende la clase abstracta {@link Moneda} y define el valor específico de 500 pesos.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Moneda500 extends Moneda {

    /**
     * Constructor que inicializa una moneda de 500 pesos con un número de serie aleatorio.
     */
    public Moneda500() {
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor 500 (pesos chilenos)
     */
    @Override
    public int getValor() {
        return 500;
    }
}