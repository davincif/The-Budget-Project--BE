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

package davincif.the_budget_project.login.dto;

import davincif.the_budget_project.login.entity.UserEntity;
import java.time.LocalDateTime;

public class Mapper {

    public static UserDTO userEntityToDTO(UserEntity userEntity)
        throws IllegalArgumentException {
        UserDTO user = new UserDTO();

        return user;
    }

    public static UserEntity userDTOToEntity(UserDTO userDTO)
        throws IllegalArgumentException {
        LocalDateTime createdAt = userDTO.getCreatedAt() == null
            ? LocalDateTime.now()
            : userDTO.getCreatedAt().value();

        UserEntity user = new UserEntity()
            .setEmail(userDTO.getEmail().value())
            .setPassword(userDTO.getPassword().value())
            .setActive(userDTO.isActive())
            .setCreatedAt(createdAt);

        if (userDTO.getName() != null) {
            user.setName(userDTO.getName().value());
        }

        if (userDTO.getNickName() != null) {
            user.setNickName(userDTO.getNickName().value());
        }

        if (userDTO.getUpdatedAt() != null) {
            user.setUpdatedAt(userDTO.getUpdatedAt().value());
        }

        if (userDTO.getBirthDay() != null) {
            user.setBirthDay(userDTO.getBirthDay().value());
        }

        return user;
    }
}
