package davincif.the_budget_project.login.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PasswordDTO implements Validatable {

    private String password;

    @Override
    public boolean isValid() {
        return !this.password.isBlank();
    }
}
