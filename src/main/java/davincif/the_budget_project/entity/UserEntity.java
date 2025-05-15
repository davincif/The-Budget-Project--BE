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
