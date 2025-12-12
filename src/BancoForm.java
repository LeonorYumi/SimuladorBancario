import javax.swing.*;
import java.awt.event.*;

public class BancoForm {
    private JPanel BancoPanel;
    private JButton depósitoButton;
    private JButton retiroButton;
    private JButton transferenciaButton;
    private JButton salirButton;
    private JLabel clientevalor;
    private JLabel Saldovalor;
    private JTextArea hisorialDeTransaccionesTextArea;
    private JLabel Cliente;
    private JLabel SaldoSiponible;
    private JLabel TransaccionesBancarias;
    private JLabel BancoForm;

    private double saldo = 1000.00;
    private String nombreCliente;

    public BancoForm(String nombre) {

        this.nombreCliente = nombre;

        Cliente.setText("Cliente:");
        SaldoSiponible.setText("Saldo disponible:");


        clientevalor.setText(nombreCliente);
        Saldovalor.setText("$ " + String.format("%.2f", saldo));

        // Depositar
        depósitoButton.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog("Ingrese valor a depositar:");
            try {
                double valor = Double.parseDouble(valorStr);

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor válido");
                    return;
                }
                saldo += valor;
                actualizarSaldo();
                agregarHistorial("Depósito de $" + valor);

                JOptionPane.showMessageDialog(null,
                        "Depósito exitoso.\nNuevo saldo: $" + saldo);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido");
            }
        });

        // retiro
        retiroButton.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog("Ingrese valor a retirar:");
            try {
                double valor = Double.parseDouble(valorStr);

                if (valor > saldo) {
                    JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                    return;
                }

                saldo -= valor;
                actualizarSaldo();
                agregarHistorial("Retiro de $" + valor);

                JOptionPane.showMessageDialog(null,
                        "Retiro exitoso.\nNuevo saldo: $" + saldo);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido");
            }
        });

        // Transferencia
        transferenciaButton.addActionListener(e -> {

            JTextField nombreDestino = new JTextField();
            JTextField monto = new JTextField();

            Object[] datos = {
                    "Nombre del destinatario:", nombreDestino,
                    "Monto a transferir:", monto
            };

            int opcion = JOptionPane.showConfirmDialog(
                    null, datos, "Transferencia", JOptionPane.OK_CANCEL_OPTION
            );

            if (opcion == JOptionPane.OK_OPTION) {
                try {
                    String nombreD = nombreDestino.getText();
                    double cantidad = Double.parseDouble(monto.getText());

                    if (cantidad > saldo) {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                        return;
                    }

                    saldo -= cantidad;
                    actualizarSaldo();
                    agregarHistorial("Transferencia a " + nombreD + " por $" + cantidad);

                    JOptionPane.showMessageDialog(null,
                            "Transferencia exitosa a " + nombreD +
                                    "\nMonto: $" + cantidad +
                                    "\nNuevo saldo: $" + saldo);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Monto inválido");
                }
            }
        });

        // salir
        salirButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "¡Gracias por usar el banco!");
            System.exit(0);
        });
    }

    private void actualizarSaldo() {
        Saldovalor.setText("$ " + String.format("%.2f", saldo));
    }

    private void agregarHistorial(String texto) {
        hisorialDeTransaccionesTextArea.append(texto + "\n");
    }

    public JPanel getBancoPanel() {
        return BancoPanel;
    }
}