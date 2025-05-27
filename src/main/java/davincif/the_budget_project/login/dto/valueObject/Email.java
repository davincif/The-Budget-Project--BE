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
import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern REGEX = Pattern.compile(
        "^[^@]+@[^@]+\\.[^@]+$"
    );

    public Email(String value) {
        this.value = this.guaranteedValid(value);
    }

    private String guaranteedValid(String email)
        throws InvalidArgumentException {
        if (email == null) {
            throw new InvalidArgumentException("E-mail can't be null");
        }

        String trimmedLowerCaseEmail = email.trim().toLowerCase();

        if (!Email.REGEX.matcher(trimmedLowerCaseEmail).matches()) {
            throw new InvalidArgumentException(
                "E-mail is not valid: " + trimmedLowerCaseEmail
            );
        }

        return trimmedLowerCaseEmail;
    }
}
