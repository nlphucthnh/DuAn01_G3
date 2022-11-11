package com.ebooks.Compoment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class Slider extends JSlider {
    private Color colorSlider;

    public Color getColorSlider() {
        return colorSlider;
    }

    public void setColorSlider(Color colorSlider) {
        this.colorSlider = colorSlider;
    }
    
    public Slider() {
        setOpaque(false);
        this.setUI(new BasicSliderUI(this) {
            @Override
            public void paintThumb(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setColor(colorSlider);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillOval(thumbRect.x, thumbRect.y + 5, 11, 11);
            }

            @Override
            public void paintTrack(Graphics grphcs) {
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setColor(colorSlider);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.fillRect(0, getHeight() / 2 - 2, thumbRect.x, 4);
                g2.setColor(new Color(153, 153, 153));
                g2.fillRect(thumbRect.x, getHeight() / 2 - 2, getWidth(), 4);
            }

            @Override
            public void paintFocus(Graphics grphcs) {

            }

        });
    }

}
