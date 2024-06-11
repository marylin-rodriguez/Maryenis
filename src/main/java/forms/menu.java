package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame {

    private JDesktopPane desktopPane;

    public menu() {
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú App
        JMenu appMenu = new JMenu("App");
        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        appMenu.add(salirItem);
        menuBar.add(appMenu);

        // Menú Entidades
        JMenu entidadMenu = new JMenu("Entidades");

        // Item Cliente
        JMenuItem abrirCliente = new JMenuItem("Cliente");
        abrirCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente clienteForm = new Cliente();
                desktopPane.add(clienteForm);
                clienteForm.setVisible(true);
            }
        });

        // Item Producto
        JMenuItem abrirProductoItem = new JMenuItem("Producto");
        abrirProductoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = new Producto();
                desktopPane.add(producto);
                producto.setVisible(true);
            }
        });

        // Item Proveedor
        JMenuItem abrirProveedor = new JMenuItem("Proveedor");
        abrirProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Proveedor proveedor = new Proveedor();
                desktopPane.add(proveedor);
                proveedor.setVisible(true);
            }
        });

        // Item Venta
        JMenuItem abrirVenta = new JMenuItem("Venta");
        abrirVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Venta venta = new Venta();
                desktopPane.add(venta);
                venta.setVisible(true);
            }
        });

        // Añadir los items al menú Entidades
        entidadMenu.add(abrirCliente);
        entidadMenu.add(abrirProductoItem);
        entidadMenu.add(abrirProveedor);
        entidadMenu.add(abrirVenta);

        menuBar.add(entidadMenu);

        // Añadir la barra de menú al frame
        setJMenuBar(menuBar);

        // Hacer visible el frame
        setVisible(true);
    }

}