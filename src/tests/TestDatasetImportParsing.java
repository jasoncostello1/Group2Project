package tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import configs.ErrorEventConfig;
import configs.EventCauseConfig;
import configs.FailureClassConfig;
import configs.MCC_MNCConfig;
import configs.UETypeConfig;

public class TestDatasetImportParsing {

	private static final String EXCEL_FILE = "src/res/TestDataset.xlsx";
	private static XSSFWorkbook excelData;
	
	@BeforeClass
	public static void setUp() throws InvalidFormatException, IOException{
		OPCPackage pkg = OPCPackage.open(new File(EXCEL_FILE));
		excelData = new XSSFWorkbook(pkg);
		pkg.close();
	}
	
	@Test
	public void testErrorEvents() {
		ErrorEventConfig.parseExcelData(excelData.getSheetAt(0));
		assertEquals("There should be 80 ErrorEvents.", 80, ErrorEventConfig.numberOfErrorEvents());
		assertEquals("There should be 20 InvalidErrorEvents.", 20, ErrorEventConfig.numberOfInvalidErrorEvents());
	}
	
	@Test
	public void testEventCauses(){
		EventCauseConfig.parseExcelData(excelData.getSheetAt(1));
		assertEquals("There should be 80 EventCauses.", 80, EventCauseConfig.numberOfEventCauses());
	}
	
	@Test
	public void testFailureClasses(){
		FailureClassConfig.parseExcelData(excelData.getSheetAt(2));
		assertEquals("There should be 5 FailureClasses.", 5, FailureClassConfig.numberOfFailureClasses());
	}
	
	@Test
	public void testMCC_MNCs(){
		MCC_MNCConfig.parseExcelData(excelData.getSheetAt(4));
		assertEquals("There should be 41 MCC_MNCs.", 41, MCC_MNCConfig.numberOfMCC_MNCs());
	}
	
	@Test
	public void testUETypes(){
		UETypeConfig.parseExcelData(excelData.getSheetAt(3));
		assertEquals("There should be 99 UETypes.", 99, UETypeConfig.numberOfUETypes());		
	}
}
