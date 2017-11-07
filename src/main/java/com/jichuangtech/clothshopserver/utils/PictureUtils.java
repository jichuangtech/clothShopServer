package com.jichuangtech.clothshopserver.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.IMAGE_SUFFIX;
import static com.jichuangtech.clothshopserver.constant.GoodsCategoryConstant.SERVER_IMAGE_PATH;

/**
 * Created by Bingo on 2017/8/3.
 */
public class PictureUtils {

    public static void writePic(HttpServletResponse response, String serverPath, String picName) {
        System.out.println("getPicture :" + picName);
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
            System.out.println(" getPicture e: " + e.getMessage());
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

    }
}
