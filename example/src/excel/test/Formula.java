package excel.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.junit.Test;

public class Formula {
	
	@Test
	public void formula1(){
		FileInputStream fis = null;
		try{
			fis = new FileInputStream("D:/workspace/metogo/example/src/excel/example.xls");
			Workbook wb = new HSSFWorkbook(fis); //or new XSSFWorkbook("c:/temp/test.xls")
			Sheet sheet = wb.getSheetAt(0);
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
	
			// suppose your formula is in B3
			CellReference cellReference1 = new CellReference("A1"); 
			Row row1 = sheet.getRow(cellReference1.getRow());
			Cell cell1 = row1.getCell(cellReference1.getCol()); 
			cell1.setCellValue(4);
			
			CellReference cellReference = new CellReference("B2"); 
			Row row = sheet.getRow(cellReference.getRow());
			Cell cell = row.getCell(cellReference.getCol()); 
	
			CellValue cellValue = evaluator.evaluate(cell);
	
			switch (cellValue.getCellType()) {
			    case Cell.CELL_TYPE_BOOLEAN:
			        System.out.println(cellValue.getBooleanValue());
			        break;
			    case Cell.CELL_TYPE_NUMERIC:
			        System.out.println(cellValue.getNumberValue());
			        break;
			    case Cell.CELL_TYPE_STRING:
			        System.out.println(cellValue.getStringValue());
			        break;
			    case Cell.CELL_TYPE_BLANK:
			        break;
			    case Cell.CELL_TYPE_ERROR:
			        break;
	
			    // CELL_TYPE_FORMULA will never happen
			    case Cell.CELL_TYPE_FORMULA: 
			        break;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
