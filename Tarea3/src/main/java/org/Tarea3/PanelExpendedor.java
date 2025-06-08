package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PanelExpendedor extends JPanel {
    private final Expendedor exp;
    private final BufferedImage imagenExpendedor;
    private final ArrayList<ProductoVisual> productos = new ArrayList<>();
    private final PanelBotones botones;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;

        imagenExpendedor = UtilsImagen.cargarBuffered("/img/Expendedor.png");

        setLayout(null); // Posicionamiento absoluto

        botones = new PanelBotones();
        botones.setOpaque(false);
        botones.setLayout(null);

        // Crear y agregar productos: 5 tipos × 5 productos = 25
        for (int tipo = 1; tipo <= 5; tipo++) {
            for (int j = 0; j < 5; j++) {
                ProductoVisual producto = new ProductoVisual(tipo);
                producto.setOpaque(false);
                productos.add(producto);
                add(producto);
            }
        }

        add(botones);

        // Redimensionar componentes al redimensionar panel
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarComponentes();
            }
        });
    }

    private void ajustarComponentes() {
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int columnas = 5;
        int filas = 5;

        // Tamaño relativo de cada producto (porcentaje del panel)
        double anchoRel = 0.05;   // 6% del ancho del panel
        double altoRel = 0.1;     // 10% del alto del panel

        // Espacio entre productos (porcentaje del panel)
        double espacioXRel = 0;  // 1.5% horizontal
        double espacioYRel = 0;  // 2.5% vertical

        // Offset inicial desde el borde del panel (relativo)
        double offsetXRel = 0.3;   // 5% del ancho desde la izquierda
        double offsetYRel = 0.21;   // 5% del alto desde arriba

        int anchoProducto = (int) (anchoPanel * anchoRel);
        int altoProducto = (int) (altoPanel * altoRel);
        int espacioX = (int) (anchoPanel * espacioXRel);
        int espacioY = (int) (altoPanel * espacioYRel);
        int offsetX = (int) (anchoPanel * offsetXRel);
        int offsetY = (int) (altoPanel * offsetYRel);

        for (int i = 0; i < productos.size(); i++) {
            int fila = i / columnas;
            int col = i % columnas;

            int x = offsetX + col * (anchoProducto + espacioX);
            int y = offsetY + fila * (altoProducto + espacioY);

            ProductoVisual producto = productos.get(i);
            producto.setBounds(x, y, anchoProducto, altoProducto);
            producto.escalarImagen(anchoProducto, altoProducto);
        }

        // Botones (puedes ajustar también si lo deseas)
        int botonesAncho = anchoPanel / 17;
        int botonesAlto = altoPanel / 7;
        int botonesX = (int) (anchoPanel * 0.6);
        int botonesY = (int) (altoPanel * 0.35);

        botones.setBounds(botonesX, botonesY, botonesAncho, botonesAlto);
        botones.crearYReubicarBotones(botonesAncho, botonesAlto);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenExpendedor != null) {
            g.drawImage(imagenExpendedor, 0, 0, getWidth(), getHeight(), null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imagenExpendedor.getWidth(), imagenExpendedor.getHeight());
    }
}