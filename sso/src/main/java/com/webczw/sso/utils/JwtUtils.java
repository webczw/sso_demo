package com.webczw.sso.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String  SECRET = "session_secret";

    //发布者 后面一块去校验
    private static final String  ISSUER = "mooc_user";

    //生成token的操作
    public static String genToken(Map<String, String> claims){
        try {
            //签名算法
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(c.getTime());
            //相当于将claims存储在token中
            claims.forEach((k,v) -> builder.withClaim(k, v));
            return builder.sign(algorithm).toString();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    //验证token
    public static Map<String, String> verifyToken(String token)  {
        Algorithm algorithm = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT jwt =  verifier.verify(token);

        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = new HashMap<>();
        map.forEach((k,v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

}