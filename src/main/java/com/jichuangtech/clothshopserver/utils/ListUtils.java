package com.jichuangtech.clothshopserver.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListUtils { 
	/**
	 * 给定一个集合，获得<fieldName属性对应的value(唯一),实体>
	 * @param key
	 * @param list
	 * @param keyName
	 * @return
	 */
	public static <K,V >Map<K,V> listToEntityMap(Class<K>  filedType,List<V> list,String fieldName){
		if(list.isEmpty()){
			return null;
		}
		Map<K,V> fieldEntityMap = new HashMap<K,V>();
		String getFieldMethodName = getMethodName(fieldName);
		
		for (int i = 0; i < list.size(); i++) {
			V entity = list.get(i);
			try {
				Method getFieldMethod = entity.getClass().getMethod(getFieldMethodName);
				K fieldValue = (K)getFieldMethod.invoke(entity, null);
				fieldEntityMap.put(fieldValue, entity);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fieldEntityMap;
	}

	/**
	 * 获得该属性的get方法
	 * @param fieldName
	 * @return
	 */
	private static String getMethodName(String fieldName) {
		StringBuilder methodBuilder = new StringBuilder();
		methodBuilder.append("get").append(getFirstCharName(fieldName)).append(getNameExceptFirst(fieldName));
		String methodName = methodBuilder.toString();
		return methodName;
	}
	
	private static StringBuilder getFirstCharName(String fieldName){
		StringBuilder firstCharBuilder = new StringBuilder();
		return firstCharBuilder.append(fieldName.substring(0, 1).toUpperCase());
	}
	
	private static StringBuilder getNameExceptFirst(String fieldName){
		StringBuilder firstCharBuilder = new StringBuilder();
		return firstCharBuilder.append(fieldName.substring(1, fieldName.length()));
	}
	
	/**
	 * 从集合中获取某个字段的集合
	 * @param fieldType
	 * @param list
	 * @param fieldName
	 * @return
	 */
	public static <K,V> Set<K> listToFieldSet(Class<K> fieldType,List<V> list,String fieldName){
		if(list.isEmpty()){
			return null;
		}
		Set<K> fieldValueSet = new HashSet<K>();
		String getFieldMethodName = getMethodName(fieldName);
		
		for (int i = 0; i < list.size(); i++) {
			V entity = list.get(i);
			try {
				Method getFieldMethod = entity.getClass().getMethod(getFieldMethodName);
				K fieldValue = (K)getFieldMethod.invoke(entity, null);
				fieldValueSet.add(fieldValue);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fieldValueSet;
	}
	
	/**
	 * 给定一个集合，获得<fieldName属性对应的value,实体集合>
	 * @param filedType
	 * @param list
	 * @param fieldName
	 * @return
	 */
	public static <K,V >Map<K,List<V>> listToListMap(Class<K>  filedType,List<V> list,String fieldName){
		if(list.isEmpty()){
			return null;
		}
		Map<K,List<V>> fieldListMap = new HashMap<K,List<V>>();
		String getFieldMethodName = getMethodName(fieldName);
		
		for (int i = 0; i < list.size(); i++) {
			V entity = list.get(i);
			try {
				Method getFieldMethod = entity.getClass().getMethod(getFieldMethodName);
				K fieldValue = (K)getFieldMethod.invoke(entity, null);
				List<V> fieldList = fieldListMap.get(fieldValue);
				if(null == fieldList){
					fieldList = new ArrayList<V>();
					fieldListMap.put(fieldValue, fieldList);
				}
				fieldList.add(entity);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fieldListMap;
	}
}
