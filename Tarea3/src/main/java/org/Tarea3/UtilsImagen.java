package org.Tarea3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class UtilsImagen {
    public static BufferedImage cargarBuffered(String ruta) {
        try {
            return ImageIO.read(Objects.requireNonNull(UtilsImagen.class.getResource(ruta)));
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + ruta);
            return null;
        }
    }
}
