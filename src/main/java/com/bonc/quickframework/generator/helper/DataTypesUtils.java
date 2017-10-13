package com.bonc.quickframework.generator.helper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTypesUtils
{
  private static final Map<String, String> preferredAsTypeForJavaType = new HashMap();
  private static final Map<String, String> shotType = new HashMap();
	
  public static String getPreferredAsType(String javaType)
  {
    String result = (String)preferredAsTypeForJavaType.get(javaType);
    if (result == null)
      result = javaType;

    return result;
  }
  public static String getFullType(String javaType){
	  String result = (String)shotType.get(javaType.toLowerCase());
	    if (result == null)
	      result = StringHelper.javaTypeName(javaType);
	    return result;
  }
  public static void main(String[] args)
  {
    String bb = getPreferredAsType(List.class.getName());
    System.out.println(bb);
  }
	static{
		shotType.put("byte", "java.lang.Byte");
		shotType.put("short", "java.lang.Short");
		shotType.put("int", "java.lang.Integer");
		shotType.put("long", "java.lang.Long");
		shotType.put("float", "java.lang.Float");
		shotType.put("double", "java.lang.Double");
		shotType.put("char", "java.lang.Character");
		shotType.put("boolean", "java.lang.Boolean");
		
		shotType.put("integer", "java.lang.Integer");
		shotType.put("character", "java.lang.Character");
		
		shotType.put("string", "java.lang.String");
		shotType.put("date", "java.util.Date");
		shotType.put("list", "java.util.List");
		
	}
  static
  {
    preferredAsTypeForJavaType.put("Short", "Number");
    preferredAsTypeForJavaType.put("java.lang.Short", "Number");
    preferredAsTypeForJavaType.put("short", "Number");

    preferredAsTypeForJavaType.put("Integer", "Number");
    preferredAsTypeForJavaType.put("java.lang.Integer", "Number");
    preferredAsTypeForJavaType.put("int", "Number");

    preferredAsTypeForJavaType.put("Long", "Number");
    preferredAsTypeForJavaType.put("java.lang.Long", "Number");
    preferredAsTypeForJavaType.put("long", "Number");

    preferredAsTypeForJavaType.put("Float", "Number");
    preferredAsTypeForJavaType.put("java.lang.Float", "Number");
    preferredAsTypeForJavaType.put("float", "Number");

    preferredAsTypeForJavaType.put("Double", "Number");
    preferredAsTypeForJavaType.put("java.lang.Double", "Number");
    preferredAsTypeForJavaType.put("double", "Number");

    preferredAsTypeForJavaType.put("Byte", "Number");
    preferredAsTypeForJavaType.put("java.lang.Byte", "Number");
    preferredAsTypeForJavaType.put("byte", "Number");

    preferredAsTypeForJavaType.put("java.math.BigDecimal", "Number");

    preferredAsTypeForJavaType.put("Boolean", "Boolean");
    preferredAsTypeForJavaType.put("java.lang.Boolean", "Boolean");
    preferredAsTypeForJavaType.put("boolen", "Boolean");

    preferredAsTypeForJavaType.put("char", "String");
    preferredAsTypeForJavaType.put("char[]", "String");
    preferredAsTypeForJavaType.put("java.lang.String", "String");
    preferredAsTypeForJavaType.put("java.sql.Clob", "String");

    preferredAsTypeForJavaType.put("byte[]", "flash.utils.ByteArray");
    preferredAsTypeForJavaType.put("java.sql.Blob", "flash.utils.ByteArray");
    preferredAsTypeForJavaType.put("java.sql.Array", "Array");
    preferredAsTypeForJavaType.put("java.lang.reflect.Array", "Array");
    preferredAsTypeForJavaType.put("java.util.Collection", "mx.collections.ArrayCollection");
    preferredAsTypeForJavaType.put("java.util.List", "mx.collections.ArrayCollection");
    preferredAsTypeForJavaType.put("java.util.ArrayList", "mx.collections.ArrayCollection");
    preferredAsTypeForJavaType.put("java.util.ArrayList", "mx.collections.ArrayCollection");

    preferredAsTypeForJavaType.put("java.util.Set", "Object");
    preferredAsTypeForJavaType.put("java.util.HashSet", "Object");
    preferredAsTypeForJavaType.put("java.util.Map", "Object");
    preferredAsTypeForJavaType.put("java.util.HashMap", "Object");

    preferredAsTypeForJavaType.put("java.sql.Date", "Date");
    preferredAsTypeForJavaType.put("java.sql.Time", "Date");
    preferredAsTypeForJavaType.put("java.util.Date", "Date");
    preferredAsTypeForJavaType.put("java.sql.Timestamp", "Date");
  }
}
