package vista;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static vista.UIUtil.*;

public class frmDashboard extends JInternalFrame {

    private JLabel lblTitulo, lblSubtitulo, lblSlogan, lblInfo, lblFooter;
    private JButton btnClientes, btnMotos, btnTrabajadores, btnCitas;
    private MBLogo logo;
    private JPanel panelHero, panelSlogan, panelStats;
    private MDIPrincipal padre;
    private JLabel[] statsVal, statsTit, statsSub;

    private static final Color RED_PRIMARY = new Color(0, 132, 61);
    private static final Color RED_BRIGHT = new Color(0, 170, 80);

    public frmDashboard(MDIPrincipal padre) {
        this.padre = padre;
        initComponents();
    }

    private void initComponents() {
        setTitle("Bienvenido - Taller SENA");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                reorganizar();
            }
        });

        panelHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();

                RadialGradientPaint rgp = new RadialGradientPaint(
                    w / 2f, h / 2f, Math.max(w, h) * 0.85f,
                    new float[]{0f, 0.4f, 0.8f, 1f},
                    new Color[]{Color.WHITE, new Color(240, 255, 240), new Color(220, 245, 225), new Color(200, 235, 210)});
                g2.setPaint(rgp);
                g2.fillRect(0, 0, w, h);

                g2.setColor(new Color(0, 132, 61, 25));
                g2.fillOval(-w / 6, -h / 4, (int)(w*0.5), (int)(h*2));
                g2.setColor(new Color(0, 170, 80, 15));
                g2.fillOval((int)(w*0.55), (int)(-h*0.3), (int)(w*0.4), (int)(h*1.5));

                g2.setColor(new Color(0, 132, 61));
                g2.fillRect(0, h - 3, w, 3);

                g2.dispose();
            }
        };
        panelHero.setLayout(null);
        panelHero.setOpaque(false);

        logo = new MBLogo(85);

        lblTitulo = new JLabel("TALLER SENA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblTitulo.setForeground(new Color(0, 100, 50));

        lblSubtitulo = new JLabel("TALLER DE MOTOS", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitulo.setForeground(new Color(0, 150, 70));

        panelSlogan = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();

                g2.setColor(new Color(0, 132, 61, 20));
                g2.fillRoundRect(0, 0, w, h, 15, 15);
                g2.setColor(new Color(0, 132, 61, 40));
                g2.drawRoundRect(0, 0, w - 1, h - 1, 15, 15);
                g2.setColor(RED_PRIMARY);
                g2.fillRoundRect(0, 2, 4, h - 4, 2, 2);
                g2.dispose();
            }
        };
        panelSlogan.setLayout(null);
        panelSlogan.setOpaque(false);

        lblSlogan = new JLabel("Potencia, Pasi\u00f3n y Precisi\u00f3n sobre dos ruedas", SwingConstants.CENTER);
        lblSlogan.setFont(FONT_INPUT);
        lblSlogan.setForeground(RED_PRIMARY);

        panelSlogan.add(lblSlogan);

        lblInfo = new JLabel("\u25B6  Gesti\u00f3n integral para su taller de motos", SwingConstants.CENTER);
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setForeground(new Color(100, 100, 100));

        btnClientes = crearBotonTarjeta("CLIENTES", 0);
        btnClientes.addActionListener(e -> { try { abrirFormulario(new FRMClientes()); } catch (Exception ex) { ex.printStackTrace(); } });
        btnMotos = crearBotonTarjeta("MOTOS", 1);
        btnMotos.addActionListener(e -> { try { abrirFormulario(new FRMMotos()); } catch (Exception ex) { ex.printStackTrace(); } });
        btnTrabajadores = crearBotonTarjeta("TALLER", 2);
        btnTrabajadores.addActionListener(e -> { try { abrirFormulario(new FRMTrabajadores()); } catch (Exception ex) { ex.printStackTrace(); } });
        btnCitas = crearBotonTarjeta("CITAS", 3);
        btnCitas.addActionListener(e -> { try { abrirFormulario(new FRMCitas()); } catch (Exception ex) { ex.printStackTrace(); } });

        panelStats = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth(), h = getHeight();

                g2.setColor(SHADOW_COLOR);
                g2.fillRoundRect(2, 4, w - 4, h - 2, 16, 16);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, w, h, 16, 16);

                g2.setColor(new Color(0, 132, 61));
                g2.fillRoundRect(0, 0, 5, h, 5, 5);

                int sw = (w - 20) / 4;
                for (int i = 0; i < 4; i++) {
                    int sx = 10 + i * sw;
                    if (i > 0) {
                        g2.setColor(new Color(0, 132, 61, 30));
                        g2.fillRoundRect(sx - 1, 12, 2, h - 24, 1, 1);
                    }
                    g2.setColor(new Color[]{new Color(0, 132, 61), new Color(0, 150, 70), new Color(0, 120, 55), new Color(0, 160, 75)}[i]);
                    g2.fillRoundRect(sx + 4, 6, sw - 8, 3, 2, 2);
                }

                g2.setColor(CARD_BORDER);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, w - 1, h - 1, 16, 16);

                g2.dispose();
            }
        };
        panelStats.setLayout(null);
        panelStats.setOpaque(false);

        lblFooter = new JLabel("\u00A9 2025 Taller SENA - Sistema de Gesti\u00f3n para Taller de Motos", SwingConstants.CENTER);
        lblFooter.setFont(FONT_SMALL);
        lblFooter.setForeground(TEXT_MUTED);

        String[][] statsData = {{"\uD83C\uDFCD  Motos", "En taller"}, {"\uD83D\uDC65  Clientes", "Registrados"},
                                 {"\uD83D\uDD27  Taller", "Disponibles"}, {"\uD83D\uDCC5  Citas", "Hoy"}};
        statsVal = new JLabel[4];
        statsTit = new JLabel[4];
        statsSub = new JLabel[4];
        for (int i = 0; i < 4; i++) {
            statsVal[i] = new JLabel("--", SwingConstants.CENTER);
            statsVal[i].setFont(new Font("Segoe UI", Font.BOLD, 28));
            statsVal[i].setForeground(new Color[]{new Color(0, 132, 61), new Color(0, 150, 70), new Color(0, 120, 55), new Color(0, 160, 75)}[i]);

            statsTit[i] = new JLabel(statsData[i][0], SwingConstants.CENTER);
            statsTit[i].setFont(FONT_SMALL);
            statsTit[i].setForeground(TEXT_DARK);

            statsSub[i] = new JLabel(statsData[i][1], SwingConstants.CENTER);
            statsSub[i].setFont(new Font("Segoe UI", Font.PLAIN, 10));
            statsSub[i].setForeground(TEXT_MUTED);
        }

        panelHero.add(logo);
        panelHero.add(lblTitulo);
        panelHero.add(lblSubtitulo);

        getContentPane().add(panelHero);
        getContentPane().add(panelSlogan);
        getContentPane().add(lblInfo);
        getContentPane().add(btnClientes);
        getContentPane().add(btnMotos);
        getContentPane().add(btnTrabajadores);
        getContentPane().add(btnCitas);
        getContentPane().add(panelStats);
        getContentPane().add(lblFooter);

        for (int i = 0; i < 4; i++) {
            panelStats.add(statsVal[i]);
            panelStats.add(statsTit[i]);
            panelStats.add(statsSub[i]);
        }
    }

    public void reorganizar() {
        int w = getContentPane().getWidth();
        int h = getContentPane().getHeight();
        if (w < 100) return;

        int heroH = Math.min(250, Math.max(200, h / 3));
        panelHero.setBounds(0, 0, w, heroH);

        int logoSize = Math.min(85, w / 8);
        logo.setBounds((w - logoSize) / 2, Math.max(8, heroH / 2 - logoSize - 14), logoSize, logoSize);

        int titleSize = Math.min(44, Math.max(24, w / 16));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, titleSize));
        int titleY = heroH / 2 + 2;
        lblTitulo.setBounds(20, titleY, w - 40, titleSize + 10);

        int subSize = Math.min(15, Math.max(11, w / 50));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, subSize));
        lblSubtitulo.setBounds(w / 2 - 200, titleY + titleSize + 2, 400, subSize + 8);

        int sloganW = Math.min(520, w - 40);
        int sloganX = (w - sloganW) / 2;
        panelSlogan.setBounds(sloganX, heroH - 30, sloganW, 28);
        lblSlogan.setBounds(0, 2, sloganW, 24);

        lblInfo.setBounds(50, heroH + 6, w - 100, 25);

        int cardY = heroH + 35;
        int cardGap = Math.min(25, w / 30);
        int cardW = Math.min(180, (w - 60 - cardGap * 3) / 4);
        int cardH = Math.min(155, Math.max(125, h / 5));
        if (cardW < 100) cardW = 100;
        int cardsTotalW = cardW * 4 + cardGap * 3;
        int startX = (w - cardsTotalW) / 2;

        btnClientes.setBounds(startX, cardY, cardW, cardH);
        btnMotos.setBounds(startX + cardW + cardGap, cardY, cardW, cardH);
        btnTrabajadores.setBounds(startX + 2 * (cardW + cardGap), cardY, cardW, cardH);
        btnCitas.setBounds(startX + 3 * (cardW + cardGap), cardY, cardW, cardH);

        int infoY = cardY + cardH + 12;

        int statPanelW = Math.min(760, w - 60);
        int statPanelH = Math.min(115, Math.max(85, h - infoY - 60));
        int statPanelX = (w - statPanelW) / 2;
        int statY = Math.max(infoY, h - statPanelH - 35);
        panelStats.setBounds(statPanelX, statY, statPanelW, statPanelH);

        int statGap = 10;
        int statCardW = (statPanelW - statGap * 5) / 4;
        for (int i = 0; i < 4; i++) {
            int sx = statGap + i * (statCardW + statGap);
            statsVal[i].setBounds(sx, 14, statCardW, 36);
            statsTit[i].setBounds(sx, 52, statCardW, 20);
            statsSub[i].setBounds(sx, 70, statCardW, 16);
        }

        lblFooter.setBounds(50, statY + statPanelH + 10, w - 100, 20);
    }

    private void dibujarIconoTarjeta(Graphics2D g2, int index, int w) {
        g2.setColor(RED_PRIMARY);
        g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int cx = w / 2, cy = 35;

        switch (index) {
            case 0:
                g2.drawOval(cx - 6, cy - 10, 12, 12);
                g2.drawArc(cx - 12, cy + 2, 24, 18, 0, 180);
                break;
            case 1: {
                g2.drawOval(cx - 10, cy + 2, 10, 10);
                g2.drawOval(cx + 4, cy + 2, 10, 10);
                g2.drawLine(cx - 5, cy + 7, cx + 4, cy + 7);
                g2.drawLine(cx - 10, cy - 2, cx - 2, cy - 8);
                g2.drawLine(cx - 2, cy - 8, cx + 6, cy - 4);
                g2.drawLine(cx + 6, cy - 4, cx + 10, cy + 2);
                break;
            }
            case 2: {
                g2.drawLine(cx - 6, cy + 10, cx + 8, cy - 6);
                g2.drawLine(cx - 10, cy + 6, cx + 6, cy - 10);
                g2.drawOval(cx - 8, cy - 8, 6, 6);
                g2.drawOval(cx + 2, cy + 2, 6, 6);
                break;
            }
            case 3: {
                g2.drawRoundRect(cx - 10, cy - 8, 20, 22, 4, 4);
                g2.drawLine(cx - 10, cy, cx + 10, cy);
                g2.drawLine(cx - 4, cy - 12, cx - 4, cy - 6);
                g2.drawLine(cx + 4, cy - 12, cx + 4, cy - 6);
                g2.drawLine(cx - 2, cy + 4, cx - 2, cy + 8);
                g2.drawLine(cx + 4, cy + 4, cx + 4, cy + 8);
                break;
            }
        }
    }

    private JButton crearBotonTarjeta(String titulo, int index) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                int w = getWidth(), h = getHeight();
                boolean rollover = getModel().isRollover();
                boolean press = getModel().isPressed();

                g2.setColor(SHADOW_COLOR);
                g2.fillRoundRect(3, 5, w - 6, h - 3, 16, 16);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, w, h, 16, 16);

                if (rollover) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
                }
                g2.setColor(new Color(0, 132, 61));
                g2.fillRoundRect(0, 0, w, 5, 5, 5);
                if (rollover) {
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                }

                if (rollover) {
                    g2.setColor(new Color(0, 132, 61, 12));
                    g2.fillRoundRect(0, 0, w, h, 16, 16);
                    g2.setColor(RED_PRIMARY);
                    g2.setStroke(new BasicStroke(2f));
                    g2.drawRoundRect(1, 1, w - 2, h - 2, 16, 16);
                } else {
                    g2.setColor(CARD_BORDER);
                    g2.setStroke(new BasicStroke(1f));
                    g2.drawRoundRect(0, 0, w - 1, h - 1, 16, 16);
                }

                if (press) {
                    g2.setColor(new Color(0, 132, 61, 20));
                    g2.fillRoundRect(0, 0, w, h, 16, 16);
                }

                dibujarIconoTarjeta(g2, index, w);

                g2.setColor(TEXT_DARK);
                g2.setFont(new Font("Segoe UI", Font.BOLD, Math.min(14, w / 12)));
                FontMetrics fm = g2.getFontMetrics();
                int tw = fm.stringWidth(titulo);
                g2.drawString(titulo, (w - tw) / 2, h - 38);

                g2.setColor(rollover ? RED_PRIMARY : TEXT_MUTED);
                g2.setFont(new Font("Segoe UI", Font.PLAIN, 10));
                String desc = getDescripcion(index);
                int dw = g2.getFontMetrics().stringWidth(desc);
                g2.drawString(desc, (w - dw) / 2, h - 18);

                g2.dispose();
            }
        };
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private String getDescripcion(int index) {
        switch (index) {
            case 0: return "Registre y gestione";
            case 1: return "Inventario de motos";
            case 2: return "Gesti\u00f3n del personal";
            case 3: return "Programaci\u00f3n de citas";
            default: return "";
        }
    }

    private void abrirFormulario(javax.swing.JInternalFrame form) {
        try {
            java.awt.Dimension d = padre.getEscritorio().getSize();
            form.setBounds(0, 0, d.width, d.height);
            padre.getEscritorio().add(form);
            form.setVisible(true);
            form.setSelected(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
