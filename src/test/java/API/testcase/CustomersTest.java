package API.testcase;

import API.account.CustomerDetail;
import API.common.BaseTestAPI;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomersTest extends BaseTestAPI {
    private CustomerDetail customerDetail;

    @BeforeMethod
    private CustomerDetail initData(){
        return  customerDetail = new CustomerDetail();
    }

    @Test
    public void TC_GetCustomerDetails(){
        customerDetail.getCustomerDetail();
    }

    @Test
    public void TC_GetCustomerPosition(){
        customerDetail.getCustomerPosition();
    }

    @Test
    public void TC_CreateAccountTest(){
        customerDetail.createAccount();
    }

    @Test
    public void TC_GetAccountID(){
        customerDetail.getAccountID();
    }
}
