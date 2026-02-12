package com.roque.app.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputPaths {

    private static Path downloadsDir() throws Exception {
        Path downloads = Paths.get(System.getProperty("user.home"), "Downloads");
        if (!Files.exists(downloads)) {
            Files.createDirectories(downloads);
        }
        return downloads;
    }

    public static Path xmlPath() throws Exception {
        return downloadsDir().resolve("comunicacion.xml");
    }

    public static Path zipPath() throws Exception {
        return downloadsDir().resolve("comunicacion.zip");
    }

    public static Path txtPath() throws Exception {
        return downloadsDir().resolve("comunicacion_base64.txt");
    }
}
