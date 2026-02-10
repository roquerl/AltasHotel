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
    
    private final Image imagen;

    public FondoPanel() {
        URL url = getClass().getResource("/hotel_fondo.jpg");
        imagen = url != null ? new ImageIcon(url).getImage() : null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagen != null) {
            int anchoPanel = getWidth();
            int altoPanel = getHeight();
            int anchoImagen = imagen.getWidth(this);
            int altoImagen = imagen.getHeight(this);

            double escala = Math.max((double) anchoPanel / anchoImagen, (double) altoPanel / altoImagen);

            int nuevoAncho = (int) Math.round(anchoImagen * escala);
            int nuevoAlto = (int) Math.round(altoImagen * escala);
            int x = (anchoPanel - nuevoAncho) / 2;
            int y = (altoPanel - nuevoAlto) / 2;

            g.drawImage(imagen, x, y, nuevoAncho, nuevoAlto, this);
        }
    }

    
}
