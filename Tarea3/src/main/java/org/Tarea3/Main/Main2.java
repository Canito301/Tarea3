package org.Tarea3.Main;

import org.Tarea3.Logica.*;
import org.Tarea3.Interfaz_GUI.*;
import javax.swing.*;

/**
 * Clase principal que inicia la aplicación de la máquina expendedora.
 * <p>
 * Crea una instancia de {@link Expendedor}, {@link Comprador}, y configura la ventana gráfica
 * principal con el {@link PanelPrincipal} para mostrar la interfaz de usuario.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class Main2 {

    /**
     * Método principal que lanza la aplicación.
     * <p>
     * Inicializa la máquina expendedora con 5 productos por depósito, crea un comprador,
     * configura la ventana gráfica y la hace visible.
     * </p>
     *
     * @param args argumentos de la línea de comandos (no utilizados)
     */
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