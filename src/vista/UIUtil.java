package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UIUtil {

    public static final Color SENA_GREEN = new Color(57, 169, 0);
    public static final Color SENA_GREEN_DARK = new Color(40, 130, 0);
    public static final Color SENA_GREEN_LIGHT = new Color(80, 200, 20);
    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color GOLD_DARK = new Color(200, 170, 0);
    public static final Color DARK_BG = new Color(25, 25, 25);
    public static final Color DARK_CARD = new Color(38, 38, 38);
    public static final Color DARK_INPUT = new Color(50, 50, 50);
    public static final Color TEXT_LIGHT = new Color(200, 200, 200);
    public static final Color TEXT_MUTED = new Color(140, 140, 140);

    public static JButton crearBotonRedondeado(String texto, Color bg, int x, int y, int w, int h) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(bg.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(bg.brighter());
                } else {
                    g2.setColor(bg);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
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
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
                g2.setColor(new Color(70, 70, 70));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        txt.setBounds(x, y, w, 28);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(Color.WHITE);
        txt.setOpaque(false);
        txt.setBorder(new EmptyBorder(2, 8, 2, 8));
        return txt;
    }

    public static JLabel crearLabel(String texto, int x, int y, int w) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, w, 25);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(TEXT_LIGHT);
        return lbl;
    }

    public static void aplicarHoverVerde(JButton btn) {
        Color original = btn.getBackground();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(SENA_GREEN);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(original);
            }
        });
    }
}
