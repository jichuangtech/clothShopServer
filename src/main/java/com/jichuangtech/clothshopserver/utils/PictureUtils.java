package com.jichuangtech.clothshopserver.utils;

import com.jichuangtech.clothshopserver.constant.Constant;
import com.jichuangtech.clothshopserver.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Bingo on 2017/8/3.
 */
public class PictureUtils {
    private static final String TAG = PictureUtils.class.getSimpleName();
    private static Logger LOGGER = LoggerFactory.getLogger(Constant.MODULE_NAME);
    public static int getPicture(HttpServletResponse response, String serverPath, String picName) {
        LOGGER.info("getPicture :" + picName);
        int resultCode = 200;
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(serverPath + picName);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            resultCode = ResponseCode.CODE_PIC_GET_ERROR;
            LOGGER.info("getPicture e: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (fis != null) {
                    os.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultCode;
    }


    public static int deletePicture(String serverPath, String picName) {
        int resultCode = ResponseCode.CODE_PIC_DELETE_ERROR;
        LOGGER.info(" deletePicture path: " + serverPath + picName);
        try {
            File file = new File(serverPath, picName);
            if (file.exists() && file.delete()) {
                resultCode = ResponseCode.CODE_SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.info(" deletePicture error: " + e.getMessage());
            e.printStackTrace();
        }
        return resultCode;
    }
}
