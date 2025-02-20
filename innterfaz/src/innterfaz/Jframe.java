package innterfaz;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jframe extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBuscar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Jframe frame = new Jframe();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Jframe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 806, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Campo de texto Buscar
        txtBuscar = new JTextField();
        txtBuscar.setText("Buscar");
        txtBuscar.setBounds(32, 43, 248, 27);
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        // Panel blanco en el centro
        JPanel panelBlanco = new JPanel();
        panelBlanco.setBounds(150, 120, 500, 300);  // Posición y tamaño
        panelBlanco.setBackground(Color.WHITE);
        panelBlanco.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde opcional
        contentPane.add(panelBlanco);

        // Botón Agregar
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(100, 480, 100, 30);
        contentPane.add(btnAgregar);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AgregarProfesorFrame ventana = new AgregarProfesorFrame();
                ventana.setVisible(true);
            }
        });

        // Botón Actualizar
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(225, 480, 100, 30);
        contentPane.add(btnActualizar);

        // Botón Eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(350, 480, 100, 30);
        contentPane.add(btnEliminar);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Profesor eliminado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botón Buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(475, 480, 100, 30);
        contentPane.add(btnBuscar);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Profesor encontrado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}

/**
 * Nueva ventana para agregar un profesor.
 */
class AgregarProfesorFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public AgregarProfesorFrame() {
        setTitle("Agregar Profesor");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("¿Deseas agregar un profesor?");
        add(label);

        JButton btnConfirmar = new JButton("Agregar Profesor");
        add(btnConfirmar);
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Profesor agregado", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
