package com.roque.app.modelo;

import java.io.FileWriter;

public class GeneradorXML {

    public static void generarXML(String contenidoXml) throws Exception {
        try (FileWriter writer = new FileWriter("comunicacion.xml")) {
            writer.write(contenidoXml);
        }
    }
}
