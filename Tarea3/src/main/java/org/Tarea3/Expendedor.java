package org.Tarea3;

/**
 * Clase que representa un expendedor de bebidas y dulces.
 * * <p>
 *  El expendedor contiene distintos depósitos para cada tipo de producto y gestiona
 *  la compra de productos mediante monedas. También calcula y entrega el vuelto.
 *  </p>
 *Los productos disponibles están definidos por el enum {@link Productos}, e incluyen:
 * CocaCola, Sprite, Fanta, Snickers y Super8.
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Expendedor{

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

    /** Depósito de monedas que serán el vuelto. */
    private Deposito<Moneda> monVu;

    /** Precio del producto seleccionado en la compra. */
    private int precio;

    /** Constructor del expendedor que inicializa los depósitos y los llena con productos.
     *
     * @param numProductos la cantidad de productos a cargar en cada depósito. */

    public Expendedor(int numProductos){

        this.coca = new DepositoB();
        this.sprite = new DepositoB();
        this.fanta = new DepositoB();

        this.monVu = new DepositoM();

        this.super8 = new DepositoD();
        this.snickers = new DepositoD();

        for (int i = 100; i < numProductos+100; i++) {
            Bebida b = new CocaCola(i);
            coca.addProducto(b);
            Bebida a = new Sprite(i+100);
            sprite.addProducto(a);
            Bebida c = new Fanta(i+200);
            fanta.addProducto(c);
            Dulce d = new Snickers(i+300);
            snickers.addProducto(d);
            Dulce e = new Super8(i+400);
            super8.addProducto(e);

        }
    }

    /**
     * Agrega monedas de vuelto al depósito hasta cubrir lo que se pasó del pago del usuario.
     *
     * @param m la moneda entregada por el usuario.
     */

    public void actualizar(Moneda m){
        int valor = m.getValor() - precio;
        int suma = 0;
        while (suma < valor){
            Moneda monedan = new Moneda100();
            monVu.addProducto(monedan);
            suma += 100;
        }
    }

    /**
     * Realiza la compra de un producto determinado usando una moneda.
     *
     * @param m la moneda entregada por el usuario.
     * @param tipo el identificador del producto según el enum {@link Productos}.
     * @return el producto adquirido.
     *
     * @throws PagoInsuficienteException si el valor de la moneda es menor al precio del producto.
     * @throws NoHayProductoException si no hay stock del producto seleccionado o el tipo no existe.
     * @throws PagoIncorrectoException si la moneda es nula o inválida. */

    public Producto comprarProducto(Moneda m, int tipo) throws PagoInsuficienteException, NoHayProductoException, PagoIncorrectoException {
        Producto producto = null;
        Productos elemento = Productos.obtenerProducto(tipo);

        //Si moneda es nula
        if (m == null) {
            throw new PagoIncorrectoException();
        }
        //Si moneda es menor que el valor del producto.
        if (m.getValor() < elemento.getPrecio()) {
            monVu.addProducto(m);
            throw new PagoInsuficienteException();
        }
        //Cuando si puede acceder, con esto obtiene el producto.
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
                //No había del producto escogido.
            default:
                monVu.addProducto(m);
                throw new NoHayProductoException();

        }
        //No existe el producto seleccionado (no se usa en este caso).
        if (producto == null) {
            monVu.addProducto(m);
            throw new NoHayProductoException();
        }
        //Si cumple la condición para comprar, que devuelva el vuelto.
        if (m.getValor() >= precio) {
            actualizar(m);
        }
        //Retorna el producto obtenido.
        return producto;
    }

    /**
     * Obtiene una moneda del depósito de vuelto.
     *
     * @return una moneda del vuelto o {@code null} si no hay más. */

    public Moneda getVuelto(){
        return monVu.getProducto();
    }
}