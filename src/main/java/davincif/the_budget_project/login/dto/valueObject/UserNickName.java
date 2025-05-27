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

public record UserNickName(String value) {
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 20;

    public UserNickName(String value) {
        this.value = this.guaranteedValid(value);
    }

    private String guaranteedValid(String userNickName)
        throws InvalidArgumentException {
        // TODO: MAKE A BETTER ERROR TREATMENT
        if (userNickName == null || userNickName.trim().isEmpty()) {
            throw new InvalidArgumentException(
                "user nick name can't be null nor empty"
            );
        }

        String trimmedUserNickName = userNickName.trim();
        if (
            trimmedUserNickName.length() < UserName.MIN_LENGTH ||
            trimmedUserNickName.length() > UserName.MAX_LENGTH
        ) {
            throw new InvalidArgumentException(
                "user nick name must be between " +
                UserNickName.MIN_LENGTH +
                " and " +
                UserNickName.MAX_LENGTH +
                " characters"
            );
        }

        return userNickName;
    }
}
