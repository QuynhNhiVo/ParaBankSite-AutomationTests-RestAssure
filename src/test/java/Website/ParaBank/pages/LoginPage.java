package Website.ParaBank.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;

import java.util.Hashtable;

import static keywords.WebUI.*;

public class LoginPage extends CommonPage{

    private String titleLogin = "ParaBank | Welcome | Online Banking";
    private String titleRegister = "ParaBank | Register for Free Online Account Access";

    private By ButtonLogin = By.xpath("//input[@value='Log In']");
    private By Username = By.xpath("//input[@name='username']");
    private By Password = By.xpath("//input[@name='password']");
    private By register = By.xpath("//a[normalize-space()='Register']");
    private By FirstName = By.xpath("//input[@id='customer.firstName']");
    private By LastName = By.xpath("//input[@id='customer.lastName']");
    private By Address = By.xpath("//input[@id='customer.address.street']");
    private By City = By.xpath("//input[@id='customer.address.city']");
    private By State = By.xpath("//input[@id='customer.address.state']");
    private By ZipCode = By.xpath("//input[@id='customer.address.zipCode']");
    private By Phone = By.xpath("//input[@id='customer.phoneNumber']");
    private By SSN = By.xpath("//input[@id='customer.ssn']");
    private By Confirm = By.xpath("//input[@id='repeatedPassword']");
    private By RegUsername = By.xpath("//input[@id='customer.username']");
    private By RegPassword = By.xpath("//input[@id='customer.password']");
    private By ButtonRegister = By.xpath("//input[@value='Register']");

    private By HeaderSignUp = By.xpath("//h1[normalize-space()='Signing up is easy!']");
    private String TextHeaderSignup = "Signing up is easy!";
    private By formRegister = By.xpath("//form[@id='customerForm']");

    private By headerFullName = By.xpath("//p[@class='smallText']");
    private By welcomeUser = By.xpath("//div[@id='rightPanel']//h1");
    private By messageWelcome = By.xpath("//div[@id='rightPanel']//p");
    private String textMessWc = "Your account was created successfully. You are now logged in.";
    private By messageUserExist = By.xpath("//span[@id='customer.username.errors']");
    private String textUserExist = "This username already exists.";

    private By messageError = By.xpath("//tr//td[@width='50%']//span");

    private final ExcelHelpers excelHelpers;
    private final ExcelHelpers excelRegister;

    private void gotoWeb() {
        openURL(excelHelpers.getCellData(0,0));
        verifyContain(getURL(), "index");
        verifyEqual(getTitle(), titleLogin);
    }

