package com.allure.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 6/29/2016.
 */
public class FileUtils {


    public static void deleteFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile() && !file.delete()) {
                throw new IOException("can not delete file : " + path);
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        deleteFile(f.getPath());
                    }
                    if (!file.delete()) {
                        throw new IOException("can not delete directory : " + path);
                    }
                }
            }
        }
    }

    public static void deleteFile(File file) throws IOException {
        if (file != null) {
            deleteFile(file.getPath());
        }
    }

    public static void createFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("exception occurs when delete file : " + path);
            }
        }
        File parent = file.getParentFile();
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new IOException("exception occurs when create file directories : " + parent.getPath());
            }
        }
        if (!file.createNewFile()) {
            throw new IOException("exception occurs when create file : " + path);
        }
    }


    public static void copyFile(File srcFile, File targetFile) throws IOException {
        if (srcFile == null || !srcFile.exists()) {
            throw new IOException("src file does not exist!");
        }
        if (targetFile.exists()) {
            deleteFile(targetFile);
        }
        createFile(targetFile.getPath());
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(srcFile);
            outputStream = new FileOutputStream(targetFile);
            StreamUtils.copyStream(inputStream, outputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            StreamUtils.close(inputStream);
            StreamUtils.close(outputStream);
        }
    }

}
