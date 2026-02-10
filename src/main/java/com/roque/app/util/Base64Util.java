/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roque.app.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 * @author roque
 */
public class Base64Util {

    private static final Path ZIP_PATH = Paths.get("comunicacion.zip");
    private static final Path TXT_PATH = Paths.get("comunicacion_base64.txt");
    
    public static String convertirZipBase64() throws Exception {

        byte[] zipBytes = Files.readAllBytes(ZIP_PATH);
        return Base64.getEncoder().encodeToString(zipBytes);
    }

    public static void guardarTXT(String base64) throws Exception {
        Files.writeString(TXT_PATH, base64);
    }


}
