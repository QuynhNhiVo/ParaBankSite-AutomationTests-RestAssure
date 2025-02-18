package Website.ParaBank.pages;

import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class AccountPage extends CommonPage {

    private String title = "ParaBank | Open Account";

    private By selectType = By.xpath("//select[@id='type']");
    private By ButtonCreate = By.xpath("(//div)[9]//input");
//    private By ButtonCreate = By.xpath("//input[@value='Open New Account']");
    private By messageSuccess = By.xpath("//h1[normalize-space()='Account Opened!']");
    private String textSuccess = "Account Opened!";

    private By AmountAccount = By.xpath("//a[@id='newAccountId']");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");
//    private By selectType = By.xpath("");

    public AccountPage verifyAccountPage(int row){
        waitForPageLoad();
        verifyEqual(getTitle(), title);
        verifyContain(getURL(), "account");
        return this;
    }

    public AccountPage openChecking(){
        chooseOptions(selectType, "value", "0");
        actionClick(ButtonCreate);
        sleep(5);
        return this;
    }

    public AccountPage openSaving(){
        chooseOptions(selectType, "value", "1");
        actionClick(ButtonCreate);
        return this;
    }

    public AccountPage verifyCreateSuccess(){
        waitForPageLoad(2);
        verifyContain(getText(messageSuccess), textSuccess);
        getText(AmountAccount);
        sleep(2);
        return this;
    }
}
