import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @see http://stackoverflow.com/a/12297384/714968
 */
public class SliderUI1 extends BasicSliderUI {

    private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND, 
            BasicStroke.JOIN_ROUND, 0f, new float[]{1f, 2f}, 0f);

    public SliderUI1(JSlider b) {
        super(b);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(16, 20);
    }

    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRoundRect(trackRect.x-5, trackRect.y+10, trackRect.width+10, 8, 8, 8);
        g2d.setColor(Color.GRAY);
        g2d.drawRoundRect(trackRect.x-5, trackRect.y+10, trackRect.width+10, 8, 8, 8);
        /*
        Stroke old = g2d.getStroke();
        g2d.setStroke(stroke);
        g2d.setPaint(Color.BLACK);
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2, 
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
        } else {
            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y, 
                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
        }
        g2d.setStroke(old);
        */
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int x1 = thumbRect.x+3;
        int width = thumbRect.width - 4;
        int height = thumbRect.height - 2;
        int topY = thumbRect.y + 2;
        g2d.setColor(new Color(0xBE1E2D));
        int sheight = height-8;
        g2d.fillRect(x1, topY, width, sheight);
        Polygon triangle = new Polygon();
        triangle.addPoint(x1, topY+sheight);
        triangle.addPoint(x1+width, topY+sheight);
        triangle.addPoint(x1 + (width)/2, topY+height);
        g2d.fillPolygon(triangle);
    }
}