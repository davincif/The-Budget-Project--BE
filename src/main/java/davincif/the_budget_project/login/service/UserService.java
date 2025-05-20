package davincif.the_budget_project.login.service;

import java.util.Optional;

import davincif.the_budget_project.login.dto.Mapper;
import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.dto.valueObject.EmailDTO;
import davincif.the_budget_project.login.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {

    public Optional<UserDTO> searchUser(String email) {
        EmailDTO emailDTO = new EmailDTO(email);

        if (emailDTO.value() == null) {
            throw new IllegalArgumentException("Email is not valid");
        }

        Optional<UserEntity> user = UserEntity.find(
            "email=?1",
            emailDTO.value()
        ).firstResultOptional();

        if (user.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(Mapper.userEntityToDTO(user.get()));
    }

    public void craeteUser(UserDTO userDTO) {
        // TODO IMPLEMENT
    }
}
