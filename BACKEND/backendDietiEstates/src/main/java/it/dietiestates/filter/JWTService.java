package it.dietiestates.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.UUID;

public class JWTService {
    private static final String ISSUER = "dieti-estates";
    private static final String SECRET = System.getenv("JWT_SECRET");
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
    private static final long EXPIRATION_TIME = 3600000; // 1 ora in millisecondi
    private static final JWTVerifier verifier = JWT.require(ALGORITHM)
            .withIssuer(ISSUER)
            .build();

    // Costruttore privato per impedire l'istanza della classe
    private JWTService() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    // TODO: decidere se adottare un refresh token per garantire sicurezza senza login troppo frequenti

    public static String generateToken(String nome, String cognome, String email, String tipoUtente, String partitaIVA, String nomeAgenzia) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("nome", nome)
                .withClaim("cognome", cognome)
                .withClaim("email", email)
                .withClaim("partitaIVA", partitaIVA)
                .withClaim("nomeAgenzia", nomeAgenzia)
                .withClaim("tipoUtente", tipoUtente)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withJWTId(UUID.randomUUID().toString())
                .sign(ALGORITHM);
    }

    public static void verifyToken(String token) {
        verifier.verify(token);
    }
}

