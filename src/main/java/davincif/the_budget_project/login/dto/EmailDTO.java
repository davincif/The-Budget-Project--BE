package davincif.the_budget_project.login.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EmailDTO implements Validatable {

    private String email;

    @Override
    public boolean isValid() {
        return !this.email.isBlank();
    }

    public EmailDTO setEmail(String email) {
        this.email = email.trim().toLowerCase();

        return this;
    }
}
