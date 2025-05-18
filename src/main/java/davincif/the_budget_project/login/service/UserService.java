package davincif.the_budget_project.login.service;

import davincif.the_budget_project.login.dto.Mapper;
import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    public Optional<UserDTO> searchUser(String email) {
        UserEntity user = UserEntity.find("Email", email).firstResult();

        if (user == null) {
            return Optional.empty();
        }

        return Optional.of(Mapper.userEntityToDTO(user));
    }
}
