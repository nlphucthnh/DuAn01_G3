/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.Compoment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class pgbBoderRadius extends javax.swing.JProgressBar {
     private int radius;
    public  pgbBoderRadius(int radius) {
        this.radius = radius;
        setOpaque(false);

    }
    public  pgbBoderRadius() {
    }
        

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }    
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0,getWidth(),getHeight(),radius, radius);
        super.paintComponent(grphcs);
    }
    
}
