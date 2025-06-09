package org.Tarea3;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        Expendedor exp = new Expendedor(5);
        Comprador comprador = new Comprador();
        JFrame frame = new JFrame("Expendedor Visual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PanelPrincipal(exp));

        frame.pack();
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setVisible(true);
    }
}