package org.Tarea3.Logica;

import java.util.ArrayList;

/**
 * Clase que representa un comprador que interactúa con un expendedor para adquirir productos.
 * <p>
 * Gestiona la lógica de compra, incluyendo la selección de monedas, la realización de la compra,
 * la recolección del producto y el vuelto. Maneja excepciones como {@link PagoInsuficienteException},
 * {@link NoHayProductoException} y {@link PagoIncorrectoException}. Mantiene un monedero infinito
 * y un estado cíclico para la interfaz gráfica.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Comprador {

    /** Sonido producido al consumir el producto. */
    private String sonido;

    /** Lista de monedas que representan el vuelto recibido. */
    private ArrayList<Moneda> vuelto;

    /** Valor total del vuelto en pesos. */
    private int vueltoTotal;

    /** Estado actual del comprador para el flujo gráfico. */
    private EstadoComprador estado;

    /** Producto comprado, pendiente de consumir. */
    private Producto producto;

    /** Monedero infinito del comprador. */
    private ArrayList<Moneda> monedero;

    /**
     * Constructor que inicializa el comprador con un monedero infinito y el estado inicial.
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
     * @return una lista con tres enteros: [cantidadMonedas100, cantidadMonedas500, cantidadMonedas1000]
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
     * @param valor el valor de la moneda (100, 500 o 1000)
     * @return la moneda extraída, o null si el valor es inválido
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
     * Realiza una compra en el expendedor con las monedas proporcionadas.
     *
     * @param monedas la lista de monedas para pagar
     * @param cual    el identificador del producto
     * @param exp     el expendedor donde se realiza la compra
     * @throws PagoInsuficienteException si el pago es insuficiente
     * @throws NoHayProductoException    si no hay stock del producto
     * @throws PagoIncorrectoException   si las monedas son inválidas
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
     * @param exp el expendedor de donde se recoge el producto
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
     *
     * @param exp el expendedor de donde se recoge el vuelto
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
     * Avanza al siguiente estado cíclico del comprador.
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
     * Obtiene el sonido de consumir el producto.
     *
     * @return el sonido del producto consumido, o null si no se consumió nada
     */
    public String queBebiste() {
        return sonido;
    }

    /**
     * Obtiene la lista ordenada de monedas del vuelto.
     *
     * @return una copia de la lista de monedas del vuelto, ordenada por valor
     */
    public ArrayList<Moneda> getVueltoList() {
        return new ArrayList<>(vuelto);
    }

    /**
     * Obtiene el valor total del vuelto.
     *
     * @return el total del vuelto en pesos
     */
    public int cuantoVuelto() {
        return vueltoTotal;
    }

    /**
     * Obtiene el monedero del comprador.
     *
     * @return una copia de la lista de monedas en el monedero
     */
    public ArrayList<Moneda> getMonedero() {
        return new ArrayList<>(monedero);
    }

    /**
     * Obtiene el estado actual del comprador.
     *
     * @return el estado actual
     */
    public EstadoComprador getEstado() {
        return estado;
    }
}