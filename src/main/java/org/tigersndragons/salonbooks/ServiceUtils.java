package org.tigersndragons.salonbooks;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.tigersndragons.salonbooks.exception.ValidationException;

public class ServiceUtils {
	public static void assertNotNull(String message, Object value){
		if(value == null){
			throw new IllegalArgumentException(message);
		}
	}
	
	public static String cleanPhoneNumber(String number) throws ValidationException {
		if (StringUtils.isNotEmpty(number) 
				&& number.length() == 10
				&& StringUtils.isNumeric(number)) {
			return number;
		} else if (StringUtils.isNotEmpty(number) 
				&& number.length() >= 10) {
			StringBuilder builder = new StringBuilder("");
			for (char c : number.toCharArray()) {
				if (CharUtils.isAsciiNumeric(c)) {
					builder.append(c);
				}
			}
			if (builder.toString().length() == 10) {
				return builder.toString();
			}
		}

		throw new ValidationException("Bad form Phone number" + number);

	}

}
