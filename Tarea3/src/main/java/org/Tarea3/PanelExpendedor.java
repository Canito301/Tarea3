package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class PanelExpendedor extends JPanel {
    private final Expendedor exp;
    private final BufferedImage imagenExpendedor;
    private final ArrayList<ProductoVisual> productos = new ArrayList<>();
    private final Map<Integer, ArrayList<ProductoVisual>> productosPorTipo = new HashMap<>();
    private final PanelBotones botones;
    private final PanelComprador panelComprador;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;

        imagenExpendedor = UtilsImagen.cargarBuffered("/img/Expendedor.png");

        setLayout(null); // Posicionamiento absoluto

        botones = new PanelBotones();
        botones.setOpaque(false);
        botones.setLayout(null);

        panelComprador = new PanelComprador();
        panelComprador.setOpaque(false);
        add(panelComprador);

        // Crear y agregar productos
        for (int tipo = 1; tipo <= 5; tipo++) {
            ArrayList<ProductoVisual> listaTipo = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ProductoVisual producto = new ProductoVisual(tipo);
                producto.setOpaque(false);
                productos.add(producto);
                listaTipo.add(producto);
                add(producto);
            }
            productosPorTipo.put(tipo, listaTipo);
        }

        // Asignar comportamiento a botones
        botones.setCallback(this::comprarProducto);
        add(botones);

        // Redimensionar componentes
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

        // Tamaño y espaciado relativo
        double anchoRel = 0.05;
        double altoRel = 0.101;

        //Distancia entre objetos (lo dejé en cero pero nunca se sabe)
        double distanciaEjeX = 0;
        double distanciaEjeY = 0;

        //Estos son para mover el panel.
        double movimientoEjeX = 0.3;
        double movimientoEjeY = 0.2;

        int anchoProducto = (int) (anchoPanel * anchoRel);
        int altoProducto = (int) (altoPanel * altoRel);
        int espacioX = (int) (anchoPanel * distanciaEjeX);
        int espacioY = (int) (altoPanel * distanciaEjeY);
        int offsetX = (int) (anchoPanel * movimientoEjeX);
        int offsetY = (int) (altoPanel * movimientoEjeY);

        for (int tipo = 1; tipo <= 5; tipo++) {
            ArrayList<ProductoVisual> lista = productosPorTipo.get(tipo);
            for (int j = 0; j < lista.size(); j++) {
                int x = offsetX + j * (anchoProducto + espacioX);
                int y = offsetY + (tipo - 1) * (altoProducto + espacioY);
                ProductoVisual producto = lista.get(j);
                producto.setBounds(x, y, anchoProducto, altoProducto);
                producto.escalarImagen(anchoProducto, altoProducto);
            }
        }

        int botonesAncho = anchoPanel / 17;
        int botonesAlto = altoPanel / 7;
        int botonesX = (int) (anchoPanel * 0.75);
        int botonesY = (int) (altoPanel * 0.35);

        botones.setBounds(botonesX, botonesY, botonesAncho, botonesAlto);
        botones.crearYReubicarBotones(botonesAncho, botonesAlto);

        int compradorAncho = anchoPanel / 6;
        int compradorAlto = altoPanel / 6;
        int compradorX = (int) (anchoPanel * 0.1);
        int compradorY = (int) (altoPanel * 0.75);

        panelComprador.setBounds(compradorX, compradorY, compradorAncho, compradorAlto);
    }

    private void comprarProducto(int tipo) {
        ArrayList<ProductoVisual> lista = productosPorTipo.get(tipo);
        if (lista != null && !lista.isEmpty()) {
            ProductoVisual producto = lista.remove(lista.size() - 1); // último a la derecha
            remove(producto);
            panelComprador.mostrarProducto(producto.getTipo());
            revalidate();
            repaint();
        } else {
            System.out.println("No queda  "+ Productos.obtenerProducto(tipo));
        }
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