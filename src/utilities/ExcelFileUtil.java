package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	// CREATING CONSTRUCTOR FOR READING EXCEL PATH
	public ExcelFileUtil(String excelpath) throws Throwable

	{
		FileInputStream fi= new FileInputStream(excelpath);
		wb= new XSSFWorkbook(fi);
	}
	// COUNT NOOF ROWS IN ASHEET
	public int rowCount(String Shetname)
	{
		return wb.getSheet(Shetname).getLastRowNum();

	}
	// COUNT NO OF CELL IN A ROW
	public int cellCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	// READ CELL DATA
	public String getCelldata(String sheetName, int row, int column)
	{
		String data="";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
		}
		else
		{
			data =wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;	
	}
	// GET CELL DATA
	public void setCellData(String sheetName, int row, int column, String status, String writeexcel) throws Throwable
	{
		// GET SHEET FROM WORKBOOK
		XSSFSheet ws = wb.getSheet(sheetName);
		//  GET ROW FROM SHEET
		XSSFRow rowNum = ws.getRow(row);
		// CREATE CELL
		XSSFCell cell= rowNum.createCell(column);
		// WRITE YOUR STATUS
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style=wb.createCellStyle(); 
			XSSFFont font= wb.createFont();
			// COLOR TEXT
			font.setColor(IndexedColors.BRIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);

		}
		else if(status.equalsIgnoreCase("fail"))
		{

			XSSFCellStyle style=wb.createCellStyle(); 
			XSSFFont font= wb.createFont();
			// COLOR TEXT
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);


		}
		else if(status.equalsIgnoreCase("Blocked"))
		{

			XSSFCellStyle style=wb.createCellStyle(); 
			XSSFFont font= wb.createFont();
			// COLOR TEXT
			font.setColor(IndexedColors.DARK_BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		
		
		
		FileOutputStream fo= new FileOutputStream(writeexcel);
		wb.write(fo);
	}
	public static void main(String[] args)throws Throwable {
		ExcelFileUtil xl= new ExcelFileUtil("D://Swapna.xlsx");
		int rc=xl.rowCount("Login");
		int cc= xl.cellCount("Login");
		System.out.println(rc+"   "+cc);
		for (int i=1; i<=rc; i++)
		{
			String user= xl.getCelldata("Login", i, 0);
			String pass=xl.getCelldata("Login", i, 1);
			System.out.println(user+"    "+ pass);
			xl.setCellData("Login", i, 2, "Fail", "D://Results.xlsx");
		}


	}

}
