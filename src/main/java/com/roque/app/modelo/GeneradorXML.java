package com.roque.app.modelo;

import com.roque.app.util.OutputPaths;
import java.nio.file.Files;

/**
 * Servicio de persistencia para escribir el XML de comunicación en disco.
 */
public class GeneradorXML {

    /**
     * Genera el fichero {@code comunicacion.xml} en la ruta de salida configurada.
     *
     * @param contenidoXml contenido XML completo a guardar.
     * @throws Exception si falla la escritura del fichero.
     */
    public static void generarXML(String contenidoXml) throws Exception {
        Files.writeString(OutputPaths.xmlPath(), contenidoXml);
    }
}
