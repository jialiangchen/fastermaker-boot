package com.fastermaker.common.util;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ZipFolderUtils {

    public static void zipFolder(String sourceFolder, String outputFile) {
        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            File folder = new File(sourceFolder);
            zipFile(folder, folder.getName(), zos);
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipFile(File file, String fileName, ZipOutputStream zos) throws IOException {
        if (file.isHidden()) {
            return;
        }
        if (file.isDirectory()) {
            if (fileName.endsWith("/")) {
                zos.putNextEntry(new ZipEntry(fileName));
            } else {
                zos.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zos.closeEntry();
            File[] children = file.listFiles();
            for (File child : children) {
                zipFile(child, fileName + "/" + child.getName(), zos);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, length);
        }
        zos.closeEntry();
        fis.close();
    }

    public static void main(String[] args) {
        String sourceFolder = "path/to/source/folder";
        String outputFile = "path/to/output.zip";
        zipFolder(sourceFolder, outputFile);
    }
}
