/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roque.app.modelo;

import java.io.FileWriter;

/**
 *
 * @author roque
 */
public class GeneradorXML {
    
    public static void generarXML(String nombre, String apellidos,
                              String documento, String entrada, String salida) throws Exception {

    FileWriter writer = new FileWriter("comunicacion.xml");

    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    writer.write("<comunicacion>\n");
    writer.write("<codigoEstablecimiento>0000004063</codigoEstablecimiento>\n");
    writer.write("<huesped>\n");
    writer.write("<nombre>" + nombre + "</nombre>\n");
    writer.write("<apellidos>" + apellidos + "</apellidos>\n");
    writer.write("<documento>" + documento + "</documento>\n");
    writer.write("<fechaEntrada>" + entrada + "</fechaEntrada>\n");
    writer.write("<fechaSalida>" + salida + "</fechaSalida>\n");
    writer.write("</huesped>\n");
    writer.write("</comunicacion>");

    writer.close();
}
    
}
