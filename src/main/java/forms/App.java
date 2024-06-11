package forms;
import com.sun.jna.platform.win32.Netapi32Util;
import org.hibernate.Session;
import org.marylin.Services.GenericServiceImpl;
import org.marylin.Services.IGenericService;
import org.marylin.entity.Administrador;
import org.marylin.entity.user;
import org.marylin.util.Hibernateutil;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.BLUE);
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel texto1 = new JLabel("Usuario");
        texto1.setBounds(0, 0, 60, 60);
        JTextField Usuario = new JTextField(30);
        Usuario.setBounds(75, 20, 200, 25);

        JLabel texto2 = new JLabel("Contraseña");
        texto2.setBounds(0, 60, 60, 60);
        JPasswordField password = new JPasswordField(30);
        password.setBounds(75, 80, 200, 25);

        JButton boton1 = new JButton("OK");
        boton1.setBounds(50, 150, 160, 25);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Administrador>listaDeAdmi=obtenerAdministradores();
                for (Administrador admin: listaDeAdmi){
                    //admin.
                }

                String usuario1 = Usuario.getText();
                char[] passwords = password.getPassword();
                String password = new String(passwords);

                if (usuario1.equals("Marylin") && password.equals("rodriguez")) {
                    JOptionPane.showMessageDialog(null, "Bienvenido");
                    frame.dispose(); // Cierra la ventana de login
                    menu menu = new menu();
                    menu.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña no válidos.");
                }
                String nombre="Jose";
                String contrasena="1234";
                user mary =new user(nombre,contrasena);
                guardarWorker(mary);
            }
            private static void guardarWorker(user trabajador){
                IGenericService<user> trabajadores = new GenericServiceImpl<>(user.class,Hibernateutil.getSessionFactory());
                trabajadores.save(trabajador);
            }
        });



     /*   botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrador administrador = new Administrador("marylin", "rodriguez", "marylinr@gmail.com");
                guardarAdministrador(administrador);
                JOptionPane.showMessageDialog(null, "Administrador guardado.");
                List<Administrador> admin = obtenerAdministradores();
                admin.forEach(System.out:: println);
            }
        });*/


//        botonMostrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                List<Administrador> administradores = obtenerAdministradores();
//                administradores.forEach(System.out::println);
//                JOptionPane.showMessageDialog(null, administradores.toString());
//            }
//        });

        frame.add(texto1);
        frame.add(Usuario);
        frame.add(texto2);
        frame.add(password);
        frame.add(boton1);
       // frame.add(botonGuardar);
       // frame.add(botonMostrar);
        frame.setVisible(true);
    }

    private static List<Administrador> obtenerAdministradores() {
        Transaction transaction = null;
        List<Administrador> administradores = new ArrayList<>();
        try (Session session = Hibernateutil.getSessionFactory().openSession()) {
            administradores = session.createQuery("FROM Administrador", Administrador.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return administradores;
    }

    private static void guardarAdministrador(Administrador administrador) {
        Transaction transaction = null;
        try (Session session = Hibernateutil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(administrador);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    private void agregarAdministrador() {
        Administrador administrador = new Administrador("Marylin", "rodriguez", "al frente la cancha", "87623981");
        guardarAdministrador(administrador);
        JOptionPane.showMessageDialog(null, "Administrador agregado correctamente.");
    }
}


