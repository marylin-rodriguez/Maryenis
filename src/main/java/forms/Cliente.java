package forms;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.marylin.util.Hibernateutil;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

public class Cliente extends JInternalFrame {
    private JTextField nombreField;
    private JTextField direccionField;
    private JTextField emailField;
    private JTextField idClienteField;

    private List<Cliente01> clientesRegistrados;

    public Cliente() {
        super("Cliente", true, true, true, true);
        clientesRegistrados = new ArrayList<>();
        iniciarCliente();
    }

    private void iniciarCliente() {
        setSize(400, 300);
        getContentPane().setBackground(new Color(235, 235, 235));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 5, 5));
        panel.setBackground(new Color(220, 220, 220));

        JLabel nombreLabel = new JLabel("Nombre:");
        panel.add(nombreLabel);
        nombreField = new JTextField();
        nombreField.setBackground(Color.LIGHT_GRAY);
        panel.add(nombreField);

        JLabel direccionLabel = new JLabel("Dirección:");
        panel.add(direccionLabel);
        direccionField = new JTextField();
        direccionField.setBackground(Color.LIGHT_GRAY);
        panel.add(direccionField);

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);
        emailField = new JTextField();
        emailField.setBackground(Color.LIGHT_GRAY);
        panel.add(emailField);

        JLabel idClienteLabel = new JLabel("ID Cliente:");
        panel.add(idClienteLabel);
        idClienteField = new JTextField();
        idClienteField.setBackground(Color.LIGHT_GRAY);
        panel.add(idClienteField);

        JButton registrarButton = new JButton("Registrar Cliente");
        registrarButton.setBackground(Color.MAGENTA);
        registrarButton.setForeground(Color.BLACK);
        registrarButton.addActionListener(e -> registrarCliente());
        panel.add(registrarButton);

        JButton mostrarClientesButton = new JButton("Mostrar Clientes");
        mostrarClientesButton.setBackground(Color.MAGENTA);
        mostrarClientesButton.setForeground(Color.BLACK);
        mostrarClientesButton.addActionListener(e -> mostrarClientes());
        panel.add(mostrarClientesButton);

        JButton editarButton = new JButton("Editar Cliente");
        editarButton.setBackground(Color.MAGENTA);
        editarButton.setForeground(Color.BLACK);
        editarButton.addActionListener(e -> editarCliente());
        panel.add(editarButton);

        JButton eliminarButton = new JButton("Eliminar Cliente");
        eliminarButton.setBackground(Color.MAGENTA);
        eliminarButton.setForeground(Color.BLACK);
        eliminarButton.addActionListener(e -> eliminarCliente());
        panel.add(eliminarButton);
        
        JButton guardarButton  = new JButton ("Guardar Usuario");
        guardarButton.setBackground(Color.GREEN);
        guardarButton.setForeground(Color.BLACK);
        guardarButton.addActionListener(e -> guardarUsuario());
        panel.add(guardarButton);

        setContentPane(panel);
        setVisible(true);
    }

    private void guardarUsuario() {
        Transaction transaction = null;
        try (Session session = Hibernateutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Cliente01 cliente = new Cliente01();
            cliente.setNombre(nombreField.getText());
            cliente.setDireccion(direccionField.getText());
            cliente.setEmail(emailField.getText());
            session.save(cliente);
            transaction.commit();
            JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            JOptionPane.showMessageDialog(this, "Error al guardar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarCliente() {
        try {
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();
            String email = emailField.getText();
            int idCliente = Integer.parseInt(idClienteField.getText());

            // Imprimir la información en la consola
            System.out.println("Cliente registrado:");
            System.out.println("ID Cliente: " + idCliente);
            System.out.println("Nombre: " + nombre);
            System.out.println("Dirección: " + direccion);
            System.out.println("Email: " + email);
            System.out.println("Fecha de Registro: " + LocalDateTime.now());

            // Verificar que los campos no estén vacíos
            if (nombre.isEmpty() || direccion.isEmpty() || email.isEmpty() || idClienteField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                return;
            }

            // Crear nuevo cliente y agregarlo a la lista
            Cliente01 nuevoCliente = new Cliente01(idCliente, nombre, direccion, email, LocalDateTime.now());
            clientesRegistrados.add(nuevoCliente);

            mostrarClientes();

            limpiarCampos();

            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos en los campos numéricos.");
        }
    }


    private void mostrarClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Clientes Registrados:\n");
        for (Cliente01 cliente : clientesRegistrados) {
            sb.append("ID Cliente: ").append(cliente.getIdCliente());
            sb.append(", Nombre: ").append(cliente.getNombre());
            sb.append(", Dirección: ").append(cliente.getDireccion());
            sb.append(", Email: ").append(cliente.getEmail());
            sb.append(", Fecha de Registro: ");
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Clientes Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarCliente() {
        String idClienteStr = JOptionPane.showInputDialog("Ingrese el ID del cliente que desea editar,");
        try {
            int idCliente = Integer.parseInt(idClienteStr);
            Cliente01 cliente = buscarClientePorIdCliente(idCliente);
            if (cliente != null) {
                // Rellenar los campos con la información actual del cliente
                nombreField.setText(cliente.getNombre());
                direccionField.setText(cliente.getDireccion());
                emailField.setText(cliente.getEmail());
                idClienteField.setText(String.valueOf(cliente.getIdCliente()));

                // Añadir botón de actualizar
                JButton actualizarButton = new JButton("Actualizar Cliente");
                actualizarButton.setBackground(Color.GREEN);
                actualizarButton.setForeground(Color.BLACK);
                actualizarButton.addActionListener(e -> actualizarCliente(cliente));
                getContentPane().add(actualizarButton);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de cliente válido.");
        }
    }
    private void actualizarCliente(Cliente01 cliente) {
        try {
            cliente.setNombre(nombreField.getText());
            cliente.setDireccion(direccionField.getText());
            cliente.setEmail(emailField.getText());

            // Imprimir en la terminal que la venta ha sido actualizada correctamente
            System.out.println("Cliente editado correctamente:");
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Fecha_Registro: " + cliente.getFechaRegistro());

            mostrarClientes();

            JOptionPane.showMessageDialog(null, "Cliente editado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos.");
        }
    }

    private void eliminarCliente() {
        String idClienteText = idClienteField.getText();
        if (idClienteText.matches("\\d+")) { // Verificar si el texto es un número válido
            int idCliente = Integer.parseInt(idClienteText);
            Cliente01 cliente = buscarClientePorIdCliente(idCliente);
            if (cliente!= null) {
                clientesRegistrados.remove(cliente);
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID de cliente válido.");
        }
    }

    private Cliente01 buscarClientePorIdCliente(int idCliente) {
        for (Cliente01 cliente : clientesRegistrados) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }

    private void limpiarCampos() {
        nombreField.setText("");
        direccionField.setText("");
        emailField.setText("");
        idClienteField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cliente clienteFrame = new Cliente();
            clienteFrame.setVisible(true);
        });
    }

    private class Cliente01 {
        private int idCliente;
        private String nombre;
        private String direccion;
        private String email;
        private LocalDateTime fechaRegistro;

        public Cliente01(int idCliente, String nombre, String direccion, String email, LocalDateTime fechaRegistro) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.direccion = direccion;
            this.email = email;
            this.fechaRegistro = fechaRegistro;
        }

        public Cliente01() {

        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public LocalDateTime getFechaRegistro() {
            return fechaRegistro;
        }

        public void setFechaRegistro(LocalDateTime fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }
    }
}