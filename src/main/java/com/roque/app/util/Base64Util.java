/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.roque.app.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 *
 * @author roque
 */
public class Base64Util {
    
        public static String convertirZipBase64() throws Exception {

        byte[] zipBytes = Files.readAllBytes(Paths.get("comunicacion.zip"));
        return Base64.getEncoder().encodeToString(zipBytes);
    }

    public static void guardarTXT(String base64) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
