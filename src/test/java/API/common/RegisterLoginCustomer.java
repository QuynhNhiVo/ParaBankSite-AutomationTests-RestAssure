package API.common;

import Website.ParaBank.pages.LoginPage;
import Website.common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegisterLoginCustomer extends BaseTest{

    LoginPage loginPage;

    @BeforeMethod
    private void initData() {
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void loginCustomer(@Optional("1") int row){
        loginPage.customerApi(row);

    }
}
