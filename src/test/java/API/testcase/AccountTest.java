package API.testcase;

import API.account.AccountDetail;
import API.account.CustomerDetail;
import API.common.BaseTestAPI;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AccountTest extends BaseTestAPI {

    CustomerDetail customerDetail;
    AccountDetail accountDetail;

    @BeforeMethod
    private void initData(){
        customerDetail = new CustomerDetail();
    }

    @Test
    @Parameters({"type"})
    public void TC_CreateAccount(@Optional("1") int type){
        customerDetail.getAccountID().createNewAccount(type);
    }

}
