package org.Tarea3.Logica;

/**
 * Enum que representa los productos disponibles en la máquina expendedora, junto con su código, precio y ruta de imagen.
 * <p>
 * Cada producto tiene un identificador único, un precio en pesos chilenos y una ruta a su imagen para la interfaz gráfica.
 * Proporciona métodos para acceder a estos atributos y obtener un producto por su identificador.
 * </p>
 *
 * @author Francisco Fuentealba
 */
public enum Productos {

    /**
     * Producto CocaCola con su identificador, precio y ruta de imagen.
     */
    COCACOLA(1, 900, "/img/CocaCola.png"),
    /**
     * Producto Sprite con su identificador, precio y ruta de imagen.
     */
    SPRITE(2, 700, "/img/Sprite.png"),
    /**
     * Producto Fanta con su identificador, precio y ruta de imagen.
     */
    FANTA(3, 800, "/img/Fanta.png"),
    /**
     * Producto Snickers con su identificador, precio y ruta de imagen.
     */
    SNICKERS(4, 1000, "/img/Snickers.png"),
    /**
     * Producto Super8 con su identificador, precio y ruta de imagen.
     */
    SUPER8(5, 600, "/img/Super8.png");

    /** Identificador único del producto. */
    private int valor;

    /** Precio del producto en pesos chilenos. */
    private int precio;

    /** Ruta de la imagen del producto para la interfaz gráfica. */
    private String rutaDeImagen;

    /**
     * Constructor que inicializa un producto con su identificador, precio y ruta de imagen.
     *
     * @param i          el identificador único del producto
     * @param precio     el precio del producto en pesos chilenos
     * @param rutaImagen la ruta del recurso de la imagen del producto
     */
    Productos(int i, int precio, String rutaImagen) {
        this.valor = i;
        this.precio = precio;
        this.rutaDeImagen = rutaImagen;
    }

    /**
     * Obtiene el identificador único del producto.
     *
     * @return el identificador del producto
     */
    public int getValor() {
        return this.valor;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return el precio del producto en pesos chilenos
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Obtiene la ruta de la imagen del producto.
     *
     * @return la ruta del recurso de la imagen
     */
    public String getRutaDeImagen() {
        return rutaDeImagen;
    }

    /**
     * Obtiene el producto correspondiente al identificador proporcionado.
     *
     * @param valorBuscado el identificador del producto a buscar
     * @return el producto correspondiente, o {@code null} si no se encuentra
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