package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntConsumer;

public class PanelBotones extends JPanel {
    private IntConsumer callback;

    public void setCallback(IntConsumer callback) {
        this.callback = callback;
    }

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

    public void setBotonesHabilitados(boolean habilitado) {
        for (Component c : getComponents()) {
            if (c instanceof JButton) {
                c.setEnabled(habilitado);
            }
        }
    }
}