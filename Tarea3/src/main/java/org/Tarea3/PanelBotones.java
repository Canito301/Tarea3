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

        int columnas = 2;
        int filas = 3;
        int btnWidth = (int) (anchoPanel * 0.45);
        int btnHeight = (int)(altoPanel * 0.25);

        for (int i = 0; i < 5; i++) {
            int columna = i % 2;
            int fila = i / 2;

            int x = columna * (btnWidth + 10);
            int y = fila * (btnHeight + 10);

            JButton boton = new JButton(String.valueOf(i + 1));
            boton.setBounds(x, y, btnWidth, btnHeight);
            boton.setFont(new Font("Comic Sans",Font.BOLD,10));
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
}