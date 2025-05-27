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
import io.quarkus.elytron.security.common.BcryptUtil;
import org.eclipse.microprofile.config.ConfigProvider;

public record Password(String value) {
    public static final int MIN_LENGTH = 6;
    public static final int MAX_LENGTH = 60;
    public static final int ITERATION_COUNT = 12;
    public static final byte[] SALT = ConfigProvider.getConfig()
        .getValue("bcrypt.salt", String.class)
        .getBytes();

    public Password(String value) {
        this.value = this.encrypt(this.guaranteedValid(value));
    }

    public String encrypt(String password) {
        return BcryptUtil.bcryptHash(password, Password.ITERATION_COUNT, SALT);
    }

    private String guaranteedValid(String password)
        throws InvalidArgumentException {
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidArgumentException(
                "password can't be null nor empty"
            );
        }

        String trimmedPassword = password.trim();
        if (
            trimmedPassword.length() < Password.MIN_LENGTH ||
            trimmedPassword.length() > Password.MAX_LENGTH
        ) {
            throw new InvalidArgumentException(
                "password must be between " +
                Password.MIN_LENGTH +
                " and " +
                Password.MAX_LENGTH +
                " characters"
            );
        }

        return password;
    }
}
