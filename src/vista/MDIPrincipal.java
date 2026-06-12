package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MDIPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private JMenuBar menuBar;
    private JMenu MNUAdministrar, MNUSena;
    private JMenuItem ItemClientes, ItemMotos, ItemTrabajadores, ItemCitas, ItemSalir, ItemAcercaDe;
    private JLabel lblStatus, lblStatusSena;
    private frmDashboard dashboard;
    private SENALogo iconoApp;

    private static final Color SENA_GREEN = new Color(57, 169, 0);
    private static final Color DARK_BG = new Color(22, 22, 22);

    public MDIPrincipal() {
        initComponents();
        abrirDashboard();
        iniciarReloj();
    }

    private void initComponents() {
        setTitle("Multimarcas Brazo - Taller de Motos [SENA]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        iconoApp = new SENALogo(32, true);
        iconoApp.setPreferredSize(new java.awt.Dimension(32, 32));
        setIconImage(crearIconoVentana());

        escritorio = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(DARK_BG);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(57, 169, 0, 12));
                g2.fillOval(-200, -100, 600, 600);
                g2.setColor(new Color(255, 215, 0, 8));
                g2.fillOval(getWidth() - 400, getHeight() - 400, 500, 500);
                g2.dispose();
            }
        };
        setContentPane(escritorio);

        menuBar = new JMenuBar() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, SENA_GREEN, getWidth(), 0, new Color(40, 130, 0));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        menuBar.setBorderPainted(false);

        MNUAdministrar = new JMenu("Administrar");
        MNUAdministrar.setForeground(Color.WHITE);
        MNUAdministrar.setFont(MNUAdministrar.getFont().deriveFont(14f));
        MNUAdministrar.setMnemonic('A');

        ItemClientes = new JMenuItem("Clientes");
        ItemClientes.setFont(ItemClientes.getFont().deriveFont(13f));
        ItemClientes.setMnemonic('C');
        ItemClientes.addActionListener(e -> abrirVentana(new FRMClientes(), 50, 50));

        ItemMotos = new JMenuItem("Motos");
        ItemMotos.setFont(ItemMotos.getFont().deriveFont(13f));
        ItemMotos.setMnemonic('M');
        ItemMotos.addActionListener(e -> abrirVentana(new FRMMotos(), 70, 70));

        ItemTrabajadores = new JMenuItem("Trabajadores");
        ItemTrabajadores.setFont(ItemTrabajadores.getFont().deriveFont(13f));
        ItemTrabajadores.setMnemonic('T');
        ItemTrabajadores.addActionListener(e -> abrirVentana(new FRMTrabajadores(), 90, 90));

        ItemCitas = new JMenuItem("Citas");
        ItemCitas.setFont(ItemCitas.getFont().deriveFont(13f));
        ItemCitas.setMnemonic('i');
        ItemCitas.addActionListener(e -> abrirVentana(new FRMCitas(), 110, 110));

        ItemSalir = new JMenuItem("Salir");
        ItemSalir.setFont(ItemSalir.getFont().deriveFont(13f));
        ItemSalir.setMnemonic('S');
        ItemSalir.addActionListener(e -> System.exit(0));

        MNUAdministrar.add(ItemClientes);
        MNUAdministrar.add(ItemMotos);
        MNUAdministrar.add(ItemTrabajadores);
        MNUAdministrar.add(ItemCitas);
        MNUAdministrar.addSeparator();
        MNUAdministrar.add(ItemSalir);

        MNUSena = new JMenu("SENA");
        MNUSena.setForeground(Color.WHITE);
        MNUSena.setFont(MNUSena.getFont().deriveFont(14f));
        MNUSena.setMnemonic('S');

        ItemAcercaDe = new JMenuItem("Acerca del Taller");
        ItemAcercaDe.setFont(ItemAcercaDe.getFont().deriveFont(13f));
        ItemAcercaDe.addActionListener(e -> mostrarAcercaDe());

        MNUSena.add(ItemAcercaDe);

        menuBar.add(MNUAdministrar);
        menuBar.add(MNUSena);

        setJMenuBar(menuBar);

        JPanel statusBar = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(35, 35, 35), 0, getHeight(), new Color(20, 20, 20));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(57, 169, 0, 80));
                g2.fillRect(0, 0, getWidth(), 1);
                g2.dispose();
            }
        };
        statusBar.setPreferredSize(new java.awt.Dimension(0, 28));

        JPanel statusLeft = new JPanel(new BorderLayout());
        statusLeft.setOpaque(false);
        SENALogo logoMini = new SENALogo(16, true);
        logoMini.setPreferredSize(new java.awt.Dimension(20, 20));
        lblStatusSena = new JLabel("  SENA - Conocimiento para todos");
        lblStatusSena.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblStatusSena.setForeground(SENA_GREEN);
        statusLeft.add(logoMini, BorderLayout.WEST);
        statusLeft.add(lblStatusSena, BorderLayout.CENTER);

        lblStatus = new JLabel("", SwingConstants.RIGHT);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblStatus.setForeground(new Color(160, 160, 160));
        lblStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 3, 12));

        statusBar.add(statusLeft, BorderLayout.WEST);
        statusBar.add(lblStatus, BorderLayout.EAST);

        escritorio.add(statusBar, BorderLayout.SOUTH);
        statusBar.setBounds(0, 0, getWidth(), 28);
    }

    private java.awt.Image crearIconoVentana() {
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(32, 32, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(SENA_GREEN);
        g2.fillOval(2, 2, 28, 28);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        g2.drawString("S", 11, 22);
        g2.dispose();
        return img;
    }

    private void iniciarReloj() {
        Timer timer = new Timer(1000, e -> {
            lblStatus.setText(new Date().toString());
        });
        timer.start();
    }

    private void mostrarAcercaDe() {
        javax.swing.JOptionPane.showMessageDialog(this,
            "MULTIMARCAS BRAZO - Taller de Motos\n" +
            "Proyecto de Formación SENA\n" +
            "Versión 1.0\n\n" +
            "Tecnólogo en Análisis y Desarrollo de Software\n" +
            "Ficha: GFPI-F-135",
            "Acerca del Taller",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void abrirDashboard() {
        dashboard = new frmDashboard(this);
        dashboard.setVisible(true);
        escritorio.add(dashboard);
        try {
            dashboard.setSelected(true);
        } catch (Exception ex) {
        }
    }

    private void abrirVentana(javax.swing.JInternalFrame ventana, int offsetX, int offsetY) {
        ventana.setLocation(offsetX, offsetY);
        escritorio.add(ventana);
        ventana.setVisible(true);
        try {
            ventana.setSelected(true);
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MDIPrincipal().setVisible(true);
        });
    }
}
