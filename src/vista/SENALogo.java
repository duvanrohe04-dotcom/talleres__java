package vista;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

public class SENALogo extends JComponent {

    private int size;
    private boolean conFondo;

    public SENALogo(int size, boolean conFondo) {
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
        int pad = 4;

        if (conFondo) {
            g2.setColor(new Color(57, 169, 0));
            g2.fillOval(pad, pad, s - pad * 2, s - pad * 2);
            g2.setColor(new Color(45, 135, 0));
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(pad, pad, s - pad * 2, s - pad * 2);
        }

        String texto = "SENA";
        g2.setFont(new Font("Segoe UI", Font.BOLD, (int) (s * 0.28f)));
        FontMetrics fm = g2.getFontMetrics();
        int tw = fm.stringWidth(texto);
        int th = fm.getAscent();
        g2.setColor(Color.WHITE);
        if (conFondo) {
            g2.drawString(texto, (w - tw) / 2, (h + th) / 2 - 2);
        } else {
            g2.setColor(new Color(57, 169, 0));
            g2.drawString(texto, (w - tw) / 2, (h + th) / 2 - 2);
        }

        g2.dispose();
    }
}
