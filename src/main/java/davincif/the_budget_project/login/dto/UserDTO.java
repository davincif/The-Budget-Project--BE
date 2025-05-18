package davincif.the_budget_project.login.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO implements Validatable {

    private UUID id;

    private EmailDTO email;

    private PasswordDTO password;

    private String name;

    private String nickName;

    private boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate birthDay;

    public UserDTO setEmail(String email) {
        this.email = new EmailDTO().setEmail(email);

        return this;
    }

    @Override
    public boolean isValid() {
        return this.email.isValid() && this.password.isValid();
    }
    // private boolean isNameValid(Optional<String> newName) {
    //     int nameLength = newName.orElse(this.nickName).trim().length();

    //     return nameLength >= 3 && nameLength <= 121;
    // }
}
