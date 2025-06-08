package org.Tarea3;
import javax.swing.*;
import java.awt.*;
/**
 * Clase abstracta que representa un producto identificado por un número de serie.
 *
 * @author Francisco Fuentealba
 */
public abstract class Producto extends JPanel {
    private int serie;
    private int x, y;

    public Producto(int serie) {
        this.serie = serie;
    }

    public int getSerie() {
        return serie;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
