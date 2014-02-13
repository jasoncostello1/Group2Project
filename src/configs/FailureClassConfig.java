package configs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import entity.FailureClass;

public class FailureClassConfig {
	private static List<Object> failureClasses = new ArrayList<>();
	
	public static void parseExcelData(XSSFSheet excelSheet){		
		Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
        	Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
        	parseCells(cellIterator);
        }
		
		addObjectsToDb();
		System.out.println(failureClasses.size() + " FailureClasses added to database.");
	}

	private static void parseCells(Iterator<Cell> cellIterator){
		int failureClass;
		String desc;
    	
		failureClass = (int) cellIterator.next().getNumericCellValue();
    	desc = cellIterator.next().getStringCellValue();
    	
    	createFailureClass(failureClass, desc);
	}
	
	private static void createFailureClass(int failureClass, String desc){
		failureClasses.add(new FailureClass(failureClass, desc));
	}
	
	private static void addObjectsToDb(){
		PersistenceUtil.persistMany(failureClasses);
	}

	public static int numberOfFailureClasses() {
		return failureClasses.size();
	}
}