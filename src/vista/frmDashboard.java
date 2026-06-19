package vista;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class frmDashboard extends JInternalFrame {

    private JLabel lblTitulo, lblSubtitulo, lblSlogan, lblInfo;
    private JButton btnClientes, btnMotos, btnTrabajadores, btnCitas;
    private MBLogo logo;
    private JPanel panelHero;
    private MDIPrincipal padre;

    private static final Color RED_PRIMARY = new Color(211, 30, 30);
    private static final Color RED_DARK = new Color(160, 18, 18);
    private static final Color RED_BRIGHT = new Color(255, 40, 40);
    private static final Color DARK_CARD = new Color(16, 16, 16);

    public frmDashboard(MDIPrincipal padre) {
        this.padre = padre;
        initComponents();
        centrar(padre);
    }

    private void initComponents() {
        setTitle("Bienvenido - Multimarcas Brazo");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setSize(700, 540);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(8, 8, 8));

        panelHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(14, 14, 14), 0, getHeight(), new Color(8, 8, 8));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(211, 30, 30, 25));
                g2.fillOval(-40, -60, 280, 280);
                g2.setColor(new Color(255, 40, 40, 12));
                g2.fillOval(420, -80, 300, 300);
                g2.dispose();
            }
        };
        panelHero.setBounds(0, 0, 700, 200);
        panelHero.setLayout(null);
        panelHero.setOpaque(false);

        logo = new MBLogo(85, true);
        logo.setBounds(307, 12, 85, 85);

        lblTitulo = new JLabel("MULTIMARCAS BRAZO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblTitulo.setForeground(RED_BRIGHT);
        lblTitulo.setBounds(50, 95, 600, 50);

        lblSubtitulo = new JLabel("TALLER DE MOTOS", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblSubtitulo.setForeground(new Color(200, 200, 200));
        lblSubtitulo.setBounds(150, 138, 400, 25);

        JPanel panelSlogan = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(211, 30, 30, 35));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(new Color(211, 30, 30, 60));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
            }
        };
        panelSlogan.setBounds(125, 168, 450, 28);

        lblSlogan = new JLabel("Potencia, Pasion y Precision sobre dos ruedas", SwingConstants.CENTER);
        lblSlogan.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblSlogan.setForeground(RED_PRIMARY);
        lblSlogan.setBounds(0, 2, 450, 24);

        lblInfo = new JLabel("Gestion integral para su taller de motos", SwingConstants.CENTER);
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(new Color(130, 130, 130));
        lblInfo.setBounds(100, 210, 500, 25);

        btnClientes = crearBotonTarjeta("CLIENTES", "\uD83D\uDC65", "Registre y gestione", 30, 260);
        btnClientes.addActionListener(e -> abrirFormulario(new FRMClientes()));

        btnMotos = crearBotonTarjeta("MOTOS", "\uD83C\uDFCD", "Inventario de motos", 190, 260);
        btnMotos.addActionListener(e -> abrirFormulario(new FRMMotos()));

        btnTrabajadores = crearBotonTarjeta("TALLER", "\uD83D\uDD27", "Gestion del personal", 350, 260);
        btnTrabajadores.addActionListener(e -> abrirFormulario(new FRMTrabajadores()));

        btnCitas = crearBotonTarjeta("CITAS", "\uD83D\uDCC5", "Programacion de citas", 510, 260);
        btnCitas.addActionListener(e -> abrirFormulario(new FRMCitas()));

        JPanel panelStats = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(14, 14, 14));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(new Color(40, 40, 40));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.dispose();
            }
        };
        panelStats.setBounds(30, 410, 640, 90);
        panelStats.setLayout(null);
        panelStats.setOpaque(false);

        JLabel lblFooter = new JLabel("Multimarcas Brazo - Taller de Motos", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(70, 70, 70));
        lblFooter.setBounds(100, 510, 500, 20);

        int statW = 160;
        crearStatLabel(panelStats, "Motos", "En taller", 15, 15, statW);
        crearStatLabel(panelStats, "Clientes", "Registrados", 15 + statW + 5, 15, statW);
        crearStatLabel(panelStats, "Mecanicos", "Disponibles", 15 + (statW + 5) * 2, 15, statW);
        crearStatLabel(panelStats, "Citas", "Hoy", 15 + (statW + 5) * 3, 15, statW);

        panelHero.add(logo);
        panelHero.add(lblTitulo);
        panelHero.add(lblSubtitulo);
        getContentPane().add(panelHero);
        getContentPane().add(panelSlogan);
        panelSlogan.add(lblSlogan);
        getContentPane().add(lblInfo);
        getContentPane().add(btnClientes);
        getContentPane().add(btnMotos);
        getContentPane().add(btnTrabajadores);
        getContentPane().add(btnCitas);
        getContentPane().add(panelStats);
        getContentPane().add(lblFooter);
    }

    private void crearStatLabel(JPanel panel, String titulo, String subtitulo, int x, int y, int w) {
        JLabel val = new JLabel("--", SwingConstants.CENTER);
        val.setFont(new Font("Segoe UI", Font.BOLD, 24));
        val.setForeground(RED_BRIGHT);
        val.setBounds(x, y, w, 30);

        JLabel lbl = new JLabel(titulo, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lbl.setForeground(new Color(180, 180, 180));
        lbl.setBounds(x, y + 28, w, 18);

        JLabel sub = new JLabel(subtitulo, SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        sub.setForeground(new Color(100, 100, 100));
        sub.setBounds(x, y + 44, w, 15);

        panel.add(val);
        panel.add(lbl);
        panel.add(sub);
    }

    private JButton crearBotonTarjeta(String titulo, String icono, String desc, int x, int y) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2.setColor(RED_PRIMARY);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.12f));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    g2.setColor(RED_PRIMARY);
                    g2.setStroke(new java.awt.BasicStroke(2f));
                    g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);
                }
                g2.setColor(DARK_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(new Color(50, 50, 50));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(x, y, 145, 120);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);
        btn.setText("<html><center><span style='font-size:30px'>" + icono + "</span><br><span style='font-size:13px; color:#FF2828;'>" + titulo + "</span><br><span style='font-size:11px; color:#999;'>" + desc + "</span></center></html>");
        return btn;
    }

    private void abrirFormulario(javax.swing.JInternalFrame form) {
        form.setLocation(60, 60);
        padre.getEscritorio().add(form);
        form.setVisible(true);
        try { form.setSelected(true); } catch (Exception ex) {}
    }

    private void centrar(MDIPrincipal padre) {
        int sidebarW = padre.getSidebarWidth();
        int x = (padre.getWidth() - sidebarW - getWidth()) / 2;
        int y = (padre.getHeight() - getHeight()) / 2;
        setLocation(Math.max(x, 10), Math.max(y, 10));
    }
}
