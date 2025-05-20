package davincif.the_budget_project.login.dto.valueObject;

import java.time.LocalDate;

public record UpdateDate(LocalDate value) {
    public UpdateDate(LocalDate value) {
        this.value = this.guaranteedValidDate(value);
    }

    private LocalDate guaranteedValidDate(LocalDate birthdayDate) {
        return birthdayDate;
    }
}
