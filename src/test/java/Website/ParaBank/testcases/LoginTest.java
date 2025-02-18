package Website.ParaBank.testcases;

import Website.ParaBank.pages.LoginPage;
import Website.common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    private void initData(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_RegisterTestSuccess(@Optional("1") int row){
        loginPage.registerSuccess(row)
                .clickRegister()
                .verifyNewAccount(row)
                .logout();
    }

    @Test
    @Parameters({"row"})
    public void TC_RegisterTestAccountExist(@Optional("1") int row){
        loginPage.registerSuccess(row)
                .clickRegister()
                .verifyExistAccount();
    }

    @Test
    public void TC_RegisterTestRequiredData(){
        loginPage.verifyFieldsRequired();
    }

    @Test
    @Parameters({"row"})
    public void TC_LoginSuccess(@Optional("1") int row){
        loginPage.loginSuccess(row)
                .verifyLoginSuccess(row)
                .logout();
    }
}
