package configs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import entity.UEType;

public class UETypeConfig {
	private static List<Object> ueTypes = new ArrayList<>();
	
	public static void parseExcelData(XSSFSheet excelSheet){		
		Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
            Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
            parseCells(cellIterator);
        }
		
		addObjectsToDb();
		System.out.println(ueTypes.size() + " UETypes added to database.");
	}

	private static void parseCells(Iterator<Cell> cellIterator) {
		int tac;
		String mName;
		String manu;
		String access;
		String model;
		String vName;
		String ueType;
		String os;
		String inputMode;
		
		Cell next;
		int nextType;
		
		tac = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_NUMERIC)
			mName = String.valueOf(next.getNumericCellValue());
		else if(nextType == Cell.CELL_TYPE_STRING)
			mName = next.getStringCellValue();
		else mName = "";
		
		manu = cellIterator.next().getStringCellValue();
		access = cellIterator.next().getStringCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_NUMERIC)
			model = String.valueOf(next.getNumericCellValue());
		else if(nextType == Cell.CELL_TYPE_STRING)
			model = next.getStringCellValue();
		else model = "";
		
		vName = cellIterator.next().getStringCellValue();
		ueType = cellIterator.next().getStringCellValue();
		os = cellIterator.next().getStringCellValue();
		inputMode = cellIterator.next().getStringCellValue();
		
		
		createUEType(tac, mName, manu, access, model, vName, ueType, os, inputMode);
	}
	
	private static void createUEType(int tac, String mName, String manu, String access, String model, String vName,
									 String ueType, String os, String inputMode){
		ueTypes.add(new UEType(tac, mName, manu, access, model, vName, ueType, os, inputMode));
	}
	
	private static void addObjectsToDb(){
		PersistenceUtil.persistMany(ueTypes);
	}

	public static int numberOfUETypes() {
		return ueTypes.size();
	}
}