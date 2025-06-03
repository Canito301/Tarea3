package org.Tarea1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Clase que es el main de pruebas para probar el funcionamiento del expendedor,
 * el comprador, el uso de excepciones y la comparación de monedas.
 * <p>
 * Realiza distintas simulaciones de compra y ordena monedas utilizando Comparable.
 *</p>
 * @author Francisco Fuentealba, Leonardo Guerrero.
 */
public class Main {
    /** Constructor por defecto de Main.*/
    public Main(){}
    /**
     * Método principal que ejecuta las pruebas de interacción con el expendedor y cuando se ordena la lista de monedas.
     * @param args argumentos de línea de comandos (no se utilizan, default de main).
     * @throws NoHayProductoException si el producto solicitado ya no está disponible.
     * @throws PagoInsuficienteException si la moneda ingresada no alcanza para el precio del producto.
     * @throws PagoIncorrectoException si la moneda es nula o inválida.
     */

    public static void main(String[] args) throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {
        Expendedor exp = new Expendedor(1);  // Solo un producto de cada tipo
        Comprador c = null;
        Moneda m;

        // Moneda incorrecta (null)
        try {
            c = new Comprador(null, 1, exp);  // CocaCola
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Pago exacto
        try {
            m = new Moneda1000();  // Precio de CocaCola = 900
            c = new Comprador(m, 4, exp);
            System.out.println(c.queBebiste() + ", Vuelto: " + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Pago mayor al precio
        try {
            m = new Moneda1000();  // Precio de Sprite = 700
            c = new Comprador(m, 2, exp);
            System.out.println(c.queBebiste() + ", Vuelto: " + c.cuantoVuelto());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Pago insuficiente
        try {
            m = new Moneda500();  // Precio de Fanta = 800
            c = new Comprador(m, 3, exp);
            System.out.println(c.queBebiste() + ", Vuelto: " + exp.getVuelto().getValor());
        } catch (Exception e) {

            System.out.println(e.getMessage() + ", Vuelto: " + exp.getVuelto().getValor());
        }
        // Producto agotado
        try {
            m = new Moneda1000();
            c = new Comprador(m, 4, exp); //ya se compró
            System.out.println(c.queBebiste() + ", Vuelto: " + exp.getVuelto().getValor());
        } catch (Exception e) {
            System.out.println(e.getMessage() + ", Vuelto: " + exp.getVuelto().getValor());
        }
        //Creación de lista de monedas y ordenar por comparable.
        Deposito<Moneda> monedas = new DepositoM();
        //Se crean 3 monedas (distintos valores en este caso).
        Moneda moneda1 = new Moneda100();
        Moneda moneda2 = new Moneda500();
        Moneda moneda3 = new Moneda1000();
        //Se agregan los productos al depósito de monedas.
        monedas.addProducto(moneda1);
        monedas.addProducto(moneda3);
        monedas.addProducto(moneda2);
        //Se crea lista de monedas.
        ArrayList<Moneda> listaOrdenada = new ArrayList<>();
        Moneda prueba;
        //Llena el array de monedas con las instancias dentro del depósito.
        while ((prueba = monedas.getProducto()) != null) {
            listaOrdenada.add(prueba);
        }
        //Utiliza el compareTo de monedas, sino no podría ejecutarlo.
        Collections.sort(listaOrdenada);
        //Muestra la lista ordenada
        System.out.println("Lista de monedas ordenada:");
        for (Moneda moneda : listaOrdenada) {
            System.out.println(moneda.getSerie());
        }

    }
}

