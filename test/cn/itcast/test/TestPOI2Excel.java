package cn.itcast.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {
	@Test
	public void testWrite03Excle() throws IOException{
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//2、创建工作表
		HSSFSheet sheet = workbook.createSheet("hello world");//指定工作表名
		//3、创建行;创建第3行
		HSSFRow row = sheet.createRow(2);
		//4、创建单元格;创建第3行第3列
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello World");
		//输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("D:\\itcast\\测试.xls");
		//把excel输出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	@Test
	public void testRead03Excle() throws IOException{
		FileInputStream inputStream = new FileInputStream("D:\\itcast\\测试.xls");
		//1、读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		//2、读取第一个工作表
		HSSFSheet sheet = workbook.getSheetAt(0);//指定工作表名
		//3、读取行;创建第3行
		HSSFRow row = sheet.getRow(2);
		//4、读取单元格;读取第3行第3列
		HSSFCell cell = row.getCell(2);
		System.out.println("第3行第3列单元格的内容为:"+cell.getStringCellValue());
		
		
		workbook.close();
		inputStream.close();
	}
	@Test
	public void testWrite07Excle() throws IOException{
		//1、创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//2、创建工作表
		XSSFSheet sheet = workbook.createSheet("hello world");//指定工作表名
		//3、创建行;创建第3行
		XSSFRow row = sheet.createRow(2);
		//4、创建单元格;创建第3行第3列
		XSSFCell cell = row.createCell(2);
		cell.setCellValue("Hello World");
		//输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("D:\\itcast\\测试.xlsx");
		//把excel输出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	@Test
	public void testRead07Excle() throws IOException{
		FileInputStream inputStream = new FileInputStream("D:\\itcast\\测试.xlsx");
		//1、读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//2、读取第一个工作表
		XSSFSheet sheet = workbook.getSheetAt(0);//指定工作表名
		//3、读取行;创建第3行
		XSSFRow row = sheet.getRow(2);
		//4、读取单元格;读取第3行第3列
		XSSFCell cell = row.getCell(2);
		System.out.println("第3行第3列单元格的内容为:"+cell.getStringCellValue());
		
		
		workbook.close();
		inputStream.close();
	}
	@Test
	public void testRead03And07Excle() throws IOException{
		String fileName="D:\\itcast\\测试.xlsx";
		//正则表达式判断
		if(fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){//判断是否excel文档
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			FileInputStream inputStream = new FileInputStream(fileName);
			//1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
			//2、读取第一个工作表
			Sheet sheet = workbook.getSheetAt(0);//指定工作表名
			//3、读取行;创建第3行
			Row row = sheet.getRow(2);
			//4、读取单元格;读取第3行第3列
			Cell cell = row.getCell(2);
			System.out.println("第3行第3列单元格的内容为:"+cell.getStringCellValue());
			
			
			workbook.close();
			inputStream.close();
		}
	
		
	}
	@Test
	public void testExcleStyle() throws IOException{
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1创建合并单元格对象,合并第3行的第3列到第5列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2,2,2,4);//起始行号，结束行号，起始列号，结束列号
		//1.2创建单元格样式
		HSSFCellStyle style=workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.3创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints((short)16);;//设置字体大小
		//加载字体
		style.setFont(font);
		//单元格背景
		//设置背景填充模式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//设置填充背景色
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		//设置填充前景色
		style.setFillForegroundColor(HSSFColor.RED.index);
		//2、创建工作表
		HSSFSheet sheet = workbook.createSheet("Hello World");//指定工作表名
		//2.1加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		//3、创建行;创建第3行
		HSSFRow row = sheet.createRow(2);
		//4、创建单元格;创建第3行第3列
		HSSFCell cell = row.createCell(2);
		//加载样式
		cell.setCellStyle(style);
		cell.setCellValue("Hello World!");
		//输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("D:\\itcast\\测试.xls");
		//把excel输出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
