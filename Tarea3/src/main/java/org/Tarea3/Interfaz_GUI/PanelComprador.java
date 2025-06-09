package org.Tarea3.Interfaz_GUI;

import org.Tarea3.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * Clase que representa un panel para mostrar el producto comprado en la interfaz gráfica.
 * <p>
 * Este panel muestra una imagen del producto seleccionado por el comprador, escalada según las
 * dimensiones del panel. Si no hay producto, el panel se limpia.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class PanelComprador extends JPanel {

    /** Etiqueta que muestra la imagen del producto. */
    private final JLabel imagenProducto;

    /**
     * Constructor que inicializa el panel con un diseño de borde y una etiqueta centrada para la imagen del producto.
     */
    public PanelComprador() {
        setLayout(new BorderLayout());
        imagenProducto = new JLabel();
        imagenProducto.setHorizontalAlignment(JLabel.CENTER);
        imagenProducto.setVerticalAlignment(JLabel.CENTER);
        add(imagenProducto, BorderLayout.CENTER);
    }

    /**
     * Muestra la imagen del producto correspondiente al tipo especificado.
     * <p>
     * Carga la imagen del producto desde la ruta definida en el enum {@link Productos}, la escala
     * según las dimensiones del panel y la muestra en la etiqueta.
     * </p>
     *
     * @param tipo el identificador del producto (1 a 5)
     */
    public void mostrarProducto(int tipo) {
        Productos producto = Productos.obtenerProducto(tipo);
        try {
            BufferedImage imagen = ImageIO.read(Objects.requireNonNull(getClass().getResource(producto.getRutaDeImagen())));
            Image escalada = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            imagenProducto.setIcon(new ImageIcon(escalada));
        } catch (Exception e) {
            System.err.println("Error cargando imagen comprador: " + producto.getRutaDeImagen());
        }
    }

    /**
     * Limpia la imagen del producto mostrado en el panel.
     */
    public void limpiarProducto() {
        imagenProducto.setIcon(null);
    }
}