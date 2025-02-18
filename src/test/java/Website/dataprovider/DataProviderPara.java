package Website.dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import utils.LogUtils;

public class DataProviderPara {

    public Object[][] dataLogin(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("");
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.EXCEL_PARA, "Register", 4, 4);
        return data;
    }
}
