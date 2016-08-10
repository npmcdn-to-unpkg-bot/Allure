package com.allure.image;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by yang_shoulai on 2016/8/10.
 */
public class ImageServlet extends HttpServlet {

    private static final String ALBUM_REPSITORY = "D:\\zeal-albums\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getPathInfo();
        Integer width = parseInt(req.getParameter("width"));
        Integer height = parseInt(req.getParameter("height"));
        String name = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        String floder = ALBUM_REPSITORY + servletPath.substring(0, servletPath.lastIndexOf("/"));
        File srcFile = new File(ALBUM_REPSITORY + servletPath);
        if (!srcFile.exists()) return;
        if (width != null && height != null && width > 0 && height > 0) {
            File resizeFile = new File(floder + File.separator + width + "x" + height + File.separator + name);
            if (!resizeFile.exists()) {
                FileUtils.createFile(resizeFile.getPath());
                ImageUtils.resize(srcFile, resizeFile, width, height);
            }
            returnFile(resp, resizeFile);
        } else {
            returnFile(resp, srcFile);
        }


    }

    protected void returnFile(HttpServletResponse response, File file) {
        if (file.exists() && file.canRead()) {
            FileInputStream fileInputStream = null;
            OutputStream stream = null;
            try {
                fileInputStream = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fileInputStream.read(data);
                MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
                String contentType = mimetypesFileTypeMap.getContentType(file);
                response.setContentType(contentType);
                stream = response.getOutputStream();
                stream.write(data);
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private Integer parseInt(Object obj) {
        if (obj == null) return null;
        try {
            return Integer.valueOf(obj.toString());
        } catch (Exception e) {
            return null;
        }
    }
}
