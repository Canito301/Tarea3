package org.Tarea3.Interfaz_GUI;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;

/**
 * Clase que representa un panel de botones para seleccionar productos en la interfaz gráfica de una máquina expendedora.
 * <p>
 * Este panel contiene cinco botones numerados (del 1 al 5) dispuestos en una cuadrícula de 3 filas y 2 columnas,
 * que permiten al usuario seleccionar un tipo de producto. Cada botón está asociado a un callback que recibe el
 * tipo de producto seleccionado.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class PanelBotones extends JPanel {

    /** Callback para manejar la selección de un producto. */
    private IntConsumer callback;

    /**
     * Establece el callback que se ejecutará cuando se seleccione un producto.
     *
     * @param callback el consumidor de enteros que procesa el tipo de producto seleccionado
     */
    public void setCallback(IntConsumer callback) {
        this.callback = callback;
    }

    /**
     * Crea y reposiciona los botones en el panel según las dimensiones especificadas.
     * <p>
     * Los botones se organizan en una cuadrícula de 3 filas y 2 columnas (5 botones en total).
     * Cada botón se etiqueta con un número del 1 al 5 y se asocia al callback para manejar la selección.
     * El panel se actualiza después de crear o reposicionar los botones.
     * </p>
     *
     * @param anchoPanel el ancho del panel
     * @param altoPanel  el alto del panel
     */
    public void crearYReubicarBotones(int anchoPanel, int altoPanel) {
        removeAll();

        int btn_Ancho = (int) (anchoPanel * 0.45);
        int btn_Alto = (int)(altoPanel * 0.25);

        for (int i = 0; i < 5; i++) {
            int columna = i % 2;
            int fila = i / 2;

            int x = columna * (btn_Ancho + 10);
            int y = fila * (btn_Alto + 10);

            JButton boton = new JButton(String.valueOf(i + 1));
            boton.setBounds(x, y, btn_Ancho, btn_Alto);
            boton.setFont(new Font("Comic Sans", Font.BOLD, 15));
            boton.setBackground(Color.GRAY);
            final int tipo = i + 1;
            boton.addActionListener(e -> {
                if (callback != null) callback.accept(tipo);
            });
            add(boton);
        }

        revalidate();
        repaint();
    }

    /**
     * Habilita o deshabilita todos los botones del panel.
     *
     * @param habilitado true para habilitar los botones, false para deshabilitarlos
     */
    public void setBotonesHabilitados(boolean habilitado) {
        for (Component c : getComponents()) {
            if (c instanceof JButton) {
                c.setEnabled(habilitado);
            }
        }
    }
}