import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazTienda extends JFrame {
    private Tienda tienda;

    private JTextArea areaInventario;
    private JTextField campoId;
    private JTextField campoNombre;
    private JTextField campoStock;
    private JTextField campoPrecio;
    private JButton botonAgregar;

    public InterfazTienda(Tienda tienda) {
        this.tienda = tienda;

        setTitle("Interfaz de Tienda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        areaInventario = new JTextArea();
        areaInventario.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaInventario);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelAgregar = new JPanel();
        panelAgregar.setLayout(new GridLayout(5, 2));

        panelAgregar.add(new JLabel("ID:"));
        campoId = new JTextField();
        panelAgregar.add(campoId);

        panelAgregar.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelAgregar.add(campoNombre);

        panelAgregar.add(new JLabel("Stock:"));
        campoStock = new JTextField();
        panelAgregar.add(campoStock);

        panelAgregar.add(new JLabel("Precio:"));
        campoPrecio = new JTextField();
        panelAgregar.add(campoPrecio);

        botonAgregar = new JButton("Agregar Producto");
        botonAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        panelAgregar.add(botonAgregar);

        panelPrincipal.add(panelAgregar, BorderLayout.SOUTH);

        getContentPane().add(panelPrincipal);

        // Mostrar inventario al iniciar la interfaz
        mostrarInventario();
    }

    private void mostrarInventario() {
        StringBuilder inventarioTexto = new StringBuilder();
        for (Producto producto : tienda.getInventario()) {
            inventarioTexto.append("ID: ").append(producto.getId())
                    .append(", Nombre: ").append(producto.getNombre())
                    .append(", Stock: ").append(producto.getStock())
                    .append(", Precio: ").append(producto.getPrecio())
                    .append("\n");
        }
        areaInventario.setText(inventarioTexto.toString());
    }

    private void agregarProducto() {
        try {
            int id = Integer.parseInt(campoId.getText());
            String nombre = campoNombre.getText();
            int stock = Integer.parseInt(campoStock.getText());
            double precio = Double.parseDouble(campoPrecio.getText());

            Producto producto = new Producto(id, nombre, stock, precio);
            tienda.agregarProducto(producto);
            mostrarInventario();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error al ingresar datos. Por favor, verifica los valores.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        InterfazTienda interfazTienda = new InterfazTienda(tienda);
        interfazTienda.setVisible(true);
    }
}
