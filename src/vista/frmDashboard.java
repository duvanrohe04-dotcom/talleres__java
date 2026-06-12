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
    private SENALogo logo;
    private JPanel panelHero;
    private MDIPrincipal padre;

    private static final Color SENA_GREEN = new Color(57, 169, 0);
    private static final Color DARK_CARD = new Color(38, 38, 38);
    private static final Color GOLD = new Color(255, 215, 0);

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
        setSize(680, 520);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(20, 20, 20));

        panelHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(30, 30, 30), 0, getHeight(), new Color(15, 15, 15));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(57, 169, 0, 30));
                g2.fillOval(-50, -80, 300, 300);
                g2.setColor(new Color(255, 215, 0, 15));
                g2.fillOval(400, -100, 350, 350);
                g2.dispose();
            }
        };
        panelHero.setBounds(0, 0, 680, 200);
        panelHero.setLayout(null);
        panelHero.setOpaque(false);

        logo = new SENALogo(80, true);
        logo.setBounds(300, 15, 80, 80);

        lblTitulo = new JLabel("MULTIMARCAS BRAZO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        lblTitulo.setForeground(GOLD);
        lblTitulo.setBounds(40, 95, 600, 50);

        lblSubtitulo = new JLabel("TALLER DE MOTOS", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(180, 180, 180));
        lblSubtitulo.setBounds(150, 140, 380, 25);

        lblSlogan = new JLabel("SENA - Conocimiento para todos", SwingConstants.CENTER);
        lblSlogan.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblSlogan.setForeground(SENA_GREEN);
        lblSlogan.setBounds(150, 165, 380, 20);

        JPanel panelSlogan = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(57, 169, 0, 40));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
            }
        };
        panelSlogan.setBounds(170, 193, 340, 30);

        lblInfo = new JLabel("Gestión integral para su taller", SwingConstants.CENTER);
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(new Color(140, 140, 140));
        lblInfo.setBounds(100, 225, 480, 25);

        btnClientes = crearBotonTarjeta("CLIENTES", "\uD83D\uDC65", "Registre y gestione", 40, 270);
        btnClientes.addActionListener(e -> abrirFormulario(new FRMClientes()));

        btnMotos = crearBotonTarjeta("MOTOS", "\uD83C\uDFCD", "Inventario de motos", 175, 270);
        btnMotos.addActionListener(e -> abrirFormulario(new FRMMotos()));

        btnTrabajadores = crearBotonTarjeta("TRABAJADORES", "\uD83D\uDD27", "Gestión del personal", 310, 270);
        btnTrabajadores.addActionListener(e -> abrirFormulario(new FRMTrabajadores()));

        btnCitas = crearBotonTarjeta("CITAS", "\uD83D\uDCC5", "Programación de citas", 445, 270);
        btnCitas.addActionListener(e -> abrirFormulario(new FRMCitas()));

        JLabel lblFooter = new JLabel("Tecnólogo en Análisis y Desarrollo de Software — GFPI-F-135", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(80, 80, 80));
        lblFooter.setBounds(50, 480, 580, 20);

        panelHero.add(logo);
        panelHero.add(lblTitulo);
        panelHero.add(lblSubtitulo);
        panelHero.add(lblSlogan);
        getContentPane().add(panelHero);
        getContentPane().add(panelSlogan);
        getContentPane().add(lblInfo);
        getContentPane().add(btnClientes);
        getContentPane().add(btnMotos);
        getContentPane().add(btnTrabajadores);
        getContentPane().add(btnCitas);
        getContentPane().add(lblFooter);
    }

    private JButton crearBotonTarjeta(String titulo, String icono, String desc, int x, int y) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2.setColor(new Color(57, 169, 0));
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                    g2.setColor(new Color(57, 169, 0));
                    g2.setStroke(new java.awt.BasicStroke(2f));
                    g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 15, 15);
                }
                g2.setColor(new Color(38, 38, 38));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(new Color(60, 60, 60));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(x, y, 165, 130);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);
        btn.setText("<html><center><span style='font-size:32px'>" + icono + "</span><br><span style='font-size:13px; color:#FFD700;'>" + titulo + "</span><br><span style='font-size:11px; color:#999;'>" + desc + "</span></center></html>");
        return btn;
    }

    private void abrirFormulario(javax.swing.JInternalFrame form) {
        form.setLocation(130, 60);
        padre.getContentPane().add(form);
        form.setVisible(true);
        try {
            form.setSelected(true);
        } catch (Exception ex) {
        }
    }

    private void centrar(MDIPrincipal padre) {
        setLocation(
            (padre.getWidth() - getWidth()) / 2,
            (padre.getHeight() - getHeight()) / 2
        );
    }
}
