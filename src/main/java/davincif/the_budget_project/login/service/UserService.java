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

package davincif.the_budget_project.login.service;

import davincif.the_budget_project.login.dto.TokenDTO;
import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.dto.valueObject.Email;
import davincif.the_budget_project.login.entity.UserEntity;
import davincif.the_budget_project.login.exception.InvalidArgumentException;
import davincif.the_budget_project.login.exception.UserAlreadyExistsException;
import davincif.the_budget_project.login.exception.UserNotFoundException;
import davincif.the_budget_project.login.mapper.LoginMapper;
import davincif.the_budget_project.login.mapper.UserMapper;
import davincif.the_budget_project.login.response.LoginResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    private UserMapper userMapper;

    @Inject
    private LoginMapper loginMapper;

    public Optional<UserDTO> searchUser(String email)
        throws InvalidArgumentException {
        Email Email = new Email(email);

        Optional<UserEntity> user = UserEntity.findByEmail(Email.value());

        if (user.isEmpty()) {
            return Optional.empty();
        }

        UserDTO userDTO = userMapper.userEntityToDTO(user.get());

        return Optional.of(userDTO);
    }

    @Transactional
    public void createUser(String email, String password)
        throws InvalidArgumentException {
        UserDTO userDTO = UserDTO.of(email, password).setActive(true);

        this.guaranteeNonExistingUser(email);

        UserEntity userEntity = userMapper.userDTOToEntity(userDTO);

        userEntity.persist();
    }

    public UserDTO getUser(String email) {
        Optional<UserDTO> existentUser = this.searchUser(email);

        if (existentUser.isEmpty()) {
            throw new UserNotFoundException(
                "This user doesn't exist. Try registering"
            );
        }

        return existentUser.get();
    }

    public LoginResponse generateUserToken(UserDTO userDTO) {
        TokenDTO token = new TokenDTO(userDTO);

        LoginResponse userDTOToLoginResponse =
            loginMapper.userDTOToLoginResponse(userDTO, token.getJwt());

        return userDTOToLoginResponse;
    }

    private void guaranteeNonExistingUser(String email)
        throws UserAlreadyExistsException {
        Email Email = new Email(email);

        Optional<UserEntity> user = UserEntity.findByEmail(Email.value());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException(
                "This user already exists. Try logging in"
            );
        }
    }
}
