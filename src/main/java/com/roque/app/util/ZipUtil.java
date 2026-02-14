package com.roque.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Utilidad para comprimir el XML de comunicación en formato ZIP.
 */
public class ZipUtil {

    /**
     * Comprime el fichero XML generado en un ZIP llamado {@code comunicacion.zip}.
     *
     * @throws Exception si ocurre un error de lectura/escritura durante la compresión.
     */
    public static void comprimirXML() throws Exception {

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(OutputPaths.zipPath().toFile()));
             FileInputStream fis = new FileInputStream(OutputPaths.xmlPath().toFile())) {

            ZipEntry entry = new ZipEntry("comunicacion.xml");
            zipOut.putNextEntry(entry);

            byte[] buffer = new byte[1024];
            int len;

            while ((len = fis.read(buffer)) > 0) {
                zipOut.write(buffer, 0, len);
            }
        }
    }
}
