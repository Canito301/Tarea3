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


    /** Estado actual del comprador para el flujo gráfico. */
    private EstadoComprador estado;


    /** Producto comprado, pendiente de consumir. */
    private Producto producto;
    /** Monedero infinito del comprador. */
    private ArrayList<Moneda> monedero;

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

    /**
     * Constructor para uso gráfico, inicializa el comprador con un monedero infinito.
     */
    public Comprador() {
        vuelto = new ArrayList<>();
        monedero = new ArrayList<>();
        estado = EstadoComprador.SELECCIONAR_MONEDA;
        producto = null;
        // Inicializar monedero con 10 monedas de cada tipo
        rellenarMonedero();
    }

    /**
     * Rellena el monedero con 10 monedas de cada tipo (100, 500, 1000) para mantenerlo infinito.
     */
    private void rellenarMonedero() {
        for (int i = 0; i < 10; i++) {
            monedero.add(new Moneda100());
            monedero.add(new Moneda500());
            monedero.add(new Moneda1000());
        }
    }
    /**
     * Cuenta la cantidad de monedas de cada tipo en el monedero.
     *
     * @return Lista con 3 enteros: [cantidadMonedas100, cantidadMonedas500, cantidadMonedas1000].
     */
    public ArrayList<Integer> contarMonedas() {
        int cant100 = 0;
        int cant500 = 0;
        int cant1000 = 0;

        for (Moneda m : monedero) {
            int valor = m.getValor();
            if (valor == 100) cant100++;
            else if (valor == 500) cant500++;
            else if (valor == 1000) cant1000++;
        }

        ArrayList<Integer> listaMonedas = new ArrayList<>();
        listaMonedas.add(cant100);
        listaMonedas.add(cant500);
        listaMonedas.add(cant1000);
        return listaMonedas;
    }
    /**
     * Saca una moneda del monedero según el valor especificado.
     * <p>
     * Si no hay monedas del valor solicitado, rellena el monedero para mantenerlo infinito.
     * </p>
     *
     * @param valor Valor de la moneda (100, 500, o 1000).
     * @return Moneda extraída del monedero.
     */
    public Moneda sacarMoneda(int valor) {
        for (Moneda m : monedero) {
            if (m.getValor() == valor) {
                monedero.remove(m);
                return m;
            }
        }
        // Si no hay monedas del valor, rellenar monedero
        switch (valor) {
            case 100:
                monedero.add(new Moneda100());
                break;
            case 500:
                monedero.add(new Moneda500());
                break;
            case 1000:
                monedero.add(new Moneda1000());
                break;
            default:
                return null; // Valor inválido
        }
        Moneda m = monedero.get(monedero.size() - 1);
        monedero.remove(m);
        return m;
    }

    /**
     * Realiza una compra en el expendedor y actualiza el estado para el flujo gráfico.
     * <p>
     * Las monedas son proporcionadas por {@link } a partir de las selecciones del usuario,
     * habiendo sido extraídas del monedero mediante {@link #sacarMoneda(int)}. La validación del pago
     * es real, pudiendo lanzar excepciones como {@link PagoInsuficienteException} si el valor total es
     * menor al precio.
     * </p>
     *
     * @param monedas Lista de monedas para pagar, extraídas del monedero.
     * @param cual    Identificador del producto.
     * @param exp     Expendedor donde se realiza la compra.
     * @throws PagoInsuficienteException Si el pago es insuficiente.
     * @throws NoHayProductoException Si no hay producto disponible.
     * @throws PagoIncorrectoException Si las monedas son inválidas.
     */
    public void realizarCompra(ArrayList<Moneda> monedas, int cual, Expendedor exp) throws PagoInsuficienteException, NoHayProductoException, PagoIncorrectoException {
        vuelto.clear();
        vueltoTotal = 0;
        producto = null;

        exp.getVueltoList();
        exp.comprarProducto(monedas, cual);

        estado = EstadoComprador.RECOGER_PRODUCTO;
    }

    /**
     * Recoge el producto comprado del expendedor y lo consume.
     *
     * @param exp Expendedor de donde se recoge el producto.
     */


    public void recogerProducto(Expendedor exp) {
        producto = exp.getDepositoProducto().getProducto();
        if (producto != null) {
            if (producto instanceof Bebida) {
                sonido = ((Bebida) producto).beber();
            } else if (producto instanceof Dulce) {
                sonido = ((Dulce) producto).comer();
            }
        } else {
            sonido = null;
        }
        estado = EstadoComprador.RECOGER_VUELTO;
    }

    /**
     * Recoge el vuelto del expendedor y lo agrega al monedero.
     * <p>
     * La lista de monedas es ordenada por valor, como se genera en {@link Expendedor#getVueltoList()}.
     * </p>
     *
     * @param exp Expendedor de donde se recoge el vuelto.
     */

    public void recogerVuelto(Expendedor exp) {
        vuelto = exp.getVueltoList();
        for (Moneda m : vuelto) {
            if (m != null) {
                vueltoTotal += m.getValor();
                monedero.add(m);
            }
        }
        estado = EstadoComprador.SELECCIONAR_MONEDA;
    }

    /**
     * Avanza al siguiente estado cíclico para el flujo gráfico.
     */
    public void avanzarEstado() {
        switch (estado) {
            case SELECCIONAR_MONEDA:
                estado = EstadoComprador.SELECCIONAR_PRODUCTO;
                break;
            case SELECCIONAR_PRODUCTO:
                estado = EstadoComprador.RECOGER_PRODUCTO;
                break;
            case RECOGER_PRODUCTO:
                estado = EstadoComprador.RECOGER_VUELTO;
                break;
            case RECOGER_VUELTO:
                estado = EstadoComprador.SELECCIONAR_MONEDA;
                break;
        }
    }

    /**
     * Devuelve el sonido de consumir el producto.
     *
     * @return Sonido del producto consumido, o null si no se consumió nada.
     */
    public String queBebiste() {
        return sonido;
    }

    /**
     * Devuelve la lista ordenada de monedas del vuelto.
     *
     * @return Copia de la lista de monedas del vuelto, ordenada por valor.
     */
    public ArrayList<Moneda> getVueltoList() {
        return new ArrayList<>(vuelto);
    }

    /**
     * Devuelve el valor total del vuelto.
     *
     * @return Total del vuelto en pesos.
     */
    public int cuantoVuelto() {
        return vueltoTotal;
    }

    /**
     * Devuelve el monedero del comprador.
     *
     * @return Copia de la lista de monedas en el monedero.
     */
    public ArrayList<Moneda> getMonedero() {
        return new ArrayList<>(monedero);
    }

    /**
     * Devuelve el estado actual del comprador.
     *
     * @return Estado actual.
     */
    public EstadoComprador getEstado() {
        return estado;
    }
}