package davincif.the_budget_project.login.dto.valueObject;

import java.time.LocalDate;
import java.time.Period;

public record BirthdayDate(LocalDate value) {
    public static final int MIN_AGE = 7;
    public static final int MAX_AGE = 20;

    public BirthdayDate(LocalDate value) {
        this.value = this.guaranteedValid(value);
    }

    private LocalDate guaranteedValid(LocalDate birthdayDate) {
        int age = Period.between(birthdayDate, LocalDate.now()).getYears();

        if (age < BirthdayDate.MIN_AGE || age > BirthdayDate.MAX_AGE) {
            throw new IllegalArgumentException(
                "user must be between " +
                BirthdayDate.MIN_AGE +
                " and " +
                BirthdayDate.MAX_AGE +
                " years old"
            );
        }

        return birthdayDate;
    }
}
