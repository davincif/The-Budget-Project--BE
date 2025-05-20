package davincif.the_budget_project.login.dto.valueObject;

public record UserNickName(String value) {
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 20;

    public UserNickName(String value) {
        this.value = this.guaranteedValid(value);
    }

    private String guaranteedValid(String userName) {
        // TODO: MAKE A BETTER ERROR TREATMENT
        if (userName == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "user nick name can't be null nor empty"
            );
        }

        String trimmedUserName = userName.trim();
        if (
            trimmedUserName.length() < UserName.MIN_LENGTH ||
            trimmedUserName.length() > UserName.MAX_LENGTH
        ) {
            throw new IllegalArgumentException(
                "user nick name must be between " +
                UserNickName.MIN_LENGTH +
                " and " +
                UserNickName.MAX_LENGTH +
                " characters"
            );
        }

        return userName;
    }
}
