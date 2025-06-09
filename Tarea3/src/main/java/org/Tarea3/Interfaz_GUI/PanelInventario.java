package org.Tarea3.Interfaz_GUI;

import org.Tarea3.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Clase que representa el panel de inventario del comprador en la interfaz gráfica.
 * <p>
 * Muestra las monedas disponibles del comprador (100, 500, 1000 pesos) y los productos adquiridos.
 * Permite seleccionar monedas para realizar compras y actualiza los contadores de monedas.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class PanelInventario extends JPanel {

    /** Imagen de fondo del inventario. */
    private BufferedImage imagenInventario;

    /** Lista de productos visuales en el inventario. */
    private final java.util.List<ProductoVisual> productosInventario = new ArrayList<>();

    /** Etiqueta que muestra la cantidad de monedas de 100 pesos. */
    private JLabel cantidadMonedas100;

    /** Etiqueta que muestra la cantidad de monedas de 500 pesos. */
    private JLabel cantidadMonedas500;

    /** Etiqueta que muestra la cantidad de monedas de 1000 pesos. */
    private JLabel cantidadMonedas1000;

    /** Botón para seleccionar una moneda de 100 pesos. */
    private JButton moneda100;

    /** Botón para seleccionar una moneda de 500 pesos. */
    private JButton moneda500;

    /** Botón para seleccionar una moneda de 1000 pesos. */
    private JButton moneda1000;

    /**
     * Constructor que inicializa el panel de inventario con una imagen de fondo y componentes gráficos.
     */
    public PanelInventario() {
        setOpaque(false);
        imagenInventario = UtilsImagen.cargarBuffered("/img/Inventario.jpg");
        setLayout(null);
        inicializarComponentes();
    }

    /**
     * Inicializa los componentes gráficos del inventario, como etiquetas y botones de monedas.
     */
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

    /**
     * Crea un botón para una moneda con la imagen especificada.
     *
     * @param rutaImagen la ruta de la imagen de la moneda
     * @param valor      el valor de la moneda (100, 500 o 1000)
     * @param x          la coordenada x del botón
     * @param y          la coordenada y del botón
     * @return el botón creado
     */
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
     * Asocia listeners a los botones de monedas para manejar su selección.
     *
     * @param callback   el callback que procesa la moneda seleccionada
     * @param comprador  el comprador que gestiona las monedas
     */
    public void setMonedaListeners(CallbackMoneda callback, Comprador comprador) {
        moneda100.addActionListener(e -> {
            if (comprador.getEstado() == EstadoComprador.SELECCIONAR_MONEDA) {
                Moneda m = comprador.sacarMoneda(100);
                if (m != null) {
                    callback.onMonedaSeleccionada(m);
                    actualizarContadorMonedas(comprador.contarMonedas());
                }
            }
        });
        moneda500.addActionListener(e -> {
            if (comprador.getEstado() == EstadoComprador.SELECCIONAR_MONEDA) {
                Moneda m = comprador.sacarMoneda(500);
                if (m != null) {
                    callback.onMonedaSeleccionada(m);
                    actualizarContadorMonedas(comprador.contarMonedas());
                }
            }
        });
        moneda1000.addActionListener(e -> {
            if (comprador.getEstado() == EstadoComprador.SELECCIONAR_MONEDA) {
                Moneda m = comprador.sacarMoneda(1000);
                if (m != null) {
                    callback.onMonedaSeleccionada(m);
                    actualizarContadorMonedas(comprador.contarMonedas());
                }
            }
        });
    }

    /**
     * Actualiza los contadores de monedas en el panel.
     *
     * @param conteo lista con la cantidad de monedas [100, 500, 1000]
     */
    public void actualizarContadorMonedas(ArrayList<Integer> conteo) {
        cantidadMonedas100.setText("" + conteo.get(0));
        cantidadMonedas500.setText("" + conteo.get(1));
        cantidadMonedas1000.setText("" + conteo.get(2));
    }

    /**
     * Interfaz para el callback de selección de monedas.
     */
    public interface CallbackMoneda {
        /**
         * Método invocado cuando se selecciona una moneda.
         *
         * @param moneda la moneda seleccionada
         */
        void onMonedaSeleccionada(Moneda moneda);
    }

    /**
     * Dibuja la imagen de fondo del inventario.
     *
     * @param g el contexto gráfico
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenInventario != null) {
            g.drawImage(imagenInventario, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /** Posiciones fijas para los productos en el inventario. */
    private final Point[] posicionesFijas = {
            new Point(50, 270), new Point(135, 270), new Point(220, 270),
            new Point(50, 370), new Point(120, 370), new Point(200, 370),
    };

    /**
     * Agrega un producto visual al inventario.
     * <p>
     * Si el producto ya existe, incrementa su contador. Si no, lo añade en una posición fija
     * si hay espacio disponible.
     * </p>
     *
     * @param nuevo el producto visual a agregar
     * @return true si se agregó o actualizó el producto, false si el inventario está lleno
     */
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