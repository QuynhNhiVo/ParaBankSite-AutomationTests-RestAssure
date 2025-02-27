package constants;

import helpers.ExcelHelpers;
import helpers.PropertiesHelpers;

public class ConfigData {
    public static String URI = "";
//    private static final ExcelHelpers excelHelpers = new ExcelHelpers();
//
////    public static String URI;
//    private ConfigData(ExcelHelpers excelHelpers) {
//        this.excelHelpers.setExcelFile(EXCEL_PARA, "Uri");
//    }
//    public static String getURI(){
//        return URI = excelHelpers.getCellData(0, 1);
//    }

    public static String EXTENT_REPORT = PropertiesHelpers.getValue("EXTENT_REPORT");
    public static String SCREENSHOT_PATH = PropertiesHelpers.getValue("SCREENSHOT_PATH");
    public static String RECORD_PATH = PropertiesHelpers.getValue("RECORD_PATH");
    public static String SCREENSHOT = PropertiesHelpers.getValue("SCREENSHOT");
    public static String RECORD = PropertiesHelpers.getValue("RECORD");
    public static String FULLSCREEN = PropertiesHelpers.getValue("FULLSCREEN");
    public static String HEADLESS = PropertiesHelpers.getValue("HEADLESS");
    public static String EXCEL_PARA = PropertiesHelpers.getValue("EXCEL_PARA");
    public static int TIMEOUT = Integer.parseInt(PropertiesHelpers.getValue("TIMEOUT"));
    public static int SLEEP_TIME = Integer.parseInt(PropertiesHelpers.getValue("SLEEP_TIME"));
    public static int LOAD_TIME = Integer.parseInt(PropertiesHelpers.getValue("LOAD_TIME"));

}
