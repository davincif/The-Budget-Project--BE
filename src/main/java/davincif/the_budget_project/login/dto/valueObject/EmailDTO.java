package davincif.the_budget_project.login.dto.valueObject;

import java.util.regex.Pattern;

public record EmailDTO(String value) {
    private static final Pattern REGEX = Pattern.compile(
        "^[^@]+@[^@]+\\.[^@]+$"
    );

    public EmailDTO(String value) {
        this.value = this.guaranteedValid(value);
    }

    private String guaranteedValid(String email) {
        // TODO: MAKE A BETTER ERROR TREATMENT
        if (email == null) {
            throw new IllegalArgumentException("E-mail can't be null");
        }

        String trimmedLowerCaseEmail = email.trim().toLowerCase();

        if (!REGEX.matcher(trimmedLowerCaseEmail).matches()) {
            throw new IllegalArgumentException(
                "E-mail is not valid: " + trimmedLowerCaseEmail
            );
        }

        return trimmedLowerCaseEmail;
    }
}
