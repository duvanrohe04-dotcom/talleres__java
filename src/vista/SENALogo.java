package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class SENALogo extends JComponent {

    private int size;
    private static Image imagenGlobal;
    private static final Color RED_PRIMARY = new Color(0, 132, 61);
    private static final Color RED_BRIGHT = new Color(0, 170, 80);
    private static final Color RED_GLOW = new Color(0, 132, 61, 60);

    public SENALogo(int size) {
        this.size = size;
        setPreferredSize(new java.awt.Dimension(size, size));
        setMinimumSize(new java.awt.Dimension(size, size));
        cargarImagen();
    }

    public SENALogo(int size, boolean unused) {
        this(size);
    }

    private static void cargarImagen() {
        if (imagenGlobal != null) return;
        try (InputStream is = SENALogo.class.getResourceAsStream("logo_sena.png")) {
            if (is != null) {
                imagenGlobal = ImageIO.read(is);
            }
        } catch (IOException e) {
            imagenGlobal = null;
        }
    }

    public static BufferedImage crearIconoVentana(int size) {
        cargarImagen();
        BufferedImage icon = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = icon.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if (imagenGlobal != null) {
            int s = Math.min(imagenGlobal.getWidth(null), imagenGlobal.getHeight(null));
            float scale = (float) (size - 4) / s;
            int sw = (int) (imagenGlobal.getWidth(null) * scale);
            int sh = (int) (imagenGlobal.getHeight(null) * scale);
            g2.drawImage(imagenGlobal, (size - sw) / 2, (size - sh) / 2, sw, sh, null);
        }
        g2.dispose();
        return icon;
    }

    private void pintarFondo(Graphics2D g2, int x, int y, int s) {
        RadialGradientPaint rgp = new RadialGradientPaint(
            x + s / 2f, y + s / 2f, s / 2f,
            new float[]{0f, 0.8f, 1f},
            new Color[]{Color.WHITE, new Color(252, 252, 252), new Color(240, 240, 240)});
        g2.setPaint(rgp);
        g2.fillOval(x, y, s, s);
    }

    private void pintarAnillo(Graphics2D g2, int x, int y, int s) {
        int ancho = Math.max(2, s / 16);
        LinearGradientPaint lgp = new LinearGradientPaint(
            x, y, x + s, y + s,
            new float[]{0f, 0.5f, 1f},
            new Color[]{RED_PRIMARY, RED_BRIGHT, RED_PRIMARY});
        g2.setPaint(lgp);
        g2.setStroke(new BasicStroke(ancho, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int offset = ancho / 2;
        g2.drawOval(x + offset, y + offset, s - ancho, s - ancho);

        g2.setColor(RED_GLOW);
        g2.setStroke(new BasicStroke(ancho / 2f));
        g2.drawOval(x + offset + ancho, y + offset + ancho, s - ancho * 3, s - ancho * 3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int w = getWidth();
        int h = getHeight();
        int s = Math.min(w, h);
        int x = (w - s) / 2;
        int y = (h - s) / 2;

        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillOval(x + 2, y + 3, s, s);

        pintarFondo(g2, x, y, s);

        if (imagenGlobal != null) {
            int pad = Math.max(4, s / 5);
            int drawSize = s - pad * 2;
            g2.drawImage(imagenGlobal, (w - drawSize) / 2, (h - drawSize) / 2, drawSize, drawSize, null);
        } else {
            g2.setColor(new Color(200, 50, 50));
            g2.fillOval(x + s / 6, y + s / 6, s * 2 / 3, s * 2 / 3);
            g2.setColor(Color.WHITE);
            g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, s / 3));
            java.awt.FontMetrics fm = g2.getFontMetrics();
            String t = "SENA";
            g2.drawString(t, (w - fm.stringWidth(t)) / 2, (h + fm.getAscent()) / 2 - 2);
        }

        pintarAnillo(g2, x, y, s);

        g2.dispose();
    }
}
