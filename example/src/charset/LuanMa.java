package charset;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class LuanMa {
	
	
	private static final String STR = "张三";
	
	@Test
	public void testCharset(){
		try {
			String s1 = new String(STR.getBytes("GBK"), "utf-8");
			System.out.println(new String(s1.getBytes("utf-8"), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
