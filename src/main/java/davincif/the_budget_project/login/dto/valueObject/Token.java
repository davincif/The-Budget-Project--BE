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

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Token {

    private String jwt;

    /// JTW HEADER
    private String alg = "HS512";
    private String typ = "JWT";

    /// JTW REGISTERED CLAIMS PAYLOAD

    /** iss (Issuer) — identifies the principal that issued the JWT */
    private String iss;

    /**
     * sub (Subject) — identifies the subject of the JWT (usually the user)
     * (in our case, the id)
     * */
    private String sub;

    /** aud (Audience) — identifies the recipients the JWT is intended for */
    private String aud;

    /** exp (Expiration Time) — identifies the expiration time after which the JWT must not be accepted */
    private String exp;

    /** nbf (Not Before) — identifies the time before which the JWT must not be accepted */
    private String nbf;

    /** iat (Issued At) — identifies the time at which the JWT was issued */
    private String iat;

    /** jti (JWT ID) — provides a unique identifier for the JWT (used for preventing replay attacks) */
    private String jti;

    /// JTW CUSTOM PAYLOAD

    /// JTW FOOTER
    private String secret;
}
