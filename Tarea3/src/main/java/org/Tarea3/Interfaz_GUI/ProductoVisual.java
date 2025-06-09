package org.Tarea3.Interfaz_GUI;

import org.Tarea3.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * Clase que representa un producto visual en la interfaz gráfica.
 * <p>
 * Muestra la imagen de un producto y un contador de cantidad, usado tanto en la máquina expendedora
 * como en el inventario del comprador.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class ProductoVisual extends JPanel {

    /** Imagen original del producto. */
    private final BufferedImage imagenOriginal;

    /** Etiqueta que muestra la imagen escalada del producto. */
    private final JLabel etiqueta;

    /** Tipo de producto (1 a 5). */
    private final int tipo;

    /** Etiqueta que muestra la cantidad de productos. */
    private final JLabel contador;

    /** Cantidad de productos representada. */
    private int cantidad;

    /**
     * Constructor que inicializa un producto visual con el tipo especificado.
     *
     * @param numero_producto el identificador del producto (1 a 5)
     */
    public ProductoVisual(int numero_producto) {
        this.tipo = numero_producto;
        this.cantidad = 1;

        setLayout(new BorderLayout());
        setOpaque(false);

        Productos producto = Productos.obtenerProducto(numero_producto);
        imagenOriginal = cargarBuffered(producto.getRutaDeImagen());

        etiqueta = new JLabel();
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        etiqueta.setVerticalAlignment(JLabel.CENTER);
        add(etiqueta, BorderLayout.CENTER);

        contador = new JLabel("1");
        contador.setForeground(Color.WHITE);
        contador.setFont(new Font("Comic Sans", Font.BOLD, 20));
        contador.setHorizontalAlignment(JLabel.CENTER);
        contador.setVerticalAlignment(JLabel.BOTTOM);
        add(contador, BorderLayout.SOUTH);

        escalarImagen(60, 60);
        actualizarContador();
    }

    /**
     * Obtiene el tipo de producto.
     *
     * @return el identificador del producto
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Incrementa la cantidad de productos y actualiza el contador.
     */
    public void incrementarCantidad() {
        cantidad++;
        actualizarContador();
    }

    /**
     * Escala la imagen del producto según las dimensiones especificadas.
     *
     * @param ancho el ancho deseado de la imagen
     * @param alto  el alto deseado de la imagen
     */
    public void escalarImagen(int ancho, int alto) {
        if (imagenOriginal != null) {
            Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            etiqueta.setIcon(new ImageIcon(imagenEscalada));
        }
    }

    /**
     * Actualiza el texto del contador según la cantidad de productos.
     * <p>
     * Muestra la cantidad solo si es mayor a 1; de lo contrario, muestra una cadena vacía.
     * </p>
     */
    private void actualizarContador() {
        contador.setText(cantidad > 1 ? String.valueOf(cantidad) : "");
    }

    /**
     * Carga una imagen desde la ruta especificada.
     *
     * @param ruta la ruta de la imagen
     * @return la imagen cargada, o null si ocurre un error
     */
    private BufferedImage cargarBuffered(String ruta) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(ruta)));
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + ruta);
            return null;
        }
    }
}