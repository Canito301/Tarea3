package org.Tarea3.Interfaz_GUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Clase de utilidad para cargar imágenes en la interfaz gráfica.
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public class UtilsImagen {

    /**
     * Carga una imagen desde la ruta especificada.
     *
     * @param ruta la ruta del recurso de la imagen
     * @return la imagen cargada como {@link BufferedImage}, o null si ocurre un error
     */
    public static BufferedImage cargarBuffered(String ruta) {
        try {
            return ImageIO.read(Objects.requireNonNull(UtilsImagen.class.getResource(ruta)));
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + ruta);
            return null;
        }
    }
}