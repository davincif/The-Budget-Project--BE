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

package davincif.the_budget_project.login.response;

import java.util.Optional;
import lombok.Getter;

public class BaseErrorResponse<T> {

    @Getter
    private String code;

    @Getter
    private Optional<String> friendlyMessage;

    @Getter
    private Optional<String> technicalMessage;

    @Getter
    private Optional<T> details;

    public static BaseErrorResponse<String> notImplemented() {
        return new BaseErrorResponse<String>()
            .setCode("501")
            .setFriendlyMessage(Optional.of("This method ain't implemented yet"))
            .setTechnicalMessage(Optional.of("NOT IMPLEMENTED"));
    }

    public BaseErrorResponse<T> setCode(String code) {
        this.code = code;

        return this;
    }

    public BaseErrorResponse<T> setFriendlyMessage(Optional<String> friendlyMessage) {
        this.friendlyMessage = friendlyMessage;

        return this;
    }

    public BaseErrorResponse<T> setTechnicalMessage(Optional<String> technicalMessage) {
        this.technicalMessage = technicalMessage;

        return this;
    }

    public BaseErrorResponse<T> setDetails(Optional<T> details) {
        this.details = details;

        return this;
    }
}
