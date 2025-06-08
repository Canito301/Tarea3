package org.Tarea3;

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
    COCACOLA(1, 900,"/img/CocaCola.png"),
    /**
     * Producto Sprite y su precio.
     */
    SPRITE(2, 700,"/img/Sprite.png"),
    /**
     * Producto Fanta y su precio.
     */
    FANTA(3, 800,"/img/Fanta.png"),
    /**
     * Producto Snickers y su precio.
     */
    SNICKERS(4, 1000,"/img/Snickers.png"),
    /**
     * Producto Super8 y su precio.
     */
    SUPER8(5, 600,"/img/Super8.png");

    /**
     * Guardará el identificador del producto.
     */
    private int valor = 0;

    /**
     * Guardará el precio del producto.
     */
    private int precio = 0;


    private String rutaDeImagen;

    /**
     * Constructor para inicializar el valor y el precio por producto.
     *
     * @param i el valor identificador del producto.
     * @param precio el precio del producto.
     */

    Productos(int i, int precio,String rutaImagen) {
        this.valor = i;
        this.precio = precio;
        this.rutaDeImagen = rutaImagen;
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

    public String getRutaDeImagen() {
        return rutaDeImagen;
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