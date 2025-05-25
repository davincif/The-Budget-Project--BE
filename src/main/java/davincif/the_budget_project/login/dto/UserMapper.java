package davincif.the_budget_project.login.dto;

import davincif.the_budget_project.login.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings(
        {
            @Mapping(
                target = "email",
                expression = "java(new davincif.the_budget_project.login.dto.valueObject.Email(entity.getEmail()))"
            ),
            @Mapping(
                target = "password",
                expression = "java(new davincif.the_budget_project.login.dto.valueObject.Password(entity.getPassword()))"
            ),
            @Mapping(
                target = "name",
                expression = "java(entity.getName() != null ? new davincif.the_budget_project.login.dto.valueObject.UserName(entity.getName()) : null)"
            ),
            @Mapping(
                target = "nickName",
                expression = "java(entity.getNickName() != null ? new davincif.the_budget_project.login.dto.valueObject.UserNickName(entity.getNickName()) : null)"
            ),
            @Mapping(
                target = "createdAt",
                expression = "java(new davincif.the_budget_project.login.dto.valueObject.UpdateDate(entity.getCreatedAt()))"
            ),
            @Mapping(
                target = "updatedAt",
                expression = "java(entity.getUpdatedAt() != null ? new davincif.the_budget_project.login.dto.valueObject.UpdateDate(entity.getUpdatedAt()) : null)"
            ),
            @Mapping(
                target = "birthDay",
                expression = "java(entity.getBirthDay() != null ? new davincif.the_budget_project.login.dto.valueObject.BirthdayDate(entity.getBirthDay()) : null)"
            ),
        }
    )
    public UserDTO userEntityToDTO(UserEntity entity);

    @Mappings(
        {
            @Mapping(
                target = "email",
                expression = "java(userDTO.getEmail().value())"
            ),
            @Mapping(
                target = "password",
                expression = "java(userDTO.getPassword().value())"
            ),
            @Mapping(
                target = "name",
                expression = "java(userDTO.getName() != null ? userDTO.getName().value() : null)"
            ),
            @Mapping(
                target = "nickName",
                expression = "java(userDTO.getNickName() != null ? userDTO.getNickName().value() : null)"
            ),
            @Mapping(
                target = "createdAt",
                expression = "java(userDTO.getCreatedAt().value())"
            ),
            @Mapping(
                target = "updatedAt",
                expression = "java(userDTO.getUpdatedAt() != null ? userDTO.getUpdatedAt().value() : null)"
            ),
            @Mapping(
                target = "birthDay",
                expression = "java(userDTO.getBirthDay() != null ? userDTO.getBirthDay().value() : null)"
            ),
        }
    )
    public UserEntity userDTOToEntity(UserDTO userDTO);
}
