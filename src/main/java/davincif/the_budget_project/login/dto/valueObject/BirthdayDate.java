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

package davincif.the_budget_project.login.dto.valueObject;

import davincif.the_budget_project.login.exception.InvalidArgumentException;
import java.time.LocalDate;
import java.time.Period;

public record BirthdayDate(LocalDate value) {
    public static final int MIN_AGE = 7;
    public static final int MAX_AGE = 120;

    public BirthdayDate(LocalDate value) {
        this.value = this.guaranteedValid(value);
    }

    private LocalDate guaranteedValid(LocalDate birthdayDate)
        throws InvalidArgumentException {
        int age = Period.between(birthdayDate, LocalDate.now()).getYears();

        if (age < BirthdayDate.MIN_AGE || age > BirthdayDate.MAX_AGE) {
            throw new InvalidArgumentException(
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
