package com.bonc.quickframework.generator.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringHelper {
	
	public static void main(String[] args) {
//		System.err.println(StringHelper.makeAllWordFirstLetterUpperCase("USER_NAME"));//UserName
//		System.err.println(StringHelper.uncapitalize("UserName"));//userName
//		System.err.println(StringHelper.toUnderscoreName("userName"));//user_name
		
		System.err.println(javaTypeName("org_Name"));
		System.err.println(javaVarName("ORG_NAME"));
	}
	/**
	 * 判断文本中是否含有表达式
	 * @param context
	 * @return
	 */
	public static boolean containExpression(String context){
		if(StringHelper.isBlank(context))return false;
		return Pattern.compile("\\$\\{.+?\\}").matcher(context).find();
	}
	
	/**
	 * 数据库类型,如：UserName -> user_name
	 * @param name
	 * @return
	 */
	public static String sqlTypeName(String name){
		if(name==null)return name;
		return StringHelper.toUnderscoreName(name).toUpperCase();
	}
	/**
	 * Java类型，如 user_name -> UserName
	 * @param name
	 * @return
	 */
	public static String javaTypeName(String name){
		if(name==null)return name;
		return StringHelper.makeAllWordFirstLetterUpperCase(sqlTypeName(name));
	}
	/**
	 * Java
	 * @param name
	 * @return
	 */
	public static String javaVarName(String name){
		if(name==null)return name;
		return StringHelper.uncapitalize(javaTypeName(sqlTypeName(name)));
	}
	
	/**
	 * 转换：Long -> boolean 
	 */
	public static boolean convert2Boolean(Long value){
		return value!=null && value == 1;
	}
	
	private static final Random RANDOM = new Random();
	static Pattern three = Pattern.compile("(.*)\\((.*),(.*)\\)");
	static Pattern two = Pattern.compile("(.*)\\((.*)\\)");

	public static String removeCrlf(String str) {
		if (str == null)
			return null;
		return join(tokenizeToStringArray(str, "\t\n\r\f"), " ");
	}

	public static String removePrefix(String str, String prefix) {
		if (str == null)
			return null;
		if (str.startsWith(prefix))
			return str.substring(prefix.length());

		return str;
	}

	public static boolean isBlank(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	public static boolean isNotBlank(String str) {
		return (!(isBlank(str)));
	}


	public static boolean contains(String str, String[] keywords) {
		if (str == null)
			return false;
		if (keywords == null)
			throw new IllegalArgumentException("'keywords' must be not null");

		String[] arr$ = keywords;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			String keyword = arr$[i$];
			if (str.contains(keyword.toLowerCase()))
				return true;
		}

		return false;
	}

	public static String defaultIfEmpty(Object value, String defaultValue) {
		if ((value == null) || ("".equals(value)))
			return defaultValue;

		return value.toString();
	}

	private static String makeAllWordFirstLetterUpperCase(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for (int i = 0; i < strs.length; ++i) {
			if (preStr.length() == 1)
				result = result + strs[i];
			else
				result = result + capitalize(strs[i]);

			preStr = strs[i];
		}
		return result;
	}

	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (inString == null)
			return null;

		if ((oldPattern == null) || (newPattern == null)) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();

		int pos = 0;
		int index = inString.indexOf(oldPattern);

		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		return sbuf.toString();
	}

	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str,
			boolean capitalize) {
		if ((str == null) || (str.length() == 0))
			return str;

		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		} else
			buf.append(Character.toLowerCase(str.charAt(0)));

		buf.append(str.substring(1));
		return buf.toString();
	}

	public static String randomNumeric(int count) {
		return random(count, false, true);
	}

	public static String random(int count, boolean letters, boolean numbers) {
		return random(count, 0, 0, letters, numbers);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers) {
		return random(count, start, end, letters, numbers, null, RANDOM);
	}

	public static String random(int count, int start, int end, boolean letters,
			boolean numbers, char[] chars, Random random) {
		if (count == 0)
			return "";
		if (count < 0) {
			throw new IllegalArgumentException(
					"Requested random string length " + count
							+ " is less than 0.");
		}

		if ((start == 0) && (end == 0)) {
			end = 123;
			start = 32;
			if ((!(letters)) && (!(numbers))) {
				start = 0;
				end = 2147483647;
			}
		}

		char[] buffer = new char[count];
		int gap = end - start;

		while (count-- != 0) {
			char ch;
			if (chars == null)
				ch = (char) (random.nextInt(gap) + start);
			else
				ch = chars[(random.nextInt(gap) + start)];

			if (((letters) && (Character.isLetter(ch)))
					|| ((numbers) && (Character.isDigit(ch)))
					|| ((!(letters)) && (!(numbers)))) {
				if ((ch >= 56320) && (ch <= 57343))
					if (count == 0) {
						++count;
					} else {
						buffer[count] = ch;
						--count;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				else if ((ch >= 55296) && (ch <= 56191))
					if (count == 0) {
						++count;
					} else {
						buffer[count] = (char) (56320 + random.nextInt(128));
						--count;
						buffer[count] = ch;
					}
				else if ((ch >= 56192) && (ch <= 56319)) {
					++count;
				} else
					buffer[count] = ch;
			} else
				++count;
		}

		return new String(buffer);
	}

	private static String toUnderscoreName(String name) {
		if (name == null)
			return null;

		String filteredName = name;
		if ((filteredName.indexOf("_") >= 0)
				&& (filteredName.equals(filteredName.toUpperCase())))
			filteredName = filteredName.toLowerCase();

		if ((filteredName.indexOf("_") == -1)
				&& (filteredName.equals(filteredName.toUpperCase()))) {
			filteredName = filteredName.toLowerCase();
		}

		StringBuffer result = new StringBuffer();
		if ((filteredName != null) && (filteredName.length() > 0)) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); ++i) {
				String preChart = filteredName.substring(i - 1, i);
				String c = filteredName.substring(i, i + 1);
				if (c.equals("_")) {
					result.append("_");
				} else if (preChart.equals("_")) {
					result.append(c.toLowerCase());
				} else if (c.matches("\\d")) {
					result.append(c);
				} else if (c.equals(c.toUpperCase())) {
					result.append("_");
					result.append(c.toLowerCase());
				} else {
					result.append(c);
				}
			}
		}
		return result.toString();
	}

	public static String removeEndWiths(String inputString, String[] endWiths) {
		String[] arr$ = endWiths;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			String endWith = arr$[i$];
			if (inputString.endsWith(endWith))
				return inputString.substring(0,
						inputString.length() - endWith.length());
		}

		return inputString;
	}


	public static boolean substringMatch(CharSequence str, int index,
			CharSequence substring) {
		for (int j = 0; j < substring.length(); ++j) {
			int i = index + j;
			if ((i >= str.length()) || (str.charAt(i) != substring.charAt(j)))
				return false;
		}

		return true;
	}

	public static String[] tokenizeToStringArray(String str, String seperators) {
		if (str == null)
			return new String[0];
		StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
		List result = new ArrayList();

		while (tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return ((String[]) (String[]) result.toArray(new String[result.size()]));
	}

	public static String join(String[] array, String seperator) {
		if (array == null)
			return null;
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			result.append(array[i]);
			if (i != array.length - 1)
				result.append(seperator);
		}

		return result.toString();
	}

	public static int containsCount(String string, String keyword) {
		if (string == null)
			return 0;
		int count = 0;
		for (int i = 0; i < string.length(); ++i) {
			int indexOf = string.indexOf(keyword, i);
			if (indexOf < 0)
				break;

			++count;
			i = indexOf;
		}
		return count;
	}
}