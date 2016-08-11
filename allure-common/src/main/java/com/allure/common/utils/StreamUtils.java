package com.allure.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yang_shoulai on 8/11/2016.
 */
public class StreamUtils {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtils.class);

    /**
     * close stream
     *
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
    }

    /**
     * copy input stream to out stream
     *
     * @param in  input stream
     * @param out out stream
     * @throws IOException
     */
    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[4096];
        for (int bytesRead = in.read(buf, 0, 4096); bytesRead != -1; bytesRead = in.read(buf, 0, 4096)) {
            out.write(buf, 0, bytesRead);
        }
    }

}
