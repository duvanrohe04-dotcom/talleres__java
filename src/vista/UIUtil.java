package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class UIUtil {

    public static final Color RED_PRIMARY = new Color(211, 30, 30);
    public static final Color RED_DARK = new Color(160, 18, 18);
    public static final Color RED_BRIGHT = new Color(255, 40, 40);
    public static final Color RED_BG = new Color(180, 20, 20);
    public static final Color RED_HOVER = new Color(230, 50, 50);
    public static final Color BLACK_BG = new Color(6, 6, 6);
    public static final Color DARK_BG = new Color(12, 12, 12);
    public static final Color DARK_CARD = new Color(18, 18, 18);
    public static final Color DARK_CARD_BORDER = new Color(40, 40, 40);
    public static final Color DARK_INPUT = new Color(24, 24, 24);
    public static final Color BORDER_DARK = new Color(55, 55, 55);
    public static final Color TEXT_LIGHT = new Color(220, 220, 220);
    public static final Color TEXT_MUTED = new Color(120, 120, 120);
    public static final Color TABLE_HEADER_BG = new Color(160, 18, 18);
    public static final Color TABLE_HEADER_FG = Color.WHITE;
    public static final Color TABLE_GRID = new Color(50, 50, 50);
    public static final Color TABLE_SELECTION = new Color(211, 30, 30, 180);
    public static final Color TITLE_COLOR = RED_BRIGHT;
    public static final Color LABEL_COLOR = TEXT_LIGHT;
    public static final Color SIDEBAR_BG = new Color(14, 14, 14);
    public static final Color SIDEBAR_HOVER = new Color(30, 30, 30);
    public static final Color SIDEBAR_ACCENT = RED_PRIMARY;

    public static JButton crearBotonRedondeado(String texto, Color bg, int x, int y, int w, int h) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(RED_DARK);
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(
                        Math.min(255, bg.getRed() + 30),
                        Math.min(255, bg.getGreen() + 30),
                        Math.min(255, bg.getBlue() + 30)));
                } else {
                    g2.setColor(bg);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(x, y, w, h);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static JTextField crearCampoTexto(int x, int y, int w) {
        JTextField txt = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(DARK_INPUT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(BORDER_DARK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        txt.setBounds(x, y, w, 30);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setOpaque(false);
        txt.setBorder(new EmptyBorder(2, 10, 2, 10));
        return txt;
    }

    public static JLabel crearLabel(String texto, int x, int y, int w) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, w, 25);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(LABEL_COLOR);
        return lbl;
    }

    public static JPanel crearPanelCard(int x, int y, int w, int h) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(DARK_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(DARK_CARD_BORDER);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        panel.setBounds(x, y, w, h);
        panel.setOpaque(false);
        return panel;
    }

    public static JPanel crearHeaderBar(String titulo, MBLogo logo, int ancho) {
        JPanel header = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(20, 20, 20), 0, getHeight(), new Color(8, 8, 8));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(RED_PRIMARY);
                g2.fillRect(0, getHeight() - 2, getWidth(), 2);
                g2.dispose();
            }
        };
        header.setBounds(0, 0, ancho, 42);
        header.setLayout(null);

        if (logo != null) {
            logo.setBounds(12, 9, 24, 24);
            header.add(logo);
        }

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(RED_BRIGHT);
        lblTitulo.setBounds(logo != null ? 44 : 15, 8, ancho - 60, 28);
        header.add(lblTitulo);

        return header;
    }

    public static void estiloBotonSidebar(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(new Color(180, 180, 180));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(8, 20, 8, 10));
    }
}
