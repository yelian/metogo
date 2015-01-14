package string;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class StringByteLength {

	public void length(String str, String charset){
		byte[] bytes = null;
		try {
			bytes = str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("|" + str + "|字符"+ charset +"下长度为：" + bytes.length);
	}
	
	@Test
	public void test(){
		length("12","utf-8");
		length("中国","utf-8");
		length("12","gbk");
		length("中国","gbk");
		length("12中国", "utf-8");
		length("12中国", "gbk");
	}
}
