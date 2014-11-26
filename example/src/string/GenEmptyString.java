package string;

import org.junit.Test;

public class GenEmptyString {

	public String gen(int length){
		final char[] c = new char[length];
		String s = String.valueOf(c);
		System.out.println("|" + s + "|");
		return s;
	}
	
	@Test
	public void test(){
		gen(10);
		gen(11);
	}
}
