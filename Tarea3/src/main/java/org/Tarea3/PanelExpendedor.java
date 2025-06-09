
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
    private final PanelInventario panelInventario;

    private ProductoVisual productoEnMano;  // Producto que está "afuera"

    private final JButton btnAgregarInventario;

    public PanelExpendedor(Expendedor exp, PanelInventario panelInventario) {
        this.exp = exp;
        this.panelInventario = panelInventario;

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

        // Botón para agregar producto a inventario
        btnAgregarInventario = new JButton("Agregar a Inventario");
        btnAgregarInventario.addActionListener(e -> {
            if (productoEnMano != null) {
                boolean agregado = panelInventario.agregarProducto(productoEnMano);
                if (agregado) {
                    panelComprador.limpiarProducto();
                    productoEnMano = null;
                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Inventario lleno.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay producto para agregar al inventario.");
            }
        });
        add(btnAgregarInventario);

        // Redimensionar componentes
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarComponentes();
            }
        });

        // Tamaño preferido basado en la imagen
        if (imagenExpendedor != null) {
            setPreferredSize(new Dimension(imagenExpendedor.getWidth(), imagenExpendedor.getHeight()));
        } else {
            setPreferredSize(new Dimension(500, 500)); // fallback
        }
    }

    private void ajustarComponentes() {
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        int columnas = 5;
        int filas = 5;

        double anchoRel = 0.05;
        double altoRel = 0.101;
        double distanciaEjeX = 0;
        double distanciaEjeY = 0;
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

        // Posicionar botón "Agregar a Inventario"
        int btnAncho = 150;
        int btnAlto = 30;
        int btnX = compradorX;
        int btnY = compradorY - btnAlto - 10; // arriba del comprador
        btnAgregarInventario.setBounds(btnX, btnY, btnAncho, btnAlto);
    }

    private void comprarProducto(int tipo) {
        ArrayList<ProductoVisual> lista = productosPorTipo.get(tipo);
        if (lista != null && !lista.isEmpty()) {
            productoEnMano = lista.remove(lista.size() - 1); // sacar del expendedor
            remove(productoEnMano);
            panelComprador.mostrarProducto(productoEnMano.getTipo());
            revalidate();
            repaint();
        } else {
            System.out.println("No queda " + Productos.obtenerProducto(tipo));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenExpendedor != null) {
            g.drawImage(imagenExpendedor, 0, 0, getWidth(), getHeight(), null);
        }
    }
}
