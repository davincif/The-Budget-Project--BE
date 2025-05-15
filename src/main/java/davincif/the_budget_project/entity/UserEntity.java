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
    private UUID id;

    @NotBlank
    private String Email;

    @NotBlank
    private String Name;

    @Getter
    private String NickName;

    @NotBlank
    private String Password;

    @NotNull
    private boolean isActive;

    @NotNull
    private LocalDate CreatedAt;

    @Getter
    private LocalDate UpdatedAt;

    @Getter
    private LocalDate BirthDay;
}
