package Website.ParaBank.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class OverviewPage extends CommonPage{

    private String title = "ParaBank | Accounts Overview";
    private By headerOverview = By.xpath("//p[@class='smallText']");
    private By welcomeUser = By.xpath("//div[@id='rightPanel']//h1");


    private final ExcelHelpers excelRegister;

    public OverviewPage(){
        this.excelRegister = new ExcelHelpers();
        this.excelRegister.setExcelFile(ConfigData.EXCEL_PARA, "Register");
    }

    private String getWelcomeName(int row){
        return "Welcome " + excelRegister.getCellData("First name", row) + " "
                + excelRegister.getCellData("Last name", row);
    }


    public OverviewPage verifyLoginSuccess(int row){
        waitForPageLoad();
        verifyEqual(getTitle(), title);
        verifyContain(getURL(), "overview");
        verifyEqual(getText(headerOverview), getWelcomeName(row));
        return this;
    }

}
