package org.Tarea3;

import java.util.ArrayList;

/**
 * Clase que representa a un comprador que interactúa con un expendedor para adquirir un producto.
 * <p>
 * El comprador entrega una lista de {@link Moneda}, elige un producto identificado por un número,
 * y el {@link Expendedor} gestiona la transacción. La clase almacena el resultado de consumir el producto
 * y la lista de monedas del vuelto recibido.
 * </p>
 *
 * Maneja posibles excepciones relacionadas a comprar:
 * {@link PagoInsuficienteException}, {@link NoHayProductoException} y {@link PagoIncorrectoException}.
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Comprador {

    /**
     * String que representa el sonido producido al consumir el producto.
     */
    private String sonido;

    /**
     * Lista de monedas que representan el vuelto recibido tras la compra.
     */
    private ArrayList<Moneda> vuelto;

    /**
     * Entero que representa el valor total del vuelto.
     */
    private int vueltoTotal;

    /**
     * Constructor que realiza la compra de un producto desde un expendedor.
     *
     * @param monedas la lista de monedas que se utilizan para pagar.
     * @param cual el identificador del producto deseado.
     * @param exp el expendedor desde el cual se adquiere el producto.
     *
     * @throws PagoInsuficienteException si el valor total de las monedas no cubre el precio del producto.
     * @throws NoHayProductoException si no hay stock del producto solicitado.
     * @throws PagoIncorrectoException si alguna moneda es nula o inválida.
     */
    public Comprador(ArrayList<Moneda> monedas, int cual, Expendedor exp) throws PagoInsuficienteException, NoHayProductoException, PagoIncorrectoException {
        vuelto = new ArrayList<>();
        vueltoTotal = 0;
        Producto aux2 = null;

        // Limpia el depósito de vuelto antes de comenzar la compra
        exp.getVueltoList(); // Vacia el depósito para evitar acumulación
        aux2 = exp.comprarProducto(monedas, cual);

        if (monedas == null || monedas.isEmpty()) {
            aux2 = null;
        }
        if (aux2 == null) {
            sonido = null;
        } else {
            if (aux2 instanceof Bebida) {
                sonido = ((Bebida) aux2).beber();
            } else if (aux2 instanceof Dulce) {
                sonido = ((Dulce) aux2).comer();
            }
        }

        // Obtener la lista de monedas del vuelto (o las monedas originales en caso de error)
        vuelto = exp.getVueltoList();
        for (Moneda m : vuelto) {
            vueltoTotal += m.getValor();
        }
    }

    /**
     * Devuelve un String representando lo que consumió el comprador.
     *
     * @return el sonido de la bebida o dulce, o {@code null} si no se consumió nada.
     */
    public String queBebiste() {
        return sonido;
    }

    /**
     * Devuelve la lista ordenada de monedas que componen el vuelto.
     *
     * @return una lista de monedas del vuelto.
     */
    public ArrayList<Moneda> getVueltoList() {
        return new ArrayList<>(vuelto);
    }

    /**
     * Devuelve el total del vuelto tras la compra realizada en el expendedor.
     *
     * @return la cantidad de dinero de vuelto.
     */
    public int cuantoVuelto() {
        return vueltoTotal;
    }
}