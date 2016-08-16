package com.allure.image;


import com.allure.common.utils.FileUtils;
import com.allure.common.utils.ImageUtils;
import com.allure.common.utils.NumberUtils;
import com.allure.common.utils.StreamUtils;

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
public class ImageServlet extends BaseFileServlet {

    private static final String ALBUM_REPOSITORY = "D:\\zeal-albums\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getPathInfo();
        Integer width = NumberUtils.parseInteger(req.getParameter("width"));
        Integer height = NumberUtils.parseInteger(req.getParameter("height"));
        String name = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        String folder = ALBUM_REPOSITORY + servletPath.substring(0, servletPath.lastIndexOf("/"));
        File srcFile = new File(ALBUM_REPOSITORY + servletPath);
        if (!srcFile.exists()) return;
        if (width != null && height != null && width > 0 && height > 0) {
            File resizeFile = new File(folder + File.separator + width + "x" + height + File.separator + name);
            if (!resizeFile.exists()) {
                FileUtils.createFile(resizeFile.getPath());
                ImageUtils.resize(srcFile, resizeFile, width, height);
            }
            returnFile(resp, resizeFile);
        } else {
            returnFile(resp, srcFile);
        }


    }


}
