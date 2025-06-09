package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class PanelInventario extends JPanel {
    private BufferedImage imagenInventario;
    private final java.util.List<ProductoVisual> productosInventario = new ArrayList<>();
    private JLabel cantidadMonedas100;
    private JLabel cantidadMonedas500;
    private JLabel cantidadMonedas1000;
    private JButton moneda100;
    private JButton moneda500;
    private JButton moneda1000;

    public PanelInventario() {
        setOpaque(false);
        imagenInventario = UtilsImagen.cargarBuffered("/img/Inventario.jpg");
        setLayout(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        cantidadMonedas100 = new JLabel("0");
        cantidadMonedas500 = new JLabel("0");
        cantidadMonedas1000 = new JLabel("0");

        cantidadMonedas100.setFont(new Font("Comic Sans", Font.BOLD, 34));
        cantidadMonedas500.setFont(new Font("Comic Sans", Font.BOLD, 34));
        cantidadMonedas1000.setFont(new Font("Comic Sans", Font.BOLD, 34));

        cantidadMonedas100.setBounds(172, 200, 50, 50);
        cantidadMonedas500.setBounds(258, 200, 50, 50);
        cantidadMonedas1000.setBounds(343, 200, 50, 50);

        moneda100 = crearBotonMoneda("/img/moneda100.png", 100, 150, 100);
        moneda500 = crearBotonMoneda("/img/moneda500.png", 500, 235, 100);
        moneda1000 = crearBotonMoneda("/img/moneda1000.png", 1000, 320, 100);

        add(cantidadMonedas100);
        add(cantidadMonedas500);
        add(cantidadMonedas1000);
        add(moneda100);
        add(moneda500);
        add(moneda1000);
    }

    private JButton crearBotonMoneda(String rutaImagen, int valor, int x, int y) {
        JButton boton = new JButton();
        try {
            ImageIcon icon = new ImageIcon(UtilsImagen.cargarBuffered(rutaImagen));
            Image imagenEscalada = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
            boton.setContentAreaFilled(false);
            boton.setBorderPainted(false);
            boton.setFocusPainted(false);
            boton.setBounds(x, y, 60, 60);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + rutaImagen);
        }
        return boton;
    }

    /**
     * Configura los listeners de los botones de monedas para interactuar con el Comprador.
     *
     * @param callback Interfaz para notificar la selección de monedas.
     * @param comprador Instancia de Comprador para extraer monedas.
     */
    public void setMonedaListeners(CallbackMoneda callback, Comprador comprador) {
        moneda100.addActionListener(e -> {
            if (comprador.getEstado() == Comprador.EstadoComprador.SELECCIONAR_MONEDA) {
                Moneda m = comprador.sacarMoneda(100);
                if (m != null) {
                    callback.onMonedaSeleccionada(m);
                    actualizarContadorMonedas(comprador.contarMonedas());
                }
            }
        });
        moneda500.addActionListener(e -> {
            if (comprador.getEstado() == Comprador.EstadoComprador.SELECCIONAR_MONEDA) {
                Moneda m = comprador.sacarMoneda(500);
                if (m != null) {
                    callback.onMonedaSeleccionada(m);
                    actualizarContadorMonedas(comprador.contarMonedas());
                }
            }
        });
        moneda1000.addActionListener(e -> {
            if (comprador.getEstado() == Comprador.EstadoComprador.SELECCIONAR_MONEDA) {
                Moneda m = comprador.sacarMoneda(1000);
                if (m != null) {
                    callback.onMonedaSeleccionada(m);
                    actualizarContadorMonedas(comprador.contarMonedas());
                }
            }
        });
    }

    /**
     * Interfaz para notificar la selección de monedas.
     */
    public interface CallbackMoneda {
        void onMonedaSeleccionada(Moneda moneda);
    }

    public void actualizarContadorMonedas(ArrayList<Integer> conteo) {
        cantidadMonedas100.setText("" + conteo.get(0));
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
        for (ProductoVisual existente : productosInventario) {
            if (existente.getTipo() == nuevo.getTipo()) {
                existente.incrementarCantidad();
                return true;
            }
        }

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