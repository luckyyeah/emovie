package com.fh.util;

public class ExcelObject {
    private int cellType;
    private String cellValue;
    
    private boolean isHidden;
    
	public int getCellType() {
		return cellType;
	}
	public void setCellType(int cellType) {
		this.cellType = cellType;
	}
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	

   
	
}
