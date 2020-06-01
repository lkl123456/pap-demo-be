package com.yonyou.base.support.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.hutool.core.date.DateUtil;

public class ExcelUtil {

	@SuppressWarnings(value = { "all" })
	public static void downloadExcelList(List<BaseVo> list, HttpServletResponse response) throws Exception {
		String fileName = "XXXXXXXXXX";
		fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("统计");
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell = row1.createCell(0);
		Date date = new Date();
		if (list != null && list.size() > 0) {
			String time = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
			// 设置单元格内容
			cell.setCellValue("导出时间:" + time);
		} else {
			// 设置单元格内容
			cell.setCellValue("统计");
		}
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		// 设置行高
		row1.setHeight((short) 1000);
		// 设置列宽
		sheet.setColumnWidth(0, 9000);
		sheet.setColumnWidth(1, 9000);
		sheet.setColumnWidth(2, 9000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 5000);
		// 设置字体
		HSSFFont font = wb.createFont();
		// font.setColor(HSSFFont.COLOR_RED); //颜色
		font.setBold(true); // 加粗
		font.setFontHeightInPoints((short) 20); // 字体大小
		// 设置样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setAlignment(HorizontalAlignment.CENTER); // 左右居中
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);;// 上下居中
		cell.setCellStyle(cellStyle);
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("第一列");
		row2.createCell(1).setCellValue("第二列");
		row2.createCell(2).setCellValue("第三列");
		row2.createCell(3).setCellValue("第四列");
		row2.createCell(4).setCellValue("第五列");
		if (list != null && list.size() > 0) {
			int j = 2;
			for (BaseVo baseVo : list) {
				HSSFRow row3 = sheet.createRow(j);
				row3.createCell(0).setCellValue(baseVo.getId());
				row3.createCell(1).setCellValue(baseVo.getName());
				row3.createCell(2).setCellValue(baseVo.getCode());
				row3.createCell(3).setCellValue(baseVo.getContent());
				row3.createCell(4).setCellValue(baseVo.getDate());
				j++;
			}
		}
		OutputStream out = response.getOutputStream();
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		response.setContentType("application/msexcel;charset=UTF-8");
		wb.write(out);
		out.flush();
		out.close();
		System.out.println("export CourseThemeList success!!!");
	}

	/**
	 * excle 读取用户返回User集合，兼容2003-2007-2010excel
	 * 
	 * @param type
	 *            :目标实体类型
	 * @param path
	 *            ：excel 文件
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 */
	public static List<BaseVo> readExcelToEntity(InputStream inputStream) throws EncryptedDocumentException, InvalidFormatException, IOException {
		Workbook workbook = WorkbookFactory.create(inputStream);
		if (workbook == null) {
			throw new RuntimeException("Excel workbook is null...");
		}
		BaseVo bv = null;
		List<BaseVo> baseVoList = new ArrayList<>();
		System.out.println("sheet个数:" + workbook.getNumberOfSheets());
		// 循环excel sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet sheet = workbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			System.out.println("第" + (numSheet + 1) + "个sheet的行数：" + sheet.getLastRowNum());
			// 循环row，从第二行开始，第一行是表头
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					bv = new BaseVo();
					Cell cell_0 = row.getCell(0);
					Cell cell_1 = row.getCell(1);
					Cell cell_2 = row.getCell(2);
					Cell cell_3 = row.getCell(3);

					bv.setId(cell_0==null?"":cell_0.getStringCellValue());
					bv.setName(cell_1==null?"":cell_1.getStringCellValue());
					bv.setCode(cell_2==null?"":cell_2.getStringCellValue());
					bv.setContent(cell_3==null?"":cell_3.getStringCellValue());
					baseVoList.add(bv);
				}
			}

		}
		return baseVoList;
	}
	public static void main(String[] args) throws Exception{
		InputStream inputStream = new FileInputStream(new File("D://user.xlsx"));
		List<BaseVo> baseVos= readExcelToEntity(inputStream);
		for (BaseVo baseVo : baseVos) {
			System.out.println(baseVo.getId());
			System.out.println(baseVo.getName());
		}
		System.out.println(baseVos.size());
	}

}
