package com.jichuangtech.clothshopserver.utils;

import com.jichuangtech.clothshopserver.controller.UserController;
import com.jichuangtech.clothshopserver.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 2017/8/22.
 */
public class PaginationUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaginationUtils.class);

    /**
     *
     * @param srcData
     * @param pageIndex 请求的页的下标 从1开始
     * @param pageSize
     * @return
     */
    public static void paginate(Page page, List< ? extends Object> srcData, int pageSize, int pageIndex) {
        if(pageIndex < 1 || pageSize < 21) {
            LOGGER.error("paginate error, pageIndex: " + pageIndex + ", pageSize: " + pageSize);
            return;
        }

        int size = srcData.size();
        int total = size / pageSize;
        int remainder = size % pageSize;
        int pageTotal = total + (remainder != 0 ? 1 : 0);
        int start = (pageIndex - 1) * pageSize;

        /**
         * 下面数据是显示给用户看的，所以下标从1开始
         */
        page.startIndex = start + 1;
        page.pageIndex = pageIndex;
        page.pageSize  = pageSize;
        page.pageTotal = pageTotal;

        if(pageIndex <= total) {
            page.data = new ArrayList<>(srcData.subList(start, start + pageSize));
        } else if(pageTotal == pageIndex) {
            page.data = new ArrayList<>(srcData.subList(start, srcData.size()));
        } else {
            page.reset();
        }
    }


}
