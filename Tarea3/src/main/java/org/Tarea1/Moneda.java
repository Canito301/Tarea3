package org.Tarea1;

import java.io.StringReader;
/**
 * Clase abstracta que representa una moneda de peso chileno,
 * esta clase implementa la interfaz Comparable para permitir comparar monedas según su valor.
 * @author Francisco Fuentealba
 */
public abstract class Moneda implements Comparable<Moneda>{
    /**
     * Constructor de la clase Moneda.
     */
    public Moneda(){
    }
    /**
     * Retorna la direccion de la instancia actual de la moneda.
     * @return la misma instancia de Moneda (this).
     */
    public Moneda getSerie(){
        return this;
    }

    /**
     * Compara esta moneda con otra según su valor.
     *
     * @param otraMoneda la otra moneda con la que se va a comparar.
     * @return un número negativo si la moneda es menor a otraMoneda,
     * cero si son iguales,
     * o un número positivo si la moneda es mayor a otraMoneda.
     */
    @Override
    public int compareTo(Moneda otraMoneda) {
        return Integer.compare(this.getValor(), otraMoneda.getValor());
    }

    /**
     * Retorna una representación en texto de la moneda, que incluye su número de serie
     * (derivado de {@link #hashCode()}) y su valor en pesos chilenos
     * (obtenido desde {@link #getValor()}).
     *
     * @return una cadena con el número de serie y el valor en pesos chilenos.
     */
    @Override
    public String toString() {
        return "Número de Serie=" + this.hashCode() + " " + getValor() + "pesos chilenos";
    }

    /**
     * Método abstracto que debe ser implementado por las subclases.
     * para indicar el valor de la moneda en pesos chilenos.
     * @return el valor de la moneda en pesos chilenos.
     */
    public abstract int getValor();
}



