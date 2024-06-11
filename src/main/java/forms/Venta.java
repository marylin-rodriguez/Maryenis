package forms;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.marylin.util.Hibernateutil;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import org.marylin.entity.Venta01;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.marylin.entity.Venta01;
import org.marylin.util.Hibernateutil;

public class Venta extends JInternalFrame {
    private JTextField fechaField;
    private JTextField productoField;
    private JTextField cantidadField;
    private JTextField precioUnitarioField;
    private JTextField idClienteField;
    private JTextField nombreClienteField;
    private List<Venta01> ventasRegistradas;

    public Venta() {
        super("Venta", true, true, true, true);
        ventasRegistradas = new ArrayList<>();
        iniciarVenta();
    }

    private void iniciarVenta() {
        setSize(400, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 5, 5));
        panel.setBackground(new Color(220, 220, 220));

        JLabel fechaLabel = new JLabel("Fecha:");
        panel.add(fechaLabel);
        fechaField = new JTextField();
        fechaField.setBackground(Color.LIGHT_GRAY);
        panel.add(fechaField);

        JLabel productoLabel = new JLabel("Producto:");
        panel.add(productoLabel);
        productoField = new JTextField();
        productoField.setBackground(Color.LIGHT_GRAY);
        panel.add(productoField);

        JLabel cantidadLabel = new JLabel("Cantidad:");
        panel.add(cantidadLabel);
        cantidadField = new JTextField();
        cantidadField.setBackground(Color.LIGHT_GRAY);
        panel.add(cantidadField);

        JLabel precioUnitarioLabel = new JLabel("Precio Unitario:");
        panel.add(precioUnitarioLabel);
        precioUnitarioField = new JTextField();
        precioUnitarioField.setBackground(Color.LIGHT_GRAY);
        panel.add(precioUnitarioField);

        JLabel idClienteLabel = new JLabel("ID Cliente:");
        panel.add(idClienteLabel);
        idClienteField = new JTextField();
        idClienteField.setBackground(Color.LIGHT_GRAY);
        panel.add(idClienteField);

        JLabel nombreClienteLabel = new JLabel("Nombre Cliente:");
        panel.add(nombreClienteLabel);
        nombreClienteField = new JTextField();
        nombreClienteField.setBackground(Color.LIGHT_GRAY);
        panel.add(nombreClienteField);

        JButton registrarButton = new JButton("Registrar Venta");
        registrarButton.setBackground(Color.MAGENTA);
        registrarButton.setForeground(Color.BLACK);
        registrarButton.addActionListener(e -> registrarVenta());
        panel.add(registrarButton);

        JButton mostrarVentasButton = new JButton("Mostrar Ventas");
        mostrarVentasButton.setBackground(Color.MAGENTA);
        mostrarVentasButton.setForeground(Color.BLACK);
        mostrarVentasButton.addActionListener(e -> mostrarVentas());
        panel.add(mostrarVentasButton);

        JButton editarButton = new JButton("Editar Venta");
        editarButton.setBackground(Color.MAGENTA);
        editarButton.setForeground(Color.BLACK);
        editarButton.addActionListener(e -> editarVenta());
        panel.add(editarButton);

        JButton eliminarButton = new JButton("Eliminar Venta");
        eliminarButton.setBackground(Color.MAGENTA);
        eliminarButton.setForeground(Color.BLACK);
        eliminarButton.addActionListener(e -> eliminarVenta());
        panel.add(eliminarButton);

