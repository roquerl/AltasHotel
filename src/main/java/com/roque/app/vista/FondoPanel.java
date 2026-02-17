package com.roque.app.vista;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Panel con imagen de fondo escalada para ocupar toda la ventana.
 */
public class FondoPanel extends JPanel {

    /** Imagen de fondo cargada desde recursos. */
    private final Image imagen;

    /**
     * Crea el panel y carga la imagen {@code /hotel_fondo.jpg} si existe en el classpath.
     */
    public FondoPanel() {
        URL url = getClass().getResource("/hotel_fondo.jpg");
        imagen = url != null ? new ImageIcon(url).getImage() : null;
    }

    /**
     * Pinta el panel y escala la imagen de fondo manteniendo proporciones.
     *
     * @param g contexto gráfico de Swing.
     */
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
