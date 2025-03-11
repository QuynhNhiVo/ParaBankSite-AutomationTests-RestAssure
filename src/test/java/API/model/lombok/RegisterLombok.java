package API.model.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterLombok {

    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String ssn;
    private String username;
    private String password;
}
