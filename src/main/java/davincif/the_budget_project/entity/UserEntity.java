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

package davincif.the_budget_project.entity;

import java.time.LocalDate;
import java.util.UUID;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;

    @NotBlank
    @Getter
    private String Email;

    @NotBlank
    @Getter
    private String Password;

    @Getter
    private String Name;

    @Getter
    private String NickName;

    @NotNull
    @Getter
    private boolean isActive;

    @NotNull
    @Getter
    private LocalDate CreatedAt;

    @Getter
    private LocalDate UpdatedAt;

    @Getter
    private LocalDate BirthDay;
}
