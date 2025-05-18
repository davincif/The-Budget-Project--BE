package davincif.the_budget_project.login.service;

import davincif.the_budget_project.login.dto.Mapper;
import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    public Optional<UserDTO> searchUser(String email) {
        Optional<UserEntity> user = UserEntity.find("email", email).firstResultOptional();

        if (user.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(Mapper.userEntityToDTO(user.get()));
    }
}
