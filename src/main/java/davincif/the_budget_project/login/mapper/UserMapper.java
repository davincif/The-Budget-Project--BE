package davincif.the_budget_project.login.mapper;

import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings(
        {
            @Mapping(
                target = "email",
                source = "email",
                qualifiedByName = "emailToString"
            ),
            @Mapping(
                target = "password",
                source = "password",
                qualifiedByName = "passwordToString"
            ),
            @Mapping(
                target = "name",
                source = "name",
                qualifiedByName = "userNameToString"
            ),
            @Mapping(
                target = "nickName",
                source = "nickName",
                qualifiedByName = "userNickNameToString"
            ),
            @Mapping(
                target = "createdAt",
                source = "createdAt",
                qualifiedByName = "updateDateToLocalDateTime"
            ),
            @Mapping(
                target = "updatedAt",
                source = "updatedAt",
                qualifiedByName = "updateDateToLocalDateTime"
            ),
            @Mapping(
                target = "birthDay",
                source = "birthDay",
                qualifiedByName = "birthdayDateToLocalDate"
            ),
        }
    )
    public UserEntity userDTOToEntity(UserDTO userDTO);

    @Mappings(
        {
            @Mapping(
                target = "email",
                source = "email",
                qualifiedByName = "stringToEmail"
            ),
            @Mapping(
                target = "password",
                source = "password",
                qualifiedByName = "stringToPassword"
            ),
            @Mapping(
                target = "name",
                source = "name",
                qualifiedByName = "stringToUserName"
            ),
            @Mapping(
                target = "nickName",
                source = "nickName",
                qualifiedByName = "stringToUserNickName"
            ),
            @Mapping(
                target = "createdAt",
                source = "createdAt",
                qualifiedByName = "localDateTimeToUpdateDate"
            ),
            @Mapping(
                target = "updatedAt",
                source = "updatedAt",
                qualifiedByName = "localDateTimeToUpdateDate"
            ),
            @Mapping(
                target = "birthDay",
                source = "birthDay",
                qualifiedByName = "localDateToBirthdayDate"
            ),
        }
    )
    public UserDTO userEntityToDTO(UserEntity entity);

    @Named("emailToString")
    public static String emailToString(
        davincif.the_budget_project.login.dto.valueObject.Email email
    ) {
        return email != null ? email.value() : null;
    }

    @Named("passwordToString")
    public static String passwordToString(
        davincif.the_budget_project.login.dto.valueObject.Password password
    ) {
        return password != null ? password.value() : null;
    }

    @Named("userNameToString")
    public static String userNameToString(
        davincif.the_budget_project.login.dto.valueObject.UserName name
    ) {
        return name != null ? name.value() : null;
    }

    @Named("userNickNameToString")
    public static String userNickNameToString(
        davincif.the_budget_project.login.dto.valueObject.UserNickName nickName
    ) {
        return nickName != null ? nickName.value() : null;
    }

    @Named("updateDateToLocalDateTime")
    public static java.time.LocalDateTime updateDateToLocalDateTime(
        davincif.the_budget_project.login.dto.valueObject.UpdateDate date
    ) {
        return date != null ? date.value() : null;
    }

    @Named("birthdayDateToLocalDate")
    public static java.time.LocalDate birthdayDateToLocalDate(
        davincif.the_budget_project.login.dto.valueObject.BirthdayDate date
    ) {
        return date != null ? date.value() : null;
    }

    @Named("stringToEmail")
    public static davincif.the_budget_project.login.dto.valueObject.Email stringToEmail(
        String email
    ) {
        return email != null
            ? new davincif.the_budget_project.login.dto.valueObject.Email(email)
            : null;
    }

    @Named("stringToPassword")
    public static davincif.the_budget_project.login.dto.valueObject.Password stringToPassword(
        String password
    ) {
        return password != null
            ? new davincif.the_budget_project.login.dto.valueObject.Password(
                password
            )
            : null;
    }

    @Named("stringToUserName")
    public static davincif.the_budget_project.login.dto.valueObject.UserName stringToUserName(
        String name
    ) {
        return name != null
            ? new davincif.the_budget_project.login.dto.valueObject.UserName(
                name
            )
            : null;
    }

    @Named("stringToUserNickName")
    public static davincif.the_budget_project.login.dto.valueObject.UserNickName stringToUserNickName(
        String nickName
    ) {
        return nickName != null
            ? new davincif.the_budget_project.login.dto.valueObject.UserNickName(
                nickName
            )
            : null;
    }

    @Named("localDateTimeToUpdateDate")
    public static davincif.the_budget_project.login.dto.valueObject.UpdateDate localDateTimeToUpdateDate(
        java.time.LocalDateTime date
    ) {
        return date != null
            ? new davincif.the_budget_project.login.dto.valueObject.UpdateDate(
                date
            )
            : null;
    }

    @Named("localDateToBirthdayDate")
    public static davincif.the_budget_project.login.dto.valueObject.BirthdayDate localDateToBirthdayDate(
        java.time.LocalDate date
    ) {
        return date != null
            ? new davincif.the_budget_project.login.dto.valueObject.BirthdayDate(
                date
            )
            : null;
    }
}
