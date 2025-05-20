package davincif.the_budget_project.login.dto;

import java.util.UUID;

import davincif.the_budget_project.login.dto.valueObject.BirthdayDate;
import davincif.the_budget_project.login.dto.valueObject.EmailDTO;
import davincif.the_budget_project.login.dto.valueObject.PasswordDTO;
import davincif.the_budget_project.login.dto.valueObject.UpdateDate;
import davincif.the_budget_project.login.dto.valueObject.UserName;
import davincif.the_budget_project.login.dto.valueObject.UserNickName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {

    private UUID id;

    private EmailDTO email;

    private PasswordDTO password;

    private UserName name;

    private UserNickName nickName;

    private boolean isActive;

    private UpdateDate createdAt;

    private UpdateDate updatedAt;

    private BirthdayDate birthDay;

    public UserDTO setEmail(String email) {
        this.email = new EmailDTO(email);

        return this;
    }
}
