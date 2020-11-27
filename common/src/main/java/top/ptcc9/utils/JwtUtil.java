package top.ptcc9.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-22 14:32
 */
public class JwtUtil {
    private static final long ONE_HOUR = 60 * 1000 * 60;
    private static final long EXPIRE_TIME = 5 * ONE_HOUR;
    private static final String TOKEN_SECRET = "SDFdkmWEUTMC151kmho6dfeFGOlkc0ERDF";


    public static String create(Map<String,String> map){
        JWTCreator.Builder token = JWT.create();

        Map<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        token.withHeader(header);

        map.forEach(token::withClaim);

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        token.withExpiresAt(date);

        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return token.sign(algorithm);
    }

    public static boolean verity(String token){
        try {
            JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

    public static Map<String,Claim> getClaims(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build().verify(token).getClaims();
    }
}
