import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginInterface {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginInterface() {
        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        ImageIcon image = new ImageIcon("C:\\Users\\Dania\\Desktop\\Mate\\Captura.png");
        JLabel imageLabel = new JLabel(image);
        mainPanel.add(imageLabel, BorderLayout.NORTH);

        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.YELLOW);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Usuario: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Contraseña: ");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 14));
        loginButton.setBackground(Color.decode("#4CAF50"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        panel.add(titleLabel, constraints);

        constraints.gridy = 1;
        panel.add(usernameLabel, constraints);

        constraints.gridy = 2;
        panel.add(usernameField, constraints);

        constraints.gridy = 3;
        panel.add(passwordLabel, constraints);

        constraints.gridy = 4;
        panel.add(passwordField, constraints);

        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Verificación de credenciales en la base de datos
                boolean inicioSesionExitoso = validarCredenciales(username, password);

                if (inicioSesionExitoso) {
                    JOptionPane.showMessageDialog(frame, "¡Inicio de sesión exitoso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Inicio de sesión fallido. Inténtalo de nuevo.");
                }
            }
        });

        mainPanel.add(panel, BorderLayout.CENTER);
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Método para validar las credenciales en la base de datos
    private boolean validarCredenciales(String username, String password) {
        String url = "jdbc:mariadb://localhost:3306/tienda"; // Reemplaza con tu URL de conexión
        String usuarioDB = "";
        String contraseñaDB = "";

        try (Connection conn = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM Usuarios WHERE nombre = ? AND contraseña = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Devuelve true si las credenciales son válidas y se encuentra el usuario
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StyledLoginInterface();
            }
        });
    }
}
