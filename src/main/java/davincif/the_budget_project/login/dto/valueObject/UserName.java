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
