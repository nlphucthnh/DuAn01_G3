/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.Compoment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author Thinh
 */
public class txtFieldPassBoder extends javax.swing.JPasswordField{
    private int radius;

    public txtFieldPassBoder() {
      
    }

    public txtFieldPassBoder(int radius) {
        this.radius = radius;
    }
    
    
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0,getWidth(),getHeight(),radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2,2,getWidth()-4,getHeight()-4,radius, radius);
        super.paintComponent(g); 
    }
    
    
}
