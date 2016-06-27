package com.fh.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;


/**
* 导入到EXCEL
* 类名称：ObjectExcelView.java
* 类描述： 
* @author FH
* 作者单位： 
* 联系方式：
* @version 1.0
 */
public class ObjectExcelExport extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String filename =(String)model.get("filename");
		if(filename==null){
		    filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		} else {
			filename+=Tools.date2Str(date, "yyyyMMddHHmmss");
		}
		HSSFSheet sheet;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(filename, "UTF-8")+".xls");
		sheet = workbook.createSheet("sheet1");
		
		List<String> titles = (List<String>) model.get("titles");
		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		headerFont.setColor(HSSFColor.WHITE.index);
		headerFont.setFontHeightInPoints((short)10);
		headerStyle.setFont(headerFont);
		headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headerStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headerStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headerStyle.setRightBorderColor(HSSFColor.BLACK.index);
		headerStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
		headerStyle.setTopBorderColor(HSSFColor.BLACK.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(HSSFColor.DARK_RED.index);	
		
		short width = 9,height=25*15;
		//sheet.setDefaultColumnWidth(width);
		for(int i=0; i<len; i++){ //设置标题
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
			
		}
		String hiddenColumns= (String)model.get(Const.HIDDEN_COLUMN);
		try{
			//增加隐藏列功能
			if(hiddenColumns !=null ){
				String []arrhiddenColumns =hiddenColumns.split(",");
				for(int i=0;i<arrhiddenColumns.length;i++){
					sheet.setColumnHidden(Integer.parseInt(arrhiddenColumns[i])-1, true);
				}
			}
		}catch(Exception ex){
			
		}
		sheet.getRow(0).setHeight(height);
		
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		contentStyle.setRightBorderColor(HSSFColor.BLACK.index);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(HSSFColor.BLACK.index);
		List<PageData> varList = (List<PageData>) model.get("varList");
		int varCount = varList.size();
		for(int i=0; i<varCount; i++){
			PageData vpd = varList.get(i);
			for(int j=0;j<len;j++){
				
				ExcelObject excelObject =(ExcelObject)vpd.get("var"+(j+1));
				//String varstr = vpd.getString("var"+(j+1)) != null ? vpd.getString("var"+(j+1)) : "";
				
				cell = getCell(sheet, i+1, j);
				cell.setCellStyle(contentStyle);
				if(excelObject!=null &&  excelObject.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
					setNumber(cell,excelObject.getCellValue());
				} else if(excelObject!=null &&   excelObject.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
					setFormula(cell,excelObject.getCellValue());
				} else if(excelObject!=null) {
					setText(cell,excelObject.getCellValue());
				}
			}
			
		}
		sheet.setForceFormulaRecalculation(true);
	}
	protected void setNumber(HSSFCell cell, String text) {
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(Tools.str2Number(text));
		
	}
	protected void setFormula(HSSFCell cell, String formula) {
		cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(formula);
		
	}
}
