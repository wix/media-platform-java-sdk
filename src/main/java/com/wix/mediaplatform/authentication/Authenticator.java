package com.wix.mediaplatform.authentication;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.wix.mediaplatform.configuration.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;

public class Authenticator {

    private final Configuration configuration;
    private final JWTSigner signer;
    private final JWTVerifier verifier;

    /**
     * @param configuration The Media Platform configuration
     */
    public Authenticator(Configuration configuration) {
        this.configuration = configuration;

        this.signer = new JWTSigner(configuration.getSharedSecret());
        this.verifier = new JWTVerifier(configuration.getSharedSecret(),
                NS.APPLICATION + configuration.getAppId());
    }

    /**
     * @return The authorization header
     */
    public String getHeader() {
        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId());

        return getHeader(token);
    }

    /**
     * @param verbs a list of verbs to which the token will be limited
     * @return A limited authorization header
     * @throws IllegalArgumentException must provide at least one verb, otherwise the token will not be limited...
     */
    public String getHeader(List<String> verbs) {
        if (verbs.size() < 1) {
            throw new IllegalArgumentException("must provide at least one verb");
        }

        Token token = new Token()
                .setIssuer(NS.APPLICATION + configuration.getAppId())
                .setSubject(NS.APPLICATION + configuration.getAppId())
                .setVerbs(verbs);

        return getHeader(token);
    }

    /**
     * @param token an optional Token
     * @return The authorization header
     */
    public String getHeader(Token token) {
        return encode(token);
    }

    public String encode(Token token) {
        return signer.sign(token.toClaims());
    }

    public Token decode(String token) {
        try {
            return new Token(verifier.verify(token));
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | JWTVerifyException | SignatureException e) {
            throw new RuntimeException("invalid token", e);
        }
    }
}
