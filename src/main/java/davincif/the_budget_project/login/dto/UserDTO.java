/*
Copyright 2025 Leonardo Da Vinci Feliciano Sebasitão

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
*/

package davincif.the_budget_project.login.dto;

import davincif.the_budget_project.login.dto.valueObject.BirthdayDate;
import davincif.the_budget_project.login.dto.valueObject.Email;
import davincif.the_budget_project.login.dto.valueObject.Password;
import davincif.the_budget_project.login.dto.valueObject.UpdateDate;
import davincif.the_budget_project.login.dto.valueObject.UserName;
import davincif.the_budget_project.login.dto.valueObject.UserNickName;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {

    private UUID id;

    private Email email;

    private Password password;

    private UserName name;

    private UserNickName nickName;

    private boolean isActive = false;

    private UpdateDate createdAt = new UpdateDate(LocalDateTime.now());

    private UpdateDate updatedAt;

    private BirthdayDate birthDay;

    public static UserDTO of(String email, String password) {
        return new UserDTO()
            .setEmail(new Email(email))
            .setPassword(new Password(password));
    }
}
