package org.Tarea3;
import javax.swing.*;
import java.awt.*;

/**
 * Clase principal que simula una máquina expendedora, nos apoyamos en ChatGPT para implementar una interfaz gráfica
 * utilizando Java Swing.
 * <p>
 * Permite al usuario seleccionar productos, ingresar valor de moneda por teclado y recibir vuelto.
 * Se muestran botones con imágenes representativas de los productos.
 *</p>
 * @author Leonardo Guerrero
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Clase principal que simula una máquina expendedora, nos apoyamos en ChatGPT para implementar una interfaz gráfica
 * utilizando Java Swing.
 * <p>
 * Permite al usuario seleccionar productos, ingresar valores de monedas por teclado y recibir vuelto.
 * Se muestran botones con imágenes representativas de los productos.
 * </p>
 * @author Leonardo Guerrero
 */
public class MainInteractivo extends JFrame {

    /**
     * Objeto de tipo {@link Expendedor} y que se utiliza para la lógica
     * de compra y entrega de productos y vuelto.
     */
    private Expendedor expendedor;

    /**
     * Constructor de la clase {@code MainInteractivo}.
     * <p>
     * Configura la ventana principal, inicializa el expendedor y agrega
     * los productos disponibles en la máquina expendedora junto con un botón de salida para el usuario.
     * </p>
     */
    public MainInteractivo() {
        setTitle("Máquina Expendedora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(2, 3));

        expendedor = new Expendedor(2);

        agregarProducto("CocaCola", 1, "/img/cocacola.png");
        agregarProducto("Sprite", 2, "/img/sprite.png");
        agregarProducto("Fanta", 3, "/img/fanta.png");
        agregarProducto("Snickers", 4, "/img/snickers.png");
        agregarProducto("Super8", 5, "/img/super8.png");
        agregarBotonSalir();

        setVisible(true);
    }

    /**
     * Agrega un botón con imagen y etiqueta correspondiente a un producto.
     * Al presionar el botón, se solicita una lista de monedas al usuario e intenta realizar la compra.
     * @param nombre Nombre del producto seleccionado.
     * @param idProducto Identificador del producto (utilizado por el {@code Expendedor}).
     * @param rutaImagen Ruta al archivo de imagen del producto.
     */
    private void agregarProducto(String nombre, int idProducto, String rutaImagen) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ImageIcon icon = null;
        java.net.URL imgURL = getClass().getResource(rutaImagen);
        if (imgURL != null) {
            Image img = new ImageIcon(imgURL).getImage();
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(scaledImg);
        } else {
            System.err.println("No se encontró la imagen: " + rutaImagen);
            icon = new ImageIcon();
        }

        JButton boton = new JButton(icon);
        boton.setToolTipText(nombre);

        boton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this,
                    "Ingresa los valores de las monedas (100, 500, 1000) separados por comas:");

            ArrayList<Moneda> monedas = new ArrayList<>();
            try {
                if (input != null && !input.trim().isEmpty()) {
                    String[] valores = input.split(",");
                    for (String valorStr : valores) {
                        int valor = Integer.parseInt(valorStr.trim());
                        Moneda moneda = switch (valor) {
                            case 100 -> new Moneda100();
                            case 500 -> new Moneda500();
                            case 1000 -> new Moneda1000();
                            default -> null;
                        };
                        if (moneda == null) {
                            throw new PagoIncorrectoException();
                        }
                        monedas.add(moneda);
                    }
                } else {
                    monedas = null;
                }
            } catch (Exception ex) {
                monedas = null;
            }

            try {
                Comprador comprador = new Comprador(monedas, idProducto, expendedor);
                StringBuilder vueltoStr = new StringBuilder("[");
                ArrayList<Moneda> vuelto = comprador.getVueltoList();
                for (int i = 0; i < vuelto.size(); i++) {
                    vueltoStr.append(vuelto.get(i).getValor());
                    if (i < vuelto.size() - 1) {
                        vueltoStr.append(", ");
                    }
                }
                vueltoStr.append("] pesos");
                JOptionPane.showMessageDialog(this,
                        "Consumiste: " + comprador.queBebiste() +
                                "\nVuelto: " + vueltoStr +
                                "\nVuelto total: " + comprador.cuantoVuelto() + " pesos");
            } catch (Exception ex) {
                StringBuilder vueltoStr = new StringBuilder("[");
                ArrayList<Moneda> vuelto = expendedor.getVueltoList();
                for (int i = 0; i < vuelto.size(); i++) {
                    vueltoStr.append(vuelto.get(i).getValor());
                    if (i < vuelto.size() - 1) {
                        vueltoStr.append(", ");
                    }
                }
                vueltoStr.append("] pesos");
                JOptionPane.showMessageDialog(this,
                        "Error: " + ex.getMessage() +
                                "\nVuelto: " + vueltoStr);
            }
        });

        Productos prod = Productos.obtenerProducto(idProducto);
        JLabel etiqueta = new JLabel(nombre + " - $" + prod.getPrecio(), JLabel.CENTER);

        panel.add(boton, BorderLayout.CENTER);
        panel.add(etiqueta, BorderLayout.SOUTH);

        add(panel);
    }

    /**
     * Agrega un botón de salida que permite cerrar la aplicación con confirmación.
     */
    private void agregarBotonSalir() {
        JButton salir = new JButton("Salir");
        salir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que deseas salir?", "Salir",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        add(salir);
    }

    /**
     * Método principal que lanza la aplicación de forma segura en el Swing.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainInteractivo::new);
    }
}

/*
import java.util.Scanner;
//main interactivo por consola en caso de que el otro no funcione
public class MainInteractivo {
    public static void main(String[] args) throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {
        Scanner scan = new Scanner(System.in);
        Expendedor exp = new Expendedor(2); // 2 unidades de cada producto

        while (true) {
            System.out.println("\n=== MÁQUINA EXPENDEDORA ===");
            mostrarMenu();
            System.out.print("Ingrese número de producto (0 para salir): ");
            int opcion = scan.nextInt();

            if (opcion == 0) {
                System.out.println("Gracias por su compra. ¡Hasta luego!");
                break;
            }

            System.out.println("Ingrese la moneda");
            int tipoMoneda = scan.nextInt();

            Moneda moneda = null;
            switch (tipoMoneda) {
                case 100: moneda = new Moneda100(); break;
                case 500: moneda = new Moneda500(); break;
                case 1000: moneda = new Moneda1000(); break;
                default:
                    System.out.println("Opción de moneda inválida. Se usará null.");
            }

            try {
                Comprador c = new Comprador(moneda, opcion, exp);
                String consumido = c.queBebiste();
                int vuelto = c.cuantoVuelto();

                if (consumido != null) {
                    System.out.println("Consumiste: " + consumido);
                } else {
                    System.out.println("No se pudo consumir ningún producto.");
                }
                System.out.println("Vuelto recibido: " + vuelto + " pesos");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                Moneda vueltoExtra = exp.getVuelto();
                System.out.println("Se le devolvió: " + vueltoExtra.getValor() + " pesos");
            }
        }

        scan.close();
    }

    private static void mostrarMenu() {
        System.out.println("Productos disponibles:");
        for (Productos p : Productos.values()) {
            System.out.println(p.getValor() + ". " + p.name() + " - $" + p.getPrecio());
        }
    }
}
*/



