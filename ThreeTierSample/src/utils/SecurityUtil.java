package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.codec.digest.DigestUtils;



public class SecurityUtil {
	public static String md5Encode(byte[] input) {
		return DigestUtils.md5Hex(input);
	}
	public static String sha256Encode(byte[] input) {
		return DigestUtils.sha256Hex(input);
	}
	//base64加密操作
	public static String base64Encode(byte[] input) {
		String result=null;
		try {
			Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method =clazz.getMethod("encode", byte[].class);
			result=(String)method.invoke(null, input);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//base64解密操作
	public static byte[] base64Decode(String input) {
		
		byte[] result = null;
		
		try {
			Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
			Method method=clazz.getMethod("decode", String.class);
			result = (byte[])method.invoke(null,input);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str="hello";		
//		
//		String str1=md5Encode(str.getBytes());
//		System.out.println(str+"通过md5加密后的16进制串数据为："+str1);
//		
//		String str256="helloword";		
//		//字符串->转换成 16进制串
//		String str2=sha256Encode(str256.getBytes());
//		System.out.println(str256+"通过sha256加密后16进制串的数据为："+str2);
//		
		String str3="hello中文你好";
		String str4=base64Encode(str3.getBytes());//"hello";
		System.out.println(str3+"通过base64Encode加密后的数据为："+str4);
		
		byte[] strByte=base64Decode(str4);
		String str5=new String(strByte);
		System.out.print(str4+"是通过base64Encode加密后；");
		System.out.println("再通过base64Decode解密后的数据为："+str5);
		

	}

}
