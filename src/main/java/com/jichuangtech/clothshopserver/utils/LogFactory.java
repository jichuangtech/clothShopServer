package com.jichuangtech.clothshopserver.utils;

import com.jichuangtech.clothshopserver.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {
    public static Logger newLogger() {
        return LoggerFactory.getLogger(Constant.MODULE_NAME);
    }
}
