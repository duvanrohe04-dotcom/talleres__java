package vista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import modelo.BaseDB;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class MDIPrincipal extends JFrame {

    private JDesktopPane escritorio;
    private JPanel sidebar;
    private JButton btnDashboard, btnMotos, btnClientes, btnTrabajadores, btnCitas;
    private JLabel lblStatus;
    private frmDashboard dashboard;
    private static final Color RED_PRIMARY = new Color(0, 132, 61);
    private static final Color RED_BRIGHT = new Color(0, 170, 80);

    public MDIPrincipal() {
        initComponents();
        abrirDashboard();
        iniciarReloj();
    }

    private void initComponents() {
        setTitle("Taller SENA - Taller de Motos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setIconImage(SENALogo.crearIconoVentana(28));

        escritorio = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();

                RadialGradientPaint rgp = new RadialGradientPaint(
                    w / 2f, h / 2f, Math.max(w, h) * 0.6f,
                    new float[]{0f, 0.5f, 1f},
                    new Color[]{new Color(252, 252, 252), new Color(248, 248, 248), new Color(242, 242, 242)});
                g2d.setPaint(rgp);
                g2d.fillRect(0, 0, w, h);

                g2d.setColor(new Color(0, 132, 61, 8));
                g2d.fillOval(-w / 4, -h / 4, w / 2, h / 2);
                g2d.setColor(new Color(0, 90, 40, 5));
                g2d.fillOval(w * 3 / 4 - 100, h * 2 / 3 - 100, w / 3, h / 3);
                g2d.dispose();
            }
        };
        escritorio.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                redimensionarVentanasAbiertas();
            }
        });

        sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int sw = getWidth(), sh = getHeight();
                LinearGradientPaint lgp = new LinearGradientPaint(
                    0, 0, sw, 0,
                    new float[]{0f, 0.7f, 1f},
                    new Color[]{new Color(252, 252, 252), new Color(250, 250, 250), new Color(248, 248, 248)});
                g2d.setPaint(lgp);
                g2d.fillRect(0, 0, sw, sh);

                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRect(sw - 4, 0, 4, sh);

                g2d.setColor(RED_PRIMARY);
                g2d.fillRect(sw - 2, 0, 2, sh);

                g2d.dispose();
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(0, 0, 10, 0));
        sidebar.setPreferredSize(new Dimension(180, 0));

        JPanel logoPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int bw = getWidth(), bh = getHeight();

                g2.setColor(new Color(0, 0, 0, 30));
                g2.fillRoundRect(3, 4, bw - 6, bh - 3, 18, 18);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, bw, bh - 2, 18, 18);

                g2.setColor(new Color(210, 210, 210));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(1, 1, bw - 3, bh - 4, 18, 18);

                g2.dispose();
            }
        };
        logoPanel.setOpaque(false);
        logoPanel.setMaximumSize(new Dimension(160, 100));
        logoPanel.setMinimumSize(new Dimension(160, 80));
        logoPanel.setPreferredSize(new Dimension(160, 90));
        logoPanel.setBorder(new EmptyBorder(10, 0, 6, 0));

        MBLogo logoGrande = new MBLogo(60);
        logoPanel.add(logoGrande);

        JLabel lblNombre = new JLabel("TALLER SENA", SwingConstants.CENTER);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblNombre.setForeground(new Color(40, 40, 40));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTipo = new JLabel("TALLER DE MOTOS", SwingConstants.CENTER);
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTipo.setForeground(RED_PRIMARY);
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipo.setBorder(new EmptyBorder(2, 0, 6, 0));

        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        sidebar.add(logoPanel);
        sidebar.add(Box.createRigidArea(new Dimension(0, 2)));
        sidebar.add(lblNombre);
        sidebar.add(lblTipo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));
        sidebar.add(crearSeparadorSidebar());
        sidebar.add(Box.createRigidArea(new Dimension(0, 6)));

        btnDashboard = crearBotonSidebar("\u2302  Inicio", true);
        btnDashboard.addActionListener(e -> { try { abrirDashboard(); } catch (Exception ex) { ex.printStackTrace(); } });
        sidebar.add(btnDashboard);

        btnMotos = crearBotonSidebar("\uD83C\uDFCD  Motos", false);
        btnMotos.addActionListener(e -> { try { abrirVentana(new FRMMotos()); } catch (Exception ex) { ex.printStackTrace(); } });
        sidebar.add(btnMotos);

        btnClientes = crearBotonSidebar("\uD83D\uDC65  Clientes", false);
        btnClientes.addActionListener(e -> { try { abrirVentana(new FRMClientes()); } catch (Exception ex) { ex.printStackTrace(); } });
        sidebar.add(btnClientes);

        btnTrabajadores = crearBotonSidebar("\uD83D\uDD27  Taller", false);
        btnTrabajadores.addActionListener(e -> { try { abrirVentana(new FRMTrabajadores()); } catch (Exception ex) { ex.printStackTrace(); } });
        sidebar.add(btnTrabajadores);

        btnCitas = crearBotonSidebar("\uD83D\uDCC5  Citas", false);
        btnCitas.addActionListener(e -> { try { abrirVentana(new FRMCitas()); } catch (Exception ex) { ex.printStackTrace(); } });
        sidebar.add(btnCitas);

        sidebar.add(Box.createVerticalGlue());

        JButton btnSalir = crearBotonSalir();
        sidebar.add(btnSalir);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel statusBar = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int bw = getWidth(), bh = getHeight();
                LinearGradientPaint lgp = new LinearGradientPaint(
                    0, 0, bw, 0,
                    new float[]{0f, 0.5f, 1f},
                    new Color[]{new Color(248, 248, 248), new Color(245, 245, 245), new Color(240, 240, 240)});
                g2d.setPaint(lgp);
                g2d.fillRect(0, 0, bw, bh);
                g2d.setColor(new Color(0, 132, 61, 80));
                g2d.fillRect(0, 0, bw, 2);
                g2d.dispose();
            }
        };
        statusBar.setPreferredSize(new Dimension(0, 30));

        MBLogo senaMini = new MBLogo(16);
        senaMini.setPreferredSize(new Dimension(16, 16));

        JLabel lblMarca = new JLabel("  Taller SENA");
        lblMarca.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMarca.setForeground(RED_PRIMARY);

        JPanel statusLeft = new JPanel(new BorderLayout(6, 0));
        statusLeft.setOpaque(false);
        statusLeft.add(senaMini, BorderLayout.WEST);
        statusLeft.add(lblMarca, BorderLayout.CENTER);

        JPanel statusBrand = new JPanel(new BorderLayout(0, 0));
        statusBrand.setOpaque(false);
        statusBrand.setBorder(new EmptyBorder(0, 8, 0, 0));
        statusBrand.add(statusLeft, BorderLayout.WEST);

        lblStatus = new JLabel("", SwingConstants.RIGHT);
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblStatus.setForeground(new Color(140, 140, 140));
        lblStatus.setBorder(new EmptyBorder(3, 0, 3, 12));

        statusBar.add(statusBrand, BorderLayout.WEST);
        statusBar.add(lblStatus, BorderLayout.EAST);

        JPanel contenedor = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(245, 245, 245));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contenedor.setOpaque(false);
        contenedor.add(sidebar, BorderLayout.WEST);
        contenedor.add(escritorio, BorderLayout.CENTER);
        contenedor.add(statusBar, BorderLayout.SOUTH);
        setContentPane(contenedor);
    }

    private JPanel crearSeparadorSidebar() {
        JPanel sep = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                java.awt.LinearGradientPaint lgp = new java.awt.LinearGradientPaint(
                    0, 0, w, 0,
                    new float[]{0f, 0.5f, 1f},
                    new Color[]{new Color(0, 132, 61, 0), new Color(0, 132, 61, 80), new Color(0, 132, 61, 0)});
                g2.setPaint(lgp);
                g2.fillRect(10, 1, w - 20, 1);
                g2.dispose();
            }
        };
        sep.setOpaque(false);
        sep.setMaximumSize(new Dimension(180, 3));
        sep.setMinimumSize(new Dimension(180, 3));
        sep.setPreferredSize(new Dimension(180, 3));
        return sep;
    }

    private JButton crearBotonSidebar(String texto, boolean esPanelPrincipal) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();
                boolean rollover = getModel().isRollover();
                boolean press = getModel().isPressed();

                if (rollover || press) {
                    g2.setColor(new Color(230, 245, 235));
                    g2.fillRoundRect(8, 2, w - 16, h - 4, 8, 8);

                    g2.setColor(new Color(0, 0, 0, 15));
                    g2.fillRoundRect(10, 6, w - 20, h - 8, 8, 8);

                    LinearGradientPaint accent = new LinearGradientPaint(
                        0, 0, 0, h,
                        new float[]{0f, 0.5f, 1f},
                        new Color[]{RED_PRIMARY, new Color(0, 150, 70, 180), RED_PRIMARY});
                    g2.setPaint(accent);
                    g2.fillRoundRect(8, 4, 4, h - 8, 2, 2);

                    g2.setColor(new Color(0, 170, 80, 20));
                    g2.fillRoundRect(8, 4, 14, h - 8, 4, 4);
                }

                g2.setFont(getFont());
                g2.setColor(rollover || press ? new Color(0, 100, 50) : new Color(80, 80, 80));
                FontMetrics fm = g2.getFontMetrics();
                String txt = getText();
                int tx = 18;
                int ty = (h + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(txt, tx, ty);

                g2.dispose();
            }
        };
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(new Color(80, 80, 80));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(0, 0, 0, 0));
        btn.setMaximumSize(new Dimension(180, 36));
        btn.setMinimumSize(new Dimension(180, 36));
        btn.setPreferredSize(new Dimension(180, 36));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }

    private JButton crearBotonSalir() {
        JButton btn = new JButton("Salir") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();
                boolean rollover = getModel().isRollover();

                if (rollover) {
                    g2.setColor(new Color(235, 250, 235));
                    g2.fillRoundRect(8, 2, w - 16, h - 4, 8, 8);
                    g2.setColor(new Color(0, 132, 61, 40));
                    g2.fillRoundRect(8, 4, 3, h - 8, 2, 2);
                }

                g2.setFont(getFont());
                if (rollover) {
                    g2.setColor(RED_PRIMARY);
                } else {
                    g2.setColor(new Color(120, 120, 120));
                }
                FontMetrics fm = g2.getFontMetrics();
                String txt = "\u00D7  " + getText();
                int tw = fm.stringWidth(txt);
                g2.drawString(txt, (w - tw) / 2, (h + fm.getAscent() - fm.getDescent()) / 2);
                g2.dispose();
            }
        };
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setForeground(new Color(120, 120, 120));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(180, 32));
        btn.setMinimumSize(new Dimension(180, 32));
        btn.setPreferredSize(new Dimension(180, 32));
        btn.addActionListener(e -> System.exit(0));
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
            Dimension d = escritorio.getSize();
            dashboard.setBounds(0, 0, d.width, d.height);
            escritorio.add(dashboard);
            dashboard.setVisible(true);
        } else {
            dashboard.toFront();
        }
        try { dashboard.setSelected(true); } catch (Exception ex) {}
    }

    private void abrirVentana(JInternalFrame ventana) {
        try {
            Dimension d = escritorio.getSize();
            ventana.setBounds(0, 0, d.width, d.height);
            escritorio.add(ventana);
            ventana.setVisible(true);
            ventana.setSelected(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void redimensionarVentanasAbiertas() {
        Dimension d = escritorio.getSize();
        if (d.width < 50 || d.height < 50) return;
        for (JInternalFrame frame : escritorio.getAllFrames()) {
            if (!frame.isIcon()) {
                frame.setBounds(0, 0, d.width, d.height);
                if (frame instanceof frmDashboard) {
                    ((frmDashboard) frame).reorganizar();
                }
            }
        }
    }

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public int getSidebarWidth() {
        return 180;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MDIPrincipal().setVisible(true);
        });
        new Thread(() -> BaseDB.inicializar()).start();
    }
}
