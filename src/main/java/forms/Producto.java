package forms;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.marylin.entity.Producto01;
import org.marylin.util.Hibernateutil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.List;

public class Producto extends JInternalFrame {
    private ArrayList<Producto01> productos;
    private JTextField idField;
    private JTextField nombreField;
    private JTextField descripcionField;
    private JTextField precioField;
    private JTextField costoField;
    private JTextField stockField;



    public Producto() {
        super("Producto", true, true, true, true);
        productos = new ArrayList<>();
        iniciarProducto();
    }

    private void iniciarProducto() {
        setSize(400, 300);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 5, 5));
        panel.setBackground(new Color(220, 220, 220));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel idLabel = new JLabel("ID del Producto:");
        panel.add(idLabel);
        idField = new JTextField();
        idField.setBackground(Color.LIGHT_GRAY);
        panel.add(idField);

        JLabel nombreLabel = new JLabel("Nombre del Producto:");
        panel.add(nombreLabel);
        nombreField = new JTextField();
        nombreField.setBackground(Color.LIGHT_GRAY);
        panel.add(nombreField);

        JLabel descripcionLabel = new JLabel("Descripción del Producto:");
        panel.add(descripcionLabel);
        descripcionField = new JTextField();
        descripcionField.setBackground(Color.LIGHT_GRAY);
        panel.add(descripcionField);

        JLabel precioLabel = new JLabel("Precio del Producto:");
        panel.add(precioLabel);
        precioField = new JTextField();
        precioField.setBackground(Color.LIGHT_GRAY);
        panel.add(precioField);

        JLabel costoLabel = new JLabel("Costo del Producto:");
        panel.add(costoLabel);
        costoField = new JTextField();
        costoField.setBackground(Color.LIGHT_GRAY);
        panel.add(costoField);

        JLabel stockLabel = new JLabel("Cantidad en Stock:");
        panel.add(stockLabel);
        stockField = new JTextField();
        stockField.setBackground(Color.LIGHT_GRAY);
        panel.add(stockField);

        JButton registrarButton = new JButton("Registrar producto");
        registrarButton.setBackground(Color.MAGENTA);
        registrarButton.setForeground(Color.BLACK);
        registrarButton.addActionListener(e -> guardarProducto());
        panel.add(registrarButton);

        JButton mostrarButton = new JButton("Mostrar Productos");
        mostrarButton.setBackground(Color.MAGENTA);
        mostrarButton.setForeground(Color.BLACK);
        mostrarButton.addActionListener(e -> mostrarProductos());
        panel.add(mostrarButton);

        JButton editarButton = new JButton("Editar Producto");
        editarButton.setBackground(Color.MAGENTA);
        editarButton.setForeground(Color.BLACK);
        editarButton.addActionListener(e -> editarProducto());
        panel.add(editarButton);

        JButton eliminarButton = new JButton("Eliminar Producto");
        eliminarButton.setBackground(Color.MAGENTA);
        eliminarButton.setForeground(Color.BLACK);
        eliminarButton.addActionListener(e -> eliminarProducto());
        panel.add(eliminarButton);

        JButton guardarButton = new JButton ("Guardar Usuario");
        guardarButton.setBackground(Color.GREEN);
        guardarButton.setForeground(Color.BLACK);
        guardarButton.addActionListener(e -> guardarUsuario());
        panel.add(guardarButton);

        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void guardarUsuario()
    {
        Transaction transaction = null;
        try(Session session = Hibernateutil.getSessionFactory().openSession())
        {
            transaction = session.beginTransaction();
            Object Producto01 = new Producto();
            session.save(Producto01);
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


    private void guardarProducto() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            String descripcion = descripcionField.getText();
            double precio = Double.parseDouble(precioField.getText());
            double costo = Double.parseDouble(costoField.getText());
            int stock = Integer.parseInt(stockField.getText());

            // Imprimir la información en la consola
            System.out.println("Producto guardado:");
            System.out.println("ID Producto: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Descripción: " + descripcion);
            System.out.println("Precio: " + precio);
            System.out.println("Costo: " + costo);
            System.out.println("Stock: " + stock);

            JOptionPane.showMessageDialog(null, "Producto guardado correctamente.");

            // Crear un nuevo producto y agregarlo a la lista
            int idProducto = 0;
            Producto01 nuevoProducto = new Producto01(idProducto, nombre, descripcion, precio, costo, stock);
            productos.add(nuevoProducto);

            mostrarProductos();

            // Limpiar los campos después de registrar el producto
            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores válidos para ID, Precio, Costo y Stock.");
        }
    }

    private void mostrarProductos() {
        StringBuilder productosText = new StringBuilder();
        for (Producto producto : productos) {
            productosText.append("ID Producto: ").append(producto.getId()).append(", Nombre: ").append(producto.getNombre())
                    .append(", Descripción: ").append(producto.getDescripcion()).append(", Precio: ").append(producto.getPrecio())
                    .append(", Costo: ").append(producto.getCosto()).append(", Stock: ").append(producto.getStock())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, productosText.toString(), "Productos Registrados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editarProducto() {
        // Implementa la lógica para editar un producto aquí


    }
    private void eliminarProducto() {
        // Implementa la lógica para eliminar un producto aquí
    }

    private void limpiarCampos() {
        idField.setText("");
        nombreField.setText("");
        descripcionField.setText("");
        precioField.setText("");
        costoField.setText("");
        stockField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Producto productoFrame = new Producto();
            productoFrame.setVisible(true);
        });
    }

    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private double costo;
    private int stock;

    public Producto(int id, String nombre, String descripcion, double precio, double costo, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public double getCosto() {
        return costo;
    }

    public int getStock() {
        return stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
