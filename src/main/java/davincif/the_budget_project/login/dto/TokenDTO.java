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

import io.quarkus.runtime.util.StringUtil;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.eclipse.microprofile.config.ConfigProvider;

@Data
@Accessors(chain = true)
public class TokenDTO {

    public static final String ALGORITHM = "HS256";
    public static final ZoneOffset OFFSET = ZoneOffset.UTC;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final LocalDateTime NOW = LocalDateTime.now();

    @Setter(AccessLevel.NONE)
    private String jwt;

    private int tokenLongevityInSeconds = 1 * 60 * 60; // 1 hour by default

    /// JTW HEADER
    private String alg = TokenDTO.ALGORITHM;
    private String typ = "JWT";

    /// JTW REGISTERED CLAIMS PAYLOAD

    /** iss (Issuer) — identifies the principal that issued the JWT */
    @Inject
    private String iss = ConfigProvider.getConfig()
        .getValue("jwt.issuer", String.class);

    /**
     * sub (Subject) — identifies the subject of the JWT (usually the user)
     * (in our case, the id)
     * */
    private String sub;

    /** aud (Audience) — identifies the recipients the JWT is intended for */
    private Set<String> aud = Set.of("localhost");

    /** exp (Expiration Time) — identifies the expiration time after which the JWT must not be accepted */
    @Setter(AccessLevel.NONE)
    private long exp;

    /** nbf (Not Before) — identifies the time before which the JWT must not be accepted */
    @Setter(AccessLevel.NONE)
    private long nbf;

    /** iat (Issued At) — identifies the time at which the JWT was issued */
    @Setter(AccessLevel.NONE)
    private long iat;

    /** jti (JWT ID) — provides a unique identifier for the JWT (used for preventing replay attacks) */
    private String jti;

    /// JTW CUSTOM PAYLOAD

    /// JTW FOOTER
    private String secret = ConfigProvider.getConfig()
        .getValue("jwt.secret.salt", String.class);

    public TokenDTO(String token) {
        this.jwt = token;
        // TODO: parse the token and set the fields accordingly
    }

    public TokenDTO(UserDTO user) {
        this.sub = user.getId().toString();
        this.exp = this.calcExpirationTime();
        this.nbf = NOW.toEpochSecond(TokenDTO.OFFSET);
        this.iat = NOW.toEpochSecond(TokenDTO.OFFSET);
    }

    public TokenDTO setTokenLongevityInSeconds(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException(
                "Token longevity must be greater than zero."
            );
        }

        this.tokenLongevityInSeconds = seconds;

        this.exp = this.calcExpirationTime();

        return this;
    }

    public String getJwt() {
        if (!StringUtil.isNullOrEmpty(this.jwt)) {
            return this.jwt;
        }

        JwtClaimsBuilder claim = Jwt.claims()
            .issuer(this.iss)
            .subject(this.sub)
            .audience(this.aud)
            .expiresAt(this.exp)
            .issuedAt(this.iat);

        if (!StringUtil.isNullOrEmpty(this.jti)) {
            claim.claim("jti", this.jti);
        }

        // (JWS) with HS256
        this.jwt = claim.signWithSecret(this.secret);

        return this.jwt;
    }

    private long calcExpirationTime() {
        return this.NOW.plusSeconds(tokenLongevityInSeconds).toEpochSecond(
                TokenDTO.OFFSET
            );
    }
}
