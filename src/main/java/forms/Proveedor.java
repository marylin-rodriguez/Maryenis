package forms;

// Proveedor.java
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.marylin.util.Hibernateutil;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class Proveedor extends JInternalFrame {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField direccionField;
    private JTextField contactoField;
    private ArrayList<Proveedor01> proveedores;

    public Proveedor() {
        super("Proveedor", true, true, true, true);
        proveedores = new ArrayList<>();
        iniciarProveedor();
    }

    private void iniciarProveedor() {
        setSize(400, 300);
        getContentPane().setBackground(new Color(235, 235, 235));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5));

        JLabel idLabel = new JLabel("ID del Proveedor:");
        panel.add(idLabel);
        idField = new JTextField();
        idField.setBackground(Color.LIGHT_GRAY);
        panel.add(idField);

        JLabel nombreLabel = new JLabel("Nombre del Proveedor:");
        panel.add(nombreLabel);
        nombreField = new JTextField();
        nombreField.setBackground(Color.LIGHT_GRAY);
        panel.add(nombreField);

        JLabel direccionLabel = new JLabel("Dirección del Proveedor:");
        panel.add(direccionLabel);
        direccionField = new JTextField();
        direccionField.setBackground(Color.LIGHT_GRAY);
        panel.add(direccionField);

        JLabel contactoLabel = new JLabel("Número de Contacto:");
        panel.add(contactoLabel);
        contactoField = new JTextField();
        contactoField.setBackground(Color.LIGHT_GRAY);
        panel.add(contactoField);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBackground(Color.MAGENTA);
        registrarButton.setForeground(Color.BLACK);
        registrarButton.addActionListener(e -> guardarProveedor());
        panel.add(registrarButton);

        JButton mostrarButton = new JButton("Mostrar Proveedores");
        mostrarButton.setBackground(Color.MAGENTA);
        mostrarButton.setForeground(Color.BLACK);
        mostrarButton.addActionListener(e -> mostrarProveedores());
        panel.add(mostrarButton);

        JButton editarButton = new JButton("Editar Proveedor");
        editarButton.setBackground(Color.MAGENTA);
        editarButton.setForeground(Color.BLACK);
        editarButton.addActionListener(e -> editarProveedor());
        panel.add(editarButton);

        JButton eliminarButton = new JButton("Eliminar Proveedor");
        eliminarButton.setBackground(Color.MAGENTA);
        eliminarButton.setForeground(Color.BLACK);
        eliminarButton.addActionListener(e -> eliminarProveedor());
        panel.add(eliminarButton);

        JButton guardarButton = new JButton("Guardar Usuario");
        guardarButton.setBackground(Color.GREEN);
        guardarButton.setForeground(Color.BLACK);
        guardarButton.addActionListener(e -> guardarUsuario());
        panel.add(guardarButton);

        setContentPane(panel);
        setVisible(true);
    }

    public static void guardarUsuario()
    {
        Transaction transaction = null;
        try(Session session = Hibernateutil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            Object proveedor01 = new Proveedor();
            session.save(proveedor01);
            transaction.commit();
        }

        catch (Exception e)
        {
            e.printStackTrace();
            if ((transaction != null))
            {
                transaction.rollback();
            }
        }
    }

    private void guardarProveedor() {
        try {
            String id = idField.getText();
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();
            String contacto = contactoField.getText();

            // Crear un nuevo proveedor y agregarlo a la lista
            Proveedor01 nuevoProveedor = new Proveedor01(id, nombre, direccion, contacto);
            proveedores.add(nuevoProveedor);

            // Imprimir la información en la consola
            System.out.println("Proveedor guardado:");
            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Dirección: " + direccion);
            System.out.println("Contacto: " + contacto);

            JOptionPane.showMessageDialog(null, "Proveedor guardado correctamente.");

            // Limpiar los campos después de registrar el proveedor
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
        }
    }

    private void mostrarProveedores() {
        StringBuilder proveedoresText = new StringBuilder();
        for (Proveedor01 proveedor : proveedores) {
            proveedoresText.append("ID: ").append(proveedor.getId()).append(", Nombre: ").append(proveedor.getNombre())
                    .append(", Dirección: ").append(proveedor.getDireccion()).append(", Contacto: ").append(proveedor.getContacto())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, proveedoresText.toString(), "Proveedores Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarProveedor() {
        try {
            String id = idField.getText();
            for (Proveedor01 proveedor : proveedores) {
                if (proveedor.getId().equals(id)) {
                    proveedor.setNombre(nombreField.getText());
                    proveedor.setDireccion(direccionField.getText());
                    proveedor.setContacto(contactoField.getText());

                    JOptionPane.showMessageDialog(null, "Proveedor editado correctamente.");

                    // Limpiar los campos después de editar el proveedor
                    limpiarCampos();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Proveedor no encontrado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
        }
    }

    private void eliminarProveedor() {
        try {
            String id = idField.getText();
            for (Proveedor01 proveedor : proveedores) {
                if (proveedor.getId().equals(id)) {
                    proveedores.remove(proveedor);

                    JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente.");

                    // Limpiar los campos después de eliminar el proveedor
                    limpiarCampos();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Proveedor no encontrado.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor válido para el ID.");
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        direccionField.setText("");
        contactoField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Proveedor proveedorFrame = new Proveedor();
            proveedorFrame.setVisible(true);
        });
    }

    private class Proveedor01 {
        private String id;
        private String nombre;
        private String direccion;
        private String contacto;

        public Proveedor01(String id, String nombre, String direccion, String contacto) {
            this.id = id;
            this.nombre = nombre;
            this.direccion = direccion;
            this.contacto = contacto;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public String getContacto() {
            return contacto;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public void setContacto(String contacto) {
            this.contacto = contacto;
        }
    }
}
