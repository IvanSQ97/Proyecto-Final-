import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TiendaInterfaz extends JFrame {

    private List<String> imagenesProductos = new ArrayList<>();
    private JTextArea carritoTextArea;

    public TiendaInterfaz() {
        setTitle("Tienda Online");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Bienvenido a la Tienda Online");
        topPanel.add(titleLabel);

        // Panel central con productos e imágenes locales
        JPanel centerPanel = new JPanel(new GridLayout(3, 2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Ubicación de las imágenes locales (modifica la ruta según tu configuración)
        String rutaImagenes = "C:\\Users\\Dania\\Desktop\\Mate";

        // Cargar imágenes locales
        File carpeta = new File(rutaImagenes);
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    imagenesProductos.add(archivo.getAbsolutePath());
                }
            }
        }

        // Simulación de productos con imágenes locales
        // Simulación de productos con imágenes locales
for (String rutaImagen : imagenesProductos) {
    JPanel productPanel = new JPanel();
    productPanel.setBorder(BorderFactory.createEtchedBorder());

    ImageIcon imageIcon = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    JLabel productImageLabel = new JLabel(imageIcon);
    JLabel productNameLabel = new JLabel(new File(rutaImagen).getName()); // Nombre del archivo como nombre del producto
    JLabel productPriceLabel = new JLabel("$10"); // Precio simulado

    productPanel.add(productImageLabel);
    productPanel.add(productNameLabel);
    productPanel.add(productPriceLabel);

    centerPanel.add(productPanel);
}


        // Panel lateral para el carrito de compras
        JPanel sidePanel = new JPanel(new BorderLayout());
        JLabel carritoLabel = new JLabel("Carrito de Compras");
        carritoTextArea = new JTextArea(15, 20);
        JScrollPane scrollPane = new JScrollPane(carritoTextArea);
        sidePanel.add(carritoLabel, BorderLayout.NORTH);
        sidePanel.add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel bottomPanel = new JPanel();
        JButton addToCartButton = new JButton("Agregar al carrito");
        JButton checkoutButton = new JButton("Realizar compra");

        bottomPanel.add(addToCartButton);
        bottomPanel.add(checkoutButton);

        // Manejador para el botón Agregar al carrito
        addToCartButton.addActionListener(e -> agregarAlCarrito());

        // Agregar paneles al frame principal
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Centrar ventana
    }

    private void agregarAlCarrito() {
        // Simulación de agregar un producto al carrito
        int productoSeleccionado = (int) (Math.random() * imagenesProductos.size());
        String producto = new File(imagenesProductos.get(productoSeleccionado)).getName(); // Nombre del archivo como producto
        carritoTextArea.append(producto + " agregado al carrito\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TiendaInterfaz tienda = new TiendaInterfaz();
            tienda.setVisible(true);
        });
    }

    public void mostrarTiendaInterfaz() {
        EventQueue.invokeLater(() -> {
            TiendaInterfaz tienda = new TiendaInterfaz();
            tienda.setVisible(true);
        });
    }
    
}
