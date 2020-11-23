package zuka.cloud.icaphe.controller;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zuka.cloud.icaphe.entity.User;
import zuka.cloud.icaphe.jwt.JwtAuthenticationFilter;
import zuka.cloud.icaphe.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JwtValidatorController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtAuthenticationFilter jwtAu;

    @PostMapping("/jwt")
    @PreAuthorize("hasRole('ADMIN')")
    public String getIdRoleFromJwt(HttpServletRequest request) {
        String jwt = jwtAu.getJwtFromRequest(request);
        String JWT_SECRET = "MinhChuan";
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(jwt)
                    .getBody();

            int idUser =  Integer.parseInt(claims.getSubject());
            User userJwt = userRepository.findById(idUser);
            return "UserId: " + idUser + ", UserRole:" + userJwt.getRole();

        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return null;
    }
}
