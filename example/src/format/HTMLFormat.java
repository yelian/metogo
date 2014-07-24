package format;

import java.util.ArrayList;
import java.util.List;

public class HTMLFormat {

	/*private static final char LEFT = '<';
	private static final char RIGHT = '<';
	private static final char GLAPS = '/';*/
	
	String miniHTML = "";
	boolean isInElement = true;
	boolean isStartElement = true;
	boolean isFirstElement = true;
	boolean isTextElementStart = true;
	List<String> tagNames = new ArrayList<String>(); 
	int loop = 0;
	
	int toPrintStartPosition = 0;
	char preChar = '~';
	
	public void format(){
		for(int x=0; x<miniHTML.length(); x++){
			char currChar = miniHTML.charAt(x);
			
			switch (currChar) {
				case '<' :
					isInElement = true;
					isStartElement = true;
					preChar = currChar;
					if(!isTextElementStart){
						printIndent(loop);
						for(; toPrintStartPosition<x; toPrintStartPosition++){
							System.out.print(miniHTML.charAt(toPrintStartPosition));
						}
						isTextElementStart = true;
						loop--;
					}
					toPrintStartPosition = x;
					break;
				case '>' :
					if(isStartElement){
						if(preChar == '/'){
							isStartElement = false;
						} else {
							if(isFirstElement){
								isFirstElement = false;
							} else {
								loop++;
							}
						}
					}
					printIndent(loop);
					for(; toPrintStartPosition<=x; toPrintStartPosition++){
						System.out.print(miniHTML.charAt(toPrintStartPosition));
					}
					if(!isStartElement){
						loop--;
					}
					preChar = currChar;
					isInElement = false;
					break;
				case '/':
					if(preChar == '<'){
						isStartElement = false;;
					}
					preChar = currChar;
					break;
				default : 
					if(!isInElement){
						if(isTextElementStart){
							loop++;
							toPrintStartPosition = x;
							isTextElementStart = false;
						}
					}
					preChar = currChar;
			};
		};	
	}
	
	
	public void printIndent(int loop){
		String out = "\n";
		for(int x=0; x<loop; x++){
			out += "\t";
		}
		System.out.print(out);
	}
	
	public static void main(String[] args) {
		HTMLFormat format = new HTMLFormat();
		format.miniHTML =  "<html><head><title>test</title></head><body bgcolor=\"transparent\"scroll=\"auto\"><div><p>yyyyyyyyyyyyyy</p></div></body></html>";
		format.format();
	}

}
