package davincif.the_budget_project.login.dto.valueObject;

public record UserName(String value) {
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 121;

    public UserName(String value) {
        this.value = this.guaranteedValid(value);
    }

    private String guaranteedValid(String userName) {
        // TODO: MAKE A BETTER ERROR TREATMENT
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "user name can't be null nor empty"
            );
        }

        String trimmedUserName = userName.trim();
        if (
            trimmedUserName.length() < UserName.MIN_LENGTH ||
            trimmedUserName.length() > UserName.MAX_LENGTH
        ) {
            throw new IllegalArgumentException(
                "user name must be between " +
                UserName.MIN_LENGTH +
                " and " +
                UserName.MAX_LENGTH +
                " characters"
            );
        }

        return userName;
    }
}
