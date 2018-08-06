package com.thoughtworks.training.xueqing.todoservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;



public class JwtTest {
    @Test
    public void generate(){

        HashMap<String,Object> claims = new HashMap<>();
        claims.put("name","xue");
        claims.put("role","dev");
        byte[] secretKey = "kitty".getBytes();

        //generate
        String token = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .setExpiration(Date.from(Instant.now()
                        .plus(1,ChronoUnit.DAYS)))
                .compact();
        System.out.println(token);

        //parse and verification
        Claims body =Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        assertThat(body.get("name"),is("xue"));
        assertThat(body.get("role"),is("dev"));
    }

}
