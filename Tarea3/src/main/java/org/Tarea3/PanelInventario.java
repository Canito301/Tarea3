package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;

public class PanelInventario extends JPanel {
    private BufferedImage imagenInventario;
    private final java.util.List<ProductoVisual> productosInventario = new ArrayList<>();
    private final JButton moneda100 = new JButton();
    private final JButton moneda500 = new JButton();
    private final JButton moneda1000 = new JButton();
    private JLabel cantidadMonedas100;
    private JLabel cantidadMonedas500;
    private JLabel cantidadMonedas1000;

    public PanelInventario() {
        setOpaque(false);
        try {
            imagenInventario = ImageIO.read(Objects.requireNonNull(getClass().getResource("/img/Inventario.jpg")));
        } catch (Exception e) {
            System.err.println("Error cargando imagen inventario: /img/Inventario.jpg");
        }
        setLayout(null);
        inicializarEtiquetas();
    }

    private void inicializarEtiquetas() {
        agregarBotonMoneda(moneda100, "/img/moneda100.png", 145, 80, 72, 72, 100);
        agregarBotonMoneda(moneda500, "/img/moneda500.png", 230, 80, 75, 75, 500);
        agregarBotonMoneda(moneda1000, "/img/moneda1000.png", 315, 80, 75, 75, 1000);

        cantidadMonedas100 = new JLabel("0");
        cantidadMonedas500 = new JLabel("0");
        cantidadMonedas1000 = new JLabel("0");

        cantidadMonedas100.setFont(new Font("Comic Sans", Font.BOLD, 34));
        cantidadMonedas500.setFont(new Font("Comic Sans", Font.BOLD, 34));
        cantidadMonedas1000.setFont(new Font("Comic Sans", Font.BOLD, 34));

        cantidadMonedas100.setBounds(172, 200, 25, 25);
        cantidadMonedas500.setBounds(258, 200, 25, 25);
        cantidadMonedas1000.setBounds(343, 200, 25, 25);

        add(cantidadMonedas100);
        add(cantidadMonedas500);
        add(cantidadMonedas1000);
    }

    private void agregarBotonMoneda(JButton boton, String rutaImagen, int x, int y, int ancho, int alto, int valor) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(rutaImagen));
            Image imagenOriginal = icon.getImage();
            Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
            boton.setBounds(x, y, ancho, alto);
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            boton.setFocusPainted(false);
            boton.setOpaque(false);

            boton.addActionListener(e -> {
                // Aquí defines qué hacer al hacer clic en la moneda
                System.out.println("Insertaste moneda de $" + valor);
                // Aquí puedes agregar lógica para actualizar el sistema con esa moneda
            });

            add(boton);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + rutaImagen);
        }

    }
    public void actualizarContadorMonedas(ArrayList<Integer> conteo) {
        cantidadMonedas100.setText(""+conteo.get(0));
        cantidadMonedas500.setText("" + conteo.get(1));
        cantidadMonedas1000.setText("" + conteo.get(2));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenInventario != null) {
            g.drawImage(imagenInventario, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private final Point[] posicionesFijas = {
            new Point(50, 270), new Point(135, 270), new Point(220, 270),
            new Point(50, 370), new Point(120, 370), new Point(200, 370),
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
        int ancho = 85;
        int alto = 85;

        nuevo.setBounds(pos.x, pos.y, ancho, alto);
        nuevo.escalarImagen(ancho, alto);
        add(nuevo);
        productosInventario.add(nuevo);
        revalidate();
        repaint();
        return true;
    }
}
