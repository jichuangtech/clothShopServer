package com.jichuangtech.clothshopserver.model;

import java.util.List;

public class Page<T extends Object> {
    /**
     * 请求的每页的大小
     */
    public int pageSize;

    /**
     * 请求的下标
     */
    public int pageIndex;
    public int startIndex;
    public int pageTotal;
    public List<T> data;

    public void reset() {
        pageSize = -1;
        pageIndex = -1;
        startIndex = -1;
        pageTotal = -1;
        data = null;
    }

    @Override
    public String toString() {
        return "[pageSize:" + pageSize
                + ", pageIndex: " + pageIndex
                + ", startIndex: " + startIndex
                + ", pageTotal: " + pageTotal
                + ", data: " + data + "]";

    }
}