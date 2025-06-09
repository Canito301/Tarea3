package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;

public class PanelInventario extends JPanel {
    private BufferedImage imagenInventario;
    private final java.util.List<ProductoVisual> productosInventario = new ArrayList<>();

    public PanelInventario() {
        setOpaque(false);
        try {
            imagenInventario = ImageIO.read(Objects.requireNonNull(getClass().getResource("/img/Inventario.jpg")));
        } catch (Exception e) {
            System.err.println("Error cargando imagen inventario: /img/Inventario.jpg");
        }
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenInventario != null) {
            g.drawImage(imagenInventario, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private final Point[] posicionesFijas = {
            new Point(10, 10), new Point(80, 10), new Point(150, 10),
            new Point(10, 80), new Point(80, 80), new Point(150, 80),
            new Point(10, 150), new Point(80, 150), new Point(150, 150)
    };

    public boolean agregarProducto(ProductoVisual nuevo) {
        // Ver si ya hay un producto del mismo tipo
        for (ProductoVisual existente : productosInventario) {
            if (existente.getTipo() == nuevo.getTipo()) {
                existente.incrementarCantidad();
                return true;
            }
        }

        // Si no existe, agregarlo como nuevo
        if (productosInventario.size() >= posicionesFijas.length) {
            return false;
        }

        Point pos = posicionesFijas[productosInventario.size()];
        int ancho = 60;
        int alto = 60;

        nuevo.setBounds(pos.x, pos.y, ancho, alto);
        nuevo.escalarImagen(ancho, alto);
        add(nuevo);
        productosInventario.add(nuevo);
        revalidate();
        repaint();
        return true;
    }
}
