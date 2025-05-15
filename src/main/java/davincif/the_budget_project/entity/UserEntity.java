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

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotBlank
    private String Email;

    @NotNull
    @NotBlank
    private String Name;

    @NotNull
    @NotBlank
    private String Password;

    @NotNull
    @NotBlank
    private boolean isActive;

    @NotNull
    @NotBlank
    private LocalDate CreatedAt;

    private LocalDate UpdatedAt;
    private String NickName;
    private LocalDate BirthDay;
}
