package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class MDIPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private JPanel sidebar;
    private JButton btnClientes, btnMotos, btnTrabajadores, btnCitas, btnDashboard;
    private JLabel lblStatus;
    private frmDashboard dashboard;
    private MBLogo iconoApp;

    private static final Color RED_PRIMARY = new Color(211, 30, 30);
    private static final Color RED_DARK = new Color(160, 18, 18);
    private static final Color SIDEBAR_BG = new Color(10, 10, 10);

    public MDIPrincipal() {
        initComponents();
        abrirDashboard();
        iniciarReloj();
    }

    private void initComponents() {
        setTitle("Multimarcas Brazo - Taller de Motos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        iconoApp = new MBLogo(28, true);
        iconoApp.setPreferredSize(new Dimension(28, 28));

        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(28, 28, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(RED_PRIMARY);
        g2.fillOval(2, 2, 24, 24);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
        g2.drawString("MB", 4, 19);
        g2.dispose();
        setIconImage(img);

        escritorio = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(8, 8, 8));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(new Color(211, 30, 30, 10));
                g2d.fillOval(-150, -80, 500, 500);
                g2d.setColor(new Color(180, 18, 18, 8));
                g2d.fillOval(getWidth() - 350, getHeight() - 350, 400, 400);
                g2d.setColor(new Color(160, 18, 18, 6));
                g2d.fillOval(getWidth() / 2 - 100, getHeight() / 2 - 200, 300, 300);
                g2d.dispose();
            }
        };

        sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, SIDEBAR_BG, getWidth(), 0, new Color(16, 16, 16));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(RED_PRIMARY);
                g2d.fillRect(getWidth() - 2, 0, 2, getHeight());
                g2d.dispose();
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(0, 0, 10, 0));
        sidebar.setPreferredSize(new Dimension(170, 0));

        MBLogo logoGrande = new MBLogo(50, true);
        logoGrande.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        logoGrande.setBorder(new EmptyBorder(15, 0, 5, 0));

        JLabel lblNombre = new JLabel("Multimarcas", SwingConstants.CENTER);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(new Color(220, 220, 220));
        lblNombre.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        JLabel lblTipo = new JLabel("TALLER DE MOTOS", SwingConstants.CENTER);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTipo.setForeground(RED_PRIMARY);
        lblTipo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        sidebar.add(logoGrande);
        sidebar.add(lblNombre);
        sidebar.add(lblTipo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        btnDashboard = crearBotonSidebar("\u2302  Panel Principal");
        btnDashboard.addActionListener(e -> abrirDashboard());
        btnMotos = crearBotonSidebar(" Motos");
        btnMotos.addActionListener(e -> abrirVentana(new FRMMotos(), 60, 60));
        btnClientes = crearBotonSidebar(" Clientes");
        btnClientes.addActionListener(e -> abrirVentana(new FRMClientes(), 60, 60));
        btnTrabajadores = crearBotonSidebar(" Trabajadores");
        btnTrabajadores.addActionListener(e -> abrirVentana(new FRMTrabajadores(), 60, 60));
        btnCitas = crearBotonSidebar(" Citas");
        btnCitas.addActionListener(e -> abrirVentana(new FRMCitas(), 60, 60));

        sidebar.add(btnDashboard);
        sidebar.add(btnMotos);
        sidebar.add(btnClientes);
        sidebar.add(btnTrabajadores);
        sidebar.add(btnCitas);

        sidebar.add(Box.createVerticalGlue());

        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnSalir.setForeground(new Color(120, 120, 120));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btnSalir.addActionListener(e -> System.exit(0));
        sidebar.add(btnSalir);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel statusBar = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(16, 16, 16), 0, getHeight(), new Color(8, 8, 8));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(new Color(211, 30, 30, 70));
                g2d.fillRect(0, 0, getWidth(), 1);
                g2d.dispose();
            }
        };
        statusBar.setPreferredSize(new Dimension(0, 26));

        MBLogo logoMini = new MBLogo(14, true);
        logoMini.setPreferredSize(new Dimension(18, 18));

        JLabel lblMarca = new JLabel("  Multimarcas Brazo");
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMarca.setForeground(RED_PRIMARY);

        JPanel statusLeft = new JPanel(new BorderLayout());
        statusLeft.setOpaque(false);
        statusLeft.add(logoMini, BorderLayout.WEST);
        statusLeft.add(lblMarca, BorderLayout.CENTER);

        lblStatus = new JLabel("", SwingConstants.RIGHT);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblStatus.setForeground(new Color(140, 140, 140));
        lblStatus.setBorder(new EmptyBorder(3, 0, 3, 12));

        statusBar.add(statusLeft, BorderLayout.WEST);
        statusBar.add(lblStatus, BorderLayout.EAST);

        JPanel contenedor = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(8, 8, 8));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contenedor.setOpaque(false);
        contenedor.add(sidebar, BorderLayout.WEST);
        contenedor.add(escritorio, BorderLayout.CENTER);
        contenedor.add(statusBar, BorderLayout.SOUTH);
        setContentPane(contenedor);
    }

    private JButton crearBotonSidebar(String texto) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2d.setColor(new Color(30, 30, 30));
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                    g2d.setColor(RED_PRIMARY);
                    g2d.fillRect(0, 0, 3, getHeight());
                }
                g2d.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(new Color(180, 180, 180));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(10, 18, 10, 10));
        btn.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(170, 40));
        btn.setMinimumSize(new Dimension(170, 40));
        return btn;
    }

    private void iniciarReloj() {
        Timer timer = new Timer(1000, e -> {
            lblStatus.setText(new Date().toString());
        });
        timer.start();
    }

    private void abrirDashboard() {
        if (dashboard == null || dashboard.isClosed()) {
            dashboard = new frmDashboard(this);
            dashboard.setVisible(true);
            escritorio.add(dashboard);
        } else {
            dashboard.toFront();
        }
        try { dashboard.setSelected(true); } catch (Exception ex) {}
    }

    private void abrirVentana(javax.swing.JInternalFrame ventana, int offsetX, int offsetY) {
        ventana.setLocation(offsetX, offsetY);
        escritorio.add(ventana);
        ventana.setVisible(true);
        try { ventana.setSelected(true); } catch (Exception ex) {}
    }

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public int getSidebarWidth() {
        return 170;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MDIPrincipal().setVisible(true);
        });
    }
}
