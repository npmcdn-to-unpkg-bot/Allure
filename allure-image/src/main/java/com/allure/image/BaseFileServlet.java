package com.allure.image;

import com.allure.common.utils.StreamUtils;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by yang_shoulai on 8/16/2016.
 */
public abstract class BaseFileServlet extends HttpServlet {
    protected void returnFile(HttpServletResponse response, File file) {
        if (file.exists() && file.canRead()) {
            FileInputStream fileInputStream = null;
            OutputStream stream = null;
            try {
                fileInputStream = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                int count = fileInputStream.read(data);
                if (count <= 0) return;
                MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
                String contentType = mimetypesFileTypeMap.getContentType(file);
                response.setContentType(contentType);
                stream = response.getOutputStream();
                stream.write(data);
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtils.close(fileInputStream);
                StreamUtils.close(stream);
            }
        }
    }
}
