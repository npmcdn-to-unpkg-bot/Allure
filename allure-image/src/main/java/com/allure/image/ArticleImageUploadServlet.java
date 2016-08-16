package com.allure.image;

import com.allure.common.utils.DateUtils;
import com.allure.common.utils.FileUtils;
import com.allure.common.utils.JsonUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.aspectj.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by yang_shoulai on 8/16/2016.
 */
public class ArticleImageUploadServlet extends BaseFileServlet {

    private static final String tmp = "D:\\allure-upload\\article-image-tmp\\";
    private static final String path = "D:\\allure-upload\\article-image\\";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(5 * 1024 * 1024);
        factory.setRepository(new File(tmp));
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setFileSizeMax(5 * 1024 * 1024);
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> list = servletFileUpload.parseRequest(req);
            List<String> relativePaths = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (FileItem item : list) {
                    if (!item.isFormField()) {
                        String value = item.getName();
                        int start = value.lastIndexOf("\\");
                        String fileName = value.substring(start + 1);
                        String relativePath = DateUtils.format(new Date(), null) + File.separator + fileName;
                        relativePaths.add(relativePath);
                        File file = new File(path + relativePath);
                        if (!file.exists()) {
                            FileUtils.createFile(file.getPath());
                        }
                        item.write(file);
                    }
                }
            }
            resp.getWriter().write(JsonUtils.toJson(relativePaths));
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getPathInfo();
        File file = new File(path + servletPath);
        if (!file.exists()) {
            resp.sendError(404);
        } else {
            returnFile(resp, file);
        }
    }
}
