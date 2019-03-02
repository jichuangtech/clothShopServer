package com.jichuangtech.clothshopserver.controller;


import com.jichuangtech.clothshopserver.utils.LogFactory;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
public class BaseController {
    protected static final Logger LOGGER = LogFactory.newLogger();
    protected String TAG = BaseController.class.getSimpleName();

    protected void showLog(String msg) {
        LOGGER.info("[--" + TAG + "--] " + msg);
    }
}
