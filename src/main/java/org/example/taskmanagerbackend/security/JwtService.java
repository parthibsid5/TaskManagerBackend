package org.example.taskmanagerbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt-secret}")
    private   String SECRET_KEY;

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*3)) // 3 minutes
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userdetails){
        final String email=extractEmail(token);
        return  (email.equals(userdetails.getUsername()) && isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return  extractClaims(token).getExpiration().after(new Date());
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    we can also generate key using keygenerater

//    private String secret;
//    public JwtService(){
//        secret=generateSecretKey();
//    }
//
//    private String generateSecretKey() {
//        try{
//            KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
//            SecretKey secretKey=keyGen.generateKey();
//            return  Base64.getEncoder().encodeToString(secretKey.getEncoded());
//        }
//        catch (NoSuchAlgorithmException e){
//            throw new RuntimeException("Error generating secret key "+e);
//        }
//    }

}