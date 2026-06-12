package org.tigersndragons.salonbooks.model.utils;

import org.springframework.core.convert.converter.Converter;

public class StringToClassConverter implements Converter<String, Class<?>> {
//	@Override
	public Class<?> convert(String source) {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(source);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException (e);
		}
	}

}
