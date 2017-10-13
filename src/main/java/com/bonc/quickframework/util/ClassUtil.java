package com.bonc.quickframework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.ParameterizedType;

public class ClassUtil {
	public static Class getGenericSuperclass(Class clazz) {
		ParameterizedType type = (ParameterizedType) clazz
				.getGenericSuperclass();
		Class entityClass = (Class) type.getActualTypeArguments()[0];
		return entityClass;
	}
	
	/**
	 * 克隆对象
	 * @param obj
	 * @return
	 */
	public static Object DeepClone(Object obj) {
		// 将对象写到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo;
		try {
			oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);
			// 从流里读出来
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			ObjectInputStream oi = new ObjectInputStream(bi);
			return (oi.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Object();
	}
	
}
