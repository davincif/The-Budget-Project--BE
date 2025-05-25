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

package davincif.the_budget_project.login.dto;

import davincif.the_budget_project.login.exception.NotImplementedException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Data
@Accessors(chain = true)
public class TokenDTO {

    public static final String ALGORITHM = "HS512";
    public static final ZoneOffset OFFSET = ZoneOffset.UTC;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final LocalDateTime now = LocalDateTime.now();

    @Setter(AccessLevel.NONE)
    private String jwt;

    private int tokenLongevityInSeconds = 1 * 60 * 60; // 1 hour

    /// JTW HEADER
    private String alg = TokenDTO.ALGORITHM;
    private String typ = "JWT";

    /// JTW REGISTERED CLAIMS PAYLOAD

    /** iss (Issuer) — identifies the principal that issued the JWT */
    @ConfigProperty(name = "jwt.issuer", defaultValue = "")
    private String iss;

    /**
     * sub (Subject) — identifies the subject of the JWT (usually the user)
     * (in our case, the id)
     * */
    private String sub;

    /** aud (Audience) — identifies the recipients the JWT is intended for */
    private List<String> aud = List.of("localhost");

    /** exp (Expiration Time) — identifies the expiration time after which the JWT must not be accepted */
    @Setter(AccessLevel.NONE)
    private long exp;

    /** nbf (Not Before) — identifies the time before which the JWT must not be accepted */
    @Setter(AccessLevel.NONE)
    private long nbf = now.toEpochSecond(TokenDTO.OFFSET);

    /** iat (Issued At) — identifies the time at which the JWT was issued */
    @Setter(AccessLevel.NONE)
    private long iat = now.toEpochSecond(TokenDTO.OFFSET);

    /** jti (JWT ID) — provides a unique identifier for the JWT (used for preventing replay attacks) */
    private String jti;

    /// JTW CUSTOM PAYLOAD

    /// JTW FOOTER
    @ConfigProperty(name = "jtw.secret.salt", defaultValue = "")
    private String secret;

    // public static Token from(String token) {

    // }

    public void setTokenLongevityInSeconds(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException(
                "Token longevity must be greater than zero."
            );
        }

        this.tokenLongevityInSeconds = seconds;

        this.exp = now
            .plusSeconds(tokenLongevityInSeconds)
            .toEpochSecond(TokenDTO.OFFSET);
    }

    public String getJwt() {
        if (this.jwt != null && !this.jwt.isEmpty()) {
            return this.jwt;
        }

        throw new NotImplementedException(
            "jtw creation is not implemented yet"
        );
        // this.jwt = JWT.create()
        //     .setAlgorithm(this.alg)
        //     .setType(this.typ)
        //     .setIssuer(this.iss)
        //     .setSubject(this.sub)
        //     .setAudience(this.aud)
        //     .setExpirationTime(this.exp)
        //     .setNotBeforeTime(this.nbf)
        //     .setIssuedAtTime(this.iat)
        //     .setId(this.jti)
        //     .sign(this.secret);

        // return this.jwt;
    }
}
