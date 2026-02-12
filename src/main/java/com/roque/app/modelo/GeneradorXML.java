package com.roque.app.modelo;

import com.roque.app.util.OutputPaths;
import java.nio.file.Files;

public class GeneradorXML {

    public static void generarXML(String contenidoXml) throws Exception {
        Files.writeString(OutputPaths.xmlPath(), contenidoXml);
    }
}
