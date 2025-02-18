package Website.ParaBank.pages;

import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class CommonPage {

    private By buttonLogout = By.xpath("//a[normalize-space()='Log Out']");
    private By linkOverviewPage = By.xpath("//a[normalize-space()='Accounts Overview']");
    private By linkAccountPage = By.xpath("//a[normalize-space()='Open New Account']");

    LoginPage loginPage;
    AccountPage accountPage;
    OverviewPage overviewPage;

    public AccountPage navigateAccountPage(){
        waitForPageLoad();
        clickElement(linkAccountPage);
        return new AccountPage();
    }

    public LoginPage logout(){
        waitForPageLoad(2);
        clickElement(buttonLogout);
        return new LoginPage();
    }

    public OverviewPage navigateOverviewPage(){
        waitForPageLoad();
        clickElement(linkOverviewPage);
        return new OverviewPage();
    }

    public LoginPage getLoginPage(){
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public OverviewPage getOverviewPage(){
        if (overviewPage == null) {
            overviewPage = new OverviewPage();
        }
        return overviewPage;
    }

    public AccountPage getAccountPage(){
        if (accountPage == null) {
            accountPage = new AccountPage();
        }
        return accountPage;
    }
}
