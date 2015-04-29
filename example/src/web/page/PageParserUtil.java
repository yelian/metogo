package web.page;


import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

import com.gargoylesoftware.htmlunit.javascript.host.Node;
import com.gargoylesoftware.htmlunit.javascript.host.NodeFilter;

public class PageParserUtil {
	
	public void getTargetNode(String url) throws ParserException{
		Parser parser = new Parser(url);
		new Node();
		new NodeFilter().acceptNode(new Node().setAttributes(index, attributes));
		NodeIterator
		parser.extractAllNodesThatMatch();
	}
}
