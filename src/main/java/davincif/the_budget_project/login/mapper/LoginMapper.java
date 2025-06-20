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

package davincif.the_budget_project.login.mapper;

import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.dto.valueObject.BirthdayDate;
import davincif.the_budget_project.login.dto.valueObject.Email;
import davincif.the_budget_project.login.dto.valueObject.UpdateDate;
import davincif.the_budget_project.login.dto.valueObject.UserName;
import davincif.the_budget_project.login.dto.valueObject.UserNickName;
import davincif.the_budget_project.login.response.LoginResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    @Mappings(
        {
            @Mapping(target = "token", source = "token"),
            @Mapping(
                target = "email",
                source = "userDTO.email",
                qualifiedByName = "emailToString"
            ),
            @Mapping(
                target = "name",
                source = "userDTO.name",
                qualifiedByName = "userNameToString"
            ),
            @Mapping(
                target = "nickName",
                source = "userDTO.nickName",
                qualifiedByName = "userNickNameToString"
            ),
            @Mapping(
                target = "createdAt",
                source = "userDTO.createdAt",
                qualifiedByName = "updateDateToLocalDateTime"
            ),
            @Mapping(
                target = "updatedAt",
                source = "userDTO.updatedAt",
                qualifiedByName = "updateDateToLocalDateTime"
            ),
            @Mapping(
                target = "birthDay",
                source = "userDTO.birthDay",
                qualifiedByName = "birthdayDateToLocalDate"
            ),
        }
    )
    public LoginResponse userDTOToLoginResponse(UserDTO userDTO, String token);

    @Named("emailToString")
    public static String emailToString(Email email) {
        return email != null ? email.value() : null;
    }

    @Named("userNameToString")
    public static String userNameToString(UserName name) {
        return name != null ? name.value() : null;
    }

    @Named("userNickNameToString")
    public static String userNickNameToString(UserNickName nickName) {
        return nickName != null ? nickName.value() : null;
    }

    @Named("updateDateToLocalDateTime")
    public static LocalDateTime updateDateToLocalDateTime(UpdateDate date) {
        return date != null ? date.value() : null;
    }

    @Named("birthdayDateToLocalDate")
    public static LocalDate birthdayDateToLocalDate(BirthdayDate date) {
        return date != null ? date.value() : null;
    }
}
