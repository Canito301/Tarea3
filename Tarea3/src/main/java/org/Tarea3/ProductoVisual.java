package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ProductoVisual extends JPanel {
    private final BufferedImage imagenOriginal;
    private final JLabel etiqueta;

    public ProductoVisual(int numero_producto) {
        setLayout(new BorderLayout());
        setOpaque(false);

        Productos producto = Productos.obtenerProducto(numero_producto);
        imagenOriginal = cargarBuffered(producto.getRutaDeImagen());

        etiqueta = new JLabel();
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        etiqueta.setVerticalAlignment(JLabel.CENTER);
        add(etiqueta, BorderLayout.CENTER);

        // Escala inicial por si no se llama a escalarImagen todavía
        escalarImagen(60, 60);
    }

    public void escalarImagen(int ancho, int alto) {
        if (imagenOriginal != null) {
            Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            etiqueta.setIcon(new ImageIcon(imagenEscalada));
        }
    }

    private BufferedImage cargarBuffered(String ruta) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResource(ruta)));
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + ruta);
            return null;
        }
    }
}