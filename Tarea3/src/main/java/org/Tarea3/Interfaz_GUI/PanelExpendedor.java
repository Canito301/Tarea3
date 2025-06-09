package org.Tarea3.Interfaz_GUI;

import org.Tarea3.Logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Clase que representa el panel principal de la máquina expendedora en la interfaz gráfica.
 * <p>
 * Este panel muestra la máquina expendedora, los productos disponibles, los botones de selección,
 * el panel de comprador, el panel de inventario, y los controles para confirmar compras, recoger vuelto
 * y rellenar stock. Gestiona la lógica de interacción entre el comprador y la máquina.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class PanelExpendedor extends JPanel {

    /** Instancia de la máquina expendedora lógica. */
    private final Expendedor exp;

    /** Instancia del comprador lógico. */
    private final Comprador comprador;

    /** Imagen de fondo de la máquina expendedora. */
    private final BufferedImage imagenExpendedor;

    /** Lista de productos visuales en la máquina. */
    private final ArrayList<ProductoVisual> productos = new ArrayList<>();

    /** Mapa que agrupa los productos visuales por tipo. */
    private final Map<Integer, ArrayList<ProductoVisual>> productosPorTipo = new HashMap<>();

    /** Panel de botones para seleccionar productos. */
    private final PanelBotones botones;

    /** Panel para mostrar el producto comprado. */
    private final PanelComprador panelComprador;

    /** Panel para mostrar el inventario del comprador. */
    private final PanelInventario panelInventario;

    /** Producto visual en mano, pendiente de ser agregado al inventario. */
    private ProductoVisual productoEnMano;

    /** Botón para agregar el producto al inventario. */
    private final JButton btnAgregarInventario;

    /** Botón para rellenar el stock de la máquina. */
    private final JButton btnRellenarStock;

    /** Botón para confirmar la compra. */
    private final JButton btnConfirmarCompra;

    /** Botón para recoger el vuelto. */
    private final JButton btnRecogerVuelto;

    /** Etiqueta que muestra el valor total insertado. */
    private final JLabel valorInsertadoLabel;

    /** Lista de monedas seleccionadas por el comprador. */
    private final ArrayList<Moneda> monedasSeleccionadas;

    /** Valor total de las monedas insertadas. */
    private int valorInsertado;

    /**
     * Constructor que inicializa el panel con la máquina expendedora, el comprador y el inventario.
     *
     * @param exp            la máquina expendedora lógica
     * @param comprador      el comprador lógico
     * @param panelInventario el panel de inventario del comprador
     */
    public PanelExpendedor(Expendedor exp, Comprador comprador, PanelInventario panelInventario) {
        this.exp = exp;
        this.comprador = comprador;
        this.panelInventario = panelInventario;

        imagenExpendedor = UtilsImagen.cargarBuffered("/img/Expendedor.png");

        setLayout(null);

        botones = new PanelBotones();
        botones.setOpaque(false);
        botones.setLayout(null);

        panelComprador = new PanelComprador();
        panelComprador.setOpaque(false);
        add(panelComprador);

        monedasSeleccionadas = new ArrayList<>();
        valorInsertado = 0;

        valorInsertadoLabel = new JLabel("Insertado: $0");
        valorInsertadoLabel.setFont(new Font("Comic Sans", Font.BOLD, 16));
        valorInsertadoLabel.setForeground(Color.WHITE);
        add(valorInsertadoLabel);

        btnConfirmarCompra = new JButton("Confirmar Compra");
        btnConfirmarCompra.setFont(new Font("Comic Sans", Font.BOLD, 14));
        btnConfirmarCompra.setBackground(Color.CYAN);
        btnConfirmarCompra.addActionListener(e -> {
            if (!monedasSeleccionadas.isEmpty()) {
                comprador.avanzarEstado(); // Pasa a SELECCIONAR_PRODUCTO
                actualizarEstado();
            } else {
                JOptionPane.showMessageDialog(this, "Inserta al menos una moneda.");
            }
        });
        add(btnConfirmarCompra);

        inicializarProductos();

        botones.setCallback(this::seleccionarProducto);
        add(botones);

        btnAgregarInventario = new JButton("Agregar a Inventario");
        btnAgregarInventario.addActionListener(e -> {
            if (productoEnMano != null) {
                comprador.recogerProducto(exp); // Recoge el producto y consume (avanza a RECOGER_VUELTO)
                boolean agregado = panelInventario.agregarProducto(productoEnMano);
                if (agregado) {
                    panelComprador.limpiarProducto();
                    productoEnMano = null;
                    revalidate();
                    repaint();
                    actualizarEstado();
                } else {
                    JOptionPane.showMessageDialog(this, "Inventario lleno.");
                    // Restaurar estado a RECOGER_PRODUCTO si el inventario está lleno
                    while (comprador.getEstado() != EstadoComprador.RECOGER_PRODUCTO) {
                        comprador.avanzarEstado();
                    }
                    actualizarEstado();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay producto para agregar al inventario.");
            }
        });
        add(btnAgregarInventario);

        btnRellenarStock = new JButton("Rellenar Stock");
        btnRellenarStock.addActionListener(e -> rellenarStock());
        add(btnRellenarStock);

        btnRecogerVuelto = new JButton("Recoger Vuelto");
        btnRecogerVuelto.addActionListener(e -> {
            comprador.recogerVuelto(exp);
            monedasSeleccionadas.clear();
            valorInsertado = 0;
            valorInsertadoLabel.setText("Insertado: $0");
            panelInventario.actualizarContadorMonedas(comprador.contarMonedas());
            // Avanzar hasta SELECCIONAR_MONEDA
            while (comprador.getEstado() != EstadoComprador.SELECCIONAR_MONEDA) {
                comprador.avanzarEstado();
            }
            actualizarEstado();
        });
        add(btnRecogerVuelto);

        panelInventario.setMonedaListeners(moneda -> {
            if (comprador.getEstado() == EstadoComprador.SELECCIONAR_MONEDA) {
                monedasSeleccionadas.add(moneda);
                valorInsertado += moneda.getValor();
                valorInsertadoLabel.setText("Insertado: $" + valorInsertado);
                actualizarEstado();
            }
        }, comprador);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ajustarComponentes();
            }
        });

        if (imagenExpendedor != null) {
            setPreferredSize(new Dimension(imagenExpendedor.getWidth(), imagenExpendedor.getHeight()));
        } else {
            setPreferredSize(new Dimension(500, 500));
        }

        actualizarEstado();
    }

    /**
     * Inicializa los productos visuales en la máquina expendedora.
     * <p>
     * Crea cinco productos visuales por cada tipo de producto (1 a 5) y los organiza en el panel.
     * </p>
     */
    private void inicializarProductos() {
        productos.clear();
        productosPorTipo.clear();

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
    }

    /**
     * Ajusta la posición y tamaño de los componentes gráficos según las dimensiones del panel.
     */
    private void ajustarComponentes() {
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

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

        int botonesAncho = anchoPanel / 15;
        int botonesAlto = altoPanel / 7;
        int botonesX = (int) (anchoPanel * 0.61);
        int botonesY = (int) (altoPanel * 0.38);

        botones.setBounds(botonesX, botonesY, botonesAncho, botonesAlto);
        botones.crearYReubicarBotones(botonesAncho, botonesAlto);

        int compradorAncho = anchoPanel / 6;
        int compradorAlto = altoPanel / 6;
        int compradorX = (int) (anchoPanel * 0.1);
        int compradorY = (int) (altoPanel * 0.75);

        panelComprador.setBounds(compradorX, compradorY, compradorAncho, compradorAlto);

        int btnAncho = 150;
        int btnAlto = 30;

        valorInsertadoLabel.setBounds(10, 500, 150, 20);
        btnConfirmarCompra.setBounds(10, 530, btnAncho, btnAlto);
        btnAgregarInventario.setBounds(compradorX + compradorAncho + 10, compradorY, btnAncho + 100, btnAlto + 40);
        btnAgregarInventario.setBackground(Color.white);
        btnRellenarStock.setBounds(compradorX, compradorY - 2 * (btnAlto + 10), btnAncho, btnAlto);
        btnRecogerVuelto.setBounds(compradorX, compradorY + compradorAlto + 5, btnAncho, btnAlto);
    }

    /**
     * Selecciona un producto y realiza la compra si el estado del comprador lo permite.
     *
     * @param tipo el identificador del producto (1 a 5)
     * @throws PagoInsuficienteException si el pago es insuficiente
     * @throws NoHayProductoException si no hay stock del producto
     * @throws PagoIncorrectoException si las monedas son inválidas
     */
    private void seleccionarProducto(int tipo) {
        if (comprador.getEstado() != EstadoComprador.SELECCIONAR_PRODUCTO) {
            return;
        }

        try {
            comprador.realizarCompra(new ArrayList<>(monedasSeleccionadas), tipo, exp);
            ArrayList<ProductoVisual> lista = productosPorTipo.get(tipo);
            if (lista != null && !lista.isEmpty()) {
                productoEnMano = lista.remove(lista.size() - 1);
                remove(productoEnMano);
                revalidate();
                repaint();
            }
            panelComprador.mostrarProducto(tipo);
            monedasSeleccionadas.clear();
            valorInsertado = 0;
            valorInsertadoLabel.setText("Insertado: $0");
            actualizarEstado();
        } catch (PagoInsuficienteException | NoHayProductoException | PagoIncorrectoException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            comprador.recogerVuelto(exp);
            monedasSeleccionadas.clear();
            valorInsertado = 0;
            valorInsertadoLabel.setText("Insertado: $0");
            panelInventario.actualizarContadorMonedas(comprador.contarMonedas());
            // Avanzar hasta SELECCIONAR_MONEDA
            while (comprador.getEstado() != EstadoComprador.SELECCIONAR_MONEDA) {
                comprador.avanzarEstado();
            }
            actualizarEstado();
        }
    }

    /**
     * Rellena el stock de productos en la máquina expendedora.
     * <p>
     * Elimina los productos visuales actuales, reinicializa los productos y ajusta los componentes.
     * </p>
     */
    private void rellenarStock() {
        exp.rellenarDepositos();
        for (ProductoVisual pv : productos) {
            remove(pv);
        }
        inicializarProductos();
        ajustarComponentes();
        revalidate();
        repaint();
    }

    /**
     * Actualiza el estado de los componentes gráficos según el estado del comprador.
     * <p>
     * Habilita o deshabilita botones según el estado actual del comprador y actualiza el contador de monedas.
     * </p>
     */
    private void actualizarEstado() {
        botones.setBotonesHabilitados(comprador.getEstado() == EstadoComprador.SELECCIONAR_PRODUCTO);
        btnConfirmarCompra.setEnabled(comprador.getEstado() == EstadoComprador.SELECCIONAR_MONEDA && !monedasSeleccionadas.isEmpty());
        btnAgregarInventario.setEnabled(comprador.getEstado() == EstadoComprador.RECOGER_PRODUCTO && productoEnMano != null);
        btnRecogerVuelto.setEnabled(comprador.getEstado() == EstadoComprador.RECOGER_VUELTO);
        panelInventario.actualizarContadorMonedas(comprador.contarMonedas());
    }

    /**
     * Dibuja la imagen de fondo de la máquina expendedora.
     *
     * @param g el contexto gráfico
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenExpendedor != null) {
            g.drawImage(imagenExpendedor, 0, 0, getWidth(), getHeight(), null);
        }
    }
}