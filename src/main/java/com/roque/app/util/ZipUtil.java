/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roque.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author roque
 */
public class ZipUtil {
    
    public static void comprimirXML() throws Exception {

    ZipOutputStream zipOut =
        new ZipOutputStream(new FileOutputStream("comunicacion.zip"));

    FileInputStream fis = new FileInputStream("comunicacion.xml");
    ZipEntry entry = new ZipEntry("comunicacion.xml");

    zipOut.putNextEntry(entry);

    byte[] buffer = new byte[1024];
    int len;

    while ((len = fis.read(buffer)) > 0) {
        zipOut.write(buffer, 0, len);
    }

    zipOut.close();
    fis.close();
}

    
}
