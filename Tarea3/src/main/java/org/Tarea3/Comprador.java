package org.Tarea3;

/**
 * Clase que representa a un comprador que interactúa con un expendedor para adquirir un producto.
 * <p>
 * El comprador entrega una {@link Moneda}, elige un producto identificado por un número,
 * y el {@link Expendedor} gestiona la transacción. La clase almacena el resultado de consumir el producto
 * y el total del vuelto recibido.
 * </p>
 *
 * Maneja posibles excepciones relacionadas a comprar:
 * {@link PagoInsuficienteException}, {@link NoHayProductoException} y {@link PagoIncorrectoException}.
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */

public class Comprador{

    /**
     * String que representa el sonido producido al consumir el producto.
     */
    private String sonido;

    /**
     * Entero que representa el vuelto que se entregará al usuario luego de comprar.
     */
    private int vuelto;

    /**
     * Constructor que realiza la compra de un producto desde un expendedor.
     *
     * @param m    la moneda que se utiliza para pagar.
     * @param cual el identificador del producto deseado.
     * @param exp  el expendedor desde el cual se adquiere el producto.
     *
     * @throws PagoInsuficienteException si la moneda no cubre el precio del producto.
     * @throws NoHayProductoException si no hay stock del producto solicitado.
     * @throws PagoIncorrectoException si la moneda ingresada es nula o inválida.
     */
    public Comprador(Moneda m, int cual, Expendedor exp) throws PagoInsuficienteException, NoHayProductoException, PagoIncorrectoException {

        vuelto = 0;
        Producto aux2 = null;

        // Limpia el depósito de vuelto antes de comenzar la compra.
        do {
            exp.getVuelto();
        } while (exp.getVuelto() != null);
        aux2 = exp.comprarProducto(m, cual);

        Moneda auxM = m;
        if (auxM == null){
            aux2 = null;
        }
        if(aux2 == null){
            sonido = null;
        } else {
            if (aux2 instanceof Bebida) {
                sonido = ((Bebida) aux2).beber();
            } else if (aux2 instanceof Dulce) {
                sonido = ((Dulce) aux2).comer();
            }
        }

        // Suma el total del vuelto.
        while(true){
            Moneda aux = exp.getVuelto();
            if(aux==null){
                break;
            } else {
                vuelto += aux.getValor();
                if(aux2 == null){
                    vuelto = aux.getValor();
                    break;
                }
            }

        }
    }
    /**
     * Devuelve un String representando lo que consumió el comprador.
     *
     * @return el sonido de la bebida o dulce, o {@code null} si no se consumió nada.
     */
    public String queBebiste(){
        return sonido;
    }

    /**
     * Devuelve el total del vuelto tras la compra realizada en el expendedor.
     *
     * @return la cantidad de dinero de vuelto.
     */
    public int cuantoVuelto(){
        return vuelto;
    }
}