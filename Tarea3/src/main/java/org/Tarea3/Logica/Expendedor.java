package org.Tarea3.Logica;

import java.util.Collections;
import java.util.ArrayList;

/**
 * Clase que representa una máquina expendedora de bebidas y dulces.
 * <p>
 * Contiene depósitos para cada tipo de producto (CocaCola, Sprite, Fanta, Snickers, Super8),
 * gestiona la compra de productos con monedas y calcula el vuelto. Los productos disponibles
 * están definidos en el enum {@link Productos}.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Expendedor {

    /** Depósito de bebidas CocaCola. */
    private Deposito<Bebida> coca;

    /** Depósito de dulces Snickers. */
    private Deposito<Dulce> snickers;

    /** Depósito de dulces Super8. */
    private Deposito<Dulce> super8;

    /** Depósito de bebidas Fanta. */
    private Deposito<Bebida> fanta;

    /** Depósito de bebidas Sprite. */
    private Deposito<Bebida> sprite;

    /** Depósito de monedas usadas en compras exitosas. */
    private Deposito<Moneda> monedasPagadas;

    /** Depósito de monedas que serán el vuelto. */
    private Deposito<Moneda> monVu;

    /** Depósito de producto único comprado. */
    private Deposito<Producto> depositoProducto;

    /** Precio del producto seleccionado en la compra. */
    private int precio;

    /**
     * Constructor que inicializa los depósitos y los llena con productos.
     *
     * @param numProductos la cantidad de productos a cargar en cada depósito
     */
    public Expendedor(int numProductos) {
        this.coca = new DepositoB();
        this.sprite = new DepositoB();
        this.fanta = new DepositoB();
        this.monedasPagadas = new DepositoM();
        this.monVu = new DepositoM();
        this.depositoProducto = new Deposito<>();
        this.super8 = new DepositoD();
        this.snickers = new DepositoD();

        for (int i = 100; i < numProductos + 100; i++) {
            Bebida b = new CocaCola(i);
            coca.addProducto(b);
            Bebida a = new Sprite(i + 100);
            sprite.addProducto(a);
            Bebida c = new Fanta(i + 200);
            fanta.addProducto(c);
            Dulce d = new Snickers(i + 300);
            snickers.addProducto(d);
            Dulce e = new Super8(i + 400);
            super8.addProducto(e);
        }
    }

    /**
     * Calcula y agrega monedas de vuelto al depósito hasta cubrir el exceso del pago.
     * <p>
     * Usa monedas de 1000, 500 y 100 pesos para minimizar la cantidad de monedas devueltas.
     * </p>
     *
     * @param valorTotal el valor total de las monedas entregadas por el usuario
     */
    private void calcularVuelto(int valorTotal) {
        int valor = valorTotal - precio;

        // Calcular monedas de 1000
        while (valor >= 1000) {
            monVu.addProducto(new Moneda1000());
            valor -= 1000;
        }
        // Calcular monedas de 500
        while (valor >= 500) {
            monVu.addProducto(new Moneda500());
            valor -= 500;
        }
        // Calcular monedas de 100
        while (valor >= 100) {
            monVu.addProducto(new Moneda100());
            valor -= 100;
        }
    }

    /**
     * Realiza la compra de un producto con las monedas proporcionadas.
     *
     * @param monedas la lista de monedas entregadas por el usuario
     * @param tipo    el identificador del producto según el enum {@link Productos}
     * @throws PagoInsuficienteException si el valor de las monedas es menor al precio del producto
     * @throws NoHayProductoException    si no hay stock del producto seleccionado o el tipo no existe
     * @throws PagoIncorrectoException   si las monedas son nulas o inválidas
     */
    public void comprarProducto(ArrayList<Moneda> monedas, int tipo) throws PagoInsuficienteException, NoHayProductoException, PagoIncorrectoException {
        Producto producto = null;
        Productos elemento = Productos.obtenerProducto(tipo);

        // Verificar que todas las monedas sean válidas
        if (monedas == null || monedas.isEmpty()) {
            throw new PagoIncorrectoException();
        }
        for (Moneda m : monedas) {
            if (m == null) {
                for (Moneda moneda : monedas) {
                    if (moneda != null) {
                        monVu.addProducto(moneda);
                    }
                }
                throw new PagoIncorrectoException();
            }
        }
        // Calcular el valor total de las monedas
        int valorTotal = 0;
        for (Moneda m : monedas) {
            valorTotal += m.getValor();
        }

        // Verificar si el valor total es suficiente
        if (valorTotal < elemento.getPrecio()) {
            for (Moneda m : monedas) {
                monVu.addProducto(m); // Guardar todas las monedas en el depósito de vuelto
            }
            throw new PagoInsuficienteException();
        }

        // Obtener el producto
        switch (elemento) {
            case COCACOLA:
                producto = coca.getProducto();
                precio = Productos.COCACOLA.getPrecio();
                break;
            case SPRITE:
                producto = sprite.getProducto();
                precio = Productos.SPRITE.getPrecio();
                break;
            case FANTA:
                precio = Productos.FANTA.getPrecio();
                producto = fanta.getProducto();
                break;
            case SNICKERS:
                producto = snickers.getProducto();
                precio = Productos.SNICKERS.getPrecio();
                break;
            case SUPER8:
                producto = super8.getProducto();
                precio = Productos.SUPER8.getPrecio();
                break;
            default:
                for (Moneda m : monedas) {
                    monVu.addProducto(m);
                }
                throw new NoHayProductoException();
        }

        // No existe el producto seleccionado
        if (producto == null) {
            for (Moneda m : monedas) {
                monVu.addProducto(m);
            }
            throw new NoHayProductoException();
        }

        // Almacenar monedas en el depósito de compras exitosas
        for (Moneda m : monedas) {
            monedasPagadas.addProducto(m);
        }

        // Calcular y agregar el vuelto
        depositoProducto.addProducto(producto);
        calcularVuelto(valorTotal);
    }

    /**
     * Obtiene todas las monedas del depósito de vuelto como una lista ordenada.
     *
     * @return una lista ordenada de monedas del vuelto, o una lista vacía si no hay monedas
     */
    public ArrayList<Moneda> getVueltoList() {
        ArrayList<Moneda> vuelto = new ArrayList<>();
        Moneda moneda;
        while ((moneda = monVu.getProducto()) != null) {
            vuelto.add(moneda);
        }
        Collections.sort(vuelto); // Ordenar por valor usando Comparable
        return vuelto;
    }

    /**
     * Rellena los depósitos vacíos con 4 productos de cada tipo.
     */
    public void rellenarDepositos() {
        if (coca.getProducto() == null) {
            for (int i = 100; i < 104; i++) {
                coca.addProducto(new CocaCola(i));
            }
        }
        if (sprite.getProducto() == null) {
            for (int i = 200; i < 204; i++) {
                sprite.addProducto(new Sprite(i));
            }
        }
        if (fanta.getProducto() == null) {
            for (int i = 300; i < 304; i++) {
                fanta.addProducto(new Fanta(i));
            }
        }
        if (snickers.getProducto() == null) {
            for (int i = 400; i < 404; i++) {
                snickers.addProducto(new Snickers(i));
            }
        }
        if (super8.getProducto() == null) {
            for (int i = 500; i < 504; i++) {
                super8.addProducto(new Super8(i));
            }
        }
    }

    /**
     * Obtiene el depósito de CocaCola.
     *
     * @return el depósito de CocaCola
     */
    public Deposito<Bebida> getDepositoCoca() {
        return coca;
    }

    /**
     * Obtiene el depósito de Sprite.
     *
     * @return el depósito de Sprite
     */
    public Deposito<Bebida> getDepositoSprite() {
        return sprite;
    }

    /**
     * Obtiene el depósito de Fanta.
     *
     * @return el depósito de Fanta
     */
    public Deposito<Bebida> getDepositoFanta() {
        return fanta;
    }

    /**
     * Obtiene el depósito de Snickers.
     *
     * @return el depósito de Snickers
     */
    public Deposito<Dulce> getDepositoSnickers() {
        return snickers;
    }

    /**
     * Obtiene el depósito de Super8.
     *
     * @return el depósito de Super8
     */
    public Deposito<Dulce> getDepositoSuper8() {
        return super8;
    }

    /**
     * Obtiene el depósito de monedas pagadas.
     *
     * @return el depósito de monedas pagadas
     */
    public Deposito<Moneda> getDepositoMonedasPagadas() {
        return monedasPagadas;
    }

    /**
     * Obtiene el depósito de monedas de vuelto.
     *
     * @return el depósito de monedas de vuelto
     */
    public Deposito<Moneda> getDepositoMonedasVuelto() {
        return monVu;
    }

    /**
     * Obtiene el depósito de producto comprado.
     *
     * @return el depósito de producto comprado
     */
    public Deposito<Producto> getDepositoProducto() {
        return depositoProducto;
    }
}