    public LoginPage() {
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.EXCEL_PARA, "Uri");
        this.excelRegister = new ExcelHelpers();
        this.excelRegister.setExcelFile(ConfigData.EXCEL_PARA, "Register");
    }

    public LoginPage clickRegister(){
        clickElement(ButtonRegister);
        return this;
    }


    //Specific Data
    private String getWelcomeName(int row){
        return "Welcome " + excelRegister.getCellData("First name", row) + " "
                + excelRegister.getCellData("Last name", row);
    }
    private String getWelcomeUser(int row){
        return "Welcome " + excelRegister.getCellData("Username", row);
    }

    public LoginPage registerSuccess(int row) {
        gotoWeb();
        clickElement(register);
        verifyContain(getURL(), "register");
        verifyEqual(getTitle(), titleRegister);
        verifyVisible(formRegister);
        setText(FirstName, excelRegister.getCellData("First name", row));
        setText(LastName, excelRegister.getCellData("Last name", row));
        setText(Address, excelRegister.getCellData("Address", row));
        setText(City, excelRegister.getCellData("City", row));
        setText(State, excelRegister.getCellData("State", row));
        setText(ZipCode, excelRegister.getCellData("Zip Code", row));
        setText(Phone, excelRegister.getCellData("Phone", row));
        setText(SSN, excelRegister.getCellData("SSN", row));
        setText(RegUsername, excelRegister.getCellData("Username", row));
        setText(RegPassword, excelRegister.getCellData("Password", row));
        setText(Confirm, excelRegister.getCellData("Password", row));
        return this;
    }

    public LoginPage verifyNewAccount(int row){
        waitForVisible(headerFullName);
        verifyEqual(getText(headerFullName), getWelcomeName(row));
        verifyEqual(getText(welcomeUser), getWelcomeUser(row));
        verifyEqual(getText(messageWelcome), textMessWc);
        return this;
    }

    public LoginPage verifyExistAccount(){
        verifyFromError("This username already exists.");
        return this;
    }

    public LoginPage verifyFieldsRequired(){
        gotoWeb();
        clickElement(register);
        clickElement(ButtonRegister);
        verifyFromError("is required");
        return this;
    }

    public OverviewPage loginSuccess(int row) {
        gotoWeb();
        setText(Username, excelRegister.getCellData("Username", row));
        setText(Password, excelRegister.getCellData("Password", row));
        clickElement(ButtonLogin);
        return new OverviewPage();
    }

    public LoginPage loginFail(int row){
        gotoWeb();
        setText(Username, excelRegister.getCellData("Username", row));
        setText(Password, excelRegister.getCellData("Password", row));
        clickElement(ButtonLogin);
        verifyEqual(getText(welcomeUser), "Error!");
        verifyEqual(getText(messageWelcome), "An internal error has occurred and has been logged.");
        return this;
    }

    //More Data

    private String getWelcomeName(Hashtable<String, String> data) {
        return "Welcome " + data.get("First name") + " "
                + data.get("Last name");
    }
    private String getWelcomeUser(Hashtable<String, String> data){
        return "Welcome " + data.get("Username");
    }

    public LoginPage registerSuccess(Hashtable<String, String> data) {
        gotoWeb();
        clickElement(register);
        verifyContain(getURL(), "register");
        verifyEqual(getTitle(), titleRegister);
        verifyVisible(formRegister);
        setText(FirstName, data.get("First name"));
        setText(LastName, data.get("Last name"));
        setText(Address, data.get("Address"));
        setText(City, data.get("City"));
        setText(State, data.get("State"));
        setText(ZipCode, data.get("Zip Code"));
        setText(Phone, data.get("Phone"));
        setText(SSN, data.get("SSN"));
        setText(RegUsername, data.get("Username"));
        setText(RegPassword, data.get("Password"));
        setText(Confirm, data.get("Password"));
        return this;
    }
    public LoginPage verifyNewAccount(Hashtable<String, String> data){
        waitForVisible(headerFullName);
        verifyEqual(getText(headerFullName), getWelcomeName(data));
        verifyEqual(getText(welcomeUser), getWelcomeUser(data));
        verifyEqual(getText(messageWelcome), textMessWc);
        return this;
    }

//    public void verifyFrom(){
//        verifyEqual(getText(FirstName), excelRegister.getCellData("FirstName", 1));
//        verifyEqual(getText(LastName), excelRegister.getCellData("LastName", 1));
//        verifyEqual(getText(Address), excelRegister.getCellData("Address", 1));
//        verifyEqual(getText(City), excelRegister.getCellData("City", 1));
//        verifyEqual(getText(State), excelRegister.getCellData("State", 1));
//        verifyEqual(getText(ZipCode), excelRegister.getCellData("ZipCode", 1));
//        verifyEqual(getText(Phone), excelRegister.getCellData("Phone", 1));
//        verifyEqual(getText(SSN), excelRegister.getCellData("SSN", 1));
//        verifyEqual(getText(RegUsername), excelRegister.getCellData("Username", 1));
//        verifyEqual(getText(RegPassword), excelRegister.getCellData("Password", 1));
//        verifyEqual(getText(Confirm), excelRegister.getCellData("Password", 1));
//    }

}
