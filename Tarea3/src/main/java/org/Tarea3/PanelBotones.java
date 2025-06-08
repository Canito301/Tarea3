package org.Tarea3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelBotones extends JPanel {
    public void agregarBotones(JPanel panel, int baseX, int baseY, int tam, int espaciado, ActionListener compra) {
        panel.setLayout(null); // Posicionamiento absoluto
        for (int i = 0; i < 5; i++) {
            int fila = i / 2;
            int columna = i % 2;

            int x = baseX + columna * (tam + espaciado);
            int y = baseY + fila * (tam + espaciado);

            JButton boton = new JButton(String.valueOf(i + 1));
            boton.setBounds(x, y, tam, tam);
            boton.addActionListener(compra);
            panel.add(boton);
        }
    }

    public void dibujarFondo(Graphics g, int baseX, int baseY, int tam, int espaciado) {
        for (int i = 0; i < 5; i++) {
            int y = baseY + i * (tam + espaciado);
            g.setColor(Color.GRAY);
            g.fillRect(baseX, y, tam, tam);
            g.setColor(Color.BLACK);
            g.drawRect(baseX, y, tam, tam);
        }
    }
    public void crearYReubicarBotones(int anchoPanel, int altoPanel) {
        removeAll(); // Borra botones anteriores


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
            boton.addActionListener(e -> {
                System.out.println("Botón " + ((JButton)e.getSource()).getText() + " presionado");
            });
            add(boton);
        }

        revalidate();
        repaint();
    }
}