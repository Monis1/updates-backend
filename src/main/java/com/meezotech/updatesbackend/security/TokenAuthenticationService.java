package com.meezotech.updatesbackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * The Utility Class for Token authentication.
 * Class added for JWTs, copied from https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 */
public class TokenAuthenticationService {

    private static final long EXPIRATIONTIME = 864_000_000; // 10 days
    private static final String SECRET = "mangomanupatesmystery";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    /**
     * Add authentication token to the header.
     *
     * @param response      the response
     * @param userName the user name for which the token is generated
     */
    public static void addAuthentication(HttpServletResponse response, String userName) {
        String JWT = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    /**
     * Gets authentication for the user.
     *
     * @param request the request contains username and password
     * @return the authentication for the provided request
     */
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }

}
