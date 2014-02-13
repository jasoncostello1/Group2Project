package main;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import persistence.PersistenceComplexQueries;
import persistence.PersistenceSimpleQueries;
import persistence.PersistenceUtil;
import configs.ErrorEventConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;

public class Driver {
	
	private static final String EXCEL_FILE = "src/res/Dataset.xlsx";
	private static XSSFWorkbook excelData;
	public static boolean liveDatabase = false;
	
	public static void main(String[] args){
		long startTime = System.nanoTime();
		loadExcelFile(EXCEL_FILE);
		generateDatabase();
		System.out.println("Time taken: " + String.format("%.2f", (double) (((System.nanoTime()-startTime)/1000)/1000)) + "seconds");
		runSimpleQueries();
		runHighlyAdvancedExtremeQueries();
//		PersistenceUtil.dropTables();
//		PersistenceUtil.truncateTables();
	}
	
	private static void runHighlyAdvancedExtremeQueries(){
//		PersistenceComplexQueries.findEventIdAndCauseCodeByIMSI(344930000000011L);
		PersistenceComplexQueries.findEventIdAndCauseCodeByIMSIGroupByEventId(344930000000011L);
		PersistenceComplexQueries.findEventIdAndCauseCodeByIMSIGroupByCauseCode(344930000000011L);
//		PersistenceComplexQueries.findCauseCodeAndCountry();
		PersistenceComplexQueries.findCauseCodeAndCountryGroupByCountry();
	}

	
	private static void runSimpleQueries() {
		//"findAlls" currently only return how many were found - if needed, we could add a print out of all relevant entries
		//All others return a single result - again, if we need to, we can print all results instead
		
		/**EE queries*/
		System.out.println(PersistenceSimpleQueries.findAllErrorEvents().size() + " Error Events found!");
		PersistenceSimpleQueries.findErrorEventByEventId(4098).print();
		System.out.println();
		
		
		/**EC queries*/
		System.out.println(PersistenceSimpleQueries.findAllEventCauses().size() + " Event-Causes found!");
		PersistenceSimpleQueries.findEventCauseByEventID(4098).print();
		PersistenceSimpleQueries.findEventCauseByCauseCode(11).print();
		System.out.println();
		
		
		/**FC queries*/
		System.out.println(PersistenceSimpleQueries.findAllFailureClasses().size() + " Failure Classes found!");
		PersistenceSimpleQueries.findFailureClassByFailureClass(1).print();
		System.out.println();
		
		
		/**MCC_MNC queries*/
		System.out.println(PersistenceSimpleQueries.findAllMCC_MNCs().size() + " MCC_MNCs found!");
		PersistenceSimpleQueries.findMCC_MNCByMCC(240).print();
		PersistenceSimpleQueries.findMCC_MNCByMNC(1).print();
		System.out.println();
		
		
		/**UEType queries*/
		System.out.println(PersistenceSimpleQueries.findAllUETypes().size() + " UE Types found!");
		PersistenceSimpleQueries.findUETypeByTAC(100100).print();
		System.out.println();
		
		/**InvalidErrorEvents*/
		System.out.println(PersistenceSimpleQueries.findAllInvalidErrorEvents().size() + " InvalidErrorEvents found!");
		PersistenceSimpleQueries.findInvalidErrorEventByEventId(4099).print();
	}
	
	private static void loadExcelFile(String fileLocation){
		try{
			OPCPackage pkg = OPCPackage.open(new File(fileLocation));
			excelData = new XSSFWorkbook(pkg);
			pkg.close();
		} catch (IOException | InvalidFormatException e){
			System.out.println("Can't load database!");
			System.exit(-1);
		}
	}

	private static void generateDatabase(){
		PersistenceUtil.setDatabaseState(true);
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0));
		EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		MCC_MNCConfig.parseExcelData(excelData.getSheetAt(4));
		System.out.println("\n>>Dataset imported!");
	}
}