package org.Tarea3;

import javax.swing.*;
import java.awt.*;

/**
 * Panel principal que contiene la interfaz gráfica del expendedor y el comprador.
 * <p>
 * Crea una única instancia de {@link Comprador} y la pasa a {@link PanelExpendedor}
 * para gestionar la lógica de compra.
 * </p>
 */
public class PanelPrincipal extends JPanel {
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



