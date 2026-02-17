package com.roque.app.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Resuelve las rutas de salida para los artefactos generados por la aplicación.
 */
public class OutputPaths {

    /**
     * Obtiene la carpeta {@code Downloads} del usuario actual, creándola si no existe.
     *
     * @return ruta absoluta a la carpeta de descargas.
     * @throws Exception si falla la creación del directorio.
     */
    private static Path downloadsDir() throws Exception {
        Path downloads = Paths.get(System.getProperty("user.home"), "Downloads");
        if (!Files.exists(downloads)) {
            Files.createDirectories(downloads);
        }
        return downloads;
    }

    /**
     * Devuelve la ruta objetivo del fichero XML.
     *
     * @return ruta completa de {@code comunicacion.xml}.
     * @throws Exception si no se puede resolver la carpeta de descargas.
     */
    public static Path xmlPath() throws Exception {
        return downloadsDir().resolve("comunicacion.xml");
    }

    /**
     * Devuelve la ruta objetivo del fichero ZIP.
     *
     * @return ruta completa de {@code comunicacion.zip}.
     * @throws Exception si no se puede resolver la carpeta de descargas.
     */
    public static Path zipPath() throws Exception {
        return downloadsDir().resolve("comunicacion.zip");
    }

    /**
     * Devuelve la ruta objetivo del fichero Base64 final.
     *
     * @return ruta completa de {@code roque.txt}.
     * @throws Exception si no se puede resolver la carpeta de descargas.
     */
    public static Path txtPath() throws Exception {
        return downloadsDir().resolve("roque.txt");
    }
}
