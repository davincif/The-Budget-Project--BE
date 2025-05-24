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

package davincif.the_budget_project.login.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Table(name = "tb_users")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 255)
    private String password;

    @Size(min = 3, max = 121) // The length of the name of Dom Pedro II from Brasil
    private String name;

    @Size(min = 3, max = 20)
    private String nickName;

    @NotNull
    private boolean isActive;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDate birthDay;

    public UserEntity userEntity() {
        this.setCreatedAt(LocalDateTime.now());
        this.setActive(true);

        return this;
    }

    public static Optional<UserEntity> findByEmail(String email) {
        return UserEntity.find("email=?1", email).firstResultOptional();
    }
}
