package davincif.the_budget_project.login.dto.valueObject;

public record PasswordDTO(String value) {
    public PasswordDTO(String value) {
        this.value = this.guaranteedValid(value);
    }

    private String guaranteedValid(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "password can't be null nor empty"
            );
        }

        return password;
    }
}
