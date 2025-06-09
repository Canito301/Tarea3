package org.Tarea3.Interfaz_GUI;

import org.Tarea3.Logica.*;
import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el panel principal de la interfaz gráfica de la máquina expendedora.
 * <p>
 * Contiene una única instancia de {@link Comprador}, el {@link PanelExpendedor} y el
 * {@link PanelInventario}, organizados en un diseño de borde para mostrar la máquina y el inventario.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class PanelPrincipal extends JPanel {

    /**
     * Constructor que inicializa el panel principal con la máquina expendedora y el inventario.
     *
     * @param exp la máquina expendedora lógica
     */
    public PanelPrincipal(Expendedor exp) {
        setLayout(new BorderLayout());

        // Crear la única instancia de Comprador
        Comprador comprador = new Comprador();

        // Crear paneles y pasar la instancia de Comprador
        PanelInventario panelInventario = new PanelInventario();
        panelInventario.setPreferredSize(new Dimension(450, 0));
        PanelExpendedor panelExpendedor = new PanelExpendedor(exp, comprador, panelInventario);

        add(panelExpendedor, BorderLayout.CENTER);
        add(panelInventario, BorderLayout.EAST);
    }
}