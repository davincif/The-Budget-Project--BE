package davincif.the_budget_project.login.dto;

import davincif.the_budget_project.login.entity.UserEntity;

public class Mapper {

    public static UserDTO userEntityToDTO(UserEntity userEntity) {
        System.out.println(new UserDTO().isValid());

        return new UserDTO();
    }
}
