package com.jichuangtech.clothshopserver.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("rawtypes")
public final class DomainCopyUtil {
    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private DomainCopyUtil() {
    }

    private static DozerBeanMapper dozerMAPPER;

    static {
        List<String> myMappingFiles = new ArrayList<String>();
        myMappingFiles.add("dozer.xml");
        dozerMAPPER = new DozerBeanMapper();
        dozerMAPPER.setMappingFiles(myMappingFiles);
    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozerMAPPER.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozerMAPPER.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozerMAPPER.map(source, destinationObject);
    }

}
