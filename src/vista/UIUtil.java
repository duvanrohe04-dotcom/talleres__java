package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class UIUtil {

    // ── Paleta central SENA ─────────────────────────────────────────
    public static final Color RED_PRIMARY      = new Color(0, 132, 61);
    public static final Color RED_DARK         = new Color(0, 90, 40);
    public static final Color RED_BRIGHT       = new Color(0, 170, 80);
    public static final Color RED_HOVER        = new Color(0, 150, 70);
    public static final Color RED_PRESSED      = new Color(0, 70, 30);

    public static final Color WHITE_BG         = new Color(245, 245, 245);
    public static final Color LIGHT_BG         = new Color(240, 240, 240);
    public static final Color WHITE_CARD       = Color.WHITE;
    public static final Color CARD_BORDER      = new Color(210, 210, 210);
    public static final Color INPUT_BG         = Color.WHITE;
    public static final Color BORDER_DARK      = new Color(190, 190, 190);
    public static final Color BORDER_FOCUS     = new Color(0, 132, 61, 200);
    public static final Color BORDER_DISABLED  = new Color(220, 220, 220);

    public static final Color TEXT_DARK        = new Color(50, 50, 50);
    public static final Color TEXT_MUTED       = new Color(140, 140, 140);

    public static final Color TABLE_HEADER_BG  = new Color(0, 90, 40);
    public static final Color TABLE_HEADER_FG  = Color.WHITE;
    public static final Color TABLE_GRID       = new Color(210, 210, 210);
    public static final Color TABLE_SELECTION  = new Color(0, 132, 61, 180);
    public static final Color TABLE_STRIPE     = new Color(248, 248, 248);
    public static final Color TABLE_STRIPE2    = Color.WHITE;

    public static final Color TITLE_COLOR      = RED_BRIGHT;
    public static final Color LABEL_COLOR      = TEXT_DARK;

    public static final Color SIDEBAR_BG       = new Color(250, 250, 250);
    public static final Color SIDEBAR_HOVER    = new Color(235, 235, 235);
    public static final Color SIDEBAR_ACCENT   = RED_PRIMARY;

    public static final Color SHADOW_COLOR     = new Color(0, 0, 0, 30);
    public static final Color SHADOW_DEEP      = new Color(0, 0, 0, 15);

    // ── Jerarquía tipográfica ───────────────────────────────────────
    public static final Font FONT_TITLE        = new Font("Segoe UI", Font.BOLD, 17);
    public static final Font FONT_SECTION      = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_LABEL        = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_INPUT        = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BUTTON       = new Font("Segoe UI", Font.BOLD, 12);
    public static final Font FONT_SMALL        = new Font("Segoe UI", Font.PLAIN, 11);

    // ── Campos de texto con foco ─────────────────────────────────────
    public static JTextField crearCampoTexto(int x, int y, int w) {
        JTextField txt = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(INPUT_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                if (hasFocus()) {
                    g2.setColor(BORDER_FOCUS);
                    g2.setStroke(new BasicStroke(1.5f));
                } else if (isEnabled()) {
                    g2.setColor(BORDER_DARK);
                    g2.setStroke(new BasicStroke(1f));
                } else {
                    g2.setColor(BORDER_DISABLED);
                    g2.setStroke(new BasicStroke(1f));
                }
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        txt.setBounds(x, y, w, 32);
        txt.setFont(FONT_INPUT);
        txt.setForeground(TEXT_DARK);
        txt.setCaretColor(TEXT_DARK);
        txt.setOpaque(false);
        txt.setBorder(new EmptyBorder(2, 12, 2, 12));
        txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) { txt.repaint(); }
            @Override
            public void focusLost(FocusEvent e)   { txt.repaint(); }
        });
        return txt;
    }

    // ── Labels ───────────────────────────────────────────────────────
    public static JLabel crearLabel(String texto, int x, int y, int w) {
        JLabel lbl = new JLabel(texto);
        lbl.setBounds(x, y, w, 32);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(LABEL_COLOR);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        return lbl;
    }

    // ── Botones con jerarquía visual ─────────────────────────────────
    public static JButton crearBotonPrimario(String texto, int x, int y) {
        return crearBotonBase(texto, RED_PRIMARY, RED_HOVER, RED_PRESSED, Color.WHITE, x, y, 105, 32);
    }

    public static JButton crearBotonSecundario(String texto, int x, int y) {
        return crearBotonBase(texto, RED_DARK, new Color(185, 30, 30), RED_PRESSED, Color.WHITE, x, y, 105, 32);
    }

    public static JButton crearBotonDestructivo(String texto, int x, int y) {
        return crearBotonBase(texto, new Color(0, 100, 50), new Color(0, 120, 60), new Color(0, 70, 35), Color.WHITE, x, y, 105, 32);
    }

    public static JButton crearBotonNeutral(String texto, int x, int y) {
        return crearBotonBase(texto, new Color(230, 230, 230), new Color(215, 215, 215), new Color(200, 200, 200), TEXT_DARK, x, y, 105, 32);
    }

    private static JButton crearBotonBase(String texto, Color bg, Color hover, Color pressed, Color fg, int x, int y, int w, int h) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                boolean rollover = getModel().isRollover();
                boolean press = getModel().isPressed();

                Color c;
                if (!getModel().isEnabled()) {
                    c = new Color(220, 220, 220);
                } else if (press) {
                    c = pressed;
                } else if (rollover) {
                    c = hover;
                } else {
                    c = bg;
                }

                if (rollover && !press) {
                    g2.setColor(SHADOW_COLOR);
                    g2.fillRoundRect(3, 5, w - 6, h - 2, 10, 10);
                }

                g2.setColor(c);
                g2.fillRoundRect(0, 0, w, h, 10, 10);

                if (rollover && !press) {
                    g2.setColor(new Color(255, 255, 255, 60));
                    g2.fillRoundRect(2, 2, w - 4, h / 2 - 2, 8, 8);
                }

                if (press) {
                    g2.setColor(new Color(0, 0, 0, 30));
                    g2.fillRoundRect(1, 1, w - 2, h - 2, 10, 10);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBounds(x, y, w, h);
        btn.setFont(FONT_BUTTON);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ── Paneles con sombra ──────────────────────────────────────────
    public static JPanel crearPanelCard(int x, int y, int w, int h) {
        return crearPanelSombra(x, y, w, h, WHITE_CARD, CARD_BORDER, 12);
    }

    public static JPanel crearPanelSombra(int x, int y, int w, int h, Color fill, Color border, int radius) {
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int r = radius, bw = getWidth(), bh = getHeight();

                g2.setColor(SHADOW_DEEP);
                g2.fillRoundRect(2, 4, bw - 4, bh - 2, r, r);
                g2.setColor(SHADOW_COLOR);
                g2.fillRoundRect(1, 2, bw - 2, bh - 1, r, r);

                g2.setColor(fill);
                g2.fillRoundRect(0, 0, bw, bh, r, r);

                g2.setColor(border);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, bw - 1, bh - 1, r, r);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        panel.setBounds(x, y, w, h);
        panel.setOpaque(false);
        return panel;
    }

    // ── Header bar ───────────────────────────────────────────────────
    public static JPanel crearHeaderBar(String titulo, Component logo, int ancho) {
        JPanel header = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int bw = getWidth(), bh = getHeight();
                LinearGradientPaint lgp = new LinearGradientPaint(
                    0, 0, 0, bh,
                    new float[]{0f, 0.5f, 1f},
                    new Color[]{Color.WHITE, new Color(250, 250, 250), new Color(245, 245, 245)});
                g2.setPaint(lgp);
                g2.fillRect(0, 0, bw, bh);

                g2.setColor(SHADOW_COLOR);
                g2.fillRect(0, bh, bw, 4);

                LinearGradientPaint lineGrad = new LinearGradientPaint(
                    0, 0, bw, 0,
                    new float[]{0f, 0.15f, 0.85f, 1f},
                    new Color[]{new Color(0, 132, 61, 0), RED_PRIMARY, RED_PRIMARY, new Color(0, 132, 61, 0)});
                g2.setPaint(lineGrad);
                g2.fillRect(0, bh - 3, bw, 3);

                g2.dispose();
            }
        };
        header.setBounds(0, 0, ancho, 44);
        header.setLayout(null);

        if (logo != null) {
            if (logo instanceof javax.swing.JComponent) {
                ((javax.swing.JComponent) logo).setBounds(14, 10, 24, 24);
            }
            header.add(logo);
        }

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(0, 100, 50));
        lblTitulo.setBounds(logo != null ? 46 : 15, 8, ancho - 80, 28);
        header.add(lblTitulo);

        return header;
    }

    public static void estiloBotonSidebar(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(TEXT_DARK);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(8, 20, 8, 10));
    }

    // ── Tabla con zebra striping ─────────────────────────────────────
    public static void configurarTabla(JTable tabla) {
        tabla.setFont(FONT_INPUT);
        tabla.setRowHeight(34);
        tabla.setBackground(Color.WHITE);
        tabla.setForeground(TEXT_DARK);
        tabla.setGridColor(TABLE_GRID);
        tabla.setSelectionBackground(TABLE_SELECTION);
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setShowHorizontalLines(true);
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));

        JTableHeader header = tabla.getTableHeader();
        header.setBackground(TABLE_HEADER_BG);
        header.setForeground(TABLE_HEADER_FG);
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setPreferredSize(new Dimension(0, 34));
        ((JComponent) header).setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(new EmptyBorder(4, 8, 4, 8));
                if (!isSelected) {
                    setBackground(row % 2 == 0 ? TABLE_STRIPE : TABLE_STRIPE2);
                }
                if (!isSelected) {
                    setForeground(TEXT_DARK);
                }
                setFont(FONT_INPUT);
                return this;
            }
        });
    }

    // ── Separador con gradiente ──────────────────────────────────────
    public static JPanel crearSeparador(int x, int y, int w) {
        JPanel sep = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int h = getHeight();

                LinearGradientPaint lgp = new LinearGradientPaint(
                    0, 0, w, 0,
                    new float[]{0f, 0.5f, 1f},
                    new Color[]{new Color(0, 132, 61, 0), new Color(0, 132, 61, 100), new Color(0, 132, 61, 0)});
                g2.setPaint(lgp);
                g2.fillRect(0, 0, w, 1);

                g2.setColor(new Color(0, 132, 61, 50));
                g2.fillOval(w / 2 - 3, -2, 6, 6);

                g2.dispose();
            }
        };
        sep.setBounds(x, y, w, 4);
        sep.setOpaque(false);
        return sep;
    }
}
