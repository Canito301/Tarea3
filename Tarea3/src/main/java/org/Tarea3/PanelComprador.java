
package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class PanelComprador extends JPanel {
    private final JLabel imagenProducto;

    public PanelComprador() {
        setLayout(new BorderLayout());
        imagenProducto = new JLabel();
        imagenProducto.setHorizontalAlignment(JLabel.CENTER);
        imagenProducto.setVerticalAlignment(JLabel.CENTER);
        add(imagenProducto, BorderLayout.CENTER);
    }

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

    public void limpiarProducto() {
        imagenProducto.setIcon(null);
    }
}
