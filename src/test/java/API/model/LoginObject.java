package API.model;
import constants.ConfigData;
import helpers.ExcelHelpers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LoginObject {

    private ExcelHelpers excelHelpers;

    public LoginObject(ExcelHelpers excelHelpers) {
        this.excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.EXCEL_PARA, "Register");
    }

    private String username;
    private String password;
}
