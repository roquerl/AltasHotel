/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roque.app.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author roque
 */
public class FondoPanel extends JPanel{
    
    private Image imagen;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        URL url = getClass().getResource("/hotel_fondo.jpg");

        if (url != null) {
            imagen = new ImageIcon(url).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }

    
}
