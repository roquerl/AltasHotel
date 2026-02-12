package com.roque.app.util;

import java.nio.file.Files;
import java.util.Base64;

public class Base64Util {

    public static String convertirZipBase64() throws Exception {
        byte[] zipBytes = Files.readAllBytes(OutputPaths.zipPath());
        return Base64.getEncoder().encodeToString(zipBytes);
    }

    public static void guardarTXT(String base64) throws Exception {
        Files.writeString(OutputPaths.txtPath(), base64);
    }
}
