package vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class frmDashboard extends JInternalFrame {

    private JLabel lblTitulo;
    private JLabel lblSubtitulo;
    private JLabel lblIcono;
    private JButton btnIngresar;

    public frmDashboard(MDIPrincipal padre) {
        initComponents();
        centrar(padre);
    }

    private void initComponents() {
        setTitle("Bienvenido");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setSize(520, 380);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(28, 28, 28));

        lblTitulo = new JLabel("MULTIMARCAS BRAZO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBounds(40, 50, 440, 70);

        lblSubtitulo = new JLabel("Taller de Motos", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        lblSubtitulo.setForeground(new Color(180, 180, 180));
        lblSubtitulo.setBounds(100, 120, 320, 40);

        lblIcono = new JLabel("\uD83C\uDFCD", SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 60));
        lblIcono.setBounds(200, 170, 120, 70);

        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIngresar.setForeground(new Color(28, 28, 28));
        btnIngresar.setBackground(new Color(255, 215, 0));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setBounds(170, 270, 180, 50);
        btnIngresar.addActionListener(e -> dispose());

        getContentPane().add(lblTitulo);
        getContentPane().add(lblSubtitulo);
        getContentPane().add(lblIcono);
        getContentPane().add(btnIngresar);
    }

    private void centrar(MDIPrincipal padre) {
        setLocation(
            (padre.getWidth() - getWidth()) / 2,
            (padre.getHeight() - getHeight()) / 2
        );
    }
}
