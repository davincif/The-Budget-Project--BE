package davincif.the_budget_project.Presenter;

import java.util.Optional;

import lombok.Getter;

public class ErrorResponsePresenter<T> {
    @Getter
    private String code;

    @Getter
    private Optional<String> friendlyMessage;

    @Getter
    private Optional<String> technicalMessage;

    @Getter
    private Optional<T> details;

    static public ErrorResponsePresenter<String> notImplemented() {
        return new ErrorResponsePresenter<String>()
                .setCode("501")
                .setFriendlyMessage(Optional.of("This method ain't implemented yet"))
                .setTechnicalMessage(Optional.of("NOT IMPLEMENTED"));
    }

    public ErrorResponsePresenter<T> setCode(String code) {
        this.code = code;

        return this;
    }

    public ErrorResponsePresenter<T> setFriendlyMessage(Optional<String> friendlyMessage) {
        this.friendlyMessage = friendlyMessage;

        return this;
    }

    public ErrorResponsePresenter<T> setTechnicalMessage(Optional<String> technicalMessage) {
        this.technicalMessage = technicalMessage;

        return this;
    }

    public ErrorResponsePresenter<T> setDetails(Optional<T> details) {
        this.details = details;

        return this;
    }

}
