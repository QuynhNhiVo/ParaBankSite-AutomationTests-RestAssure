package API.common;


import API.model.lombok.RegisterLombok;
import constants.ConfigData;
import helpers.ExcelHelpers;
import net.datafaker.Faker;
import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class RegisterCustomer {

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
    private By formRegister = By.xpath("//form[@id='customerForm']");

    private final ExcelHelpers excelHelpers;

    public RegisterCustomer() {
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.EXCEL_PARA, "Uri");
    }

    RegisterLombok registerLombok = new RegisterLombok();
    Faker faker = new Faker();


    private void gotoWeb() {
        openURL(excelHelpers.getCellData(0, 0));
        verifyContain(getURL(), "index");
        verifyEqual(getTitle(), titleLogin);
    }

    private void setData(){
        registerLombok.setFirstname(faker.name().firstName());
        registerLombok.setLastname(faker.name().lastName());
        registerLombok.setAddress(faker.address().streetAddress());
        registerLombok.setCity(faker.address().city());
        registerLombok.setState(faker.address().state());
        registerLombok.setZip(faker.address().zipCode());
        registerLombok.setSsn(faker.idNumber().ssnValid());
        registerLombok.setUsername(faker.internet().username());
        registerLombok.setPassword(faker.internet().password());
    }

    public RegisterLombok customerApi() {
        setData();
        gotoWeb();
        setText(Username, registerLombok.getUsername());
        setText(Password, registerLombok.getPassword());
        clickElement(ButtonLogin);
        boolean check = getTitle().trim().toLowerCase().contains("overview");
        if (!check) {
            waitForPageLoad(3);
            clickElement(register);
            verifyContain(getURL(), "register");
            verifyEqual(getTitle(), titleRegister);
            verifyVisible(formRegister);
            setText(FirstName, registerLombok.getFirstname());
            setText(LastName, registerLombok.getLastname());
            setText(Address, registerLombok.getAddress());
            setText(City, registerLombok.getCity());
            setText(State, registerLombok.getState());
            setText(ZipCode, registerLombok.getZip());
            setText(SSN, registerLombok.getSsn());
            setText(RegUsername, registerLombok.getUsername());
            setText(RegPassword, registerLombok.getPassword());
            setText(Confirm, registerLombok.getPassword());
            clickElement(ButtonRegister);
        }
        getDriver().close();
        return registerLombok;
    }
}
