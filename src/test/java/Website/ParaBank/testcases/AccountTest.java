package Website.ParaBank.testcases;

import Website.ParaBank.pages.AccountPage;
import Website.ParaBank.pages.LoginPage;
import Website.ParaBank.pages.OverviewPage;
import Website.common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {
    LoginPage loginPage;
    OverviewPage overviewPage;
    AccountPage accountPage;

    @BeforeMethod
    public void initData(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_CreateNewAccountChecking01(@Optional("4") int row){
        loginPage.registerSuccess(row)
                .clickRegister()
//                .logout()
//                .loginSuccess(row)
                .navigateAccountPage()
                .verifyAccountPage(row)
                .openChecking()
                .verifyCreateSuccess();
    }

    @Test
    @Parameters({"row"})
    public void TC_CreateNewAccountChecking02(@Optional("4") int row){
        loginPage.loginSuccess(row)
                .navigateAccountPage()
                .verifyAccountPage(row)
                .openChecking()
                .verifyCreateSuccess();
    }

    @Test
    @Parameters({"row"})
    public void TC_CreateNewAccountSaving01(@Optional("3") int row){
        loginPage.registerSuccess(row)
                .clickRegister()
                .logout()
                .loginSuccess(row)
                .navigateAccountPage()
                .verifyAccountPage(row)
                .openSaving()
                .verifyCreateSuccess();
    }

    @Test
    @Parameters({"row"})
    public void TC_CreateNewAccountSaving02(@Optional("3") int row){
        loginPage.loginSuccess(row)
                .navigateAccountPage()
                .verifyAccountPage(row)
                .openSaving()
                .verifyCreateSuccess();
    }
}
