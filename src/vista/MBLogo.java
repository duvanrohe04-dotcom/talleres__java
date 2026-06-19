package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class MBLogo extends JComponent {

    private int size;
    private boolean conFondo;

    private static final Color RED_CIRCLE = new Color(211, 30, 30);
    private static final Color RED_CIRCLE_BORDER = new Color(150, 15, 15);
    private static final Color RED_GLOW = new Color(255, 50, 50, 50);

    public MBLogo(int size, boolean conFondo) {
        this.size = size;
        this.conFondo = conFondo;
        setPreferredSize(new java.awt.Dimension(size, size));
        setMinimumSize(new java.awt.Dimension(size, size));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int s = Math.min(w, h);
        int pad = 3;

        if (conFondo) {
            g2.setColor(RED_CIRCLE);
            g2.fillOval(pad, pad, s - pad * 2, s - pad * 2);
            g2.setColor(RED_CIRCLE_BORDER);
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(pad, pad, s - pad * 2, s - pad * 2);
            g2.setColor(RED_GLOW);
            g2.fillOval(pad + 3, pad + 3, s - pad * 2 - 6, (s - pad * 2) / 2 - 3);
        }

        String texto = "MB";
        g2.setFont(new Font("Segoe UI", Font.BOLD, (int) (s * 0.38f)));
        FontMetrics fm = g2.getFontMetrics();
        int tw = fm.stringWidth(texto);
        int th = fm.getAscent();
        g2.setColor(Color.WHITE);
        g2.drawString(texto, (w - tw) / 2, (h + th) / 2 - 1);

        g2.dispose();
    }
}
