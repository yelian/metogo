package poi.word;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hwpf.HWPFDocument;
import org.junit.Test;

public class ReadText {

	@Test
	public void read() throws FileNotFoundException, IOException {
		HWPFDocument doc = new HWPFDocument(new FileInputStream("D:/泰国南航7天5晚.doc"));
		System.out.println(doc.getText());
		/*TextPieceTable tbl = doc.getTextTable();

		// All ascii, so stored in one big lump
		assertEquals(1, tbl.getTextPieces().size());
		TextPiece tp = tbl.getTextPieces().get(0);

		assertEquals(0, tp.getStart());
		assertEquals(339, tp.getEnd());
		assertEquals(339, tp.characterLength());
		assertEquals(339, tp.bytesLength());
		assertTrue(tp.getStringBuilder().toString()
				.startsWith("This is a sample word document"));

		// Save and re-load
		HWPFDocument docB = saveAndReload(doc);
		tbl = docB.getTextTable();

		assertEquals(1, tbl.getTextPieces().size());
		tp = tbl.getTextPieces().get(0);

		assertEquals(0, tp.getStart());
		assertEquals(339, tp.getEnd());
		assertEquals(339, tp.characterLength());
		assertEquals(339, tp.bytesLength());
		assertTrue(tp.getStringBuilder().toString()
				.startsWith("This is a sample word document"));*/
	}
}
