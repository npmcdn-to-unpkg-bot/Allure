package com.allure.common.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by yang_shoulai on 2016/7/3.
 */
public class ImageUtils {

    private static final int SIZE = 240;

    public static void thumbnail(File srcFile, File targetFile) throws IOException {
        resize(srcFile, targetFile, SIZE, (int) (SIZE * 1.5));
    }

    public static void resize(File srcFile, File targetFile, int targetWidth, int targetHeight) throws IOException {
        if (srcFile == null || targetFile == null || !srcFile.exists()) {
            throw new RuntimeException("exception occurs when resize image");
        }
        if (!targetFile.exists()) {
            FileUtils.createFile(targetFile.getPath());
        }
        try {
            BufferedImage image;
            image = ImageIO.read(srcFile);
            Thumbnails.Builder<BufferedImage> builder;
            int width = image.getWidth();
            int height = image.getHeight();
            if ((float) targetWidth / targetHeight != (float) width / height) {
                if (width > height) {
                    image = Thumbnails.of(srcFile.getPath()).height(targetHeight).asBufferedImage();
                } else {
                    image = Thumbnails.of(srcFile.getPath()).width(targetWidth).asBufferedImage();
                }
                builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, targetWidth, targetHeight).size(targetWidth, targetHeight);
            } else {
                builder = Thumbnails.of(image).size(targetWidth, targetHeight);
            }
            builder.toFile(targetFile.getPath());
        } catch (IOException e) {
            throw e;
        }
    }
}
