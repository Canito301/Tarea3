/*

package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ProductoVisual extends JPanel {
    private final BufferedImage imagenOriginal;
    private final JLabel etiqueta;
    private final int tipo;

    public ProductoVisual(int numero_producto) {
        this.tipo = numero_producto;
        setLayout(new BorderLayout());
        setOpaque(false);

        Productos producto = Productos.obtenerProducto(numero_producto);
        imagenOriginal = cargarBuffered(producto.getRutaDeImagen());

        etiqueta = new JLabel();
        etiqueta.setHorizontalAlignment(JLabel.CENTER);
        etiqueta.setVerticalAlignment(JLabel.CENTER);
        add(etiqueta, BorderLayout.CENTER);

        escalarImagen(60, 60);
    }

    public int getTipo() {
        return tipo;
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
}*/

package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class ProductoVisual extends JPanel {
    private final BufferedImage imagenOriginal;
    private final JLabel etiqueta;
    private final int tipo;
    private final JLabel contador;
    private int cantidad;

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

    public int getTipo() {
        return tipo;
    }

    public void incrementarCantidad() {
        cantidad++;
        actualizarContador();
    }

    public void escalarImagen(int ancho, int alto) {
        if (imagenOriginal != null) {
            Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            etiqueta.setIcon(new ImageIcon(imagenEscalada));
        }
    }

    private void actualizarContador() {
        contador.setText(cantidad > 1 ? String.valueOf(cantidad) : "");
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
