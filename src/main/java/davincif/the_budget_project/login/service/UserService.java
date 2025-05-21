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

import davincif.the_budget_project.login.dto.Mapper;
import davincif.the_budget_project.login.dto.UserDTO;
import davincif.the_budget_project.login.dto.valueObject.EmailDTO;
import davincif.the_budget_project.login.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    public Optional<UserDTO> searchUser(String email)
        throws IllegalArgumentException {
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

    @Transactional
    public void craeteUser(String email, String password)
        throws IllegalArgumentException {
        UserDTO userDTO = UserDTO.of(email, password).setActive(true);
        System.out.println(userDTO);

        UserEntity userEntity = Mapper.userDTOToEntity(userDTO);
        System.out.println(userEntity);

        userEntity.persist();
    }
}
