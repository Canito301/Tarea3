package org.Tarea3.Logica;

/**
 * Clase que representa una moneda chilena de 1000 pesos (ficticia).
 * <p>
 * Extiende la clase abstracta {@link Moneda} y define el valor específico de 1000 pesos.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Moneda1000 extends Moneda {

    /**
     * Constructor que inicializa una moneda de 1000 pesos con un número de serie aleatorio.
     */
    public Moneda1000() {
        super();
    }

    /**
     * Obtiene el valor de la moneda.
     *
     * @return el valor 1000 (pesos chilenos)
     */
    @Override
    public int getValor() {
        return 1000;
    }
}