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
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseErrorResponse<T> {

    private String code;

    private Optional<String> friendlyMessage;

    private Optional<String> technicalMessage;

    private Optional<T> details;

    public static BaseErrorResponse<String> notImplemented() {
        return new BaseErrorResponse<String>()
            .setCode("501")
            .setFriendlyMessage("This method ain't implemented yet")
            .setTechnicalMessage("NOT IMPLEMENTED");
    }

    public BaseErrorResponse<T> setFriendlyMessage(String friendlyMessage) {
        this.friendlyMessage = Optional.of(friendlyMessage);
        return this;
    }

    public BaseErrorResponse<T> setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = Optional.of(technicalMessage);

        return this;
    }

    public BaseErrorResponse<T> setDetails(T details) {
        this.details = Optional.of(details);

        return this;
    }
}
