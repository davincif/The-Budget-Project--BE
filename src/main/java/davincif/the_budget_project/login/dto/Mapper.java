package davincif.the_budget_project.login.dto;

import davincif.the_budget_project.login.entity.UserEntity;

public class Mapper {

    public static UserDTO userEntityToDTO(UserEntity userEntity) {
        System.out.println(new UserDTO());

        return new UserDTO();
    }

    public static UserEntity userDTOToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();

        user
            .setEmail(userDTO.getEmail().value())
            .setPassword(userDTO.getPassword().value());

        // if (userDTO.getName(

        return user;
    }
}
