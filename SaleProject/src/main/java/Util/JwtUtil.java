package Util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "Dmx7wlvuyG9IfZa0cRsZLDNfymrlVOR1AOVhaV8RIIHkeR7xdmQxAmmaavj9+/LB";

    // Tạo token với 3 phần header,payload,signature
    public String generateToken(String email){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256); // ==> Tạo header
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("Util")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject  jwsObject = new JWSObject(header,payload);
        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    // Giải mã/ phân tích token để xác thực secretkey,expriationtime
    public boolean validateToken(String token){
        try {
            SignedJWT signedJWT = SignedJWT.parse(token); // ===> Tạo đối tượng SignedJWT từ thư viện Nimbus để giải mã/phân tích token
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes()); // ==> Tạo đối tượng JWSVerifier và dùng thuật toán MACVerifier từ thư viện Nimbus để xác thực SecretKey
            if (!signedJWT.verify(verifier)){
                return false;
            }
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime(); //==> Lấy expirationTime từ signedJWT được giải mã từ token sau đó check valid
            if (expiration == null){
                return false;
            }
            if (expiration.before(new Date())){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
    // Lấy subject từ token để xác thực người truy cập
    public String getSubject(String token){
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
