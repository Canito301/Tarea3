package org.Tarea1;

/**
 * Enum que representa los productos disponibles, junto con su código y precio.
 * <p>
 * Cada producto tiene un código y un precio únicos asociado en pesos chilenos.
 * <p>
 * Se puede acceder al valor mediante {@link #getValor()} y al precio mediante {@link #getPrecio()}.
 * <p>
 * También permite obtener un producto a partir de su código con el método {@link #obtenerProducto(int)}.
 * @author Francisco Fuentealba
 */

public enum Productos {

    /**
     * Producto CocaCola y su precio.
     */
    COCACOLA(1, 900),
    /**
     * Producto Sprite y su precio.
     */
    SPRITE(2, 700),
    /**
     * Producto Fanta y su precio.
     */
    FANTA(3, 800),
    /**
     * Producto Snickers y su precio.
     */
    SNICKERS(4, 1000),
    /**
     * Producto Super8 y su precio.
     */
    SUPER8(5, 600);

    /**
     * Guardará el identificador del producto.
     */
    private int valor = 0;

    /**
     * Guardará el precio del producto.
     */
    private int precio = 0;

    /**
     * Constructor para inicializar el valor y el precio por producto.
     *
     * @param i el valor identificador del producto.
     * @param precio el precio del producto.
     */

    Productos(int i, int precio) {
        this.valor = i;
        this.precio = precio;
    }

    /**
     * Retorna el valor identificador del producto.
     *
     * @return el valor del producto.
     */
    public int getValor() {
        return this.valor;
    }

    /**
     * Retorna el precio del producto.
     *
     * @return el precio del producto.
     */
    public int getPrecio() {

        return precio;
    }

    /**
     * Método que devuelve el producto que corresponde al valor entregado.
     *
     * @param valorBuscado el valor identificador a buscar.
     * @return el producto correspondiente, o {@code null} si no se encuentra.
     */
    public static Productos obtenerProducto(int valorBuscado) {
        for (Productos producto : Productos.values()) {
            if (producto.valor == valorBuscado) {
                return producto;
            }
        }
        return null;
    }

}