package org.Tarea1;
/**
 * Subclase que representa una moneda chilena de 1000 pesos (ficticia).
 * <p>
 * Esta clase extiende a {@link Moneda} y define el valor específico
 * de la moneda como 1000 pesos mediante la implementación del método {@link #getValor()}.
 * </p>
 * @author Leonardo Guerrero
 * @author Francisco Fuentealba
 */
public class Moneda1000 extends Moneda{

    /**
     * Constructor que llama al constructor de {@link Moneda}.
     */
    public Moneda1000(){
        super();
    }
    /**
     * Retorna el valor de esta moneda.
     *
     * @return el valor 100, que corresponde a 1000 pesos chilenos.
     */
    @Override
    public int getValor(){
        return 1000;
    }
}