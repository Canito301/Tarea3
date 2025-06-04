package org.Tarea3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que es el main de pruebas para probar el funcionamiento del expendedor,
 * el comprador, el uso de excepciones y la comparación de monedas.
 * <p>
 * Realiza distintas simulaciones de compra y ordena monedas utilizando Comparable.
 * </p>
 * @author Francisco Fuentealba, Leonardo Guerrero.
 */
public class Main {
    /** Constructor por defecto de Main. */
    public Main() {}

    /**
     * Método principal que ejecuta las pruebas de interacción con el expendedor y cuando se ordena la lista de monedas.
     * @param args argumentos de línea de comandos (no se utilizan, default de main).
     * @throws NoHayProductoException si el producto solicitado ya no está disponible.
     * @throws PagoInsuficienteException si el valor total de las monedas no alcanza para el precio del producto.
     * @throws PagoIncorrectoException si alguna moneda es nula o inválida.
     */
    public static void main(String[] args) throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {
        Expendedor exp = new Expendedor(1);  // Solo un producto de cada tipo
        Comprador c = null;
        ArrayList<Moneda> monedas;

        // Moneda incorrecta (null)
        try {
            c = new Comprador(null, 1, exp);  // CocaCola
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage() + ", Vuelto: ");
            printVuelto(exp.getVueltoList());
        }

        // Pago exacto
        try {
            monedas = new ArrayList<>();
            monedas.add(new Moneda1000());
            c = new Comprador(monedas, 4, exp);
            System.out.print(c.queBebiste() + ", Vuelto: ");
            printVuelto(c.getVueltoList());
            System.out.println("Vuelto total: " + c.cuantoVuelto() + " pesos");
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage() + ", Vuelto: ");
            printVuelto(exp.getVueltoList());
        }

        // Pago mayor al precio
        try {
            monedas = new ArrayList<>();
            monedas.add(new Moneda1000());  // Precio de Sprite = 700
            monedas.add(new Moneda500());
            c = new Comprador(monedas, 2, exp);
            System.out.print(c.queBebiste() + ", Vuelto: ");
            printVuelto(c.getVueltoList());
            System.out.println("Vuelto total: " + c.cuantoVuelto() + " pesos");
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage() + ", Vuelto: ");
            printVuelto(exp.getVueltoList());
        }

        // Pago insuficiente
        try {
            monedas = new ArrayList<>();
            monedas.add(new Moneda500());  // Precio de Fanta = 800
            c = new Comprador(monedas, 3, exp);
            System.out.print(c.queBebiste() + ", Vuelto: ");
            printVuelto(c.getVueltoList());
            System.out.println("Vuelto total: " + c.cuantoVuelto() + " pesos");
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage() + ", Vuelto: ");
            printVuelto(exp.getVueltoList());
        }

        // Producto agotado
        try {
            monedas = new ArrayList<>();
            monedas.add(new Moneda1000());
            c = new Comprador(monedas, 4, exp); // ya se compró
            System.out.print(c.queBebiste() + ", Vuelto: ");
            printVuelto(c.getVueltoList());
            System.out.println("Vuelto total: " + c.cuantoVuelto() + " pesos");
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage() + ", Vuelto: ");
            printVuelto(exp.getVueltoList());
        }

        // Creación de lista de monedas y ordenar por comparable
        Deposito<Moneda> monedasDep = new DepositoM();
        Moneda moneda1 = new Moneda100();
        Moneda moneda2 = new Moneda500();
        Moneda moneda3 = new Moneda1000();
        monedasDep.addProducto(moneda1);
        monedasDep.addProducto(moneda3);
        monedasDep.addProducto(moneda2);

        ArrayList<Moneda> listaOrdenada = new ArrayList<>();
        Moneda prueba;
        while ((prueba = monedasDep.getProducto()) != null) {
            listaOrdenada.add(prueba);
        }
        Collections.sort(listaOrdenada);
        System.out.println("Lista de monedas ordenada:");
        for (Moneda moneda : listaOrdenada) {
            System.out.println(moneda.getSerie());
        }
    }

    /**
     * Imprime la lista de monedas del vuelto.
     * @param vuelto lista de monedas a imprimir.
     */
    private static void printVuelto(ArrayList<Moneda> vuelto) {
        if (vuelto.isEmpty()) {
            System.out.println("0 pesos");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < vuelto.size(); i++) {
            System.out.print(vuelto.get(i).getValor());
            if (i < vuelto.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("] pesos");
    }
}

