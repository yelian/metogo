package string;

import org.junit.Test;

public class CharOperation {

	
	public void operation(String code){
		char ch = 'A';
		int codeInt = Integer.parseInt(code);
		if(codeInt > 0 && codeInt < 20){
			ch = (char) (ch + codeInt - 1);
		}
		System.out.println(ch + "");
	}
	
	@Test
	public void test(){
		operation("4");
	}
}
