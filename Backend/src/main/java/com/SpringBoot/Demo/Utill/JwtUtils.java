package com.SpringBoot.Demo.Utill;

import java.util.Date;
import org.springframework.stereotype.Component;
import com.SpringBoot.Demo.Entity.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    private static String secret = "secret_of_the_nipurna";
    private static long expiryDuration = 3600; // 1800

    public String generateJwt(UserDetails userDetailsAdd) {

        long milliTime = System.currentTimeMillis();

        long expiryTime = milliTime + expiryDuration * 1000; //  milieseconds

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer(userDetailsAdd.getName())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        // optional claims to set some values to pass
        claims.put("type", userDetailsAdd.getName());
        claims.put("Id", userDetailsAdd.getId());
        claims.put("EmployeeCode", userDetailsAdd.getEmployeeCode());
        claims.put("Role-Id", userDetailsAdd.getRole_id());

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify(String Authorization) throws Exception {
        String Token = Authorization.split(" ")[1].trim();

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(Token).getBody();
            System.out.println("Successfully Authorized");
            return claims;
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtException(null, null, "Expired JWT token");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
