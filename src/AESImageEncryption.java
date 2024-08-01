package LabAssesProblems;

import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class test2 {

    public static void encryptFile(String key, String initVector, String inputFile, String outputFile) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] inputBytes = readBytesFromFile(inputFile);
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            writeBytesToFile(outputFile, encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String key, String initVector, String inputFile, String outputFile) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] encryptedBytes = readBytesFromFile(inputFile);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            writeBytesToFile(outputFile, decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readBytesFromFile(String filePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(filePath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return byteArrayOutputStream.toByteArray();
        }
    }

    private static void writeBytesToFile(String filePath, byte[] bytes) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(bytes);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter secret key (16 bytes): ");
        String key = sc.next();
        String initVector = "0123456789012345";
        // IV (initialization vector) must be 16 bytes in length

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter file path:");
        String inputFile = br.readLine();
        String encryptedFile = inputFile;
        String decryptedFile = inputFile;

        //Encrypt the file
        encryptFile(key, initVector, inputFile, encryptedFile);
        System.out.println("File encrypted: " + encryptedFile);

        //Decrypt the file
        decryptFile(key, initVector, encryptedFile, decryptedFile);
        System.out.println("File decrypted: " + decryptedFile);
    }
}


//"C:/Users/VED/OneDrive/Pictures/God of War/gow.png"