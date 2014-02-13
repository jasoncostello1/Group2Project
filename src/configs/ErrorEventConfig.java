package configs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import persistence.PersistenceUtil;

import com.google.common.collect.Lists;

import entity.ErrorEvent;
import entity.InvalidErrorEvent;

public class ErrorEventConfig {
	private static List<Object> errorEvents = new ArrayList<>();
	private static List<Object> invalidErrorEvents = new ArrayList<>();

	public static void parseExcelData(XSSFSheet excelSheet){		
        Iterator<Row> rowIterator = excelSheet.iterator();
        List<Row> rowList = Lists.newArrayList(rowIterator);
        
        for(int i = 1; i < rowList.size(); i++){ //Start at 1 to skip column names
            Iterator<Cell> cellIterator = rowList.get(i).cellIterator();
            parseCells(cellIterator);	
        } 
		
		addObjectsToDb();
		System.out.println(errorEvents.size() + " ErrorEvents added to database.");
		System.out.println(invalidErrorEvents.size() + " ErrorEvents removed due to inconsistencies."); //Break down inconsistencies into eventId/causeCode/date/MCC?
	}

	private static void parseCells(Iterator<Cell> cellIterator) {
		Date date;
		int eventId;
		int ueType;
		int market;
		int operator;
		int cellId;
		int duration;
		int failureClass = 0;
		int causeCode = 0;
		String invalidFailureClass = "";
		String invalidCauseCode = "";
		String neVersion;
		long imsi;
		long hier3_id;
		long hier32_id;
		long hier321_id;
		Cell next;
		int nextType;
		
		boolean valid = true;
		
		date = cellIterator.next().getDateCellValue();
		
		//Need additional datasets to confirm what "invalid" date looks like
			//If none, do general conversion to string and check then
//		if(!isDateValid(date)
//			valid = false;
		
		eventId = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_STRING){
			invalidFailureClass = next.getStringCellValue();
			valid = false;
		}
		else failureClass = (int) next.getNumericCellValue();
		
		ueType = (int) cellIterator.next().getNumericCellValue();
		market = (int) cellIterator.next().getNumericCellValue();
		operator = (int) cellIterator.next().getNumericCellValue();
		cellId = (int) cellIterator.next().getNumericCellValue();
		duration = (int) cellIterator.next().getNumericCellValue();
		
		next = cellIterator.next();
		nextType = next.getCellType();
		
		if(nextType == Cell.CELL_TYPE_STRING){
			invalidCauseCode = next.getStringCellValue();
			valid = false;
		}
		else causeCode = (int) next.getNumericCellValue();
		

		neVersion = cellIterator.next().getStringCellValue();
		imsi = (long) cellIterator.next().getNumericCellValue();
		hier3_id = (long) cellIterator.next().getNumericCellValue();
		hier32_id = (long) cellIterator.next().getNumericCellValue();
		hier321_id = (long) cellIterator.next().getNumericCellValue();
		
		if(valid)
			createErrorEvents(date, eventId, failureClass, ueType, market, operator, cellId, duration, causeCode, neVersion, imsi, hier3_id, hier32_id, hier321_id);
		else createInvalidErrorEvents(date, eventId, invalidFailureClass, ueType, market, operator, cellId, duration, invalidCauseCode, neVersion, imsi, hier3_id, hier32_id, hier321_id);
	}

	private static void createErrorEvents(Date date, int eventId, int failureClass, int ueType, int market, int operator,
								   int cellId, int duration, int causeCode, String neVersion,
								   long imsi, long hier3_id, long hier32_id, long hier321_id){
		errorEvents.add(new ErrorEvent(date, eventId, failureClass, ueType, market, operator, cellId, duration, causeCode,
									   neVersion, imsi, hier3_id, hier32_id, hier321_id));
	}
	
	private static void createInvalidErrorEvents(Date date, int eventId, String failureClass, int ueType, int market, int operator,
			   									 int cellId, int duration, String causeCode, String neVersion,
			   									 long imsi, long hier3_id, long hier32_id, long hier321_id){
		invalidErrorEvents.add(new InvalidErrorEvent(date, eventId, failureClass, ueType, market, operator, cellId, duration, causeCode,
					   								 neVersion, imsi, hier3_id, hier32_id, hier321_id));
	}
	
	private static void addObjectsToDb(){
		PersistenceUtil.persistMany(errorEvents);
		PersistenceUtil.persistMany(invalidErrorEvents);
	}
	
	public static int numberOfErrorEvents(){
		return errorEvents.size();
	}
	
	public static int numberOfInvalidErrorEvents(){
		return invalidErrorEvents.size();
	}
}