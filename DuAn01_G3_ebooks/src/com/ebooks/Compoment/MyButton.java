
package com.poly.Compoment;
package com.ebooks.Compoment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;


public class MyButton extends JButton {
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color boderColor;
    private int radius;

    public MyButton(boolean over, Color color, Color colorOver, Color colorClick, Color boderColor, int radius) {
        this.over = over;
        this.color = color;
        this.colorOver = colorOver;
        this.colorClick = colorClick;
        this.boderColor = boderColor;
        this.radius = radius;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBoderColor(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBoderColor() {
        return boderColor;
    }

    public void setBoderColor(Color boderColor) {
        this.boderColor = boderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    
    public MyButton(){
        setColor(Color.BLUE);
        colorOver = new Color(198,226, 255);
        colorClick = new Color(47,79,79);
        boderColor = new Color(121,205,205);

        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(boderColor);
        g2.fillRoundRect(0, 0,getWidth(),getHeight(),radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2,2,getWidth()-4,getHeight()-4,radius, radius);
        super.paintComponent(g); 
    }
    
    
    
    
}