        setContentPane(panel);
        setVisible(true);
    }

    private void registrarVenta() {
        String fecha = fechaField.getText();
        String producto = productoField.getText();
        String cantidad = cantidadField.getText();
        String precioUnitario = precioUnitarioField.getText();
        String idCliente = idClienteField.getText();
        String nombreCliente = nombreClienteField.getText();

        if (fecha.isEmpty() || producto.isEmpty() || cantidad.isEmpty() ||
                precioUnitario.isEmpty() || idCliente.isEmpty() || nombreCliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
            return;
        }

        Venta01 nuevaVenta = new Venta01(fecha, producto, cantidad, precioUnitario, idCliente, nombreCliente);
        ventasRegistradas.add(nuevaVenta);

        guardarVenta(nuevaVenta);
        mostrarVentas();
        limpiarCampos();
    }

    private void guardarVenta(Venta01 venta) {
        Transaction transaction = null;
        try (Session session = Hibernateutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(venta);
            transaction.commit();
            JOptionPane.showMessageDialog(this, "Venta guardada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(this, "Error al guardar la venta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarVentas() {
        StringBuilder ventasText = new StringBuilder();
        for (Venta01 venta : ventasRegistradas) {
            ventasText.append("Fecha: ").append(venta.getFecha())
                    .append(", Producto: ").append(venta.getProducto())
                    .append(", Cantidad: ").append(venta.getCantidad())
                    .append(", Precio Unitario: ").append(venta.getPrecioUnitario())
                    .append(", ID Cliente: ").append(venta.getIdCliente())
                    .append(", Nombre Cliente: ").append(venta.getNombreCliente())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, ventasText.toString(), "Ventas Registradas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarVenta() {
        String idCliente = JOptionPane.showInputDialog("Ingrese el ID del Cliente de la venta que desea editar:");
        Venta01 venta = buscarVentaPorIdCliente(idCliente);
        if (venta != null) {
            fechaField.setText(Arrays.toString(venta.getFecha()));
            productoField.setText(venta.getProducto());
            cantidadField.setText(venta.getCantidad());
            precioUnitarioField.setText(Arrays.toString(venta.getPrecioUnitario()));
            idClienteField.setText(Arrays.toString(venta.getIdCliente()));
            nombreClienteField.setText(Arrays.toString(venta.getNombreCliente()));

            JButton actualizarButton = new JButton("Actualizar Venta");
            actualizarButton.setBackground(Color.GREEN);
            actualizarButton.setForeground(Color.BLACK);
            actualizarButton.addActionListener(e -> actualizarVenta(venta));
            getContentPane().add(actualizarButton);
            revalidate();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Venta no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarVenta(Venta01 venta) {
        venta.setFecha(fechaField.getText());
        venta.setProducto(productoField.getText());
        venta.setCantidad(cantidadField.getText());
        venta.setPrecioUnitario(precioUnitarioField.getText());
        venta.setIdCliente(idClienteField.getText());
        venta.setNombreCliente(nombreClienteField.getText());

        try (Session session = Hibernateutil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(venta);
            transaction.commit();
            JOptionPane.showMessageDialog(this, "Venta actualizada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar la venta", "Error", JOptionPane.ERROR_MESSAGE);
        }

        mostrarVentas();
        limpiarCampos();
    }

    private void eliminarVenta() {
        String idClienteStr = JOptionPane.showInputDialog("Ingrese el ID del Cliente de la venta que desea eliminar:");
        Venta01 venta = buscarVentaPorIdCliente(idClienteStr);
        if (venta != null) {
            try (Session session = Hibernateutil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(venta);
                transaction.commit();
                ventasRegistradas.remove(venta);
                JOptionPane.showMessageDialog(this, "Venta eliminada exitosamente");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar la venta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Venta no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Venta01 buscarVentaPorIdCliente(String idCliente) {
        for (Venta01 venta : ventasRegistradas) {
            if (venta.getIdCliente().equals(idCliente)) {
                return venta;
            }
        }
        return null;
    }

    private void limpiarCampos() {
        fechaField.setText("");
        productoField.setText("");
        cantidadField.setText("");
        precioUnitarioField.setText("");
        idClienteField.setText("");
        nombreClienteField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Venta ventaFrame = new Venta();
            ventaFrame.setVisible(true);
        });
    }
}
