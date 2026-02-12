package com.roque.app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

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
