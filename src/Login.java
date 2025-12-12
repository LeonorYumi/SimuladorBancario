import javax.swing.*;
import java.awt.event.*;

public class Login {
    private JLabel Inicio;
    private JLabel Usuario;
    private JPasswordField text2;
    private JTextField text1;
    private JPanel PanelLogin;
    private JLabel Contraseña;
    private JButton btnIngresar;

    public Login() {

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = text1.getText();
                String clave = new String(text2.getPassword());

                if (usuario.equals("cliente123") && clave.equals("clave456")) {

                    // Pedir el nombre real del cliente
                    String nombre = JOptionPane.showInputDialog(
                            null,
                            "Ingrese su nombre:",
                            "Identificación",
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (nombre == null || nombre.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Debe ingresar un nombre válido");
                        return;
                    }

                    // Abrir la ventana principal
                    JFrame frame = new JFrame("Banco");
                    BancoForm ventana = new BancoForm(nombre);
                    frame.setContentPane(ventana.getBancoPanel());
                    frame.setSize(600, 450);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                    // Cerrar ventana de login
                    SwingUtilities.getWindowAncestor(btnIngresar).dispose();

                } else {
                    JOptionPane.showMessageDialog(null,
                            "Usuario o contraseña incorrectos");
                    text1.setText("");
                    text2.setText("");
                }
            }
        });
    }

    public JPanel getLoginPanel() {
        return PanelLogin;
    }
}