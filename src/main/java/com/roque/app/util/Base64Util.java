package com.roque.app.util;

import java.nio.file.Files;
import java.util.Base64;

/**
 * Utilidades para convertir ficheros ZIP a Base64 y guardar la salida textual.
 */
public class Base64Util {

    /**
     * Lee el ZIP generado y devuelve su contenido codificado en Base64.
     *
     * @return contenido Base64 del fichero ZIP.
     * @throws Exception si el ZIP no existe o falla su lectura.
     */
    public static String convertirZipBase64() throws Exception {
        byte[] zipBytes = Files.readAllBytes(OutputPaths.zipPath());
        return Base64.getEncoder().encodeToString(zipBytes);
    }

    /**
     * Guarda la cadena Base64 resultante en el fichero {@code roque.txt}.
     *
     * @param base64 contenido codificado en Base64.
     * @throws Exception si falla la escritura del fichero de salida.
     */
    public static void guardarTXT(String base64) throws Exception {
        Files.writeString(OutputPaths.txtPath(), base64);
    }
}
