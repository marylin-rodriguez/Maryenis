package forms;// Myventana.java

import javax.swing.*;
import java.awt.*;

public class Myventana extends JFrame {

    private JDesktopPane desktopPane;
    private JLabel welcomeLabel;

    public Myventana() {
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);

        welcomeLabel = new JLabel("Welcome to a Mryenis Store");
        welcomeLabel.setBounds(10, 10, 300, 30);
        desktopPane.add(welcomeLabel);

        Cliente ventanaCliente = new Cliente();
        Proveedor ventanaProveedor = new Proveedor();
        Producto ventanaProducto = new Producto();
        Venta ventanaVenta = new Venta();

        desktopPane.add(ventanaCliente);
        desktopPane.add(ventanaProveedor);
        desktopPane.add(ventanaProducto);
        desktopPane.add(ventanaVenta);

        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}