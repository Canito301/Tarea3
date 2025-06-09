
package org.Tarea3;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    public PanelPrincipal(Expendedor exp) {
        setLayout(new BorderLayout());

        PanelInventario panelInventario = new PanelInventario();
        panelInventario.setPreferredSize(new Dimension(450, 0));  // ancho fijo, alto dinámico

        PanelExpendedor panelExpendedor = new PanelExpendedor(exp, panelInventario);

        add(panelExpendedor, BorderLayout.CENTER);
        add(panelInventario, BorderLayout.EAST);
    }
}



