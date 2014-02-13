package configs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import entity.MCC_MNC;

public class MCC_MNCConfig {
	private static List<Object> mcc_mncs = new ArrayList<>();
	
	public static void parseExcelData(XSSFSheet excelSheet){
        Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
	        
        for(int i = 1; i < rowList.size(); i++){
            Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
            parseCells(cellIterator);
        }
		
		addObjectsToDb();
		System.out.println(mcc_mncs.size() + " MCC_MNCs added to database.");
	}

	private static void parseCells(Iterator<Cell> cellIterator) {
		int mcc;
		int mnc;
		String country;
		String operator;
		
		mcc = (int) cellIterator.next().getNumericCellValue();
		mnc = (int) cellIterator.next().getNumericCellValue();
		country = cellIterator.next().getStringCellValue();
		operator = cellIterator.next().getStringCellValue();
		
		createMCC_MNC(mcc, mnc, country, operator);
	}
	
	private static void createMCC_MNC(int mcc, int mnc, String country, String operator){
		mcc_mncs.add(new MCC_MNC(mcc, mnc, country, operator));
	}
	
	private static void addObjectsToDb(){
		PersistenceUtil.persistMany(mcc_mncs);
	}

	public static int numberOfMCC_MNCs() {
		return mcc_mncs.size();
	}
}